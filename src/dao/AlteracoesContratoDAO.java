package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.AlterarcoesContrato;

public class AlteracoesContratoDAO {

	
	public static void save(AlterarcoesContrato alteracao){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(alteracao);
			em.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}
}
