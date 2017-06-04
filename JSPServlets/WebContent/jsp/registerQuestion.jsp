<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<h1>Register secret question</h1>
	<br />
	
	<form>
		Secret question:
		<input type="text" name="question" size="35" /><br />
		<input type="hidden" name="action" value="registerQuestion" />
		<input type="submit" value="Register" />		
	</form> 
	
</body>
</html>