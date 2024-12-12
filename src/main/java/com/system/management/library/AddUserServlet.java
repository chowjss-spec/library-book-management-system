package com.system.management.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.system.management.library.models.Book;

/**
 * Servlet implementation class AddUserServlet
 */
@WebServlet("/AddUserServlet")
public class AddUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUserServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Hello World from init servlet");
		
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/myDataSource");

			// Class.forName("com.mysql.cj.jdbc.Driver");
			// this.connection =
			// dataSource.getConnection("jdbc:mysql://localhost:3306/LibraryBookManagementSystem","root","my-secret-pwd");
			this.connection = dataSource.getConnection();
			System.out.println("Connection successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void destroy() {
		// TODO Auto-generated method stub
		try {
			this.connection.close();
			System.out.println("Disconnection successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("hello destroy method called");
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Database connection and query execution
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String role = "public";
		String updateUserSql = "INSERT INTO User VALUES (?, ?, ?, ?)";
		String updateUserRoleSql = "INSERT INTO UserRole VALUES (?, ?)";
		
		try {
			PreparedStatement updateUserStatement = this.connection.prepareStatement(updateUserSql);
			updateUserStatement.setString(1, firstName);
			updateUserStatement.setString(2, lastName);
			updateUserStatement.setString(3, email);
			updateUserStatement.setString(4, password);
			updateUserStatement.executeUpdate();
			PreparedStatement updateUserRoleStatement = this.connection.prepareStatement(updateUserRoleSql);
			updateUserRoleStatement.setString(1, email);
			updateUserRoleStatement.setString(2, role);
			updateUserRoleStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
	}

}
