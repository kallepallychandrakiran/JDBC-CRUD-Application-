package com.ck.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JdbcUtil {

	
	private JdbcUtil() {}
	
	
	static {
		//loading and registering the driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getJdbcconnection() throws SQLException, IOException{
		HikariConfig config = new HikariConfig("properties\\application.properties");
		try (HikariDataSource dataSource = new HikariDataSource(config)) {
			return dataSource.getConnection();
		}
		
	}
}
