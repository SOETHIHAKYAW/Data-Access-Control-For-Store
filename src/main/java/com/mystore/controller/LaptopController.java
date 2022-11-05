package com.mystore.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import com.mystore.model.Laptop;
import com.mystore.model.LaptopDAO;

/**
 * Servlet implementation class LaptopController
 */
public class LaptopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/storeResult")
	
	private DataSource dataSource;
	private LaptopDAO laptopDAO;
	
	@Override
	public void init() throws ServletException {
		laptopDAO = new LaptopDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LaptopController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String mode = request.getParameter("mode");
		if (mode == null) {
			mode = "LIST";
		}
		
		switch (mode) {
		case "LIST":
			showLaptopList(request, response);
			break;
		case "BUY":
			updateLaptop(request, response);
			createLaptop(request, response);
			break;
		case "LOAD":
			loadLaptopByGoodsId(request, response);
			break;
		default :
			showLaptopList(request, response);
		}
		
		
	}
	//create laptop in new table (sale table)
	private void createLaptop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Laptop laptop = new Laptop(goodid, name, price, quantity);
		
		int rowEffected = this.laptopDAO.createLaptop(laptop);
		
		if(rowEffected > 0) {
			showLaptopList(request, response);
		}
		
		
		
	}
	
	
	//update
	private void updateLaptop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Laptop laptop = new Laptop(goodsid, name, price, quantity);
		int rowEffected = this.laptopDAO.updateLaptop(laptop);
		
		if (rowEffected > 0) {
			showLaptopList(request, response);
		}
		
	}
	
	//buy
	private void loadLaptopByGoodsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodsid = request.getParameter("goodsid");  
		
		Laptop laptop = this.laptopDAO.getLaptop(goodsid);
		request.setAttribute("laptop", laptop);
		
		RequestDispatcher rd = request.getRequestDispatcher("laptop-buy.jsp");
		rd.forward(request, response);
		
	}
	
	//show all
	private void showLaptopList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Laptop> laptopList = this.laptopDAO.getLaptopList();
		
		request.setAttribute("laptopList", laptopList);
		RequestDispatcher rd = request.getRequestDispatcher("laptop-collection.jsp");
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
