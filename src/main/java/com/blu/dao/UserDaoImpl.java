package com.blu.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	public boolean isEventUser(String userName) throws Exception {
		String sql = "SELECT 1 FROM SYSTEM_EVENT_LOGS WHERE EVENT_USER_NAME = ?";
		PreparedStatement stmt = dataSource.getConnectionInstance().prepareStatement(sql);
		stmt.setString(1, userName);
		ResultSet result = stmt.executeQuery();

		return result.next();
	}

}
