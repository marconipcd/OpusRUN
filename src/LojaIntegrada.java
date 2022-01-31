import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.MediaType;


public class LojaIntegrada {

	public static void main(String[] args) {
		
		try{
		
//			Client client = ClientBuilder.newClient();
//			Entity payload = Entity.json("{  \"id_externo\": null,  \"sku\": \"prod-simples\",  \"mpn\": null,  \"ncm\": null,  \"nome\": \"Produto 666 Master Plus\",  \"descricao_completa\": \"&#60;strong&#62;Desctição HTML do produto&#60;/strong&#62;\",  \"ativo\": false,  \"destaque\": false,  \"peso\": 0.45,  \"altura\": 2,  \"largura\": 12,  \"profundidade\": 6,  \"tipo\": \"normal\",  \"usado\": false,  \"categorias\": [    \"/api/v1/categoria/1492616/\"  ],  \"marca\": null,  \"removido\": false}");
//			Response response = client.target("https://api.awsli.com.br/v1/produto/?chave_api=5848c34f4a1d754859bc&chave_aplicacao=f4d58eb1-acd7-4cb0-abd9-201ecc3b57e5")
//					
//			  .request(MediaType.APPLICATION_JSON_TYPE)
//			  .post(payload);
//	
//			System.out.println("status: " + response.getStatus());
//			System.out.println("headers: " + response.getHeaders());
//			System.out.println("body:" + response.readEntity(String.class));
			//----------------------------
			
			
//			String url = "https://api.awsli.com.br/v1/produto";
//			 
//		    URL obj = new URL(url);
//		    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
//		 
//		    conn.setRequestProperty("Content-Type", "application/json");
//		    conn.setDoOutput(true);			 
//		    conn.setRequestMethod("PUT");
//		    conn.setRequestProperty ("chave_api", "5848c34f4a1d754859bc");
//		    conn.setRequestProperty ("chave_aplicacao", "f4d58eb1-acd7-4cb0-abd9-201ecc3b57e5");
//		    
//		    String data =  "{  \"id_externo\": null,  \"sku\": \"prod-simples\",  \"mpn\": null,  \"ncm\": null,  \"nome\": \"Produto 666 Master Plus\",  \"descricao_completa\": \"&#60;strong&#62;Desctição HTML do produto&#60;/strong&#62;\",  \"ativo\": false,  \"destaque\": false,  \"peso\": 0.45,  \"altura\": 2,  \"largura\": 12,  \"profundidade\": 6,  \"tipo\": \"normal\",  \"usado\": false,  \"categorias\": [    \"/api/v1/categoria/1492616/\"  ],  \"marca\": null,  \"removido\": false}";			    
//		    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
//		    out.write(data);
//		    out.close();
//		 
//		    new InputStreamReader(conn.getInputStream());   
//		    
//		    BufferedReader in = new BufferedReader(
//					  new InputStreamReader(conn.getInputStream()));
//					String inputLine;
//					StringBuffer content = new StringBuffer();
//					while ((inputLine = in.readLine()) != null) {
//					    content.append(inputLine);
//					}
//					in.close();
//					
//					
//					System.out.println(content.toString());
			
			
			Client client = ClientBuilder.newClient();
			Entity payload = Entity.json("{  \"id_externo\": null,  \"sku\": \"prod-simples\",  \"mpn\": null,  \"ncm\": null,  \"nome\": \"Produto 666 Master Plus\",  \"descricao_completa\": \"&#60;strong&#62;Desctição HTML do produto&#60;/strong&#62;\",  \"ativo\": false,  \"destaque\": false,  \"peso\": 0.45,  \"altura\": 2,  \"largura\": 12,  \"profundidade\": 6,  \"tipo\": \"normal\",  \"usado\": false,  \"categorias\": [    \"/api/v1/categoria/1492616/\"  ],  \"marca\": null,  \"removido\": false}");
			Response response = client.target("https://api.awsli.com.br/v1/produto")
			  .request(MediaType.APPLICATION_JSON_TYPE)
			  .post(payload);

			System.out.println("status: " + response.getStatus());
			System.out.println("headers: " + response.getHeaders());
			System.out.println("body:" + response.readEntity(String.class));
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
