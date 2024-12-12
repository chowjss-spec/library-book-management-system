package com.system.management.library;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
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

import com.system.management.library.models.Reservation;

/**
 * Servlet implementation class ReservationServlet
 */
@WebServlet("/ReservationServlet")
public class ReservationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReservationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		try {
			InitialContext context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/myDataSource");
			this.connection = dataSource.getConnection();
			System.out.println("Connection successful");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
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
		
		try {
			String selectReservations = "SELECT Reservation.Id, Reservation.UserEmail, Reservation.DateReserved, Reservation.Status, PhysicalBook.Location, Book.Title from Reservation join PhysicalBook on Reservation.PhysicalBookId=PhysicalBook.id join Book on PhysicalBook.BookId = Book.Id;";
			PreparedStatement stmt1 = this.connection.prepareStatement(selectReservations);
			ResultSet results = stmt1.executeQuery();
			List<Reservation> reservations = new ArrayList<Reservation>();
			while (results.next()) {
				int id= results.getInt("Id");
				String userEmail = results.getString("UserEmail");
				Date dateReserved = results.getDate("DateReserved");
				String bookTitle = results.getString("Title");
				String status = results.getString("Status");
				String location = results.getString("Location");
				reservations.add(new Reservation(id, userEmail, dateReserved, bookTitle, status, location));
				
			}
			request.setAttribute("reservations", reservations);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/reservations.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Helloooo was I hit");
		Integer physicalBookId = Integer.parseInt(request.getParameter("physicalBookId"));
		String bookDetailId = request.getParameter("bookDetailId");
		System.out.println("bookDetailId" + bookDetailId);
		String userEmail = request.getUserPrincipal().getName();
		Date reservedAt = new Date(Instant.now().toEpochMilli());
		String status = "Active";
		String updateBookSql = "UPDATE PhysicalBook SET Status = 'Reserved' where Id = ? and Status = 'Available'";
		String createReservationSql = "INSERT INTO Reservation (UserEmail, PhysicalBookId, DateReserved, Status) VALUES (?, ?, ?, ?)";
		try {
			this.connection.setAutoCommit(false);
			PreparedStatement stmt1 = this.connection.prepareStatement(updateBookSql);
			stmt1.setInt(1, physicalBookId);
			int results = stmt1.executeUpdate();
			if (results == 1) {
				PreparedStatement stmt2 = this.connection.prepareStatement(createReservationSql);
				stmt2.setString(1, userEmail);
				stmt2.setInt(2, physicalBookId);
				stmt2.setDate(3, reservedAt);
				stmt2.setString(4, status);
				stmt2.executeUpdate();
			}
			this.connection.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				this.connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} finally {
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		response.sendRedirect("/TestProject/Home/BookDetailServlet/?id=" + request.getParameter("bookDetailId"));
	}

}
