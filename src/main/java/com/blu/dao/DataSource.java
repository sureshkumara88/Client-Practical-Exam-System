package com.blu.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.blu.properties.DataSourceProperties;

@Repository
public class DataSource {

	@Autowired
	private DataSourceProperties properties;
		
	private Connection connection; 
	
	public Connection getConnectionInstance() throws Exception {
		if (connection == null) {		
			Class.forName(properties.getDriverClass());
			connection = DriverManager.getConnection(properties.getUrl(), properties.getUsername(),properties.getPassword());
			connection.setSchema(properties.getSchema());
		}
		
		return connection;		
	}
	
}
