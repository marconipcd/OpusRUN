import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.ContasReceber;


public class testeBoletos {

	static EntityManager em;
	static EntityManagerFactory emf;
	
	public static void main(String[] args) {
		
		emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		em = emf.createEntityManager();
		
		Query q = em.createNativeQuery("SELECT * FROM contas_receber c WHERE c.EMPRESA_ID = 1 AND c.STATUS_2 = 'ABERTO' "
				+ "AND EXISTS(SELECT c2.ID FROM contas_receber c2 where c2.CLIENTES_ID = c.CLIENTES_ID AND c2.DATA_VENCIMENTO > c.DATA_VENCIMENTO "
				+ "and c2.STATUS_2 = 'FECHADO')", ContasReceber.class);
		
		for (ContasReceber boleto : (List<ContasReceber>)q.getResultList() ) {
			
			String codContrato = boleto.getN_doc().split("/")[0].toString();
			String regex = "^"+codContrato.toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
			Query qn = em.createNativeQuery("select * from contas_receber cr where " +				
					
					"cr.status_2 ='FECHADO' " +
					"and cr.n_doc REGEXP :reg "+	
					"and cr.data_vencimento > :dataHoje ",
					
					ContasReceber.class);
			
			qn.setParameter("reg", regex);
			qn.setParameter("dataHoje", boleto.getData_vencimento());
			
			if(qn.getResultList().size() > 0){
				System.out.println("Cod:"+boleto.getId()+" NDoc: "+boleto.getN_doc());
			}
			
		}
	}
}
