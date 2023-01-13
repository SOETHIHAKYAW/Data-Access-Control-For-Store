package com.mystore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class LaptopDAO {

	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;

	public LaptopDAO(DataSource dataSource) {
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
	
	//create laptop to new table(sale table)
	public int createLaptop(final Laptop laptop) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("INSERT INTO `sale` "
					+ "(`goodsid`, `name`, `price`, `quantity`, imgname) VALUES (?, ?, ?, ?, ?);");
			pStmt.setString(1, laptop.getGoodsid());
			pStmt.setString(2, laptop.getName());
			pStmt.setDouble(3, laptop.getPrice());
			pStmt.setInt(4, laptop.getQuantity());
			pStmt.setString(5, laptop.getImgname());
			
			rowEffected = pStmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	
	
	//get -> load by ID
	public Laptop getLaptop(String goodsid) {
		
		Laptop laptop = null;
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from items where goodsid='"+goodsid+"';");
			
			while (rs.next()) {
				
				laptop = new Laptop(
						
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
		return laptop;
		
	}
	
	//update laptop
	public int updateLaptop(final Laptop laptop) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("UPDATE items SET "
					+ "goodsid = ?, "
					+ "name = ?, "
					+ "price = ?, "
					+ "quantity=quantity-?,"
					+ " imgname = ? "
					+ "WHERE (`goodsid` = ?);");
			
			pStmt.setString(1, laptop.getGoodsid());
			pStmt.setString(2, laptop.getName());
			pStmt.setDouble(3, laptop.getPrice());
			pStmt.setInt(4, laptop.getQuantity());
			pStmt.setString(5, laptop.getImgname());
			
			pStmt.setString(6, laptop.getGoodsid());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	
	//show LaptopList
	public List<Laptop> getLaptopList() {

		List<Laptop> laptopList = new ArrayList<>();

		try {
			connection = dataSource.getConnection();

			stmt = connection.createStatement();
			rs = stmt.executeQuery("select goodsid,name,price,quantity,imgname from items where goodsid like 'A%';");

			while (rs.next()) {

				laptopList.add(new Laptop(
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
		} finally {
			close();
		}
		return laptopList;

	}

}
