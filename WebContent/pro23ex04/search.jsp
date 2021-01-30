<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" 
    import="java.util.*"
    isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 검색창</title>
</head>
<body>
<form action="${contextPath }/mem4.do">
	입력 : <input type="text" name="value" />
	<select name="action" >
		<option value = "listMembers">전체</option>
		<option value = "selectMemberById">아이디</option>
		<option value = "selectMemberByPwd">비밀번호</option>
	</select><br />
	<input type="submit" value="검색" />
</form>
</body>
</html>