package com.mystore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class PhoneDAO {
	
	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	public PhoneDAO(DataSource dataSource) {
		super();
		this.dataSource = dataSource;
	}
	
	private void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//create Phone to new table(sale table)
		public int createPhone(final Phone phone) {
			
			int rowEffected = 0;
			
			try {
				connection = dataSource.getConnection();
				
				pStmt = connection.prepareStatement(
						"INSERT INTO `sale` (`goodsid`, `name`, `price`, `quantity`, imgname) "
						+ "VALUES (?, ?, ?, ?, ?);");
				pStmt.setString(1, phone.getGoodsid());
				pStmt.setString(2, phone.getName());
				pStmt.setDouble(3, phone.getPrice());
				pStmt.setInt(4, phone.getQuantity());
				pStmt.setString(5, phone.getImgname());
				
				rowEffected = pStmt.executeUpdate();
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				close();
			}
			return rowEffected;
			
		}
		
	
	//get
	public Phone getPhone(String goodsid) {
		
		Phone phone = null;
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from items where goodsid='"+goodsid+"';");
			
			while(rs.next()) {
				phone = new Phone(
						rs.getString("goodsid"), 
						rs.getString("name"), 
						rs.getDouble("price"),
						rs.getInt("quantity"),
						rs.getString("imgname")
						);
			}
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return phone;
		
	}
	
	//show all
	public List<Phone> getPhoneList(){
		
		List<Phone> phoneList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select goodsid,name,price,quantity,imgname from items where goodsid like 'C%';");
			
			while (rs.next()) {
				phoneList.add(new Phone(
						rs.getString("goodsid"), 
						rs.getString("name"), 
						rs.getDouble("price"),
						rs.getInt("quantity"),
						rs.getString("imgname")
						));
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return phoneList;
		
	}
	
	//update
	public int updatePhone(final Phone phone) {
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("UPDATE `items` SET "
					+ "`goodsid` = ?, "
					+ "`name` = ?, "
					+ "`price` = ?, "
					+ "`quantity` = quantity-?, "
					+ "imgname = ? "
					+ "WHERE (`goodsid` = ?);");
			pStmt.setString(1, phone.getGoodsid());
			pStmt.setString(2, phone.getName());
			pStmt.setDouble(3, phone.getPrice());
			pStmt.setInt(4, phone.getQuantity());
			pStmt.setString(5, phone.getImgname());
			pStmt.setString(6, phone.getGoodsid());
			
			rowEffected = pStmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
	}

}
