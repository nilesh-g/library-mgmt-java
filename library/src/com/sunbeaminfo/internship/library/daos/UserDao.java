package com.sunbeaminfo.internship.library.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sunbeaminfo.internship.library.entities.User;

public class UserDao {
	private Connection con;
	
	public UserDao(Connection con) {
		this.con = con;
	}
	
	public User findUserByEmail(String email) throws Exception {
		String sql = "SELECT id, name, email, phone, passwd, role FROM members WHERE email=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, email);
			try(ResultSet rs = stmt.executeQuery()) {
				if(rs.next()) {
					String role = rs.getString("role");
					User user = User.newInstance(role);
					user.setId( rs.getInt("id") );
					user.setName( rs.getString("name") );
					user.setEmail( rs.getString("email") );
					user.setPhone( rs.getString("phone") );
					user.setPasswd( rs.getString("passwd") );
					return user;
				}
			}
			return null;
		}
	}
	
	public int insertUser(User user) throws Exception {
		String sql = "INSERT INTO members(name, email, phone, passwd, role) VALUES (?, ?, ?, ?, ?)";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setString(4, user.getPasswd());
			stmt.setString(5, user.getRole());
			return stmt.executeUpdate();
		}
	}
	
	public int updateUser(User user) throws Exception {
		String sql = "UPDATE members SET name=?, email=?, phone=?, passwd=?, role=? WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPhone());
			stmt.setString(4, user.getPasswd());
			stmt.setString(5, user.getRole());
			stmt.setInt(6, user.getId());
			return stmt.executeUpdate();
		}
	}
	
	public int deleteUser(int id) throws Exception {
		String sql = "DELETE FROM members WHERE id=?";
		try(PreparedStatement stmt = con.prepareStatement(sql)) {
			stmt.setInt(1, id);
			return stmt.executeUpdate();
		}
	}
}
