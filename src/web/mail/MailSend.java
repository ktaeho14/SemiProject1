package web.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import join.dao.AniDao;

public class MailSend {
		
	public void MailSend(String mailT, String coment, String mymail) {
		Properties props = System.getProperties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

		Authenticator auth = new MailAuth();
		
		Session session = Session.getDefaultInstance(props, auth);
		
		MimeMessage msg = new MimeMessage(session);
		
			try {
				msg.setSentDate(new Date());
				
				msg.setFrom(new InternetAddress("mysendingemail82@gmail.com", "찾아주개냥"));
	            InternetAddress to = new InternetAddress(mymail);         
	            msg.setRecipient(Message.RecipientType.TO, to);            
	            msg.setSubject(mailT, "UTF-8");            
	            msg.setText(coment, "UTF-8"); 

	            Transport.send(msg);
				
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
		
		
		
	}
	
	
	
	
}
