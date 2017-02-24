package net.xinqushi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.xinqushi.common.DBLib;
import net.xinqushi.pojo.User;

public class UserDAO {
	Connection conn;
	PreparedStatement ps;
	String sql;

	// ���캯���������ݿ�
	public UserDAO() {
		conn = DBLib.getConnection();
	}

	// ��
	public void addUser(User user) {
		sql = "insert into users (userName,pwd) values(?,?);";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
	}

	private void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// ɾ
	public void deleteUser(User user) {
		sql = "delete from users where userName=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
	}

	// ��
	public void updateUser(User user) {
		sql = "update users set userName=?, pwd=? where id=?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			ps.setInt(3, user.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
	}

	// ������
	public ArrayList<User> getUsers() {
		sql = "select * from users;";
		ArrayList<User> userList = new ArrayList<User>();

		try {
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPwd(rs.getString(3));
				userList.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return userList;
	}

	// �鵥��
	public User getUserById(int id) {
		sql = "select * from users where id=?;";
		User user = new User();

		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setId(rs.getInt(1));
			user.setUserName(rs.getString(2));
			user.setPwd(rs.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return user;
	}

	// ��֤��¼
	public boolean checkLogin(User user) {
		boolean flag = false;
		sql = "select * from users where userName=? and pwd=?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPwd());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return flag;
	}

	// ��֤�ظ�
	public boolean checkExists(User user) {
		boolean flag = false;
		sql = "select * from users where userName=?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				flag = true;
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return flag;
	}
}
