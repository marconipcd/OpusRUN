import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import util.MikrotikUtil;
import util.Real;
import dao.AlteracoesContratoDAO;
import dao.LogDAO;
import domain.AcessoCliente;
import domain.AlterarcoesContrato;
import domain.Cliente;
import domain.ContaBancaria;
import domain.ContasReceber;
import domain.ContratosAcesso;
import domain.ControleTitulo;
import domain.LogAcoes;
import domain.PlanoAcesso;
import domain.RadReply;
import domain.Usuario;


public class runRenovacaoAutomatica {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	static EntityManager em = emf.createEntityManager();
	
	public static void main (String[] args){
		
		List<String> contratosRenovados = new ArrayList<>();
		
		
		Query q = em.createQuery("select cr from ContasReceber cr where cr.data_vencimento=:data and cr.status = 'ABERTO'", ContasReceber.class);
		q.setParameter("data", new Date());
		
		for(ContasReceber boleto: (List<ContasReceber>)q.getResultList()){
			
			if(ChecarPadraoAcessoNDoc(boleto.getN_doc()) && boleto.getControle().equals("ACESSO-POS")){
				Integer codAcesso = Integer.parseInt(boleto.getN_doc().split("/")[0].toString());		    	
		    	AcessoCliente acessoCliente = em.find(AcessoCliente.class, codAcesso);
		    	
	            if(acessoCliente !=  null && !acessoCliente.getStatus_2().equals("ENCERRADO")){
	              
	            	List<ContasReceber> boletos = procurarAbertoBoletosDoAcessoPorContrato(codAcesso, boleto.getId());
	            	if(boletos == null){
	            		
	            		contratosRenovados.add(codAcesso.toString());
	            		renovarContratoAcesso(acessoCliente);
	            	}
	            	
	            }
			}
		}
		
		for (String contrato : contratosRenovados) {
			System.out.println("Contrato: "+contrato+" RENOVADO!");
		}
	}
	
