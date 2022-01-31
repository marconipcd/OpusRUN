import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import domain.Cliente;


public class gerarCSV {

	public static void main(String[] args) {
		try{
			System.out.println(new Date());
	
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");		
			EntityManager em = emf.createEntityManager();		
			
			Query q = em.createNativeQuery("SELECT c.NOME_RAZAO, c.CELULAR1  FROM acesso_cliente ac, clientes c, planos_acesso p "
					+ "WHERE ac.CLIENTES_ID = c.ID  "
					+ "AND ac.STATUS_2 NOT LIKE 'ENCERRADO' AND p.ID = ac.PLANOS_ACESSO_ID and p.NOME LIKE '%100M FIBRA UP%' ");
			
			//200M GAMER FIBRA TOP
			//200M COMERCIO
			
			List<Cliente> clientes = q.getResultList();
			
			
			StringBuilder sb = new StringBuilder();		
			sb.append(new String("Name,Given Name,Additional Name,Family Name,Yomi Name,Given Name Yomi,Additional Name Yomi,Family Name Yomi,Name Prefix,Name Suffix,Initials,Nickname,Short Name,Maiden Name,Birthday,Gender,Location,Billing Information,Directory Server,Mileage,Occupation,Hobby,Sensitivity,Priority,Subject,Notes,Group Membership,E-mail 1 - Type,E-mail 1 - Value,E-mail 2 - Type,E-mail 2 - Value,Phone 1 - Type,Phone 1 - Value,Phone 2 - Type,Phone 2 - Value,Phone 3 - Type,Phone 3 - Value,Website 1 - Type,Website 1 - Value"));
			
			for (Cliente c : clientes) {
				sb.append(new String(""+c.getNome_razao()+","+c.getContato()+","+c.getEmail()+",,,,,,,,,,"));
			}
			
			System.out.println(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
