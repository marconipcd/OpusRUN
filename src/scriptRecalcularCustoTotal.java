import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Produto;




public class scriptRecalcularCustoTotal {

	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select p from Produto p where p.empresaId=5  ", Produto.class);
		
		em.getTransaction().begin();
		
		for (Produto p: (List<Produto>)q.getResultList()) {
			
			Float valorCusto = p.getValorCusto() != null ? p.getValorCusto(): new Float(0);
			Float valorIpi = p.getTaxaIpi() > 0 ? new Float(p.getTaxaIpi()) : new Float(0);
			Float valorFrete = p.getFrete() != null ? p.getFrete() : new Float(0);
			Float valorDiferencaAliquota = p.getDiferenca_aliquota() != null ? p.getDiferenca_aliquota() : new Float(0);
			
			Float somaPercentuais = valorIpi+valorFrete+valorDiferencaAliquota;
			
		    double percentual = somaPercentuais / 100.0; 
		    double valor_final = valorCusto + (percentual * valorCusto);
		    
		    System.out.println("VALOR CUSTO: R$"+valorCusto);
		    System.out.println("VALOR IPI:"+valorIpi+"%");
		    System.out.println("VALOR FRETE:"+valorFrete+"%");
		    System.out.println("VALOR DIFERENÇA ALIQUOTA:"+valorDiferencaAliquota+"%");
		    System.out.println("VALOR CUSTO TOTAL: R$"+valor_final);
			
		    p.setCusto_total(new Float(valor_final));
		    em.merge(p);
		}
		
		em.getTransaction().commit();
		
	}
}
