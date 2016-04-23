<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${initParam.root}css/board.css" type="text/css">
<script type="text/javascript">
	function login() {
		var id = document.loginForm.idText.value;
		var password = document.loginForm.passwordText.value;
		if (id == "") {
			alert("아이디를 입력하세요");
			return false;
		}
		if (password == "") {
			alert("비밀번호를 입력하세요");
			return false;
		   }
	}
</script>
</head>
<body>
	<p>
		<c:choose>
			<c:when test="${sessionScope.mvo==null}">
				<form name="loginForm" action="${initParam.root}DispatcherServlet" onsubmit="return login()">
					<input type="hidden" name="command" value="login"> 
					아이디 <input type="text" name="id"> 
					비밀번호 <input type="text" name="password"> 
					<input type="submit" value="로그인">
				</form>
			</c:when>
			<c:otherwise>
				<p>
					&nbsp;&nbsp;<a href="${initParam.root}index.jsp">홈</a> | 
					<a href="${initParam.root}board/write.jsp">글쓰기</a> |
					${sessionScope.mvo.name}님 | 
					<a href="${initParam.root}DispatcherServlet?command=logout">로그아웃</a>
				</p>
			</c:otherwise>
		</c:choose>
	</p>
	<hr>
</body>
</html>