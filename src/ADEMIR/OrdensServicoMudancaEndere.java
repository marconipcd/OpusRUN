package ADEMIR;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import util.Real;
import domain.ContasReceber;
import domain.Ose;

public class OrdensServicoMudancaEndere {

	public static void main(String[] args){
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
		
		Query q = em.createQuery("SELECT c FROM ContasReceber c WHERE "
				+ "c.data_emissao >= '2020-11-23' AND "
				+ "c.controle LIKE 'SERVICO' AND "
				+ "c.status LIKE 'FECHADO' AND "
				+ "c.valor_pagamento NOT LIKE '0,00' AND "
				+ "c.valor_pagamento NOT LIKE '0' AND "
				+ "c.n_doc LIKE '%OS%' AND "
				+ "c.empresa_id = 1", ContasReceber.class);
		List<ContasReceber> boletos = q.getResultList();
		
		Integer qtd=0;
		double valor = 0;
		for (ContasReceber boleto : boletos) {
			
				String nDoc = boleto.getN_doc();	
				String codOs = nDoc.split("-")[1].replaceAll("OS", "") ;
				
				Query q2 = em.createQuery("select o from Ose o where o.id=:c and o.grupo=3", Ose.class);
				q2.setParameter("c", Integer.parseInt(codOs));
				
				if(q2.getResultList().size() > 0){
					System.out.println(((Ose)q2.getSingleResult()).getGrupo());
					valor = valor+Real.formatStringToDBDouble(boleto.getValor_pagamento());
					qtd++;
				}				
		}
		
		System.out.println("Quantidade de boletos: "+qtd);
		System.out.println("Soma de valor recebido: "+Real.formatDbToString(String.valueOf(valor)));
	}
}
