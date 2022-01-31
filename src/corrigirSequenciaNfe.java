import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.NfeMestre;


public class corrigirSequenciaNfe {

	
	public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
				
		
		Query q = em.createQuery("select n from NfeMestre n where n.id > 110636 and n.data_emissao='2021-08-03'", NfeMestre.class);
		List<NfeMestre> result = q.getResultList();
		
		Integer i = 110637;
		
		em.getTransaction().begin();
		for (NfeMestre nfeMestre : result) {
			//.detach(nfeMestre); 
			//nfeMestre.setId(i);
			//em.merge(nfeMestre);
			
			System.out.println(nfeMestre.getId().toString()+" - "+i);
			i++;
		}
		em.getTransaction().commit();
	}
}
