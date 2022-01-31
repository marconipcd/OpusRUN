package ADEMIR;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.PlanoAcesso;
import domain.RadUserGroup;

public class atualizarPlanos {

	public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select c from AcessoCliente c where c.plano=142 and c.status_2 not like 'ENCERRADO'", AcessoCliente.class);
		List<AcessoCliente> result = q.getResultList();
		
		//250 - 10M CABO URBANO PLUS
		//251 - 15M CABO URBANO PLUS
		//252 - 20M CABO URBANO PLUS		
		em.getTransaction().begin();
		for (AcessoCliente contrato: result) {
			
			contrato.setPlano(new PlanoAcesso(250));
			em.merge(contrato);
			
			Query qRadUserGroup =em.createQuery("select r from RadUserGgroup r where r.username =:u ", RadUserGroup.class);
			qRadUserGroup.setParameter("u", contrato.getId().toString());
			List<RadUserGroup> radUsersGroups = qRadUserGroup.getResultList();
			
			for (RadUserGroup radUserGroup : radUsersGroups) {
				em.remove(radUserGroup);
			}
			
			RadUserGroup rug = new RadUserGroup(null, contrato.getLogin(), "2_10M CABO URBANO PLUS", "1");
			em.persist(rug);
		}		
		em.getTransaction().commit();
		
	}
}
