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
<form action="${contextPath }/test/login.do" method="post">
	<table width="400">
		<tr>
			<td>아이디 <input type="text" name="userID" size="10" /></td>
		</tr>
		<tr>
			<td>이름 <input type="text" name="userName" size="10" /></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="로그인" />
				<input type="reset" value="다시입력" />
			</td>
		</tr>
	</table>
</form>

<form action="${contextPath }/test/login2.do" method="post">
	<input type="hid-den" name="email" value="hong@test.com" />
	<table width="400">
		<tr>
			<td>아이디 <input type="text" name="userID" size="10" /></td>
		</tr>
		<tr>
			<td>이름 <input type="text" name="userName" size="10" /></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="로그인" />
				<input type="reset" value="다시입력" />
			</td>
		</tr>
	</table>
</form>

<form action="${contextPath }/test/login3.do" method="post">
	<table width="400">
		<tr>
			<td>아이디 <input type="text" name="userID" size="10" /></td>
		</tr>
		<tr>
			<td>이름 <input type="text" name="userName" size="10" /></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="로그인" />
				<input type="reset" value="다시입력" />
			</td>
		</tr>
	</table>
</form>

<form action="${contextPath }/test/login4.do" method="post">
	<table width="400">
		<tr>
			<td>아이디 <input type="text" name="userID" size="10" /></td>
		</tr>
		<tr>
			<td>이름 <input type="text" name="userName" size="10" /></td>
		</tr>
		<tr>
			<td>
				<input type="submit" value="로그인" />
				<input type="reset" value="다시입력" />
			</td>
		</tr>
	</table>
</form>

</body>
</html>