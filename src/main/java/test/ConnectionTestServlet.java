package test;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mystore.model.Items;
import com.mystore.model.Laptop;

/**
 * Servlet implementation class ConnectionTestServlet
 */
public class ConnectionTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/storeResult")
	private DataSource dataSource;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConnectionTestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		//select only laptop from items table
		try {
			connection = dataSource.getConnection();
			
			stmt = connection.createStatement();
			
			rs = stmt.executeQuery("select goodsid,name,price,quantity from items where id between 1 and 8;");
			
			while (rs.next()) {
				
				Laptop laptop = new Laptop(
						rs.getString("goodsid"), 
						rs.getString("name"), 
						rs.getDouble("price"),
						rs.getInt("quantity")
						);		
				
				
				out.println(laptop+"\n");
				
			}
			
			
			
//			rs = stmt.executeQuery("select * from items;");
//			
//			while(rs.next()) {
//				
//				Items items = new Items(
//						rs.getInt("id"), 
//						rs.getString("goodsid"), 
//						rs.getString("name"), 
//						rs.getDouble("price"), 
//						rs.getInt("amount"), 
//						rs.getDate("stockin"), 
//						rs.getDate("stockout")
//						);
				
//				out.println(items+"\n");
//				
//			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
