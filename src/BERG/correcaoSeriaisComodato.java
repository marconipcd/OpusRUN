package BERG;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.SeriaisProduto;

public class correcaoSeriaisComodato {

	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		
		EntityManager em = emf.createEntityManager();
		
		Query qSeriaisComodato = em.createQuery("select s from SeriaisProduto s where s.status like:s", SeriaisProduto.class);
		qSeriaisComodato.setParameter("s", "COMODATO");
		
		List<SeriaisProduto> seriais = qSeriaisComodato.getResultList();
		
		for (SeriaisProduto seriaisProduto : seriais) {
			
			Query qContrato = em.createQuery("select a from AcessoCliente a where a.endereco_mac like:m", AcessoCliente.class);
			qContrato.setParameter("m", seriaisProduto.getSerial());
		}

	}

}














