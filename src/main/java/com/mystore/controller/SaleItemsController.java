package com.mystore.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.mystore.model.SaleItems;
import com.mystore.model.SaleItemsDAO;
import com.mystore.model.User;

/**
 * Servlet implementation class SaleItemsController
 */
public class SaleItemsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/storeResult")
	
	private DataSource dataSource;
	private SaleItemsDAO saleItemsDAO;
	
	@Override
	public void init() throws ServletException {
		
		saleItemsDAO = new SaleItemsDAO(dataSource);
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaleItemsController() {
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
				showSaleItemsList(request, response);
				break;
			case "LOAD": 
				loadByGoodsId(request, response);
				break;
			case "UPDATE": 
				updateSaleItems(request, response);
				break;
			case "DELETE": 
				deleteSaleItems(request, response);
				break;
			case "LOGOUT":
				session.invalidate();
				response.sendRedirect("home-page.html");
				break;
	
			default:
				showSaleItemsList(request, response);
			}
		}else {
			response.sendRedirect("home-page.html");
		}
		
	}
	
	//delete
	private void deleteSaleItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		
		int id = Integer.parseInt(request.getParameter("id"));
		
		int rowEffected = this.saleItemsDAO.deleteSaleItems(id);
		
		if(rowEffected > 0) {
			showSaleItemsList(request, response);
		}
		
	}
	
	//update db
	private void updateSaleItems(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException{
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		
		int id = Integer.parseInt(request.getParameter("id"));
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		SaleItems saleItems = new SaleItems(id, goodsid, name, price, quantity);
		
		int rowEffected = this.saleItemsDAO.updateSaleItems(saleItems);
		if(rowEffected > 0) {
			showSaleItemsList(request, response);
		}
		
	}
	
	//update load
	private void loadByGoodsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		
		int id = Integer.parseInt(request.getParameter("id"));
		
		SaleItems saleItems = this.saleItemsDAO.saleItems(id);
		
		request.setAttribute("saleItems", saleItems);
		RequestDispatcher rd = request.getRequestDispatcher("sale-update.jsp");
		rd.forward(request, response);
		
	}
	
	//Show for all items from sale table
	private void showSaleItemsList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		
		List<SaleItems> saleItemsList = this.saleItemsDAO.getSaleItemsList();
		
		request.setAttribute("saleItemsList", saleItemsList);
		RequestDispatcher rd = request.getRequestDispatcher("saleItems-admin.jsp");
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
