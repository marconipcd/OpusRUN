import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class ctoPorBairro {
	
	public static void main(String[] args){
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	EntityManager em = emf.createEntityManager();
		
	String cidade = "SANHARO";
	Query qBairros = em.createNativeQuery("SELECT DISTINCT(ep.BAIRRO) FROM acesso_cliente ac, enderecos_principais ep WHERE "
			+ "ac.ENDERECO_ID = ep.ID AND ep.CIDADE LIKE :c AND ac.STATUS_2 LIKE 'ATIVO'");
	
	qBairros.setParameter("c", cidade);
	
	List<String> bairros = qBairros.getResultList();
	
	for(String b: bairros){
				
		Query q  = em.createNativeQuery("SELECT DISTINCT(s.ID) FROM acesso_cliente ac, enderecos_principais ep, swith s WHERE "
				+ "ac.SWITH_ID = s.ID AND "
				+ "ac.ENDERECO_ID = ep.ID AND  "
				+ "ac.STATUS_2 LIKE 'ATIVO' AND "
				+ "s.IDENTIFICACAO LIKE '%CTO%'AND "
				+ "ep.BAIRRO LIKE :s AND ep.CIDADE LIKE :c");
		q.setParameter("s", b);
		q.setParameter("c", cidade);
				
		Query q2 = em.createNativeQuery("SELECT ac.ID FROM acesso_cliente ac, enderecos_principais ep WHERE "				
				+ "ac.ENDERECO_ID = ep.ID AND  "
				+ "ac.STATUS_2 LIKE 'ATIVO' AND "				
				+ "ep.BAIRRO LIKE :s AND ep.CIDADE LIKE :c");
		q2.setParameter("s", b);
		q2.setParameter("c", cidade);
		
		int qtd_clientes_por_bairro = q2.getResultList().size();
		
		System.out.println('"'+cidade+'"'+";"+'"'+b+'"'+";"+'"'+q.getResultList().size()+'"'+";"+'"'+qtd_clientes_por_bairro+'"');		
		//System.out.println("\n");
		
	}
	
	
	
	
	}
}
