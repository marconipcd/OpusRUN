import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class testeMediaGeral {

	public static void main(String[] args){
		//353 - POS-VENDA INSTALACAO
		//354 - POS-VENDA REPARO
		
		gerarMediaGeralMensal();
		
	}
	
	public static void gerarMediaGeralMensal(){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
		EntityManager em = emf.createEntityManager();
				
    	Query q = em.createNativeQuery("SELECT DISTINCT(r.COD_PERGUNTA) FROM crm_perguntas_rel r, crm c WHERE c.ID = r.COD_CRM ");    	
    	    	
    	List<Integer> perguntas = q.getResultList(); 
    	
    	double soma_medias = 0, media_geral = 0;
    	int qtdMedias = 0;
    	
    	for (Integer pergunta: perguntas) {
    		
    		if(pergunta == 19 || pergunta ==25 || pergunta == 27){
    			
	    	
			    		
			    		double qtdRegular,qtdBom,qtdExcelente	= 0;
			    		
			    		Query qRegular = em.createNativeQuery("SELECT r.RESPOSTA FROM crm_perguntas_rel r, crm c WHERE r.COD_PERGUNTA =:p AND c.ID = r.COD_CRM  "
			        			+ "AND r.RESPOSTA LIKE '03-Regular' ");
			    		qRegular.setParameter("p", pergunta);		    		
			    		qtdRegular = qRegular.getResultList().size();
			    		
			    		Query qBom = em.createNativeQuery("SELECT r.RESPOSTA FROM crm_perguntas_rel r, crm c WHERE r.COD_PERGUNTA =:p AND c.ID = r.COD_CRM  "
			        			+ "AND r.RESPOSTA LIKE '04-Bom' ");
			    		qBom.setParameter("p", pergunta);		    
			    		qtdBom = qBom.getResultList().size();
			    		
			    		Query qExcelente = em.createNativeQuery("SELECT r.RESPOSTA FROM crm_perguntas_rel r, crm c WHERE r.COD_PERGUNTA =:p AND c.ID = r.COD_CRM  "
			        			+ "AND r.RESPOSTA LIKE '05-Excelente' ");
			    		qExcelente.setParameter("p", pergunta);		    	
			    		qtdExcelente = qExcelente.getResultList().size();
	
			    		double pontos_regular, pontos_bom, pontos_excelente, total_pontos, total_pessoas = 0;
			    		double media = 0;
			    		
			    		pontos_regular = qtdRegular * 3;
			    		pontos_bom = qtdBom * 4;
			    		pontos_excelente = qtdExcelente * 5;
			    		total_pontos = pontos_regular+pontos_bom+pontos_excelente;
			    		total_pessoas = qtdRegular+qtdBom+qtdExcelente;
			    		media = total_pontos / total_pessoas;
			    		//System.out.println("MEDIA: "+media);
			    		
			    		soma_medias = soma_medias + media;
			    		qtdMedias++;
	    		
	    		
			}
	    	
    	
    	}
    	System.out.println("Média Geral: "+(soma_medias/qtdMedias));
	}
}
