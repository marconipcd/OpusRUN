import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;
import domain.PlanoAcesso;


public class runTestVerificarDataVencContrato {

	static EntityManager em;
	static EntityManagerFactory emf;
	
	
	
	public static void main(String[] args) {
		emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		em = emf.createEntityManager();
		
		PlanoAcesso planoNovo = em.find(PlanoAcesso.class, 251);
		
		Query q = em.createQuery("select a from AcessoCliente a where a.status_2 != 'ENCERRADO' and a.plano=:p", AcessoCliente.class);
		q.setParameter("p", planoNovo);
		
		em.getTransaction().begin();
		for (AcessoCliente a :(List<AcessoCliente>) q.getResultList()) {
			
			
			String regexNova = "^"+a.getId().toString()+"/[0-9]{2}-[0-9]{2}/[0-9]{2}";
			
			
			Query qn = em.createNativeQuery("select * from contas_receber cr where " +				
					"cr.status_2 ='ABERTO' " +				
					"and cr.n_doc REGEXP :rNova ORDER BY cr.DATA_VENCIMENTO DESC ",
					
					ContasReceber.class);
			
			qn.setParameter("rNova", regexNova);			
			
			if(qn.getResultList().size()>0){
			
			
				ContasReceber boleto = (ContasReceber)qn.getResultList().get(0);
				if(!boleto.getData_vencimento().equals(a.getData_venc_contrato())){
					System.out.println("CONTRATO:"+a.getId().toString()+"|DATA VENC CONTRATO:"+a.getData_venc_contrato()+"|BOLETO:"+boleto.getN_doc()+"|VENC:"+boleto.getData_vencimento());
					a.setData_venc_contrato(boleto.getData_vencimento());
					em.merge(a);
				}
			}

			
		}
		em.getTransaction().commit();
	}
}
