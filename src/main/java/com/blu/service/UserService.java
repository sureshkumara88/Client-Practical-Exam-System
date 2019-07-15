package com.blu.service;

import org.springframework.stereotype.Service;

import com.blu.bean.User;

@Service
public interface UserService {

	public User generateHashingForUsername(User user) throws Exception;
	
	public User validateUser(User user) throws Exception;
}
