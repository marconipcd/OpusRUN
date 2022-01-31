import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.net.PrintCommandListener;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;


public class testFTP {
	
	
FTPClient ftp = null;
	
	public testFTP(String host, String user, String pwd) throws Exception{
		ftp = new FTPClient();
		ftp.addProtocolCommandListener(new PrintCommandListener(new PrintWriter(System.out)));
		int reply;
		ftp.connect(host);
		reply = ftp.getReplyCode();
		if (!FTPReply.isPositiveCompletion(reply)) {
			ftp.disconnect();
			throw new Exception("Exception in connecting to FTP Server");
		}
		ftp.login(user, pwd);
		ftp.setFileType(FTP.BINARY_FILE_TYPE);
		ftp.enterLocalPassiveMode();
	}
	public void uploadFile(String localFileFullName, String fileName, String hostDir)
			throws Exception {
		try(InputStream input = new FileInputStream(new File(localFileFullName))){
		this.ftp.storeFile(hostDir + fileName, input);
		}
	}

	public void disconnect(){
		if (this.ftp.isConnected()) {
			try {
				this.ftp.logout();
				this.ftp.disconnect();
			} catch (Exception f) {
				// do nothing as file is already saved to server
			}
		}
	}
	
	

	public static void main(String[] args) {
		
		try{
			testFTP ftpUploader = new testFTP("ftp.digitalonline.com.br", "digitalonline", "idigital123mc");
			
			ftpUploader.uploadFile("E:\\quadrado_vazio.png", "quadrado_vazio.png", "/www/fotos_produtos/");
			ftpUploader.disconnect();
			System.out.println("Done");
		
		}catch(Exception e){
			e.printStackTrace();
		}

	}

}
