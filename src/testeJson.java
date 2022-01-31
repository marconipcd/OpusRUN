import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;


public class testeJson {

	public static void main(String[] args) {

		try{
//			String j = "{\"code\":200,\"data\":{\"total\":7490,\"pdf\":{\"charge\":\"https://download.gerencianet.com.br/177311_6001_CORCA6/177311-239568313-LOCA3.pdf\"},\"charge_id\":234736996,\"link\":\"https://visualizacao.gerencianet.com.br/emissao/177311_6001_CORCA6/A4XB-177311-239568313-LOCA3\",\"expire_at\":\"2021-06-10\",\"payment\":\"banking_billet\",\"barcode\":\"00190.00009 02834.429025 39568.313173 3 86470000007490\",\"status\":\"waiting\"}}";
//	        
//			 
//	        org.json.JSONObject json = new org.json.JSONObject(j);		                
//	        org.json.JSONObject jsonData = new org.json.JSONObject(json.get("data").toString());
//	        
//	       
//	        
//	        String link = jsonData.get("link").toString();
//	        System.out.println(link);
			
			detalhesTransacao("237829082");
        
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	
	public static boolean detalhesTransacao(String transacao) {
		
		if(transacao != null && !transacao.equals("")){
		
				try{
					
		            String url = "http://172.17.0.13/boleto/detalhesTransacao.php";

		            HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();

		            //add reuqest header
		            httpClient.setRequestMethod("POST");
		            httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
		            httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		            
		            String urlParameters = "cod="+Integer.parseInt(transacao);
		            		
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

		                if(response.toString() != null && !response.toString().equals("")){
		                	JSONObject json = new JSONObject(response.toString());
		                	
		                	String vencimento = json.getJSONObject("data").getJSONObject("payment").getJSONObject("banking_billet").get("expire_at").toString();
		                	String codigo_barras = json.getJSONObject("data").getJSONObject("payment").getJSONObject("banking_billet").get("barcode").toString();
		                	String criado = json.getJSONObject("data").getJSONObject("payment").get("created_at").toString();
		                	String status = json.getJSONObject("data").get("status").toString();
		                			                	
		                	JSONArray arrayHistory = json.getJSONObject("data").getJSONArray("history");
		                	
		                	System.out.println("Vencimento:"+vencimento);
		                	System.out.println("Código Barras:"+codigo_barras);
		                	System.out.println("Criado:"+criado);
		                	System.out.println("Status:"+status);
		                	
		                	for(int i=0; i<arrayHistory.length(); i++){
		                		String msg = arrayHistory.getJSONObject(i).get("message").toString();
		                		String data = arrayHistory.getJSONObject(i).get("created_at").toString();
		                		
		                		System.out.println("Mensagem:"+msg+" - "+"data:"+data);
		                	}		                		
		                }               
		            }
					
					return true;
					
					}catch(Exception e){
						e.printStackTrace();
						return false;
					}
		}
		
		return false;
    }

}
