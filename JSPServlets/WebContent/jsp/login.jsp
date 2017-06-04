<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Start session</h1>
	
	<span style="color:#f00; font-weight:bold">
		<%-- <c:if test="${requestScope.error != null }">
			<c:out value="${requestScope.error}" />
		</c:if> --%>
		
		<c:choose>
			<c:when test="${requestScope.error!=null }">
				<c:out value="${requestScope.error}" />
			</c:when>
			<c:otherwise>
				Without errors
			</c:otherwise>
		</c:choose>
		
	</span>
	<br />
	
	<form method="post" action="?action=startSession">
		<table>
			<tr>
				<td>User:</td>
				<%
					String userValue="";
					Cookie[] cookies=request.getCookies();
					if(cookies!=null){
						for(Cookie c:cookies){
							if(c.getName().equals("user")){
								userValue=c.getValue();
							}
						}
					}
				%>
				<td><input type="text" name="user" size="35" value="<%=userValue %>" /></td>
			</tr>	
			<tr>
				<td>Password:</td>
				<td><input type="password" name="password" size="35" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="checkbox" name="ckbox" checked="checked" >Remember my data</input></td>
			</tr>	
			<tr>
				<td></td>
				<td><input type="submit" value="Start Session" /></td>
			</tr>		
		</table>	
	</form>
		
</body>
</html>