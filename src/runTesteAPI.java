
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;


public class runTesteAPI {

	
	public static void main(String[] args){
		
//		try{
//			Client client = ClientBuilder.newClient();
//			Entity payload = Entity.json("{  \"name\": \"Contato de Teste\",  \"email\": \"teste@digitalonline.com.br\",  \"phone\": 551170707070}");
//			Response response = client.target("https://api.huggy.io/v2/contacts")
//			  .request(MediaType.APPLICATION_JSON_TYPE)
//			  .header("Authorization", "230467d1707df0977be7d643c9235543")
//			  //.header("Authorization", "Bearer xxxxxxxxxx")//230467d1707df0977be7d643c9235543
//			  .post(payload);
//	
//			System.out.println("status: " + response.getStatus());
//			System.out.println("headers: " + response.getHeaders());
//			System.out.println("body:" + response.readEntity(String.class));
//			
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		
		
		try{
			URL url = new URL("https://api.huggy.io/v2/contacts");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("Content-Type", "application/json");
			con.setRequestProperty("Authorization", "230467d1707df0977be7d643c9235543");
			
			Map<String, String> parameters = new HashMap<>();
			parameters.put("name", "Cliente de Teste");
			parameters.put("email", "teste@digitalonline.com.br");
			parameters.put("phone", "551170707070");
			 
			con.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(con.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();
			
			
			BufferedReader in = new BufferedReader(
			  new InputStreamReader(con.getInputStream()));
			String inputLine;
			StringBuffer content = new StringBuffer();
			while ((inputLine = in.readLine()) != null) {
			    content.append(inputLine);
			}
			in.close();
			
			
			System.out.println(content.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		 try {
			 
			    String url = "https://api.huggy.io/v2/contactso";
			 
			    URL obj = new URL(url);
			    HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
			 
			    conn.setRequestProperty("Content-Type", "application/json");
			    conn.setDoOutput(true);
			 
			    conn.setRequestMethod("PUT");
			 
			    //String userpass = "user" + ":" + "pass";
			    //String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes("UTF-8"));
			    conn.setRequestProperty ("Authorization", "230467d1707df0977be7d643c9235543");
			 
			    String data =  "{  \"name\": \"Contato de Teste\",  \"email\": \"teste@digitalonline.com.br\",  \"phone\": 551170707070}";			    
			    OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
			    out.write(data);
			    out.close();
			 
			    new InputStreamReader(conn.getInputStream());   
			    
			    BufferedReader in = new BufferedReader(
						  new InputStreamReader(conn.getInputStream()));
						String inputLine;
						StringBuffer content = new StringBuffer();
						while ((inputLine = in.readLine()) != null) {
						    content.append(inputLine);
						}
						in.close();
						
						
						System.out.println(content.toString());
			 
	    } catch (Exception e) {
	    	e.printStackTrace();
	    }
		
		
	}
	
	
}
