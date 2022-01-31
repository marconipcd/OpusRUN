package SUELLEN;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;

import domain.ContasReceber;
import domain.Crm;
import domain.CrmAssunto;
import domain.CrmFormasContato;
import domain.Setores;

public class runGerarCrmBoletosAtrasados {

	
	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("select c from ContasReceber c where c.data_vencimento <=:vencimento and c.status like 'ABERTO'", ContasReceber.class);
		q.setParameter("vencimento", new DateTime().minusDays(25).toDate());
		
		List<ContasReceber> boletosAtrasados = q.getResultList();
		
		for (ContasReceber boleto : boletosAtrasados) {
			
			 	Query q2 = em.createQuery("select c from Crm c where c.tipo_rotina LIKE :tipo", Crm.class);
				q2.setParameter("tipo", "BA45/"+boleto.getId().toString());
				
				if(q2.getResultList().size() == 0){		
			 
					Crm crm = new Crm();
					crm.setEmpresa_id(boleto.getEmpresa_id());
					crm.setSetor(new Setores(5));
					crm.setCliente(boleto.getCliente());
					crm.setCrm_assuntos(new CrmAssunto(497));
					crm.setCrm_formas_contato(new CrmFormasContato(15));
					crm.setContato(boleto.getCliente().getContato());
					crm.setOrigem("ROTINA");
					crm.setConteudo("BOLETO "+boleto.getId().toString()+" ATRASADO COM MAIS DE 25 DIAS.");
					crm.setData_agendado(new Date());
					crm.setData_cadastro(new Date());
					crm.setStatus("AGENDADO");
					crm.setOperador("opus");
					crm.setTipo_rotina("BA45/"+boleto.getId().toString());
						
					em.getTransaction().begin();
					em.persist(crm);
					em.getTransaction().commit();
				}
		}
		
		
	}
}
