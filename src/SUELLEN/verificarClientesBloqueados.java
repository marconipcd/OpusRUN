package SUELLEN;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;
import domain.RadReply;

public class verificarClientesBloqueados {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		
		EntityManager em = emf.createEntityManager();		
		Query qBoletosBloqueados = em.createQuery("select c from ContasReceber c where c.bloqueado like 's' and "
				+ "c.status != 'FECHADO'", ContasReceber.class);
		
		Integer qtd_boletos_bloqueados = qBoletosBloqueados.getResultList().size();
		List<ContasReceber> boletosBloqueados = qBoletosBloqueados.getResultList();
		
		System.out.println("boletos: "+boletosBloqueados.size());
		
		for (ContasReceber contasReceber : boletosBloqueados) {
			
			Integer cod_contrato = Integer.parseInt(contasReceber.getN_doc().split("/")[0].toString());
			
			Query qRadReply = em.createQuery("select r from RadReply r where r.username like:username and r.attribute like 'Framed-Pool' and r.value like '%BLOQUEADO%'", RadReply.class);
			qRadReply.setParameter("username", cod_contrato);
			
			
			if(qRadReply.getResultList().size() == 0){
				System.out.println(contasReceber.getCliente().getNome_razao());
			}
			
		}
		
		
	}

}
