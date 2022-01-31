import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;


public class runCorrecaoBloqueio {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		try{
			
			
			Query q = em.createQuery("select c from ContasReceber c where c.status='ABERTO' and c.bloqueado ='S'", ContasReceber.class);
			for (ContasReceber boleto : (List<ContasReceber>)q.getResultList()) {
				
				String numeroDoc = boleto.getN_doc();
				Integer codAcesso = Integer.parseInt(numeroDoc.split("/")[0].toString());
				
				if(codAcesso != null){
					AcessoCliente contrato = em.find(AcessoCliente.class, codAcesso);
					if(contrato != null && contrato.getStatus_2().equals("ATIVO")){
						System.out.println(contrato.getId());
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}
