<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Login with: <%= session.getAttribute("mail") %></h1>

	<table>
		<tr>
			<td><a href="?action=logout">Close session</a></td>
		</tr>
		<tr>
			<td><a href="?action=listAdministrators">List administrators</a></td>
		</tr>
	</table>

	<p>
		Main content
	</p>

</body>
</html>