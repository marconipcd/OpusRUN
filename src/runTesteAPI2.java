import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;



public class runTesteAPI2 {

	
	public static void main(String[] args) {

			try {
	
			URL url = new URL("http://179.127.32.7/testeAPI.php");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			//conn.setRequestProperty("Content-Type", "application/json");
			
			Map<String, String> parameters = new HashMap<>();
			parameters.put("funcao", "cadastrarProduto");
			parameters.put("id_externo", "1928374");
			parameters.put("nome", "Produto de Teste Via Web Service");
			parameters.put("descricao_completa", "Via Web Service");
			parameters.put("ativo", "true");
			parameters.put("destaque", "false");
	
			
			 
	
			conn.setDoOutput(true);
			DataOutputStream out = new DataOutputStream(conn.getOutputStream());
			out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
			out.flush();
			out.close();
	
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new RuntimeException("Erro ao acessar o webservice : " + conn.getResponseMessage());
			}
	
			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
	
			String retorno;
			while ((retorno = br.readLine()) != null) {
			System.out.println(retorno);
			}
	
			conn.disconnect();
	
			} catch (Exception e) {
	
			e.printStackTrace();
	
			}
		}
		
}
