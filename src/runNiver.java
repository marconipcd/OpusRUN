import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class runNiver {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EntityManager em = emf.createEntityManager();
		

		Query q = em.createNativeQuery("call rotina_aniversariantes();");
		List<?> result = q.getResultList();
		
	}

}
