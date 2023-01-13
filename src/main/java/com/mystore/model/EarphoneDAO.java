package com.mystore.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class EarphoneDAO {
	
	private final DataSource dataSource;
	private Connection connection;
	private Statement stmt;
	private PreparedStatement pStmt;
	private ResultSet rs;
	public EarphoneDAO(DataSource dataSource) {
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
	
	//create Earphone to new table(sale table)
		public int createEarphone(final Earphone earphone) {
			
			int rowEffected = 0;
			
			try {
				connection = dataSource.getConnection();
				
				pStmt = connection.prepareStatement(
						"INSERT INTO `sale` (`goodsid`, `name`, `price`, `quantity`, imgname) "
						+ "VALUES (?, ?, ?, ?, ?);");
				pStmt.setString(1, earphone.getGoodsid());
				pStmt.setString(2, earphone.getName());
				pStmt.setDouble(3, earphone.getPrice());
				pStmt.setInt(4, earphone.getQuantity());
				pStmt.setString(5, earphone.getImgname());
				
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
	public int updateEarphone(final Earphone earphone) {
		
		int rowEffected = 0;
		
		try {
			connection = dataSource.getConnection();
			
			pStmt = connection.prepareStatement("update items set "
					+ "goodsid = ?, "
					+ "name = ?, "
					+ "price = ?, "
					+ "quantity=quantity-?, "
					+ "imgname = ? "
					+ "where (`goodsid` = ?);");
			
			pStmt.setString(1, earphone.getGoodsid());
			pStmt.setString(2, earphone.getName());
			pStmt.setDouble(3, earphone.getPrice());
			pStmt.setInt(4, earphone.getQuantity());
			pStmt.setString(5, earphone.getImgname());
			
			pStmt.setString(6, earphone.getGoodsid());
			
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
	public Earphone getEarphone(String goodsid) {
		
		Earphone earphone = null;
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select * from items where goodsid='"+goodsid+"';");
			
			while(rs.next()) {
				earphone = new Earphone(
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
		return earphone;
		
	}
	
	
	//show all
	public List<Earphone> getEarphoneList(){
		
		List<Earphone> earphoneList = new ArrayList<>();
		
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			rs = stmt.executeQuery("select goodsid,name,price,quantity,imgname from items where goodsid like 'B%';");
			
			while(rs.next()) {
				earphoneList.add(new Earphone(
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
		return earphoneList;
		
	}
	
	
}
