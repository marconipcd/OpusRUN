import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class socketServer {

	
	public static void main(String args[]){
		
		try{
			ServerSocket ss= new ServerSocket(776);
			Socket s = ss.accept();
			DataInputStream dis = new DataInputStream(s.getInputStream());
			String str = (String)dis.readUTF();
			System.out.println(str);
			ss.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
