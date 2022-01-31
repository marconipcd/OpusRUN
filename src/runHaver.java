import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Haver;
import domain.HaverDetalhe;


public class runHaver {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select h from Haver h", Haver.class);
		
		em.getTransaction().begin();
		for (Haver haver : (List<Haver>)q.getResultList()) {
			
			Query qItens = em.createQuery("select dh from HaverDetalhe dh where dh.haverCab =:haver", HaverDetalhe.class);
			qItens.setParameter("haver", haver);
			
			double valor_total = 0;
			double vlr_entrada = 0;
			double vlr_saida = 0;
			for (HaverDetalhe item : (List<HaverDetalhe>)qItens.getResultList()) {
				if(item.getStatus().equals("ATIVO")){				
					
					if(item.getTipo().equals("ENTRADA")){
						vlr_entrada = vlr_entrada + item.getValor();
						System.out.println("ENTRADA: "+item.getValor());
					}else{
						vlr_saida = vlr_saida + item.getValor();
						System.out.println("SAIDA: "+item.getValor());
					}
					
				}
			}
			
			valor_total = vlr_entrada - vlr_saida;
			if(valor_total > 0){
				haver.setStatus("DISPONIVEL");
			}else{
				haver.setStatus("UTILIZADO");
			}
			
			haver.setValor_total(valor_total);
			em.merge(haver);
		}
		em.getTransaction().commit();
	}
}
