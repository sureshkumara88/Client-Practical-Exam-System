package com.blu.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.stereotype.Service;

import com.blu.bean.User;

@Service
public interface MailService {

	public void sendMail(User user) throws AddressException, MessagingException;
}
