package SUELLEN;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;

import domain.AcessoCliente;
import domain.ContasReceber;
import domain.Crm;
import domain.CrmAssunto;
import domain.CrmFormasContato;
import domain.Setores;

public class runGerarCrmBoletosAtrasados15Dias {

	public static void main(String[] args){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
				
		Query q = em.createNativeQuery("SELECT * "
				+ "FROM contas_receber cr "
				+ "WHERE cr.status_2 = 'ABERTO' "
				+ "AND cr.n_doc REGEXP '^[0-9]{1,}/[0-9]{2}-01/[0-9]{2}' "
				+ "AND cr.data_vencimento < :dataHoje" ,ContasReceber.class);
		
		q.setParameter("dataHoje", new DateTime().minusDays(15).toDate());
		
		List<ContasReceber> boletosAtrasados = q.getResultList();
		
		for (ContasReceber boleto : boletosAtrasados) {
			
				String codContrato =  boleto.getN_doc().split("/")[0];
			
				Query qContrato  = em.createQuery("select a from AcessoCliente a where a.id =:codContrato", AcessoCliente.class);
				qContrato.setParameter("codContrato", Integer.parseInt(codContrato) );
				
				AcessoCliente contrato = null;
				if(qContrato.getResultList().size() == 1){
					contrato = (AcessoCliente)qContrato.getSingleResult();
				}
			
			 	Query q2 = em.createQuery("select c from Crm c where c.tipo_rotina LIKE :tipo", Crm.class);
				q2.setParameter("tipo", "BA2-15/"+boleto.getId().toString());
				
				if(q2.getResultList().size() == 0 && contrato != null && contrato.getData_renovacao() == null){		
			 
					Crm crm = new Crm();
					crm.setEmpresa_id(boleto.getEmpresa_id());
					crm.setSetor(new Setores(5));
					crm.setCliente(boleto.getCliente());
					crm.setCrm_assuntos(new CrmAssunto(653));
					crm.setCrm_formas_contato(new CrmFormasContato(15));
					crm.setContato(boleto.getCliente().getContato());
					crm.setOrigem("ROTINA");
					crm.setConteudo("BOLETO "+boleto.getId().toString()+" ATRASADO COM MAIS DE 15 DIAS.");
					crm.setData_agendado(new Date());
					crm.setData_cadastro(new Date());
					crm.setStatus("AGENDADO");
					crm.setOperador("opus");
					crm.setTipo_rotina("BA2-15/"+boleto.getId().toString());
						
					em.getTransaction().begin();
					em.persist(crm);
					em.getTransaction().commit();
				}
		}
		
		
	}
}
