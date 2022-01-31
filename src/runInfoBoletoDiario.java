

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import util.Real;
import domain.ContasReceber;
import domain.InfoBoletoDiario;

public class runInfoBoletoDiario {

	
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Integer qtd_boletos_bloqueados = 0;
		double total_boletos_bloqueados = 0;
		
		Integer qtd_boletos_negativados = 0;
		double total_boletos_negativados = 0;
		
		Query qBloqueados = em.createQuery("select c from ContasReceber c where "
				+ "c.status not like 'FECHADO' AND "
				+ "c.status not like 'EXCLUIDO' AND "
				+ "c.bloqueado like 'S' ", ContasReceber.class);
				
		for (ContasReceber boleto : (List<ContasReceber>)qBloqueados.getResultList()) {			
			total_boletos_bloqueados = total_boletos_bloqueados + Real.formatStringToDBDouble(boleto.getValor_titulo());
		}
		qtd_boletos_bloqueados = qBloqueados.getResultList().size();
		
		Query qNegativados = em.createQuery("select c from ContasReceber c where "
				+ "c.status like 'NEGATIVADO' ", ContasReceber.class);
				
		for (ContasReceber boleto : (List<ContasReceber>)qNegativados.getResultList()) {	
						
			if(boleto.getValor_titulo() != null && !boleto.getValor_titulo().equals("")){
				total_boletos_negativados = total_boletos_negativados + Real.formatStringToDBDouble(boleto.getValor_titulo());
			}
		}
		qtd_boletos_negativados = qNegativados.getResultList().size();
		
		InfoBoletoDiario info_boleto_diario = new InfoBoletoDiario();
		info_boleto_diario.setQtd_boletos_bloqueados(qtd_boletos_bloqueados);
		info_boleto_diario.setTotal_boletos_bloqueados(total_boletos_bloqueados);
		info_boleto_diario.setQtd_boletos_negativados(qtd_boletos_negativados);
		info_boleto_diario.setTotal_boletos_negativados(total_boletos_negativados);
		info_boleto_diario.setData(new Date());
				
		em.getTransaction().begin();
		em.persist(info_boleto_diario);
		em.getTransaction().commit();
		
		
	}
	
}
