import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import domain.ContasReceber;


public class runTeste3 {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	static EntityManager em = emf.createEntityManager();
	

	public static void main(String[] args) {

		JSONArray jsonObject;
        //Cria o parse de tratamento
        JSONParser parser = new JSONParser();
        //Variaveis que irao armazenar os dados do arquivo JSON
        String ID;
        String N_NUMERO_SICRED;
       
        try {
            //Salva no objeto JSONObject o que o parse tratou do arquivo
            jsonObject = (JSONArray) parser.parse(new FileReader(new File("D:\\alteracoes_conta_Receber.json")));
             
            //Salva nas variaveis os dados retirados do arquivo
            //ID = (String) jsonObject.get("ID");
           // N_NUMERO_SICRED = (String) jsonObject.get("N_NUMERO_SICRED");
          
            em.getTransaction().begin();
            for (Object object : jsonObject) {
            	
            	JSONObject o = (JSONObject)object;
            	String nNumeroAntigo = (String)o.get("N_NUMERO_SICRED");
            	Integer id = Integer.parseInt(String.valueOf(o.get("ID")));
            	ContasReceber c = em.find(ContasReceber.class, id);
            	if(c != null){
            		c.setN_numero_sicred_antigo(nNumeroAntigo); 
            	}
        		
            	System.out.println(nNumeroAntigo);
			}
            em.getTransaction().commit(); 
 
         
        } 
        //Trata as exceptions que podem ser lançadas no decorrer do processo
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}
	
	
}
