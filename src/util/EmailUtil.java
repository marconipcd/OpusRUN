package util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.internet.InternetAddress;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.HtmlEmail;

import dao.CobrancaDAO;
import dao.NfeDAO;
import domain.ConfigBoleto;
import domain.ConfigNfe;
import domain.ContasReceber;
import domain.NfeMestre;

public class EmailUtil {

	private static Pattern pattern;
	private static Matcher matcher;
 
	private static final String EMAIL_PATTERN = 
		"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
		+ "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
 
	public EmailUtil() {
	}
 
	public static boolean validate(final String hex) {
		if(hex != null && !hex.isEmpty()){
			pattern = Pattern.compile(EMAIL_PATTERN);
			matcher = pattern.matcher(hex);
			return matcher.matches();
		}
		
		return false;
 
	}
	
	public static boolean Send(ContasReceber cr, String nomeCliente, String endEmail, String pathFull){
		try{
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("OpusBloqueio");
			EntityManager em = emf.createEntityManager();
	
			Query q = em.createQuery("select n from NfeMestre n where n.contas_receber =:boleto", NfeMestre.class);
			q.setParameter("boleto", cr);
			
			if(q.getResultList().size() == 0){
				
				ConfigBoleto valueDefaultNfe = CobrancaDAO.getDefaultValue();	
				
				HtmlEmail email = new HtmlEmail();
				email.setHostName(valueDefaultNfe.getServer_email()); 
				email.setSmtpPort(Integer.parseInt(valueDefaultNfe.getPorta_email()));
				email.setSSLOnConnect(true);
				//email.addTo(endEmail,nomeCliente);
				email.addTo("marconipcd@gmail.com");
				email.setFrom(valueDefaultNfe.getLogin(), "DIGITAL"); 
				email.setSubject("Boleto Serviço de Comunicação");
				email.setCharset("UTF-8");
							
				List<InternetAddress> lIa = new ArrayList<>();
				lIa.add(new InternetAddress(valueDefaultNfe.getEmail_copia_oculta()));
				//lIa.add(new InternetAddress("marconipcd@gmail.com"));
				
				
				email.setCc(lIa);
				
				//EmailAttachment attachment = new EmailAttachment();
				//attachment.setPath(pathFull);
				//attachment.setDisposition(EmailAttachment.ATTACHMENT);			
				//attachment.setDescription("Boleto");
				//attachment.setName("John");
				
				//email.attach(attachment);
				
				email.setMsg(valueDefaultNfe.getMsg_email()); 
				
				email.setAuthentication(valueDefaultNfe.getLogin(),valueDefaultNfe.getSenha());
				
				email.send(); //envia o e-mail
			}else{
				if(q.getResultList().size() == 1){
						ConfigNfe valueDefaultNfe = NfeDAO.getDefaultValue();
						
						HtmlEmail email = new HtmlEmail();
						email.setHostName(valueDefaultNfe.getServer_email()); 
						email.setSmtpPort(Integer.parseInt(valueDefaultNfe.getPorta_email()));
						email.setSSLOnConnect(true);
						//email.addTo(endEmail,nomeCliente);
						email.addTo("marconipcd@gmail.com");
						email.setFrom(valueDefaultNfe.getLogin(), "DIGITAL"); 
						email.setSubject("Nfe Serviço de Comunicação");
						email.setCharset("UTF-8");
									
						List<InternetAddress> lIa = new ArrayList<>();
						lIa.add(new InternetAddress(valueDefaultNfe.getEmail_copia_oculta()));
						//lIa.add(new InternetAddress("marconipcd@gmail.com"));
						
						email.setCc(lIa);
						
						EmailAttachment attachment = new EmailAttachment();
						attachment.setPath(pathFull);
						attachment.setDisposition(EmailAttachment.ATTACHMENT);			
						attachment.setDescription("Boleto");
						//attachment.setName("John");
						
						email.attach(attachment);
						
						email.setMsg(valueDefaultNfe.getMsg_email()); 
						
						email.setAuthentication(valueDefaultNfe.getLogin(),valueDefaultNfe.getSenha());
						
						email.send(); //envia o e-mail
				}
			}
			
			
			
			
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