	public static boolean renovarContratoAcesso(AcessoCliente contrato){
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();
		
		try{
			
				em.getTransaction().begin();
			
				if(contrato.getPlano().getPlano_Renovacao() != null){
					contrato.setPlano(contrato.getPlano().getPlano_Renovacao());
				}
			
				Integer vigencia1 = contrato.getContrato().getVigencia();			
				Date dataVencContratoNova = new Date();
			
				if(!contrato.getContrato().getTipo_contrato().equals("GRATIS")){
					
					List<ContasReceber> resConta = procurarTodosBoletosDoAcessoPorContrato(contrato.getId());		
					ContasReceber ct = resConta.get(0);
					Date dtVenc = ct.getData_vencimento();
					DateTime dt2 = new DateTime(dtVenc).plusMonths(1);
					DateTime dtVencimentoNovo = new DateTime(dtVenc).plusMonths(vigencia1);
					dtVenc = dt2.toDate();
					
					gerarBoletosAcesso(contrato.getCliente().getId(),contrato, contrato.getContrato(), contrato.getPlano(), dtVenc, contrato.getPlano().getValor());
					
					contrato.setData_venc_contrato(dtVencimentoNovo.toDate());
				}else{
					DateTime dtVencimentoNovo = new DateTime(contrato.getData_venc_contrato()).plusMonths(vigencia1);
					contrato.setData_venc_contrato(dtVencimentoNovo.toDate());
				}
				
				contrato.setData_renovacao(new Date());
				contrato.setCarencia("NAO");			
			
				em.merge(contrato);
				
			em.getTransaction().commit();
			
			boolean check = DesbloquearContrato(contrato.getId());
			
			LogDAO.add(new LogAcoes(null, "opus", "Renovou Um Novo Contrato de Acesso"));
			AlteracoesContratoDAO.save(new AlterarcoesContrato(null, "RENOVAÇÃO DE CONTRATO", contrato, new Usuario(100), new Date()));
			if(contrato.getStatus_2().equals("BLOQUEADO") && check){
				AlteracoesContratoDAO.save(new AlterarcoesContrato(null, "CONTRATO DESBLOQUEADO POR RENOVACAO", contrato, new Usuario(100), new Date()));
			}
			
			return true;
		}catch(Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();				
			}
			e.printStackTrace();	
			
			//LogDAO.add(new LogAcoes(null, OpusERP4UI.getUsuarioLogadoUI().getUsername(), "Não Conseguiu Renovar Um Contrato de Acesso, Uma Excessão foi Gerada"));
			return false;
		}
			
	}
	
	public static boolean DesbloquearContrato(Integer codContrato){
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();
		
		AcessoCliente acesso = em.find(AcessoCliente.class,codContrato);			
					
		if(acesso != null && acesso.getStatus_2() != null && acesso.getStatus_2().equals("BLOQUEADO")){
			
				em.getTransaction().begin();
				
				//Atualiza Status do Acesso para Ativo
				acesso.setStatus_2("ATIVO");
				em.merge(acesso);
				
				//Remove marcações RadReply
				Query qrr = em.createQuery("select rr from RadReply rr where rr.username = :usuario and rr.attribute = 'Framed-Pool' and rr.value = 'BLOQUEADO'", RadReply.class);
				qrr.setParameter("usuario", acesso.getLogin());						
				if(qrr.getResultList().size() >0){
							
					List<RadReply> marcacoes = qrr.getResultList(); 
					for(RadReply rr:marcacoes){
						em.remove(rr);
					}
				}
								
				if(acesso.getEndereco_ip() != null && !acesso.getEndereco_ip().equals("")){
					em.persist(new RadReply(null, acesso.getLogin(), "Framed-IP-Address", "=", acesso.getEndereco_ip()));
				}
				     							
				em.getTransaction().commit();

				//Derruba Conexão do Cliente
				MikrotikUtil.desconectarCliente(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
								
				return true;										
		}
		
		return false;
	}

	public static void gerarBoletosAcesso(Integer codCliente,AcessoCliente contratoAcesso, ContratosAcesso contrato, PlanoAcesso plano, Date dataBoleto, 	String valorPrimieiroBoleto) throws Exception{
		
		Integer vigencia = contrato.getVigencia();
		//Float resultAdesao = Float.parseFloat(String.valueOf(contratoAcesso.getValor_beneficio_adesao())) - Float.parseFloat(Real.formatStringToDB(contrato.getValor_desconto()));
		String valorAdesao = "0,00";
				
		String valorPlano = plano.getValor();		
		Date dataVenc = dataBoleto;	 	

		String controle = "";
		if(contrato.getTipo_contrato().equals("POS-PAGO")){
			controle = "ACESSO-POS";
		}else{
			controle = "ACESSO-PRE";
		}
		
		gerarBoletosAcesso(codCliente, contratoAcesso, valorPrimieiroBoleto, valorPlano, dataVenc, vigencia, valorAdesao, controle, plano.getId());
	}
	
	public static ContasReceber gerarBoletos(ContasReceber cr) throws Exception{
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();
		
		try{
		
			Date dataAtual = new Date();
			boolean definirNDocAuto;
			if(cr.getN_doc().equals(""))
			{
				definirNDocAuto = true;
			}else{
				definirNDocAuto = false;
			}  				
	         
			Query qControleNovo = em.createQuery("select c from ControleTitulo c where c.nome=:nome and c.empresa_id =:empresa", ControleTitulo.class);
			qControleNovo.setParameter("nome", cr.getControle());
			qControleNovo.setParameter("empresa", 1);
			
			ContaBancaria cb = null;
			if(qControleNovo.getResultList().size() == 1){
				
				ControleTitulo ct = (ControleTitulo)qControleNovo.getSingleResult();
				cb = ct.getConta_bancaria_bkp();
			}
			
			//-- Desativado devido a lentidão
//			String NumeroNovo = "";
//			if(cb != null){
//				if(cb.getNome_banco().equals("SICRED")){					
//					NumeroNovo = calcularNossoNumeroSicred(cr.getCliente().getId(),cb);				
//				}
//				
//				if(cb.getNome_banco().equals("BANCO DO BRASIL")){
//					NumeroNovo = calcularNossoNumero(cr.getCliente().getId());
//				}
//			}
				
	        String ndocumento  = null;
			if(definirNDocAuto)
			{
				ndocumento = String.valueOf(cr.getCliente().getId())+"/"+dataAtual.getYear()+"-"+String.format("%02d", 1)+"/"+String.format("%02d", 1);
				
				cr.setN_doc(ndocumento);
			} 
			
//			if(cb != null){
//				if(cb.getNome_banco().equals("SICRED")){					
//					cr.setN_numero_sicred(NumeroNovo);	
//				}
//				
//				if(cb.getNome_banco().equals("BANCO DO BRASIL")){
//					cr.setN_numero(NumeroNovo);	
//				}
//			}
			
			
			cr.setEmpresa_id(1);
						
			em.persist(cr);			
			//em.persist(new AlteracoesContasReceber(null, "CADASTRO UM BOLETO", cr,OpusERP4UI.getEmpresa(), OpusERP4UI.getUsuarioLogadoUI(), new Date()));
			
			return cr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public static void gerarBoletosAcesso(Integer codCliente, AcessoCliente contrato,String valorPrimeiroBoleto,String valorPlano, Date primeiroVenc, 
			Integer qtd,String valorAdesao, String controle, Integer codPlano) throws Exception{
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();
				
			try {				
				for (int i = 0; i < qtd; i++) {			
					
					
					
					Date dataAtual = new Date();
					//Calendar cal = Calendar.getInstance();
					SimpleDateFormat sdf = new SimpleDateFormat("yy"); 
					
					String anoDoc = String.valueOf(sdf.format(new Date()));
					String ndocumento = String.valueOf(contrato.getId())+"/"+anoDoc+"-"+String.format("%02d", i+1)+"/"+String.format("%02d", qtd);
					
					LocalDate localDate = new LocalDate(primeiroVenc);
					LocalDate moreDays = localDate.plusMonths(i);	
					Date dataBoleto = moreDays.toDate();
					
					ContasReceber crCadastrado = null;
					ContasReceber boletoFechado = procurarBoletosDoAcessoFechado(codCliente, ndocumento,dataBoleto);
					
					boolean subtrair = false;
					if(boletoFechado != null && !contrato.getContrato().getTipo_contrato().equals("PRE-PAGO")){
						subtrair = true;
					}
					
					if(i == 0){
						
						if(subtrair){
							double valorSubtraido = Real.formatStringToDBDouble(valorPrimeiroBoleto) - Real.formatStringToDBDouble(boletoFechado.getValor_titulo());
							
							if(valorSubtraido > 0){
								crCadastrado = gerarBoletos(new ContasReceber(new Cliente(codCliente), ndocumento, Real.formatDbToString(String.valueOf(valorSubtraido)), new Date(), dataBoleto,"ABERTO", "BOLETO",controle, codPlano));
							}
						}else{
							crCadastrado = gerarBoletos(new ContasReceber(new Cliente(codCliente), ndocumento, valorPrimeiroBoleto, new Date(), dataBoleto,"ABERTO", "BOLETO",controle, codPlano));
						}
						
					}else{
						
						if(subtrair){
							double valorSubtraido = Real.formatStringToDBDouble(valorPlano) - Real.formatStringToDBDouble(boletoFechado.getValor_titulo());
							
							crCadastrado = gerarBoletos(new ContasReceber(new Cliente(codCliente), ndocumento, Real.formatDbToString(String.valueOf(valorSubtraido)), new Date(), dataBoleto,"ABERTO", "BOLETO",controle, codPlano));
						}else{
							crCadastrado = gerarBoletos(new ContasReceber(new Cliente(codCliente), ndocumento, valorPlano, new Date(), dataBoleto,"ABERTO", "BOLETO",controle, codPlano));
						}
						
					}
				

				}	
				
				
				if(valorAdesao != null && !valorAdesao.equals("0,00") && !valorAdesao.equals("0.00") && !valorAdesao.equals("0.0") && !valorAdesao.isEmpty()){
					gerarBoletos(new ContasReceber(new Cliente(codCliente), contrato.getId().toString()+"/ADESAO", Real.formatDbToString(valorAdesao), new Date(), new Date(),"ABERTO", "BOLETO","ADESAO"));
				}
				
			} catch (Exception e) {
				
				e.printStackTrace();
			}
	}
	
	private static ContasReceber procurarBoletosDoAcessoFechado(Integer codContrato, String ndocumento,Date dataBoleto){
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();
		
		try{
		String regexNova = "^"+codContrato.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
		String regexAntiga = "^"+codContrato.toString()+"/[0-9]{2}/[0-9]{2}";
		
		Query q = em.createNativeQuery(""
				+"select * from contas_receber cr where "				
				+"cr.n_doc REGEXP :rNova  and "
				+"cr.data_vencimento = :dataVenc and cr.status_2 = 'FECHADO' "
				
				
				+"or cr.n_doc REGEXP :rAntiga  and "+
				 "cr.data_vencimento = :dataVenc and cr.status_2 = 'FECHADO' ",
				ContasReceber.class);
		
		q.setParameter("rNova", regexNova);
		q.setParameter("rAntiga", regexAntiga);	
		q.setParameter("dataVenc", dataBoleto);
		
		if(q.getResultList().size() == 1){
			return (ContasReceber)q.getSingleResult();
		}else{
			return null;
		}
		}catch (Exception e){
			e.printStackTrace();			
			return null;
		}
	}
	
	public static List<ContasReceber> procurarTodosBoletosDoAcessoPorContrato(Integer codAcesso){
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();

		try{
		String regexNova = "^"+codAcesso.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";//1245/15-12/12
		String regexAntiga = "^"+codAcesso.toString()+"/[0-9]{2}/[0-9]{2}";
		
		Query qn = em.createNativeQuery("select * from contas_receber cr where "+				
							
				"cr.n_doc REGEXP :rNova "+ "and cr.status_2 !='EXCLUIDO' "+
				
				"or "+							
				"cr.n_doc REGEXP :rAntiga " + "and cr.status_2 !='EXCLUIDO' " + "ORDER BY cr.data_vencimento DESC "
				
				, ContasReceber.class);
			
		qn.setParameter("rNova", regexNova);
		qn.setParameter("rAntiga", regexAntiga);
		
		if(qn.getResultList().size() > 0){
			return qn.getResultList();
		}else{
			return null;
		}
		
		}catch (Exception e){
			e.printStackTrace();			
			return null;
		}
	}
	
	public static List<ContasReceber> procurarAbertoBoletosDoAcessoPorContrato(Integer codAcesso, Integer boleto){
		
//		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
//		EntityManager em = emf.createEntityManager();
		
		try{
		
			String regexNova = "^"+codAcesso.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
			String regexAntiga = "^"+codAcesso.toString()+"/[0-9]{2}/[0-9]{2}";
			
			Query qn = em.createNativeQuery("select * from contas_receber cr where "+	
					"cr.n_doc REGEXP :rNova "+ "and cr.status_2 ='ABERTO' and cr.id !=:boleto "+
					"or "+							
					"cr.n_doc REGEXP :rAntiga " + "and cr.status_2 ='ABERTO'  and cr.id !=:boleto " + "ORDER BY cr.data_vencimento DESC "
					, ContasReceber.class);
				
			qn.setParameter("rNova", regexNova);
			qn.setParameter("rAntiga", regexAntiga);
			qn.setParameter("boleto", boleto);
			
			if(qn.getResultList().size() > 0){
				return qn.getResultList();
			}else{
				return null;
			}
		
		}catch (Exception e){
			e.printStackTrace();			
			return null;
		}
	}
	
	public static boolean ChecarPadraoAcessoNDoc(String nDoc){
		Pattern pAntigo = Pattern.compile("[0-9]{1,}/[0-9]{2}/[0-9]{2}");
		Matcher mAntigo = pAntigo.matcher(nDoc);
		
		Pattern pNovo = Pattern.compile("[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}");
	    Matcher mNovo = pNovo.matcher(nDoc);
	      
	    if (mAntigo.matches() || mNovo.matches()) {
	    	return true;
	    }
	    
	    return false;
	}
}
