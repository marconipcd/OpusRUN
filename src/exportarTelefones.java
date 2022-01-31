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


public class exportarTelefones {

	
public static void main(String[] args){
		
		
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
		
			Query q = em.createNativeQuery("SELECT * FROM clientes c WHERE EXISTS(SELECT * "
					+ "FROM acesso_cliente a WHERE  a.CLIENTES_ID =  c.ID AND a.PLANOS_ACESSO_ID = 295 AND "
					+ "a.STATUS_2 NOT LIKE 'ENCERRADO')", Cliente.class);
			
			
			
			List<Cliente> contratosEncerrado = q.getResultList();
			
			StringBuilder sb = new StringBuilder();		
			sb.append(new String("Telefone"));
			sb.append(System.getProperty("line.separator"));

			
			
			File f = new File("F:\\telefones_200FibraTop.csv");
			BufferedWriter br = new BufferedWriter(new FileWriter(f));  
			 					
				if(f.canWrite()){
				
				for (Cliente c : contratosEncerrado) {
					
					String telefone = "";
					if(c.getTelefone1() != null && !c.getTelefone1().equals("") && c.getTelefone1().substring(0, 4) != "3726"){
											
						String t = "";
						if(c.getTelefone1().length() == 8){
							t = "9"+c.getTelefone1();
						}else{
							t = c.getTelefone1();
						}
						
						telefone = "55"+c.getDdd_fone1()+""+t;						
						
					}else{
						if(c.getTelefone2() != null && !c.getTelefone2().equals("") && c.getTelefone2().substring(0, 4) != "3726"){
							//87573101
							String t = "";
							if(c.getTelefone2().length() == 8){
								t = "9"+c.getTelefone2();
							}else{
								t = c.getTelefone2();
							}
							telefone = "55"+c.getDdd_fone2()+""+t;
						}else{
							if(c.getCelular1() != null && !c.getCelular1().equals("") && c.getCelular1().substring(0, 4) != "3726"){
								//87573101
								String t = "";
								if(c.getCelular1().length() == 8){
									t = "9"+c.getCelular1();
								}else{
									t = c.getCelular1();
								}
								telefone = "55"+c.getDdd_cel1()+""+t;
							}else{
								if(c.getCelular2() != null && !c.getCelular2().equals("") && c.getCelular2().substring(0, 4) != "3726"){
									//87573101
									String t = "";
									if(c.getCelular2().length() == 8){
										t = "9"+c.getCelular2();
									}else{
										t = c.getCelular2();
									}
									telefone = "55"+c.getDdd_cel2()+""+t;
								}
							}
						}
					}
						
					if(!telefone.equals("")){
						
						sb.append(telefone);
						sb.append(System.getProperty("line.separator"));
					}
							
				}
				
				br.write(sb.toString());  
				br.close();		
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	// 
}
