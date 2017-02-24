package net.xinqushi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import net.xinqushi.common.DBLib;
import net.xinqushi.pojo.Picture;

public class PictureDAO {

	Connection conn;
	PreparedStatement ps;
	String sql;

	// 创建构造函数连接
	public PictureDAO() {
		conn = DBLib.getConnection();
	}

	// 增
	public void addPicture(Picture picture) {
		openConn();
		sql = "insert into pictures(uid,name,url) values(?,?,?);";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, picture.getUid());
			ps.setString(2, picture.getName());
			ps.setString(3, picture.getUrl());
			ps.executeUpdate();
		} catch (SQLException e) {
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

	private void openConn() {
		try {
			if (conn.isClosed()) {
				conn = DBLib.getConnection();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 删
	public void deletePicture(int id) {
		openConn();
		sql = "delete from pictures where id=?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
	}

	// 查by all
	public ArrayList<Picture> getPictures(int id, String idType) {
		openConn();
		// 判断所需查询的类型
		String filedName = "";
		if (idType.equals("user")) {
			filedName = "uid";
		} else {
			filedName = "id";
		}
		ArrayList<Picture> pictures = new ArrayList<Picture>();
		sql = "select * from pictures where " + filedName + " =?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Picture picture = new Picture();
				picture.setId(rs.getInt(1));
				picture.setUid(rs.getInt(2));
				picture.setName(rs.getString(3));
				picture.setUrl(rs.getString(4));
				pictures.add(picture);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return pictures;
	}

	// 查数量by id
	public int getPicturesCount(int uid) {
		openConn();
		int num = 0;
		sql = "select count(*) from pictures where uid = ?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				num = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return num;
	}

	// 查url by id
	public String getUrl(int id) {
		openConn();
		String url = "";
		sql = "select url from pictures where id = ?;";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			rs.next();
			url = rs.getString(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeConn();
		}
		return url;
	}

}
