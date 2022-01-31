package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.Cliente;
import domain.ContratosAcesso;
import domain.PlanoAcesso;
import domain.RadReply;

public class ContratosAcessoDAO {
	
	
	public static AcessoCliente find(Integer cod){
		if(cod != null){
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
			
			return em.find(AcessoCliente.class, cod);
		}
		
		return null;
	}
	
	public static AcessoCliente buscarContratoPorCliente(Cliente c){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select a from AcessoCliente a where a.cliente=:cliente and a.status_2='ATIVO'", AcessoCliente.class);
		q.setParameter("cliente", c);
		
		if(q.getResultList().size() == 1){
			return (AcessoCliente)q.getSingleResult();
		}
		
		return null;
	}
	
	
	
}
