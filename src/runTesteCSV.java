import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import dao.ContratosAcessoDAO;
import domain.AcessoCliente;
import domain.Cliente;


public class runTesteCSV {

	
	
	public static void main(String[] args) {
		try{
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
		
			Query q = em.createQuery("select c from Cliente c where c.empresa =:e and c.endereco_principal.cidade "
					+ "LIKE 'BELO JARDIM'", Cliente.class);
			q.setParameter("e", 1);
			
			List<Cliente> clientes = q.getResultList();
			
			StringBuilder sb = new StringBuilder();		
			sb.append(new String("Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby,Sensitivity,Priority,Subject,Notes,Group Membership,E-mail 1 - Type,E-mail 1 - Value,E-mail 2 - Type,E-mail 2 - Value,Phone 1 - Type,Phone 1 - Value,Phone 2 - Type,Phone 2 - Value,Phone 3 - Type,Phone 3 - Value,Website 1 - Type,Website 1 - Value"));
			sb.append(System.getProperty("line.separator"));
			//System.out.println(new String("Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby,Sensitivity,Priority,Subject,Notes,Group Membership,E-mail 1 - Type,E-mail 1 - Value,E-mail 2 - Type,E-mail 2 - Value,Phone 1 - Type,Phone 1 - Value,Phone 2 - Type,Phone 2 - Value,Phone 3 - Type,Phone 3 - Value,Website 1 - Type,Website 1 - Value"));
			
			
			File f = new File("D:\\contatos_CABO.csv");
			BufferedWriter br = new BufferedWriter(new FileWriter(f));  
			 					
				if(f.canWrite()){
				
				for (Cliente c : clientes) {
					
					//AcessoCliente contrato = ContratosAcessoDAO.buscarContratoPorCliente(c);
					Query q2 = em.createQuery("select a from AcessoCliente a where a.cliente=:cliente and a.status_2='ATIVO' and a.plano.nome like '%CABO%'", AcessoCliente.class);
					q2.setParameter("cliente", c);
					
					AcessoCliente contrato = null; 
					if(q2.getResultList().size() == 1){
						contrato = (AcessoCliente)q2.getSingleResult();
					}
					
					if(contrato != null){
						if(c.getTelefone1() != null || c.getTelefone2() != null || c.getCelular1() != null || c.getCelular2() != null){
							
							String cel1 = c.getTelefone1() != null ? c.getTelefone1() : "";
							String tipo1 = cel1 != null ? "Celular" : "";
							
							String cel2 = c.getTelefone2() != null ? c.getTelefone2() : "";
							String tipo2 = cel2 != null ? "Celular" : "";
							
							String cel3 = c.getCelular1() != null ? c.getCelular1() : "";
							String tipo3 = cel3 != null ? "Celular" : "";
							
							String cel4 = c.getCelular2() != null ? c.getCelular2() : "";
							String tipo4 = cel4 != null ? "Celular" : "";
													
							
							sb.append(new String(""+c.getNome_razao()+","+c.getContato()+",,,,,,,,,,,,,,,,,,,,,,,,,* DIGITAL,,,,,"+tipo1+","+cel1+","+tipo2+","+cel2+","+tipo3+","+cel3+",,"));					
							sb.append(System.getProperty("line.separator"));
							
						}
					}
				}
				
				br.write(sb.toString());  
				br.close();		
			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
