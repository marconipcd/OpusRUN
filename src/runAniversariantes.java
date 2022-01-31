import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Cliente;
import domain.Crm;
import domain.CrmAssunto;
import domain.CrmFormasContato;
import domain.Setores;


public class runAniversariantes {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM clientes c WHERE "
				+ "EXISTS(SELECT id as id_contrato FROM acesso_cliente ac WHERE ac.clientes_id = c.id and ac.status_2 != 'ENCERRADO' ) "
				+ "AND DATE_FORMAT(c.DATA_NASCIMENTO,  '%m-%d') = DATE_FORMAT(CURDATE(), '%m-%d')", Cliente.class);

		for (Cliente c : (List<Cliente>)q.getResultList()) {
			
			System.out.println(c.getNome_razao()+"/r/n");
			
			CrmAssunto crmAssunto = null;
			CrmFormasContato crmFormaContato = null;
			Setores setor = null;
			
			Query qCrmAssunto = em.createNativeQuery("SELECT  * FROM crm_assuntos cr WHERE cr.empresa_id="+c.getEmpresa()+" AND cr.nome='FELIZ ANIVERSARIO'", CrmAssunto.class);
			if(qCrmAssunto.getResultList() != null && qCrmAssunto.getResultList().size() == 1){
				crmAssunto = (CrmAssunto)qCrmAssunto.getSingleResult();
			}			
			
			Query qCrmFormaContato = em.createNativeQuery("SELECT  * FROM crm_formas_contato cr WHERE cr.empresa_id="+c.getEmpresa()+" AND cr.nome='TELEFONE'", CrmFormasContato.class);
			if(qCrmFormaContato.getResultList() != null && qCrmFormaContato.getResultList().size() == 1){
				crmFormaContato = (CrmFormasContato)qCrmFormaContato.getSingleResult();
			}
			
			Query qCrmSetor = em.createNativeQuery("SELECT  * FROM setores cr WHERE cr.empresa_id="+c.getEmpresa()+" AND cr.nome='COMERCIAL'", Setores.class);
			if(qCrmSetor.getResultList() != null && qCrmSetor.getResultList().size() == 1){
				setor = (Setores)qCrmSetor.getSingleResult();
			}
						
				if(crmAssunto != null && crmFormaContato != null && setor != null){
					Query qSetorDefault = em.createNativeQuery("SELECT * FROM crm WHERE crm.empresa_id="+c.getEmpresa()+" AND crm.setor_id="+setor.getId()+" AND crm.clientes_id="+c.getId()+" AND crm.crm_assuntos_id="+crmAssunto.getId()+" AND crm.crm_formas_contato_id="+crmFormaContato.getId()+" AND crm.origem='ROTINA' and crm.`DATA_AGENDADO`=CURDATE()", Crm.class);
					
					if(qSetorDefault.getResultList().size() == 0 && c.getAgendar_crm().equals("SIM")){
						
						Crm crm = new Crm();
						crm.setEmpresa_id(c.getEmpresa());
						crm.setSetor(setor);
						crm.setCliente(c);
						crm.setCrm_assuntos(crmAssunto);
						crm.setCrm_formas_contato(crmFormaContato);
						crm.setContato(c.getContato());
						crm.setOrigem("ROTINA");
						crm.setConteudo(crmAssunto.getConteudo());
						crm.setData_agendado(new Date());
						crm.setHora_agendado(new Date());
						crm.setData_cadastro(new Date());
						crm.setStatus("AGENDADO");		
						crm.setOperador("OpusERP");
						
						em.getTransaction().begin();
						em.persist(crm);
						em.getTransaction().commit();
						
	
						
					}
				}
			
		}
	}

}
