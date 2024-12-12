<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.system.management.library.models.Reservation"%>

<jsp:useBean id="reservations" type="java.util.List<Reservation>" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Book Management System - Reservations</title>
</head>
<body>
	<h1>Library Book Management System - Manage Reservations</h1>
	<%@ include file="header.jsp"%>
	<h2>List of Reservations</h2>
	<%
	if (reservations != null && !reservations.isEmpty()) {
	%>
	<table border="1">
		<thead>
			<tr>
				<th>Id</th>
				<th>Username</th>
				<th>Date Reserved</th>
				<th>Status</th>
				<th>Book Title</th>
				<th>Location</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (Reservation reservation: reservations) {
			%>
			<tr>
				<td><%=reservation.getId()%></td>
				<td><%=reservation.getUserEmail()%></td>
				<td><%=reservation.getDateReserved()%></td>			
				<td><%=reservation.getBookTitle()%></td>
				<td><%=reservation.getStatus()%></td>
				<td><%=reservation.getLocation()%></td>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<%
	} else {
	%>
	<p>No books found.</p>
	<%
	}
	%>
	<button type="button"
		onclick="window.location.href='/TestProject/LogoutServlet'">Logout</button>
</body>
</html>