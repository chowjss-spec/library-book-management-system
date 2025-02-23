<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Library Book Management System</title>
</head>
<body>
	<h1>Library Book Management System - Login Page</h1>
	<form method="POST" action="j_security_check">
        <label for="username">Email:</label>
        <input type="text" id="username" name="j_username" required>
        <br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="j_password" required>
        <br>
        <button type="submit">Login</button> <button type="button" onclick="window.location.href='addUser.jsp'">Register</button>
        
    </form>
	<!--<form method="post" action="j_security_check">
		<table>
			<tr>
				<td>Email:</td>
				<td><input name="email" /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input name="password" type="password" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Submit" /></td>
				<td><button >Register</button></td>
			</tr>
		</table>
	</form>-->
</body>
</html>