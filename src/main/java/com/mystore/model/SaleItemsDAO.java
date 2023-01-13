package com.mystore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class SaleItemsDAO {
	
	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	
	public SaleItemsDAO(DataSource dataSource) {
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
	//don't need to create
	
	//show all -> list
	public List<SaleItems> getSaleItemsList(){
		
		List<SaleItems> saleItemsList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from sale;");
			
			while(rs.next()) {
				saleItemsList.add(new SaleItems(
						rs.getInt("id"),
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
		return saleItemsList;
		
	}
	
	//delete
	public int deleteSaleItems(int id) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("delete from sale where id=?;");
			
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
	
	//update
	public int updateSaleItems(final SaleItems saleItems) {
		
		int rowEffected = 0 ;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("UPDATE `sale` SET "
					+ "`goodsid` = ?, "
					+ "`name` = ?, "
					+ "`price` = ?, "
					+ "`quantity` = ? "
					+ "WHERE (`id` = ?);"
					);
			pStmt.setString(1, saleItems.getGoodsid());
			pStmt.setString(2, saleItems.getName());
			pStmt.setDouble(3, saleItems.getPrice());
			pStmt.setInt(4, saleItems.getQuantity());
			pStmt.setInt(5, saleItems.getId());
			
			rowEffected = pStmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return rowEffected;
		
	}
	
	//Get load-
	public SaleItems saleItems(int id) {
		
		SaleItems saleItems = null;
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from sale where id='"+id+"';");
			
			while(rs.next()) {
				saleItems = new SaleItems(
						rs.getInt("id"),
						rs.getString("goodsid"), 
						rs.getString("name"), 
						rs.getDouble("price"), 
						rs.getInt("quantity")
						);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			close();
		}
		return saleItems;
		
	}

}
