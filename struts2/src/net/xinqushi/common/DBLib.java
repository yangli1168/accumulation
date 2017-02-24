package net.xinqushi.common;

import java.sql.*;
/**
 * 数据库连接
 * @author Administrator
 *
 */
public class DBLib {
	public static Connection getConnection(){
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/t_structs2?"
						 + "user=yangli&password=123456&useSSL=false";
			conn = DriverManager.getConnection(url);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
}
