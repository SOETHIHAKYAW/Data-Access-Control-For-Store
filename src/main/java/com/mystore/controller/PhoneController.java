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

import com.mystore.model.Phone;
import com.mystore.model.PhoneDAO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50)

/**
 * Servlet implementation class PhoneController
 */
public class PhoneController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String UPLOAD_DIR = "images";
	public String dbFileName = "";
	
	@Resource(name = "jdbc/storeResult")
	
	private DataSource dataSource;
	private PhoneDAO phoneDAO;
	
	@Override
	public void init() throws ServletException {
		phoneDAO = new PhoneDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhoneController() {
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
			showPhoneList(request, response);
			break;
		case "BUY":
			updatePhone(request, response);
			createPhone(request, response);
			break;
		case "LOAD":
			loadPhoneByGoodsId(request, response);
			break;
		default :
			showPhoneList(request, response);
		}
		
	}
	
	//create Phone to sale table
	private void createPhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		Phone phone = new Phone(goodid, name, price, quantity, /*dbFileName*/ file);
		
		int rowEffected = this.phoneDAO.createPhone(phone);
		
		if(rowEffected > 0) {
			showPhoneList(request, response);
		}
		
	}
//	
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
	
	//update -> buy
	private void updatePhone(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
		
		String goodsid = request.getParameter("goodsid");
		String name = request.getParameter("name");
		double price = Double.parseDouble(request.getParameter("price"));
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		String file = request.getParameter("file");
		
		
		Phone phone = new Phone(goodsid, name, price, quantity,/*dbFileName*/ file);
		
		int rowEffected = this.phoneDAO.updatePhone(phone);
		
		if(rowEffected > 0) {
			showPhoneList(request, response);
		}
		
	}
	
	//load by ID
	private void loadPhoneByGoodsId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String goodsid = request.getParameter("goodsid");
		
		Phone phone = this.phoneDAO.getPhone(goodsid);
		request.setAttribute("phone", phone);
		
		RequestDispatcher rd = request.getRequestDispatcher("phone-buy.jsp");
		rd.forward(request, response);
		
		
	}
	
	
	//show all
	private void showPhoneList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Phone> phoneList = this.phoneDAO.getPhoneList(); 
		
		request.setAttribute("phoneList", phoneList);
		RequestDispatcher rd = request.getRequestDispatcher("phone-collection.jsp");
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
