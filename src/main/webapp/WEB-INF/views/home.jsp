<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.system.management.library.models.Book"%>

<jsp:useBean id="bookList" type="java.util.List<Book>" scope="request" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Book Management System Home Page</title>
</head>
<body>
	<h1>Library Book Management System Home Page</h1>
	<%@ include file="header.jsp"%>
	<h2>List of Books</h2>
	<%
	if (bookList != null && !bookList.isEmpty()) {
	%>
	<table border="1">
		<thead>
			<tr>
				<th>Details</th>
				<th>ISBN</th>
				<th>Title</th>
				<th>Author</th>
				<th>Summary</th>
				<th>Year Published</th>
				<th>Image</th>
			</tr>
		</thead>
		<tbody>
			<%
			// Iterate through the list of users
			for (Book book : bookList) {
			%>
			<tr>
				<td><button type="button"
						onclick="window.open('/TestProject/Home/BookDetailServlet/?id=<%=book.getId()%>', '_blank')">See
						more</button></td>
				<td><%=book.getISBN()%></td>
				<td><%=book.getTitle()%></td>
				<td><%=book.getAuthor()%></td>
				<td><%=book.getSummary()%></td>
				<td><%=book.getYearPublished()%></td>
				<td><img src=<%=book.getImageUrl()%> width="135px", height="180px"></td>
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