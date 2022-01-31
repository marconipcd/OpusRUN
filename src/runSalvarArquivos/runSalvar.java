package runSalvarArquivos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import util.ScpUtil;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import domain.ArquivosContrato;
import domain.ArquivosContrato2;

public class runSalvar {

	public static void main(String[] args) {
		
		try{
		
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
					
	    	Query q = em.createQuery("select a from ArquivosContrato a order by a.id", ArquivosContrato.class);
	    	q.setFirstResult(900);
	    	q.setMaxResults(100);
	    	
	    	//q.setParameter("contrato", 11902);
	    	
	    	List<ArquivosContrato> result = q.getResultList();
	    	
	    	   String hostname = "192.168.21.13";  
			   String username = "root";  
			   String password = "managerdigi_SB@18";  
			             
			   Connection conn = new Connection(hostname);  
			   conn.connect();  
			   if (conn.authenticateWithPassword(username, password)){		      		      
			       for (ArquivosContrato arquivo: result) {
			    	   Session sess = conn.openSession();
					   sess.execCommand("mkdir /dados/ARQUIVOS/CONTRATOS/"+arquivo.getContrato().toString());
					   sess.close();
			       }
			       conn.close();	       
			   }
	    	
			em.getTransaction().begin();
	    	for (ArquivosContrato arquivo: result) {
	    		
	    		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyy");
	 		    String nomeNovo = "TERMO_ADESAO_"+arquivo.getContrato()+".pdf";
	    		
	    		writeFile((byte[]) arquivo.getFile(), arquivo.getNome(),arquivo.getContrato().toString(), nomeNovo);
	    		
	    		ArquivosContrato2 arquivo2 = new ArquivosContrato2(null, arquivo.getContrato(), nomeNovo, arquivo.getData());
	    		arquivo2.setUsuario(arquivo.getUsuario());
	    		
	    		em.persist(arquivo2); 
			}
	    	em.getTransaction().commit();
    	
		}catch(Exception e){
			e.printStackTrace();
		}
    	
	}
	
	public static void writeFile(byte[] data, String nome, String contrato, String nomeNovo) throws IOException
	 {		
		
			if(!new File("E:\\CONTRATOS\\"+contrato).exists()){
				new File("E:\\CONTRATOS\\"+contrato).mkdir();
			}
		
		   File file = new File("E:\\CONTRATOS\\"+contrato+"\\"+nome);
		
		   OutputStream fo = new FileOutputStream(file);
		   fo.write(data);
		   fo.flush();
		   fo.close();
		   
		   //Trasferir arquivo para pasta no samba
		   String destino = "/dados/ARQUIVOS/CONTRATOS/"+contrato+"/"+nomeNovo;
		   ScpUtil.transferir("root", "192.168.21.13", "managerdigi_SB@18", file.getAbsolutePath(), destino);
		
	 }
}
