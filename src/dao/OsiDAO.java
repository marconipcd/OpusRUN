package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Osi;

public class OsiDAO {
	
	

	public static Osi find(Integer cod){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		return em.find(Osi.class, cod);
	}
	
	
}
