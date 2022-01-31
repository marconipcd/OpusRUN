import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.SeriaisProduto;


public class retirarEspacosEmBrancoSeriais {
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		
		Query q  = em.createQuery("select a from AcessoCliente a", AcessoCliente.class);
		List<AcessoCliente> acessos = q.getResultList();
		
		em.getTransaction().begin();
		for (AcessoCliente acesso: acessos) {
			
			if(acesso.getOnu_serial() != null && !acesso.getOnu_serial().equals("")){
				acesso.setOnu_serial(acesso.getOnu_serial().trim());
			}
			
		}
		em.getTransaction().commit();
	
	}

}
