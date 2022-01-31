import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Days;



import dao.ContasReceberDAO;
import dao.CredenciaisAcessoDAO;
import dao.IttvDAO;
import dao.Modulo;
import domain.AcessoCliente;
import domain.ContasReceber;
import domain.RadReply;
import util.MikrotikUtil;


public class runBloqueio {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		try{

			boolean check_modulo_financeiro = true;			
			boolean check_modulo_acesso = true;
			
			
		if(check_modulo_financeiro && check_modulo_acesso){
			
			
			Query qn = em.createNativeQuery("select * from contas_receber cr where " +				
					
				"cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}-[a-z]{2}[0-9]{1,}' "+	
				"and cr.data_vencimento < :dataHoje " +
				"and cr.bloquear is null  " +
				
				"or cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
				"and cr.data_vencimento < :dataHoje " +
				"and cr.bloquear is null  " +
				
				"or cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
				"and cr.data_vencimento < :dataHoje " +
				"and cr.bloquear != 'S' " +
				
				"or " +
				"cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}/[0-9]{2}' "+
				"and cr.data_vencimento <= :dataHoje "+
				"and cr.bloquear is null  " +
				
				"or " +
				"cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}/[0-9]{2}' "+
				"and cr.data_vencimento <= :dataHoje "+
				"and cr.bloquear != 'S' " +
				
				"or " +
				"cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}/PRORATA' "+
				"and cr.data_vencimento <= :dataHoje "+
				"and cr.bloquear != 'S' "+
				
				"or " +
				"cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP '^[0-9]{1,}/PRORATA' "+
				"and cr.data_vencimento <= :dataHoje "+
				"and cr.bloquear is null ",
				
				ContasReceber.class);
		
			qn.setParameter("dataHoje", new Date());
			List<ContasReceber> boletosAtrasados = qn.getResultList();
			
			//Verifica Boletos Atrasados e Marca para Bloquear
			em.getTransaction().begin();
			for (ContasReceber contasReceber : boletosAtrasados) {
				
				String numeroDoc = contasReceber.getN_doc();
				Integer codAcesso = Integer.parseInt(numeroDoc.split("/")[0].toString());				
				AcessoCliente acesso = em.find(AcessoCliente.class,codAcesso);
				
				
				if(acesso != null && acesso.getContrato().getTipo_contrato().equals("POS-PAGO")){
					
					Integer qtdDiasAtrasados = Days.daysBetween(new DateTime(contasReceber.getData_vencimento()), new DateTime()).getDays();
					
					//acesso.getContrato().getBloqueio()
					if(qtdDiasAtrasados >= acesso.getContrato().getBloqueio()){				
						System.out.println("Boleto"+contasReceber.getId().toString());
							contasReceber.setBloquear("S");
							em.merge(contasReceber);
						
					}
					
					
				}
			}	
			
			em.getTransaction().commit();
			
			Query qn3 = em.createNativeQuery(
					"select * from contas_receber cr where " +				
					"cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
					"and cr.data_vencimento < :dataHoje " +
					"and cr.bloquear = 'S' " +
					"and cr.bloqueado is null " +
					
					"or cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
					"and cr.data_vencimento < :dataHoje " +
					"and cr.bloquear = 'S' " +
					"and cr.bloqueado != 'S' " +
									
					"or " +
					"cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}/[0-9]{2}' "+
					"and cr.data_vencimento <= :dataHoje "+
					"and cr.bloquear = 'S' "+
					"and cr.bloqueado is null "+
					
					"or " +
					"cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}/[0-9]{2}' "+
					"and cr.data_vencimento <= :dataHoje "+
					"and cr.bloquear = 'S' "+
					"and cr.bloqueado != 'S' " +
					
					"or " +
					"cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^[0-9]{1,}/PRORATA' "+
					"and cr.data_vencimento <= :dataHoje "+
					"and cr.bloquear = 'S' "+
					"and cr.bloqueado != 'S' " + 
					
					"or " +
					"cr.status_2 ='ABERTO' " +
					"and cr.n_doc REGEXP '^[0-9]{1,}/PRORATA' "+
					"and cr.data_vencimento <= :dataHoje "+
					"and cr.bloquear = 'S' "+
					"and cr.bloqueado is null ",
					ContasReceber.class);
			
			qn3.setParameter("dataHoje", new Date());
			
			List<ContasReceber> boletosParaBloquear = qn3.getResultList();
			for (ContasReceber contasReceber : boletosParaBloquear) {
				
				String numeroDoc = contasReceber.getN_doc();
				Integer codAcesso = Integer.parseInt(numeroDoc.split("/")[0].toString());				
				AcessoCliente acesso = em.find(AcessoCliente.class,codAcesso);
				
				if(acesso != null){
					
					if(acesso.getStatus_2().equals("ATIVO")){
						
						
						if(acesso != null){
							
							em.getTransaction().begin();
							
								//Muda Status do Acesso
								acesso.setStatus_2("BLOQUEADO");					
							
								//Cadastra Plano Novo
								//emr.persist(new RadUserGgroup(null, acesso.getLogin(), acesso.getContrato().getId().toString()+"_BLOQUEADO", "1"));
								
								//Retira IPs Fixados no RADIUS
								Query q4 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
								q4.setParameter("username", acesso.getLogin());
								q4.setParameter("attribute", "Framed-IP-Address");
								
								for (RadReply rr : (List<RadReply>)q4.getResultList()) {
									em.remove(rr); 
								}
								
								Query q5 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
								q5.setParameter("username", acesso.getLogin());
								q5.setParameter("attribute", "Framed-Pool");
								
								for (RadReply rr : (List<RadReply>)q5.getResultList()) {
									em.remove(rr); 
								}
								
								em.persist(new RadReply(null, acesso.getLogin(), "Framed-Pool", "=", "BLOQUEADO"));					
								
								//Derruba Cliente Caso Esteja Logado
								//MikrotikUtil.derrubarConexaoHOTSPOT(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
								if(acesso.getBase() != null && acesso.getBase().getUsuario() != null && acesso.getBase().getSenha() != null && acesso.getBase().getEndereco_ip() != null && acesso.getBase().getPorta_api() != null && acesso.getLogin() != null){
									MikrotikUtil.derrubarConexaoPPPOE(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
								}
							
							em.getTransaction().commit();
							
							
							//Mudar status Ittv para INACTIVE
							if(acesso.getIttv_id() != null && !acesso.getIttv_id().equals("")){
								IttvDAO.atualizarStatus(acesso.getIttv_id(), "INACTIVE");
							}
							
						}
						
						em.getTransaction().begin();
						contasReceber.setBloqueado("S");
						em.merge(contasReceber);
						em.getTransaction().commit();
						
						System.out.println(acesso.getId()+" : BLOQUEADO");
					}else if(acesso.getStatus_2().equals("BLOQUEADO")){						
						
						em.getTransaction().begin();
						contasReceber.setBloqueado("S");
						em.merge(contasReceber);
						em.getTransaction().commit();
						
						System.out.println(acesso.getId()+" : BLOQUEADO");
						
					}		
					 
				}
			}
			
			
			//EXECUTA BLOQUEIO POR VENCIMENTO DO CONTRATO DE ACESSO			
			Query q6 = em.createQuery("select a from AcessoCliente a where a.data_venc_contrato < :dataHoje and a.status_2 = 'ATIVO' ", AcessoCliente.class);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			
			q6.setParameter("dataHoje", sdf.parse(sdf.format(new Date())));
			List<AcessoCliente> contratosVencidos  = q6.getResultList();

			if(contratosVencidos.size() >0){
				for (AcessoCliente acessoCliente : contratosVencidos) {
					
					if(acessoCliente != null){
						
						DateTime dt1 = new DateTime(acessoCliente.getData_venc_contrato());
						DateTime dt2 = new DateTime(new Date());
											
						if(Days.daysBetween(dt1, dt2).getDays() >= 30 && acessoCliente.getContrato().getTipo_contrato().equals("POS-PAGO")){	
							
							System.out.println(acessoCliente.getId().toString());
							
							em.getTransaction().begin();
							
								//Muda Status do Acesso
								acessoCliente.setStatus_2("BLOQUEADO");					
							
								//Cadastra Plano Novo
								//emr.persist(new RadUserGgroup(null, acesso.getLogin(), acesso.getContrato().getId().toString()+"_BLOQUEADO", "1"));
								
								//Retira IPs Fixados no RADIUS
								Query q7 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
								q7.setParameter("username", acessoCliente.getLogin());
								q7.setParameter("attribute", "Framed-IP-Address");
								
								for (RadReply rr : (List<RadReply>)q7.getResultList()) {
									em.remove(rr); 
								}
								
								Query q8 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
								q8.setParameter("username", acessoCliente.getLogin());
								q8.setParameter("attribute", "Framed-Pool");
								
								for (RadReply rr : (List<RadReply>)q8.getResultList()) {
									em.remove(rr); 
								}
								
								em.persist(new RadReply(null, acessoCliente.getLogin(), "Framed-Pool", "=", "BLOQUEADO"));					
								
								//Derruba Cliente Caso Esteja Logado
								//MikrotikUtil.derrubarConexaoHOTSPOT(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
								MikrotikUtil.derrubarConexaoPPPOE(acessoCliente.getBase().getUsuario(), acessoCliente.getBase().getSenha(), acessoCliente.getBase().getEndereco_ip(), Integer.parseInt(acessoCliente.getBase().getPorta_api()), acessoCliente.getLogin());
							
								//Mudar status Ittv para INACTIVE
								if(acessoCliente.getIttv_id() != null && !acessoCliente.getIttv_id().equals("")){
									IttvDAO.atualizarStatus(acessoCliente.getIttv_id(), "INACTIVE");
								}
								
								
							em.getTransaction().commit();
							
							
							
						}else if(Days.daysBetween(dt1, dt2).getDays() >= 1 && acessoCliente.getContrato().getTipo_contrato().equals("PRE-PAGO")){
							System.out.println(acessoCliente.getId().toString());
							
							em.getTransaction().begin();
							
								//Muda Status do Acesso
								acessoCliente.setStatus_2("BLOQUEADO");					
							
								//Cadastra Plano Novo
								//emr.persist(new RadUserGgroup(null, acesso.getLogin(), acesso.getContrato().getId().toString()+"_BLOQUEADO", "1"));
								
								//Retira IPs Fixados no RADIUS
								Query q7 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
								q7.setParameter("username", acessoCliente.getLogin());
								q7.setParameter("attribute", "Framed-IP-Address");
								
								for (RadReply rr : (List<RadReply>)q7.getResultList()) {
									em.remove(rr); 
								}
								
								Query q8 = em.createQuery("select rr from RadReply rr where rr.username =:username and rr.attribute =:attribute", RadReply.class);
								q8.setParameter("username", acessoCliente.getLogin());
								q8.setParameter("attribute", "Framed-Pool");
								
								for (RadReply rr : (List<RadReply>)q8.getResultList()) {
									em.remove(rr); 
								}
								
								em.persist(new RadReply(null, acessoCliente.getLogin(), "Framed-Pool", "=", "BLOQUEADO"));					
								
								//Derruba Cliente Caso Esteja Logado
								//MikrotikUtil.derrubarConexaoHOTSPOT(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
								MikrotikUtil.derrubarConexaoPPPOE(acessoCliente.getBase().getUsuario(), acessoCliente.getBase().getSenha(), acessoCliente.getBase().getEndereco_ip(), Integer.parseInt(acessoCliente.getBase().getPorta_api()), acessoCliente.getLogin());
							
							em.getTransaction().commit();
						}
						
					}
					
					//System.out.println("CONTRATO "+acessoCliente.getId()+" BLOQUEADO POR VENCIMENTO DE CONTRATO");
				}
			}
			
			//EXECUTA BLOQUEIO TOTAL DO CONTRATO
			Query q10 = em.createQuery("select r from RadReply r where r.value ='BLOQUEADO' ", RadReply.class);
			List<RadReply> loginsBloqueados = q10.getResultList(); 
			for (RadReply radReply : loginsBloqueados) {
				try{
								
					List<ContasReceber> boletosBloqueados = ContasReceberDAO.getBoletosBloqueado(Integer.parseInt(radReply.getUsername()));
					for (ContasReceber contasReceber2 : boletosBloqueados) {
						
						Integer qtdDiasAtrasados = Days.daysBetween(new DateTime(contasReceber2.getData_vencimento()), new DateTime()).getDays();
						AcessoCliente acesso = em.find(AcessoCliente.class,Integer.parseInt(radReply.getUsername()));
						
						//
						if(qtdDiasAtrasados >= acesso.getContrato().getBloqueio_total()){
							System.out.println(acesso.getId());
							CredenciaisAcessoDAO.bloquearContratoClienteTotal(acesso.getId());					
						}
					}
				}catch(Exception e){
					//System.out.println("Contrato: "+radReply.getUsername()+ "Não encontrado!");
				}
			}
					
		}
		
		}catch(Exception e){
			e.printStackTrace();		
			em.getTransaction().rollback();
		}
	}

}
