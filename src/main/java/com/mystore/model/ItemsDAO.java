package com.mystore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class ItemsDAO {
	
	private final DataSource dataSource;	
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public ItemsDAO(DataSource dataSource) {
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
	
	//Get - Search
	public Items getItems(int id) {
		
		Items items = null;
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from items where id='"+id+"';");
			
			while(rs.next()) {
				
				items = new Items(
						rs.getInt("id"), 
						rs.getString("goodsid"), 
						rs.getString("name"), 
						rs.getDouble("price"), 
						rs.getInt("quantity"), 
						rs.getDate("stockin"),
						rs.getString("imgname")
						);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return items;
		
	}
	
	//Update
	public int updateItems(final Items items) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("UPDATE `items` SET "
					+ "`goodsid` = ?, "
					+ "`name` = ?, "
					+ "`price` = ?, "
					+ "`quantity` = ?, "
					+ "`stockin` = ? , "
					+ "imgname = ?"
					+ "WHERE (`id` = ?);"
					+ "");
			
			pStmt.setString(1, items.getGoodsid());
			pStmt.setString(2, items.getName());
			pStmt.setDouble(3, items.getPrice());
			pStmt.setInt(4, items.getQuantity());
			pStmt.setDate(5, items.getStockin());
			pStmt.setString(6, items.getImgname());
			pStmt.setInt(7, items.getId());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	//Delete
	public int deleteItems(int id) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("delete from items where id=?;");
			pStmt.setInt(1, id);
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	//Create
	public int createItems(final Items items) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("INSERT INTO `items` "
					+ "(`goodsid`, `name`, `price`, `quantity`, `stockin`, imgname) "
					+ "VALUES (?, ?, ?, ?, ?, ?);"
					);
			
			pStmt.setString(1, items.getGoodsid());
			pStmt.setString(2, items.getName());
			pStmt.setDouble(3, items.getPrice());
			pStmt.setInt(4, items.getQuantity());
			pStmt.setDate(5, items.getStockin());
			pStmt.setString(6, items.getImgname());
//			
//			File f = new File("/home/");
//			
//			pStmt.set
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	//ShowAll
	public List<Items> getItemsList(){
		
		List<Items> itemsList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from items;");
			
			while(rs.next()) {
				
				itemsList.add(new Items(
						
						rs.getInt("id"), 
						rs.getString("goodsid"), 
						rs.getString("name"), 
						rs.getDouble("price"), 
						rs.getInt("quantity"), 
						rs.getDate("stockin"),
						rs.getString("imgname")
						
						)
						);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return itemsList;
		
	}
	
}
