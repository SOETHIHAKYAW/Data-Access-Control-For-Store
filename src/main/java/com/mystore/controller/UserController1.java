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

import javax.sql.DataSource;

import com.mystore.model.User;
import com.mystore.model.UserDAO;

/**
 * Servlet implementation class UserController1
 */
public class UserController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name = "jdbc/storeResult")
	private DataSource dataSource;
	private UserDAO userDAO;
	
	@Override
	public void init() throws ServletException {
		userDAO = new UserDAO(dataSource);
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserController1() {
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
				mode = "SIGNIN";
			}
	
			switch (mode) {
			case "SIGNIN":
				signin(request, response);
				break;
			case "SIGNUP":
				signup(request, response);
				break;
			case "LOGOUT":
				session.invalidate();
				response.sendRedirect("home-page.html");
				break;
			}
		}
	}
	
	private void signup(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String userName = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		boolean adminOK = Boolean.parseBoolean(request.getParameter("role"));
		String role = adminOK ? "admin" : "user";
		

		User user = new User(userName, email, password, role);

		int rowEffected = this.userDAO.createUser(user);

		if (rowEffected > 0)
			response.sendRedirect("login");
		else
			System.err.println("Fail to Sign up User.");

	}
	
	private void signin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		if (userDAO.isValid(email, password)) {
			response.sendRedirect("admin");
			
			User user = this.userDAO.getUserByEmail(email);
			
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
		} else {
			
			RequestDispatcher rd = request.getRequestDispatcher("signin.jsp");
			rd.forward(request, response);
			
//			response.sendRedirect("signin-form.html");
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
