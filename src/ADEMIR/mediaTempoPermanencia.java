package ADEMIR;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.AcessoCliente;
import domain.ContasReceber;

public class mediaTempoPermanencia {

	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		EntityManager em = emf.createEntityManager();
		
		Query qClientes = em.createQuery("select a from AcessoCliente a", AcessoCliente.class);
		List<AcessoCliente> lista_clientes = (List<AcessoCliente>)qClientes.getResultList();
		
		Integer qtd_clientes = 0;
		Integer qtd_boletos_pagos = 0;
		for (AcessoCliente contrato: lista_clientes) {
			
			Query qBoletos = em.createQuery("select c from ContasReceber c where "
					+ "c.controle like 'ACESSO-POS' AND c.status like 'FECHADO' AND c.cliente =:cliente", ContasReceber.class);
			qBoletos.setParameter("cliente", contrato.getCliente());
			
			qtd_boletos_pagos = qtd_boletos_pagos + qBoletos.getResultList().size();
			qtd_clientes++;
		}
		
		System.out.println(qtd_boletos_pagos / qtd_clientes);
		System.out.println(qtd_clientes);
		
	}
}
