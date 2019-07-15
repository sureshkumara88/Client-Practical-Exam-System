package com.blu.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

	public boolean isEventUser(String userName) throws Exception;
	
}
