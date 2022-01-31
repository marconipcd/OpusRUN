package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.PlanoAcesso;

public class PlanoAcessoDAO {
	EntityManager em;

	

	 public static PlanoAcesso find(Integer cod){
		 EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
			
		return  em.find(PlanoAcesso.class, cod);
		
	 }
}
