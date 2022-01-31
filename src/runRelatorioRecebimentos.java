import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.ContasReceber;


public class runRelatorioRecebimentos {

	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	
	public static void main(String[] args) {
			
			try{
				
				Integer banco = 0;
				Integer cartao_credito = 0;
				Integer cartao_debito = 0;
				Integer deposito = 0;
				Integer dinheiro = 0;
				Integer gerencia_net = 0;
				Integer pagseguro = 0;
			
				EntityManager em = emf.createEntityManager();
				Query q = em.createQuery("select c from ContasReceber c where "
						+ "c.data_pagamento >= '2020-06-01' and "
						+ "c.data_pagamento <= '2020-06-31' and "
						+ "c.status = 'FECHADO'", ContasReceber.class);
				
				for (ContasReceber boleto: (List<ContasReceber>) q.getResultList()) {
					if(boleto.getCliente().getEndereco_principal().getBairro().equals("SERRA DO VENTO")){
						
						if(boleto.getForma_pgto().equals("BANCO")){
							banco++;
						}
						
						if(boleto.getForma_pgto().equals("CARTAO CREDITO")){
							cartao_credito++;
						}
						
						if(boleto.getForma_pgto().equals("CARTAO DEBITO")){
							cartao_debito++;
						}
						
						if(boleto.getForma_pgto().equals("DEPOSITO")){
							deposito++;
						}
						
						if(boleto.getForma_pgto().equals("DINHEIRO")){
							dinheiro++;
						}
						
						if(boleto.getForma_pgto().equals("GERENCIANET")){
							gerencia_net++;
						}
						
						if(boleto.getForma_pgto().equals("PAGSEGURO")){
							pagseguro++;
						}
						
						
					}
					
					
				}
				
				System.out.println("BANCO:"+banco);
				System.out.println("CARTAO CREDITO:"+cartao_credito);
				System.out.println("CARTAO DEBITO:"+cartao_debito);
				System.out.println("DEPOSITO:"+deposito);
				System.out.println("DINHEIRO:"+dinheiro);
				System.out.println("GERENCIANET:"+gerencia_net);
				System.out.println("PAGSEGURO:"+pagseguro);
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
			
	}
}
