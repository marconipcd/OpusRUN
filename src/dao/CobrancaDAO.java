package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.ConfigBoleto;

public class CobrancaDAO {

	public static ConfigBoleto getDefaultValue(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select c from ConfigBoleto c", ConfigBoleto.class);
		if(q.getResultList().size() == 1){
			return (ConfigBoleto)q.getSingleResult();
		}
		
		return null;
	}
	
	public static void saveValueDefault(ConfigBoleto c){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		if(c.getId() != null){
			em.merge(c);
		}else{
			em.persist(c); 
		}
		em.getTransaction().commit();
	}
}
