package dao;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.LocalDate;

import util.MikrotikUtil;
import util.Real;
import domain.AcessoCliente;
import domain.AlteracoesContasReceber;
import domain.Cliente;
import domain.ContaBancaria;
import domain.ContasReceber;
import domain.ContratosAcesso;
import domain.ControleTitulo;
import domain.Empresa;
import domain.PlanoAcesso;
import domain.RadReply;


public class CredenciaisAcessoDAO {
	
	public static boolean DesbloquearContrato(Integer codContrato){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		
		AcessoCliente acesso = acesso = em.find(AcessoCliente.class,codContrato);	
	
		if(	acesso != null 
				&& acesso.getStatus_2() != null
				&& acesso.getStatus_2().equals("BLOQUEADO")){
			
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
				
				Query qrr2 = em.createQuery("select rr from RadReply rr where rr.username = :usuario and rr.attribute = 'Framed-Pool' and rr.value = 'BLOQUEADO_TOTAL'", RadReply.class);
				qrr2.setParameter("usuario", acesso.getLogin());						
				if(qrr2.getResultList().size() >0){
							
					List<RadReply> marcacoes2 = qrr2.getResultList(); 
					for(RadReply rr:marcacoes2){
						em.remove(rr);
					}
				}
				
				
				if(acesso.getEndereco_ip() != null && !acesso.getEndereco_ip().equals("")){
					em.persist(new RadReply(null, acesso.getLogin(), "Framed-IP-Address", "=", acesso.getEndereco_ip()));
				}
				
     							
				em.getTransaction().commit();

				//Derruba Conexão do Cliente
				MikrotikUtil.desconectarCliente(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
				
				//Notify.Show("Contrato de Acesso Desbloqueado!", Notify.TYPE_SUCCESS);	
				return true;										
		}
		
		return false;
	}


	public static boolean bloquearContratoClienteTotal(Integer codAcesso){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		AcessoCliente acesso = em.find(AcessoCliente.class, codAcesso);
		if(acesso != null){
			
			em.getTransaction().begin();
			
				//Muda Status do Acesso
				acesso.setStatus_2("BLOQUEADO");					
			
				//Cadastra Plano Novo
				//emr.persist(new RadUserGgroup(null, acesso.getLogin(), acesso.getContrato().getId().toString()+"_BLOQUEADO", "1"));
				
				//Retira IPs Fixados no RADIUS
				Query q = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
				q.setParameter("username", acesso.getLogin());
				q.setParameter("attribute", "Framed-IP-Address");
				
				for (RadReply rr : (List<RadReply>)q.getResultList()) {
					em.remove(rr); 
				}
				
				Query q2 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
				q2.setParameter("username", acesso.getLogin());
				q2.setParameter("attribute", "Framed-Pool");
				
				for (RadReply rr : (List<RadReply>)q2.getResultList()) {
					em.remove(rr); 
				}
				
				em.persist(new RadReply(null, acesso.getLogin(), "Framed-Pool", "=", "BLOQUEADO_TOTAL"));					
				
				//Derruba Cliente Caso Esteja Logado
				//MikrotikUtil.derrubarConexaoHOTSPOT(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
				MikrotikUtil.derrubarConexaoPPPOE(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
			
				em.getTransaction().commit();
			
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
		
		
			ContasReceberDAO.gerarBoletosAcesso(codCliente, contratoAcesso, valorPrimieiroBoleto, valorPlano, dataVenc, vigencia, valorAdesao, controle, plano.getId());
		
	}
	
	
	public static void gerarBoletosAcesso(Integer codCliente, AcessoCliente contrato,String valorPrimeiroBoleto,String valorPlano, Date primeiroVenc, 
			Integer qtd,String valorAdesao, String controle, Integer codPlano) throws Exception{
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
				
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
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	EntityManager em = emf.createEntityManager();

		
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
	
	public static ContasReceber gerarBoletos(ContasReceber cr) throws Exception{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
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
			em.persist(new AlteracoesContasReceber(null, "CADASTRO UM BOLETO", cr,new Empresa(1), 100, new Date()));
			
			return cr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
		
}
