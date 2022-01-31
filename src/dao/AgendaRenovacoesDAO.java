package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.AgendaRenovacoes;

public class AgendaRenovacoesDAO {
	

	public static void save(AgendaRenovacoes ar){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(ar);
		em.getTransaction().commit();
	}
}
