import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Days;

import domain.AcessoCliente;
import domain.Cliente;
import domain.ContasReceber;


public class runFiltroAdemirClientesNovos {

	
	public static void main(String[] args){
		
		
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
		
			Query q = em.createQuery("select c from Cliente c where "
					+ "c.data_cadastro >= '2020-08-29' and c.empresa != 4", Cliente.class);
				
			List<Cliente> clientesNovos = q.getResultList();
			
			StringBuilder sb = new StringBuilder();		
			sb.append(new String("Telefone1: Telefone2: Celular1: Celular2:"));
			sb.append(System.getProperty("line.separator"));
		
			
			File f = new File("F:\\novosCliente290820.csv");
			BufferedWriter br = new BufferedWriter(new FileWriter(f));  
			 					
				if(f.canWrite()){
				
				for (Cliente c : clientesNovos) {
					
					
					if(c.getDdd_fone1() != null && !c.getDdd_fone1().equals("") && c.getTelefone1() != null && !c.getTelefone1().equals("")){
						sb.append(c.getDdd_fone1()+""+c.getTelefone1()+":");					
					}else{
						sb.append(":");
					}
					
					if(c.getDdd_fone2() != null && !c.getDdd_fone2().equals("") && c.getTelefone2() != null && !c.getTelefone2().equals("")){
						sb.append(c.getDdd_fone2()+""+c.getTelefone2()+":");					
					}else{
						sb.append(":");
					}
					
					if(c.getDdd_cel1() != null && !c.getDdd_cel1().equals("") && c.getCelular1() != null && !c.getCelular1().equals("")){
						sb.append(c.getDdd_cel1()+""+c.getCelular1()+":");					
					}else{
						sb.append(":");
					}
					
					if(c.getDdd_cel2() != null && !c.getDdd_cel2().equals("") && c.getCelular2() != null && !c.getCelular2().equals("")){
						sb.append(c.getDdd_cel2()+""+c.getCelular2()+":");
					}else{
						sb.append(":");
					}
					
					
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
