import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import util.Real;
import domain.ContasReceber;


public class BuscaBoletoInconsistentes {

	public static void main(String[] args) {
		
		
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
	
		Query q = em.createQuery("select c from ContasReceber c where "				
				+ "c.status = 'FECHADO' AND c.forma_pgto = 'BANCO' AND c.data_vencimento >='2019-01-01' ", ContasReceber.class);
		
		for (ContasReceber boletos : (List<ContasReceber>)q.getResultList()) {
			
			double valor_titulo = Real.formatStringToDBDouble(boletos.getValor_titulo());
			double valor_pagamento = Real.formatStringToDBDouble(boletos.getValor_pagamento());
			
			if(valor_pagamento < valor_titulo){
				System.out.println(boletos.getId()+" | "+boletos.getCliente().getNome_razao()+" | "+boletos.getData_pagamento()+" | "+boletos.getValor_titulo()+" | "+boletos.getValor_pagamento());
			}
			
		}
	}

}
