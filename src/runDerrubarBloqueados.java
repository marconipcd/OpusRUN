import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import util.MikrotikUtil;


public class runDerrubarBloqueados {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	static EntityManager em = emf.createEntityManager();

	public static void main(String[] args){
				
		Query q = em.createQuery("select a from AcessoCliente a where a.status_2 = 'BLOQUEADO'", AcessoCliente.class);
		
		List<AcessoCliente> result = q.getResultList();
		
		for (AcessoCliente acesso : result) {
			
			MikrotikUtil.derrubarConexaoPPPOE(acesso.getBase().getUsuario(), acesso.getBase().getSenha(), acesso.getBase().getEndereco_ip(), Integer.parseInt(acesso.getBase().getPorta_api()), acesso.getLogin());
			System.out.println(acesso.getId());
		}
		
	}
}
