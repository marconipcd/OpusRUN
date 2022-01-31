import java.io.*;
import okhttp3.*;
import org.json.simple.JSONObject;


public class contelege {
	
	public static void main(String[] args) throws IOException {
		
				  JSONObject obj = new JSONObject();
		
			      obj.put("name", "CLIENTE DE TESTE J");		     
			
		
				OkHttpClient client = new OkHttpClient().newBuilder()
			      .build();
			    MediaType mediaType = MediaType.parse("application/json");
			    //RequestBody body = RequestBody.create(mediaType, "{\r\n    \"name\":\"teste\"    \r\n}");
			    RequestBody body = RequestBody.create(mediaType, obj.toJSONString());
			    Request request = new Request.Builder()
			      .url("https://integration.contelege.com.br/v2/pois")
			      .method("POST", body)
			      .addHeader("x-api-key", "CzPZPqyk795PJ7JmesjYF3qGFPWUQDEO1Ml2ov6o")
			      .addHeader("Authorization", "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJjbGllbnRJZCI6IjczNjZiMDdmLWViYjAtNDZlZS04ZmE2LWNjZmYwMGQ5Y2M2MyIsImNyZWF0ZWRBdCI6IjIwMjEtMDYtMDlUMTE6MTg6NDIuMjcyWiIsImlkIjoiMjg5MmVjMDYtYTQ0ZC00ZDM3LWExZTktMDI3MjM0ZTlmNGE2IiwiaXNzIjoiY29udGVsZS1nZS1hcGkiLCJ1c2VySWQiOiIxOTEzZmFjYS1mNTlhLTRmNDctYmNmMi03NTJmNTJkN2M0YjQiLCJ1c2VyVHlwZSI6ImFkbWluIiwiaWF0IjoxNjIzMjM3NTIyfQ.kYfqWKS8zUGDZQZXscE3OQuFU7fvkB7JWAENwDaNXpc")
			      .addHeader("Content-Type", "application/json")
			      .build();
			    
			    Response response = client.newCall(request).execute();
			    System.out.println(response.body().string());
		
	}

}
