package ADEMIR;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Crm;
import domain.CrmAssunto;
import domain.CrmFormasContato;
import domain.Setores;


public class runCrmCorrigirOperador {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		Query q = em.createQuery("select c from Crm c where c.setor=:s and c.crm_assuntos=:a and c.crm_formas_contato=:fc and "
				+ "c.origem =:o and c.conteudo=:co and c.status=:st and c.operador=:op", Crm.class);
		q.setParameter("s", new Setores(2));
		q.setParameter("a", new CrmAssunto(363));
		q.setParameter("fc", new CrmFormasContato(15));
		q.setParameter("o", "ROTINA");
		q.setParameter("co", "POS-VENDA INSTALACAO PRIMEIRO BOLETO");
		q.setParameter("st", "AGENDADO");
		q.setParameter("op", "manu");
		
		List<Crm> crms = q.getResultList();
		
		for (Crm crm : crms) {
			System.out.println(crm.getId().toString());
			
		}
		
		System.out.println(q.getResultList().size());
	}
}
