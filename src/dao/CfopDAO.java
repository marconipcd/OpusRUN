package dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import domain.Cfop;


public class CfopDAO {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public  static Cfop find(Integer id){
		
		try{
			if(id != null && id > 0){
				EntityManager em  = emf.createEntityManager();
				Cfop cfop = em.find(Cfop.class, id);
				return cfop;
			}
			
			return null;
		
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
}
