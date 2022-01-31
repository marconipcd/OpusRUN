package dao;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IttvDAO {
	
	public static String atualizarStatus(String cod_customer, String status) throws Exception{
		String url = "http://172.17.0.71/ittv_api/updateCustomer.php";
		HttpURLConnection httpClient = (HttpURLConnection) new URL(url).openConnection();
		
        httpClient.setRequestMethod("POST");
        httpClient.setRequestProperty("User-Agent", "Mozilla/5.0");
        httpClient.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        
        String urlParameters = "idCustomer="+cod_customer+"&"+"status="+status;
        		
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

 
        }
        
        return null;
	}
	

}
