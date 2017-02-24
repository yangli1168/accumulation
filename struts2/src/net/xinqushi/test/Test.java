package net.xinqushi.test;

import java.sql.*;

import net.xinqushi.common.DBLib;
import net.xinqushi.pojo.User;

public class Test {
	
	
	public static void main(String[] args) throws SQLException {
		Connection conn = DBLib.getConnection();
		String sql = "select * from users;";
		Statement st = conn.createStatement(); 
		ResultSet rs = st.executeQuery(sql);
		while(rs.next()){
			User user = new User();
			user.setId(rs.getInt(1));
			user.setUserName(rs.getString(2));
			user.setPwd(rs.getString(3));
			System.out.println(user);
		}
	}

}
