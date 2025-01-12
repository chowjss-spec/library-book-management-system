<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
if (request.isUserInRole("admin")) {
%>
<button type="button">Manage Users</button>
<%
}
%>
<%
if (request.isUserInRole("admin") || request.isUserInRole("staff")) {
%>
<button type="button">Manage Books</button>
<%
}
%>
<button type="button" onclick="window.location.href='/LibraryBookManagementSystem/ReservationServlet'">Manage Reservations</button>
<button type="button" onclick="window.location.href='/LibraryBookManagementSystem/Home'">Home</button>