package scripts;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.joda.time.DateTime;
import org.joda.time.Minutes;
import org.json.JSONException;

import util.Real;
import dao.ContasReceberDAO;
import domain.ContasReceber;
import domain.Pix;

public class runPixConfirm {

	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
	public static void main(String[] args) {
		
		
		try{
			baixarPix();
			cancelarPixExpirado();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static  void cancelarPixExpirado(){
		EntityManager em = emf.createEntityManager();		
		Query qPix = em.createQuery("select p from Pix p WHERE p.status like 'PENDENTE'", Pix.class);
		List<Pix> pixs = qPix.getResultList();
		
		em.getTransaction().begin();
		for (Pix pix : pixs) {
			DateTime dt1 = new DateTime();
			DateTime dt2 = new DateTime(pix.getData());			
			int minutos = Minutes.minutesBetween(dt1, dt2).getMinutes();
			
			
			System.out.println("Hora gerado: "+pix.getData());
			System.out.println(minutos);
			
			if(minutos < -59){
				pix.setStatus("EXPIRADO");
				em.merge(pix);
			}
		}
		em.getTransaction().commit();	
	}
	
	private static void baixarPix() throws MalformedURLException, IOException, JSONException{
		EntityManager em = emf.createEntityManager();		
		Query qPix = em.createQuery("select p from Pix p where p.status like 'PENDENTE'", Pix.class);
		
		List<Pix> pixs = qPix.getResultList();
		
		em.getTransaction().begin();
		for (Pix pix : pixs) {
			
            HttpURLConnection httpClient = (HttpURLConnection) new URL("http://172.17.0.71/examples/pix/charge/detail.php").openConnection();
            httpClient.setRequestMethod("POST");
            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                                    
            String urlParameters = "txid="+pix.getCod_pix()+"&&cod_boleto="+pix.getCod_boleto().toString();
            httpClient.setDoOutput(true);
            try (DataOutputStream wr = new DataOutputStream(httpClient.getOutputStream())) {
                wr.writeBytes(urlParameters);
                wr.flush();
            }

            try (BufferedReader in = new BufferedReader(new InputStreamReader(httpClient.getInputStream()))) {

                String line;
                StringBuilder response = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    response.append(line);
                }

                System.out.println(response.toString());
                
                org.json.JSONObject json = new org.json.JSONObject(response.toString());	
                String status = json.get("status").toString();
                System.out.println(status);
                
                if(status.equals("CONCLUIDA")){
                	
                	pix.setStatus("PAGO");
                	em.merge(pix);
                	
                	ContasReceber boleto = em.find(ContasReceber.class, pix.getCod_boleto());
                	if(boleto != null){                		
                		                		
            			String valorPix = Real.formatDbToString(String.valueOf(pix.getValor_pix())).replace(".", ",");
            		
            			System.out.println(valorPix);
            			
                		ContasReceberDAO.baixarBoletoProcessoCompleto(boleto.getId(), valorPix, valorPix, "", "PIX", "liquidado", new Date(), 
                				new Date(), pix.getValor_pix(),null);
                	}
                }                
            }
			
		}		
		em.getTransaction().commit();
		
	}
}
