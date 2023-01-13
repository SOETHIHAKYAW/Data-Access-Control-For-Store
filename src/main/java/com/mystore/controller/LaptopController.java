package com.mystore.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import com.mystore.model.Laptop;
import com.mystore.model.LaptopDAO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

/**
 * Servlet implementation class LaptopController
 */
public class LaptopController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String UPLOAD_DIR = "images";
	public String dbFileName = "";
	
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
		
//		Part part = request.getPart("file");
//		String fileName = extractFileName(part);
//
//		String applicationPath = "/home/michael/JAVAEE/ADEN-STORE/src/main/webapp/";
//
//		String uploadPath = applicationPath + UPLOAD_DIR;
//
//		File fileUploadDir = new File(uploadPath);
//		if (!fileUploadDir.exists()) {
//			fileUploadDir.mkdir();
//
//		}
//
//		String savePath = uploadPath + File.separator + fileName;
//		part.write(savePath);
//
//		dbFileName = UPLOAD_DIR + File.separator + fileName;
		
		String goodid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String file = request.getParameter("file");
		
		Laptop laptop = new Laptop(goodid, name, price, quantity, /*dbFileName*/ file);
		
		int rowEffected = this.laptopDAO.createLaptop(laptop);
		
		if(rowEffected > 0) {
			showLaptopList(request, response);
		}
		
		
		
	}
	
//	private String extractFileName(Part part) {
//		// TODO Auto-generated method stub
//
//		String contentDisp = part.getHeader("content-disposition");
//		String[] itmes = contentDisp.split(";");
//
//		for (String s : itmes) {
//			if (s.trim().startsWith("filename")) {
//				return s.substring(s.indexOf("=") + 2, s.length() - 1);
//			}
//		}
//		return "";
//	}
	
	
	//update
	private void updateLaptop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		Part part = request.getPart("file");
//		String fileName = extractFileName(part);
//
//		String applicationPath = "/home/michael/JAVAEE/ADEN-STORE/src/main/webapp/";
//
//		String uploadPath = applicationPath + UPLOAD_DIR;
//
//		File fileUploadDir = new File(uploadPath);
//		if (!fileUploadDir.exists()) {
//			fileUploadDir.mkdir();
//
//		}
//
//		String savePath = uploadPath + File.separator + fileName;
//		part.write(savePath);
//
//		dbFileName = UPLOAD_DIR + File.separator + fileName;
//		
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String file = request.getParameter("file");
		
		Laptop laptop = new Laptop(goodsid, name, price, quantity, /*dbFileName*/ file);
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
