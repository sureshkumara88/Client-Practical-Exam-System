package com.blu.service;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blu.bean.User;
import com.blu.properties.MailProperties;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private MailProperties properties;
	
	private Session mailSession;
	private MimeMessage emailMessage;
	private Properties emailProperties;
			
	@Override
	public void sendMail(User user) throws AddressException, MessagingException{
		setMailServerProperties();
		createEmailMessage(user);
		sendEmail();
	}
	
	private void setMailServerProperties() {
		emailProperties = System.getProperties();
		emailProperties.put("mail.smtp.port", properties.getPort());
		emailProperties.put("mail.smtp.auth", properties.getAuth());
		emailProperties.put("mail.smtp.starttls.enable", properties.getEnable());
	}

	private void createEmailMessage(User user) throws AddressException, MessagingException {
		String[] toEmails = properties.getToAddress().split(",");
		String emailSubject = "Successful response for the user name " + user.getUser_name();
		
		mailSession = Session.getDefaultInstance(emailProperties, null);
		emailMessage = new MimeMessage(mailSession);

		for (int i = 0; i < toEmails.length; i++) {
			emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmails[i]));
		}

		emailMessage.setSubject(emailSubject);
		emailMessage.setContent(user.toString(), "text/html");
	}

	private void sendEmail() throws AddressException, MessagingException {
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(properties.getHost(), properties.getFromAddress(), properties.getPassword());
		transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
		transport.close();		
	}
	
}
