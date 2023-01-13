package com.mystore.controller;

import jakarta.annotation.Resource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.sql.DataSource;

import com.mystore.model.Items;
import com.mystore.model.ItemsDAO;
import com.mystore.model.User;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50)

/**
 * Servlet implementation class ItemsController
 */
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String UPLOAD_DIR = "images";
	public String dbFileName = "";

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");

		if (user != null) {

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
			default:
				showItemsList(request, response);
			}
		} else {
			response.sendRedirect("home-page.html");
		}

	}

	// Update
	private void loadItemsById(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		int id = Integer.parseInt(request.getParameter("id"));

		Items items = this.itemsDAO.getItems(id);

		request.setAttribute("items", items);

		RequestDispatcher rd = request.getRequestDispatcher("updateform.jsp");
		rd.forward(request, response);

	}

	// Create Controller
	private void createItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		if (session != null) {

			Part part = request.getPart("file");
			String fileName = extractFileName(part);

			String applicationPath = "/home/michael/JAVAEE/ADEN-STORE/src/main/webapp/";

			String uploadPath = applicationPath + UPLOAD_DIR;

			File fileUploadDir = new File(uploadPath);
			if (!fileUploadDir.exists()) {
				fileUploadDir.mkdir();

			}

			String savePath = uploadPath + File.separator + fileName;
			part.write(savePath);

			dbFileName = UPLOAD_DIR + File.separator + fileName;

			String goodsid = request.getParameter("goodsid");
			String name = request.getParameter("name");
			Double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Date stockin = Date.valueOf(request.getParameter("stockin"));

			Items items = new Items(goodsid, name, price, quantity, stockin, dbFileName);

			int rowEffected = this.itemsDAO.createItems(items);

			if (rowEffected > 0) {

				showItemsList(request, response);

			}

		}

	}

	private String extractFileName(Part part) {
		// TODO Auto-generated method stub

		String contentDisp = part.getHeader("content-disposition");
		String[] itmes = contentDisp.split(";");

		for (String s : itmes) {
			if (s.trim().startsWith("filename")) {
				return s.substring(s.indexOf("=") + 2, s.length() - 1);
			}
		}
		return "";
	}

	// Update
	private void updateItems(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		if (session != null) {

			Part part = request.getPart("file");
			String fileName = extractFileName(part);

			String applicationPath = "/home/michael/JAVAEE/ADEN-STORE/src/main/webapp/";

			String uploadPath = applicationPath + UPLOAD_DIR;

			File fileUploadDir = new File(uploadPath);
			if (!fileUploadDir.exists()) {
				fileUploadDir.mkdir();

			}

			String savePath = uploadPath + File.separator + fileName;
			part.write(savePath);

			dbFileName = UPLOAD_DIR + File.separator + fileName;

			int id = Integer.parseInt(request.getParameter("id"));
			String goodsid = request.getParameter("goodsid");
			String name = request.getParameter("name");
			Double price = Double.parseDouble(request.getParameter("price"));
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			Date stockin = Date.valueOf(request.getParameter("stockin"));
//			String file = request.getParameter("file");
			
			Items items = new Items(id, goodsid, name, price, quantity, stockin, dbFileName /*file*/);

			int rowEffected = this.itemsDAO.updateItems(items);

			if (rowEffected > 0) {

				showItemsList(request, response);

			}

		}

	}

//	
	// Delete Controller
	private void deleteItems(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		int id = Integer.parseInt(request.getParameter("id"));

		int rowEffected = this.itemsDAO.deleteItems(id);

		if (rowEffected > 0) {

			showItemsList(request, response);

		}

	}

	// Show for all items from store
	private void showItemsList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		request.setAttribute("user", user);

		List<Items> itemsList = this.itemsDAO.getItemsList();

		request.setAttribute("itemsList", itemsList);
		RequestDispatcher rd = request.getRequestDispatcher("index-admin.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
