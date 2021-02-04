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
	<%-- <h1>아이디 : ${userID }</h1>
	<h1>이름 : ${userName }</h1>
	
	<!-- Map에 key로 접근하여 값을 출력함 -->
	<h1>아이디 : ${info.userID }</h1>
	<h1>이름 : ${info.userName }</h1> 

	<!-- @ModelAttribute("info")에서 지정한 이름으로 속성에 접근함 -->
	<h1>아이디 : ${info.userID }</h1>
	<h1>이름 : ${info.userName }</h1> --%>
	
	<!-- @Model클래스 이용해 값 전달하기 -->
	<h1>아이디 : ${userID }</h1>
	<h1>이름 : ${userName }</h1>
</body>
</html>