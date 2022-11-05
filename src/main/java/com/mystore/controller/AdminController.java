package com.mystore.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.mystore.model.Items;
import com.mystore.model.ItemsDAO;
import com.mystore.model.SaleItems;
import com.mystore.model.SaleItemsDAO;
import com.mystore.model.User;

/**
 * Servlet implementation class ItemsController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/storeResult")
	
	private DataSource dataSource;
	private ItemsDAO itemsDAO;
	
	@Override
	public void init() throws ServletException {
		
		itemsDAO = new ItemsDAO(dataSource);
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		
		if(user != null) {
		
			String mode = request.getParameter("mode");
			if (mode == null) {
				mode = "LIST";
			}
	
			switch (mode) {
			case "LIST":
				showItemsList(request, response);
				break;
			case "CREATE":
				createItems(request, response);
				break;
			case "DELETE":
				deleteItems(request, response);
				break;
			case "UPDATE":
				updateItems(request, response);
				break;
			case "LOAD":
				loadItemsById(request, response);
				break;
			case "LOGOUT":
				session.invalidate();
				response.sendRedirect("home-page.html");
				break;
			default :
				showItemsList(request, response);
			}
		}
		
	}
	
	//Update
	private void loadItemsById(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Items items = this.itemsDAO.getItems(id);
		
		request.setAttribute("items", items);
		
		RequestDispatcher rd = request.getRequestDispatcher("updateform.jsp");
		rd.forward(request, response);
		
	}
	
	//Create Controller
	private void createItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		
		if(session != null) {
				
			String goodsid = request.getParameter("goodsid");
			String name = request.getParameter("name");
			Double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Date stockin = Date.valueOf(request.getParameter("stockin"));
			
			Items items = new Items(goodsid, name, price, quantity, stockin);
			
			int rowEffected = this.itemsDAO.createItems(items);
			
			if(rowEffected > 0 ) {
				
				showItemsList(request, response);
				
			}
		
		}
		
	}
	
	//Update
	private void updateItems(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		
		int id = Integer.parseInt(request.getParameter("id"));
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		Double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		Date stockin = Date.valueOf(request.getParameter("stockin"));
		
		Items items = new Items(id, goodsid, name, price, quantity, stockin);
		
		int rowEffected = this.itemsDAO.updateItems(items);
		
		if(rowEffected > 0) {
			
			showItemsList(request, response);
			
		}
		
		
	}
	
	//Delete Controller
	private void deleteItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		int rowEffected = this.itemsDAO.deleteItems(id);
		
		if(rowEffected >  0) {
			
			showItemsList(request, response);
			
		}
		
	} 
	
	//Show for all items from store
	private void showItemsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);
		
		List<Items> itemsList = this.itemsDAO.getItemsList();
		
		request.setAttribute("itemsList", itemsList);
		RequestDispatcher rd = request.getRequestDispatcher("index-admin.jsp");
		rd.forward(request, response);
		
	} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
