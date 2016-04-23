<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${initParam.root}css/board.css" type="text/css">
<script type="text/javascript">
function sendList(){
	location.href="${initParam.root}DispatcherServlet?command=list&pageNo=1";
}	
function winOpen(kind){	
	if(kind=="delete") {
		var d = confirm("삭제하시겠습니까?");
		if(d){
			location.href="${initParam.root}DispatcherServlet?command=deletePosting&no=${requestScope.bvo.no }";
		}
	} else if(kind=="update") {
		var u = confirm("수정하시겠습니까?");
		if(u) {
			location.href="${initParam.root}DispatcherServlet?command=updateView&no=${requestScope.bvo.no }";
		}
	}
} 
</script>
</head>
<body>
	<jsp:include page="/template/header.jsp"></jsp:include>
	<table class="content">
		<tr>
			<td>NO : ${requestScope.bvo.no } </td>
			<td colspan="2">${requestScope.bvo.title} </td>
		</tr>
		<tr>
			<td>작성자 :  ${requestScope.memberVO.name }</td>
			<td> ${requestScope.bvo.time_posted }</td>
			<td>조회수 : ${requestScope.bvo.hits }</td>
		</tr>
		<tr>
			<td colspan="3">
			<pre>${requestScope.bvo.content}</pre>
			</td>
		</tr>
		<tr>
			<td valign="middle" align="center" colspan="3">
				<img class="action" src="${initParam.root}img/list_btn.jpg" onclick="sendList()" >
				<c:choose>
					<c:when test="${sessionScope.mvo.id == requestScope.bvo.memberVO.id }">
						<img class="action"  onclick="winOpen('delete')" src="${initParam.root}img/delete_btn.jpg" > 
						<img class="action"  onclick="winOpen('update')" src="${initParam.root}img/modify_btn.jpg" >
					</c:when>
				</c:choose>
			</td>
		</tr>
	</table>
</body>
</html>






