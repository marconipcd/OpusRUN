package dao;

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
import domain.AcessoCliente;
import domain.AgendaRenovacoes;
import domain.AlteracoesContasReceber;
import domain.Cliente;
import domain.ContaBancaria;
import domain.ContasReceber;
import domain.ControleTitulo;
import domain.CreditoCliente;
import domain.Empresa;
import domain.LogAcoes;
import domain.RadReply;

public class ContasReceberDAO {
	
	public static void gerarBoletosAcesso2(Integer codCliente, AcessoCliente contrato,String valorPrimeiroBoleto,String valorPlano, Date primeiroVenc, 
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
							double valorSubtraido = Real.formatStringToDBDouble(valorPlano) - Real.formatStringToDBDouble(boletoFechado.getValor_titulo());
							
							if(valorSubtraido > 0){
								crCadastrado = gerarBoletos(new ContasReceber(new Cliente(codCliente), ndocumento, Real.formatDbToString(String.valueOf(valorSubtraido)), new Date(), dataBoleto,"ABERTO", "BOLETO",controle, codPlano));
							}
						}else{
							crCadastrado = gerarBoletos(new ContasReceber(new Cliente(codCliente), ndocumento, valorPlano, new Date(), dataBoleto,"ABERTO", "BOLETO",controle, codPlano));
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
	
	public static boolean ChecarPadraoAcessoNDoc(String nDoc){
		Pattern pAntigo = Pattern.compile("[0-9]{1,}/[0-9]{2}/[0-9]{2}");
		Matcher mAntigo = pAntigo.matcher(nDoc);
		
		Pattern pNovo = Pattern.compile("[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}");
	    Matcher mNovo = pNovo.matcher(nDoc);
	    
	    Pattern pNovoProRata = Pattern.compile("[0-9]{1,}/PRORATA");
	    Matcher mNovoProRata = pNovoProRata.matcher(nDoc);
	    
	    
	    if (mAntigo.matches() || mNovo.matches()|| mNovoProRata.matches()) {
	    	return true;
	    }
	    
	    return false;
	}
	
	public static boolean baixarBoletoProcessoCompleto(Integer codContaReceber, String valor, String valor_lancamento, String valor_tarifa, String formaPgto, String tipoBaixa, 
			Date dataPagamento, Date dataBaixa, Double valorRecebido, Double valorTroco){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();		
		
				//--Baixa Boleto
				ContasReceber cr = em.find(ContasReceber.class, codContaReceber);
				
				if(cr != null){
					cr.setValor_lancamento(valor_lancamento);
					cr.setValor_tarifa(valor_tarifa);
					cr.setValor_pagamento(valor);
					cr.setForma_pgto(formaPgto);
					cr.setTipo_baixa(tipoBaixa);
					cr.setData_pagamento(dataPagamento);
					cr.setData_baixa(dataBaixa);
					cr.setStatus("FECHADO");
					cr.setValor_recebido(valorRecebido);
					cr.setValor_troco(valorTroco);
					cr.setOperador("opus");
					
					//REENVIA REMESSA 
					//SOLICITA BAIXA DE BOLETO NO BANCO
					if(cr.getRemessaEnviada() != null && cr.getRemessaEnviada().equals("SIM")){
						cr.setRemessaEnviada(null);
						cr.setComandoRemessa("02");
					}
					
					em.merge(cr);	
				}
				
				//--Recarga Pre-Paga
				if (ChecarPadraoAcessoNDoc(cr.getN_doc())) {
			    	
			    	String numeroDoc = cr.getN_doc();
	
					Integer codAcesso = Integer.parseInt(numeroDoc.split("/")[0].toString());		    	
			    	AcessoCliente acessoCliente = em.find(AcessoCliente.class, codAcesso);
			    	
			    	if(acessoCliente != null && acessoCliente.getContrato().getTipo_contrato().equals("PRE-PAGO")){		    		
			    		
			    		if(acessoCliente.getData_venc_contrato().before(new Date())){
			    		
			    			acessoCliente.setData_venc_contrato(
				    				new DateTime().plusMonths(acessoCliente.getContrato().getVigencia()).toDate());		    			
			    		
			    		}else{
			    			acessoCliente.setData_venc_contrato(
			    					new DateTime(acessoCliente.getData_venc_contrato()).plusMonths(acessoCliente.getContrato().getVigencia()).toDate());
			    		}
			    		
			    		em.merge(acessoCliente);		    	
			    	}
			    }
				
				
				AcessoCliente acesso = null; 
				if(cr != null && cr.getN_doc() != null){
					String numeroDoc = cr.getN_doc();
					Integer codAcesso = Integer.parseInt(numeroDoc.split("/")[0].toString());				
					acesso = em.find(AcessoCliente.class,codAcesso);
				}
									
				if(cr != null 
						&& ChecarPadraoAcessoNDoc(cr.getN_doc())				
						&& acesso != null 
						&& acesso.getStatus_2() != null
						&& acesso.getStatus_2().equals("BLOQUEADO")){
					
					//Verificar Se Mais Algum Boleto Desse Cliente Esta Bloqueado
					if(!ContasReceberDAO.existeAlbumBoletoBloqueadoeAbertoPorContrato(acesso.getId(), cr.getId())){
											
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
									
							List<RadReply> marcacoes = qrr2.getResultList(); 
							for(RadReply rr:marcacoes){
								em.remove(rr);
							}
						}
						
						if(acesso.getEndereco_ip() != null && !acesso.getEndereco_ip().equals("")){
							em.persist(new RadReply(null, acesso.getLogin(), "Framed-IP-Address", "=", acesso.getEndereco_ip()));
						}
														
						cr.setDesbloqueado("S");
						em.merge(cr);
						
						//Derruba Conexão do Cliente
						if(acesso != null && acesso.getBase() != null && acesso.getBase().getUsuario() != null && acesso.getBase().getSenha() != null && acesso.getBase().getEndereco_ip() != null && acesso.getBase().getPorta_api() != null && acesso.getLogin() != null){
							MikrotikUtil.desconectarCliente(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
						}
						
						//Notify.Show("Contrato de Acesso Desbloqueado!", Notify.TYPE_NOTICE);				
					}										
				}
				
				//--Devolve Crédito
				Query q = em.createNativeQuery("SELECT * FROM credito_cliente WHERE CLIENTES_ID = "+cr.getCliente().getId(), CreditoCliente.class);	
				
				if(q.getResultList().size() == 1){
					CreditoCliente credito = (CreditoCliente) q.getSingleResult();
					
					Double saldo = Double.parseDouble(credito.getSaldo());
					Double novoSaldo = saldo + Real.formatStringToDBDouble(cr.getValor_titulo());
					credito.setSaldo(novoSaldo.toString());
					
					em.merge(credito);				
				}
				
								
				//--Gera Logs
				em.persist(new AlteracoesContasReceber(null, "BAIXOU UM BOLETO(R$ "+valor.toString()+")", cr,new Empresa(1), 100, new Date()));
				em.persist(new LogAcoes(null, "Baixa via PIX", "Baixou um Boleto"));
			
			em.getTransaction().commit();
			
			if (ChecarPadraoAcessoNDoc(cr.getN_doc()) && cr.getControle().equals("ACESSO-POS")) {
				Integer codAcesso = Integer.parseInt(cr.getN_doc().split("/")[0].toString());		    	
		    	AcessoCliente acessoCliente = em.find(AcessoCliente.class, codAcesso);
	            if(acessoCliente !=  null && !acessoCliente.getStatus_2().equals("ENCERRADO")){
	            	List<ContasReceber> boletosAbertos = procurarAbertoBoletosDoAcessoPorContrato(codAcesso);
	            	if(boletosAbertos == null){
	            		AcessoDAO.renovarContratoAcesso(acessoCliente);       
	            		AgendaRenovacoesDAO.save(new AgendaRenovacoes(null, acessoCliente.getId(), "RENOVADO", new Date())); 
	            	}
	            }
			}
			
			
	    	
			return true;	
		}catch(Exception e){
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public static List<ContasReceber> procurarAbertoBoletosDoAcessoPorContrato(Integer codAcesso){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		try{
		
			String regexNova = "^"+codAcesso.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
			String regexAntiga = "^"+codAcesso.toString()+"/[0-9]{2}/[0-9]{2}";
			String rProrata = codAcesso.toString()+"/PRORATA";
			
			Query qn = em.createNativeQuery("select * from contas_receber cr where "+	
					"cr.n_doc REGEXP :rNova "+ "and cr.status_2 ='ABERTO' "+
					"or "+							
					"cr.n_doc REGEXP :rAntiga " + "and cr.status_2 ='ABERTO' " + 
					"or "+
					"cr.n_doc LIKE :rProrata " + "and cr.status_2 ='ABERTO' " + 
					"ORDER BY cr.data_vencimento DESC "
					, ContasReceber.class);
				
			qn.setParameter("rNova", regexNova);
			qn.setParameter("rAntiga", regexAntiga);
			qn.setParameter("rProrata", rProrata);
			
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
	

	/**
	 * Verifica se é Permitido o Cadastro Manual do NDoc Passado
	 * 
	 * @param ndoc
	 * @return boolean
	 */
	public static boolean allowNdocManual(String ndoc){
		
		List<String> default_nDocs = new ArrayList<>();
		default_nDocs.add("^[0-9]{1,}/[0-9]{2}/[0-9]{2}");
		default_nDocs.add("^[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}");
		
		for (String string : default_nDocs) {
			
			Pattern p = Pattern.compile(string);
			Matcher m = p.matcher(ndoc);
			
			if(m.matches()){
				return false;
			}			
		}
		
		return true;
	}
	
	public static boolean existeAlbumBoletoBloqueadoeAbertoPorContrato(Integer codAcesso, Integer codBoleto){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		try{
		
		String regexNova = "^"+codAcesso.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
		String regexAntiga = "^"+codAcesso.toString()+"/[0-9]{2}/[0-9]{2}";
		String regexProrata = "^"+codAcesso.toString()+"/PRORATA";
		
		Query qn = em.createNativeQuery("select * from contas_receber cr where " +				
				"cr.status_2 ='ABERTO' " +
				"and cr.bloqueado ='S' " +
				"and cr.n_doc REGEXP :rNova " +
				"and cr.id != :id "+
				
				"or cr.status_2 ='ABERTO' " +
				"and cr.bloqueado ='S' " +
				"and cr.n_doc REGEXP :rAntiga " +
				"and cr.id != :id "+
				
				"or cr.status_2 ='ABERTO' " +
				"and cr.bloqueado ='S' " +
				"and cr.n_doc REGEXP :rProrata " +
				"and cr.id != :id ",
				
				ContasReceber.class);
		
		qn.setParameter("rNova", regexNova);
		qn.setParameter("rAntiga", regexAntiga);
		qn.setParameter("rProrata", regexProrata);
		qn.setParameter("id", codBoleto);
		
		if(qn.getResultList().size()>0){
			return true;
		}
		
		return false;
		
		}catch (Exception e){
			e.printStackTrace();			
			return false;
		}
		
	}
	
	
	public static List<ContasReceber> procurarTodosBoletosDoAcessoPorContrato(Integer codAcesso){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();

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
	
	public static List<ContasReceber> getBoletoPorContratoNaoVencidos(Integer codContrato){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
			
			try{
			
				Query qn = em.createNativeQuery(
						"select * from contas_receber cr where " +				
						"cr.status_2 ='ABERTO' " +
						"and cr.n_doc REGEXP '^"+codContrato+"/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
						"and cr.data_vencimento > :dataHoje " +
						
						"or cr.status_2 ='ABERTO' " +
						"and cr.n_doc REGEXP '^"+codContrato+"/[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
						"and cr.data_vencimento > :dataHoje " , 
						
						ContasReceber.class);
				
				qn.setParameter("dataHoje", new Date());
				
				return qn.getResultList();
			}catch (Exception e){
				e.printStackTrace();			
				return null;
			}
	}
	
	
	public static List<ContasReceber> getBoletosBloqueado(Integer codContrato){
		
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	EntityManager em = emf.createEntityManager();
		
		try{
		
			Query qn = em.createNativeQuery(
					"select * from contas_receber cr where " +				
					"cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^"+codContrato+"/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
					"and cr.data_vencimento < :dataHoje " +
					"and cr.bloqueado = 'S' " +				
					
					"or cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^"+codContrato+"/[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
					"and cr.data_vencimento < :dataHoje " +
					"and cr.bloqueado = 'S' ", 
					
					ContasReceber.class);
			
			qn.setParameter("dataHoje", new Date());
			
			return qn.getResultList();
		}catch (Exception e){
			e.printStackTrace();			
			return null;
		}
	}
	public static void gerarBoletosAcesso(Integer codCliente, AcessoCliente contrato,String valorPrimeiroBoleto,String valorPlano, Date primeiroVenc, 
			Integer qtd,String valorAdesao, String controle, Integer codPlano) throws Exception{
		
		
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
		
		em.getTransaction().begin();
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
			em.getTransaction().commit();
			
			return cr;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
