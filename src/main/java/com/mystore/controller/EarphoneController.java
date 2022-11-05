package com.mystore.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.mystore.model.Earphone;
import com.mystore.model.EarphoneDAO;

/**
 * Servlet implementation class EarphoneController
 */
public class EarphoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/storeResult")
	
	private DataSource dataSource;
	private EarphoneDAO earphoneDAO;
	
	@Override
	public void init() throws ServletException {
		earphoneDAO = new EarphoneDAO(dataSource);
		
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EarphoneController() {
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
			showEarphoneList(request, response);
			break;
		case "BUY":
			updateEarphone(request, response);
			createEarphone(request, response);
			break;
		case "LOAD":
			loadEarphoneByGoodsId(request, response);
			break;
		default :
			showEarphoneList(request, response);
		}
		
	}
	
	//create Earphone to new table
	private void createEarphone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Earphone earphone = new Earphone(goodid, name, price, quantity);
		
		int rowEffected = this.earphoneDAO.createLaptop(earphone);
		
		if(rowEffected > 0) {
			showEarphoneList(request, response);
		}
		
	}
	
	//update
	private void updateEarphone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		
		Earphone earphone = new Earphone(goodsid, name, price, quantity);
		int rowEffected = this.earphoneDAO.updateEarphone(earphone);
		
		if (rowEffected > 0) {
			showEarphoneList(request, response);
		}
		
	}
	
	//buy
	private void loadEarphoneByGoodsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodsid = request.getParameter("goodsid");
		
		Earphone earphone = this.earphoneDAO.getEarphone(goodsid);
		request.setAttribute("earphone", earphone);
		
		RequestDispatcher rd = request.getRequestDispatcher("earphone-buy.jsp");
		rd.forward(request, response);
		
	}
	
	//show list
	protected void showEarphoneList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Earphone> earphoneList = this.earphoneDAO.getEarphoneList();
		
		request.setAttribute("earphoneList", earphoneList);
		RequestDispatcher rd = request.getRequestDispatcher("earphone-collection.jsp");
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
