<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>List administrators</h1>
	
	<c:forEach var="admin" items="${requestScope.administrators }">
		<c:out value="${admin.mail}" />
		
		<c:catch var="ex">
			<c:set var="id_question" value="${admin.id_question }" />
			<sql:query var="rs" dataSource="jdbc/JSPServlets">
				SELECT question FROM question WHERE id = ?;
				<sql:param value = "${id_question}" />
			</sql:query>
			
			<c:forEach var="row" items="${rs.rows}">
				${row.question}
			</c:forEach>
			<br />
		</c:catch>
		
		<c:if test="${ex!=null }">
			<span style="color:#f00">
				* Error in connection
			</span>
		</c:if>
		
	</c:forEach>
	
</body>
</html>