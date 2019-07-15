package com.blu.bean;

import org.springframework.stereotype.Component;

/**
 * This class is act as BO for receiving request from the client
 * @author suresh kumar
 *
 */
@Component
public class User {

	private String user_name;
	
	private String user_mac;
	
	private String status_code;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_mac() {
		return user_mac;
	}

	public void setUser_mac(String user_mac) {
		this.user_mac = user_mac;
	}

	public String getStatus_code() {
		return status_code;
	}

	public void setStatus_code(String status_code) {
		this.status_code = status_code;
	}	
	
	@Override
	public String toString() {
		return "The user_name "+ user_name + " with user_mac " + user_mac + " has successfully validated";
	}
}
