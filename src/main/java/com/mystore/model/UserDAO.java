package com.mystore.model;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mystore.crypto.PasswordEncoder;
import com.mystore.crypto.PasswordValidcator;



public class UserDAO {

	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public UserDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}

	private void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public int createUser(User user) {
		int rowEffected = 0;
		try {
			connection = dataSource.getConnection();

			pStmt = connection.prepareStatement(
					"INSERT INTO `user` (`username`, `email`, `password`, `role`) VALUES (?, ?, ?, ?);"
					+ "");

			pStmt.setString(1, user.getUserName());
			pStmt.setString(2, user.getEmail());
			String rawPassword = user.getPassword();
			String securedPassword = null;
			try {
				securedPassword = PasswordEncoder.encode(rawPassword);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			pStmt.setString(3, securedPassword);
			pStmt.setString(4, user.getRole());
			

			rowEffected = pStmt.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return rowEffected;
	}
	
	public String getRole(String email) {
		User user = null;
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select role from user where email='"+email+"';");
			
			while(rs.next()) {
				
				user = new User(email);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return email;
		
		
	}

	public User getUserByEmail(String email) {
		User user = null;
		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from user where email='" + email + "';");

			while (rs.next()) {

				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("email"),
						rs.getString("password"), rs.getString("role"));

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return user;
	}

	public boolean isValid(String email, String originalPasword) {

		boolean valid = false;

		User user = getUserByEmail(email);

		if (user != null) {
			String securedPassword = user.getPassword();

			try {
				if (PasswordValidcator.validatePassword(originalPasword, securedPassword)) {

					valid = true;

				}
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeySpecException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return valid;

	}

}
