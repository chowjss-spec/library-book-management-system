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
import com.system.management.library.models.PhysicalBook;

/**
 * Servlet implementation class BookDetailServlet
 */
@WebServlet("/Home/BookDetailServlet/*")
public class BookDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BookDetailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("Hello World");
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/myDataSource");
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
		String query = "Select Title, Location, Status, PhysicalBook.id, Book.id as BookDetailId from PhysicalBook JOIN Book on PhysicalBook.BookId = Book.Id where Book.Id = ?";
		List<PhysicalBook> books = new ArrayList<PhysicalBook>();
		String title = "";
		String physicalBookId = request.getParameter("id");
		String bookDetailId = "";
		try {
			PreparedStatement stmt = this.connection.prepareStatement(query);
			stmt.setString(1, physicalBookId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				title = rs.getString("Title");
				String location = rs.getString("Location");
				String status = rs.getString("Status");
				int id = rs.getInt("Id");
				System.out.println("Physical book Id" + " " + id);
				bookDetailId = String.valueOf(rs.getInt("BookDetailId"));
				books.add(new PhysicalBook(id, location, status));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("books", books);
		request.setAttribute("title", title);
		request.setAttribute("bookDetailId", bookDetailId);
		RequestDispatcher dispatcher  = request.getRequestDispatcher("/WEB-INF/views/bookDetails.jsp");
		dispatcher.forward(request, response);
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
