<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.system.management.library.models.PhysicalBook"%>
<jsp:useBean id="books" type="java.util.List<PhysicalBook>"
	scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Details</title>
</head>
<body>
	<%
	if (books != null && !books.isEmpty()) {
	%>
	<h2>
		Details for
		<%= request.getAttribute("title") %></h2>
	<table border="1">
		<thead>
			<tr>
			<th>S/N</th>
				<th>Location</th>
				<th>Status</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (PhysicalBook book : books) {
			%>
			<tr>
				<td><%=book.getId()%></td>
				<td><%=book.getLocation()%></td>
				<td><%=book.getStatus()%></td>
				<td>
					<% boolean isPublic = request.isUserInRole("public");
					   int physicalBookId = book.getId();
					if (book.getStatus().equals("Available") && isPublic) {%>
					<form action="/LibraryBookManagementSystem/ReservationServlet" method="POST">
					<input type="hidden" id="bookDetailId" name="bookDetailId" value="<%=request.getAttribute("bookDetailId")%>">
					<input type="hidden" id="physicalBookId" name="physicalBookId" value="<%=physicalBookId%>">
					<button type="submit">Reserve</button> 
					</form><%}else {%>
					<button type="button" disabled>Reserve</button> <%} %>
				</td>
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
</body>
</html>