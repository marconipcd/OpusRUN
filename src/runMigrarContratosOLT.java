
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.Concentrador;


public class runMigrarContratosOLT {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select a from AcessoCliente a where a.swith.olt = 'BASE08-OLT2' and "
				+ "a.base.identificacao = 'REDE08-FTTH/01' and a.status_2 != 'ENCERRADO'", AcessoCliente.class);
		
		System.out.println(q.getResultList().size()+" contratos encontrados.");
		
		
		Concentrador concentrador = em.find(Concentrador.class, 98);
		
		if(concentrador != null){
			em.getTransaction().begin();
			for (AcessoCliente contratos: (List<AcessoCliente>)q.getResultList()) {
				
				contratos.setBase(concentrador);
				em.merge(contratos);
				
			}
			em.getTransaction().commit();
		}
	}
}
