<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
	request.setCharacterEncoding("UTF-8");
%>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" width="50%" align="center" >
		<tr align="center">
	    	<td>아이디</td>
	      	<td>비밀번호</td>
	   	</tr>
	   	<tr align="center">
	      	<td>${userID}</td>
	      	<td>${passwd}</td>
	   	</tr>
	</table>
</body>
</html>