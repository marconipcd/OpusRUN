package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.LogAcoes;

public class LogDAO {

	public static void add(LogAcoes log){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		try{
			
			em.getTransaction().begin();
			em.persist(log);
			em.getTransaction().commit();
		}catch(Exception e){
			
			if(em.getTransaction().isActive()){
				em.getTransaction().rollback();
			}
			e.printStackTrace();
		}
	}
}
