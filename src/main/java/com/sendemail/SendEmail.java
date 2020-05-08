package com.sendemail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	public static void Sendmail(String recepient) throws Exception {
		System.out.println("Preparing to sent mail.....");
		Properties properties = new Properties();
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		properties.put("mail.smtp.port", "465");
		
		 final String usn = "sri.litmus@gmail.com";
		final String password = "Litmus@123";

		// Get the Session object.// and pass username and password
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected javax.mail.PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("sri.litmus@gmail.com", "Litmus@123");
			}
		});
		Message message = prepareMessage(session, usn, recepient);
		Transport.send(message);
		System.out.println("Message sent Successfully");
	}
	private static Message prepareMessage(Session session, String usn, String recepient) {

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usn));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Response Status.....");
			message.setText("Total number of lines: "+"\n");
			return message;
		} catch (Exception ex) {
			Logger.getLogger(SendEmail.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
}