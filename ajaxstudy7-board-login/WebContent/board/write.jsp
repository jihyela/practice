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
	function content_submit() {
		var f = document.write_form;
		if (f.title.value == "") {
			alert("제목을 입력하세요!");
			f.title.focus();
			return;
		}
		if (f.content.value == "") {
			alert("내용을 입력하세요!");
			f.content.focus();
			return;
		}
		f.submit();
	}
	function cancel() {
		var f = document.write_form;
		f.reset();
	}
</script>
</head>
<jsp:include page="/template/header.jsp"></jsp:include>
<br>
<form action="${initParam.root}DispatcherServlet" method="post" name="write_form">
	<input type="hidden" name="command" value="write">
	<table class="inputForm">
		<caption>글쓰기</caption>
		<tbody>
			<tr>
				<td>제목</td>
				<td colspan="3"><input type="text" name="title" size="48">
				</td>
			</tr>
			<tr>
				<td>이름</td>
				<td>${sessionScope.mvo.name }</td>
			</tr>
			<tr>
				<td colspan="4" align="left">&nbsp;&nbsp; 
				<textarea cols="53" rows="15" name="content"></textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4" align="center">
					<img class="action" src="${initParam.root}img/write_btn.jpg" alt="글입력" onclick="content_submit()"> 
					<img class="action" src="${initParam.root}img/cancel.gif" onclick="cancel()">
				</td>
			</tr>
		</tbody>
	</table>
</form>
</html>




