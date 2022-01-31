import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;
import domain.Crm;
import domain.CrmAssunto;
import domain.CrmFormasContato;
import domain.Setores;


public class runCrmCobraca {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		
		// -> lista de contratos diferente de encerrado
		//Query qContratos = em.createQuery("select a from AcessoCliente a where a.status_2!=:s", AcessoCliente.class);
		//qContratos.setParameter("s", "ENCERRADO");
		
		Query qn_boletos_venc = em.createNativeQuery(
				"select * from contas_receber cr where " +				
						
						"cr.status_2 ='ABERTO' " +
						"and cr.n_doc REGEXP '^[0-9]{4}/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
						"and cr.data_vencimento < :dataHoje or "
						
						+ "cr.status_2 ='ABERTO' " +
						"and cr.n_doc REGEXP '^[0-9]{4}/PRORATA' "+	
						"and cr.data_vencimento < :dataHoje "
						
						+ "group by cr.CLIENTES_ID ",ContasReceber.class);
		
		qn_boletos_venc.setParameter("dataHoje", new Date());
		
		
		for (ContasReceber boleto: (List<ContasReceber>)qn_boletos_venc.getResultList()) {
			
			
			String nDoc = boleto.getN_doc();
			String codContrato = nDoc.split("/")[0];
			
			AcessoCliente contrato = em.find(AcessoCliente.class, Integer.parseInt(codContrato));
			
			
			Query qn = em.createNativeQuery(
					"select * from contas_receber cr where " +				
							"cr.status_2 ='ABERTO' " +
							"and cr.n_doc REGEXP '^"+codContrato+"/[0-9]{2}-[0-9]{2}/[0-9]{2}' "+	
							"and cr.data_vencimento < :dataHoje or "
							
							+ "cr.status_2 ='ABERTO' " +
							"and cr.n_doc REGEXP '^"+codContrato+"/PRORATA' "+	
							"and cr.data_vencimento < :dataHoje"
							
							,ContasReceber.class);
			
			qn.setParameter("dataHoje", new Date());
			
			if(contrato != null && !contrato.getStatus_2().equals("ENCERRADO") && qn.getResultList().size() >= 2){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyMM");
				String dataMensal = sdf.format(new Date());
				
				Query qCrm = em.createNativeQuery("select * from crm c where c.CONTRATO_ID =:contrato and c.SETOR_ID =:setor and c.CRM_ASSUNTOS_ID =:crm_assuntos and"
						+ " DATE_FORMAT(c.DATA_AGENDADO, '%y%m') =:anoMes", Crm.class);
				
				qCrm.setParameter("contrato",codContrato);
				qCrm.setParameter("setor",5);
				qCrm.setParameter("crm_assuntos",19);
				qCrm.setParameter("anoMes",dataMensal);
				
				System.out.println("Contrato: "+codContrato);
				System.out.println("Tem "+String.valueOf(qn.getResultList().size())+" Boletos Atrasados!");
				
				if(qCrm.getResultList().size() == 0){
					System.out.println("Gerar CRM!");
					System.out.println("Cod Contrato: "+codContrato);
					System.out.println("Cliente: "+boleto.getCliente().getNome_razao());
					
					Crm crm = new Crm(null,1, new Setores(5), boleto.getCliente(), new CrmAssunto(19), new CrmFormasContato(2), boleto.getCliente().getNome_razao(), "OPUS","DOIS OU MAIS BOLETOS EM ATRASO" , new Date(), null, new Date(), null, "AGENDADO", "");
					crm.setContrato(new AcessoCliente(Integer.parseInt(codContrato))); 
					
					em.getTransaction().begin();
					em.persist(crm);
					em.getTransaction().commit();
				}
				
			}
			
			
		}
	
		// -> verifica se existe um crm financeiro e de cobrança para este contrato nesse mês
		
				
		

			
		
		
		
	}
	
	public static boolean checkBoletoAdiantadoPorContrato(ContasReceber cr){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		try{
		
		String codContrato = cr.getN_doc().split("/")[0].toString();
		String regex = "^"+codContrato.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
		Query qn = em.createNativeQuery("select * from contas_receber cr where " +				
				
				"cr.status_2 ='ABERTO' " +
				"and cr.n_doc REGEXP :reg "+	
				"and cr.data_vencimento <:dataHoje ",
				
				ContasReceber.class);
		
		qn.setParameter("reg", regex);
		qn.setParameter("dataHoje", cr.getData_vencimento());
		
		if(qn.getResultList().size() > 0){
			return true;
		}
		
		return false;
		
		}catch (Exception e){
			e.printStackTrace();			
			return false;
		}
	}
	
}
