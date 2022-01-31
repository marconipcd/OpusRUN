package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Ose;

public class OseDAO {
	
	
	
	public static Ose find(Integer id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		return em.find(Ose.class, id);
	}
	
	
}
