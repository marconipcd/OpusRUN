import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;


public class runCorrecaoDataVenci {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	static EntityManager em = emf.createEntityManager();
	
	public static void main(String[] args) {

		Query qContratos = em.createQuery("select a from AcessoCliente a where a.status_2 != 'ENCERRADO'", AcessoCliente.class);
		
		List<AcessoCliente> contratos = qContratos.getResultList();		
		for (AcessoCliente c : contratos) {
			
			String n_doc = c.getId().toString()+"/21-12/12";
			Query qBoletos = em.createQuery("select c from ContasReceber c where c.n_doc =:n and c.status LIKE 'ABERTO'", ContasReceber.class);
			qBoletos.setParameter("n", n_doc);
			
			if(qBoletos.getResultList().size() == 1){
				
				ContasReceber boleto = (ContasReceber) qBoletos.getSingleResult();				
				if(!c.getData_venc_contrato().equals(boleto.getData_vencimento())){
					System.out.println(c.getId().toString());
				}
			}
			
		}
		
	}

}
