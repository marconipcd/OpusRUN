package SUELLEN;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Array;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Cliente;
import domain.ContasReceber;

public class relatorioSaldoDevedorNegativados {

	public static void main(String[] args) {
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
		
			Query q = em.createNativeQuery("SELECT DISTINCT(c.ID), c.NOME_RAZAO FROM contas_receber b, clientes c WHERE "
					+ "c.ID =b.CLIENTES_ID AND  b.STATUS_2 LIKE 'NEGATIVADO' AND b.DATA_VENCIMENTO <= '2016-12-31'");
			
			List<Object> clientes = q.getResultList();
			
			StringBuilder sb = new StringBuilder();		
			sb.append(new String("Nome,Saldo"));
			sb.append(System.getProperty("line.separator"));
			
			File f = new File("F:\\clientesNegativados.csv");
			BufferedWriter br = new BufferedWriter(new FileWriter(f));  
			 					
			if(f.canWrite()){
				
				for (Object c : clientes) {	
					
					Query q2 = em.createNativeQuery("SELECT SUM(b.VALOR_TITULO) FROM contas_receber b where  b.STATUS_2 LIKE 'NEGATIVADO' "
							+ "AND b.DATA_VENCIMENTO <= '2016-12-31' AND b.CLIENTES_ID =:cli");
					q2.setParameter("cli", Integer.parseInt(((Object[])c)[0].toString()));
					
					sb.append(new String(((Object[])c)[1].toString()+","+q2.getSingleResult().toString()));					
					sb.append(System.getProperty("line.separator"));							
				}
				
				br.write(sb.toString());  
				br.close();		
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
