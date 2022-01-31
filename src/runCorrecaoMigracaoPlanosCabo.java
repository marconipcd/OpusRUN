import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AlteracoesContasReceber;
import domain.Cliente;
import domain.ContasReceber;


public class runCorrecaoMigracaoPlanosCabo {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");

	public static void main(String[] args) {
		EntityManager em = emf.createEntityManager();
		
		
		//SELECT acr.CONTA_RECEBER_ID, c.CLIENTES_ID  FROM alteracoes_conta_Receber acr, contas_receber c WHERE 
		//c.ID = acr.CONTA_RECEBER_ID AND 
		//acr.DATA_ALTERACAO >= '2020-05-14' AND acr.TIPO LIKE 'CADASTRO UM BOLETO' AND acr.OPERADOR_ID = 1 GROUP BY c.CLIENTES_ID
		
		Query q = em.createQuery("select acr from AlteracoesContasReceber acr where "
				+ "acr.data_alteracao >= '2020-05-14' AND "
				+ "acr.tipo LIKE 'CADASTRO UM BOLETO' AND "
				+ "acr.operador = 1",AlteracoesContasReceber.class);
		
		List<AlteracoesContasReceber> alteracoesDeBoletosCadastradosHoje = q.getResultList();
		
		em.getTransaction().begin();
		
		for (AlteracoesContasReceber acr : alteracoesDeBoletosCadastradosHoje) {
			
			ContasReceber boleto = acr.getConta_Receber();
			Cliente cliente = boleto.getCliente();
			
			System.out.println(cliente.getNome_razao());
			
			Query q2  = em.createQuery("select c from ContasReceber c where c.cliente =:c and c.status='EXCLUIDO' and c.data_exclusao=:dataHoje", ContasReceber.class);
			q2.setParameter("c", cliente);
			q2.setParameter("dataHoje", new Date());
			
			List<ContasReceber> boletosExcluidosHoje = q2.getResultList();
			for (ContasReceber boletoExcluidoHoje: boletosExcluidosHoje) {
				ContasReceber boleto1 = boletoExcluidoHoje;
				boleto1.setStatus("ABERTO");
				em.merge(boleto1);
			}
			
			
			boleto.setStatus("EXCLUIDO");
			em.merge(boleto);
			
		}
		
		em.getTransaction().commit();
		
	}

}
