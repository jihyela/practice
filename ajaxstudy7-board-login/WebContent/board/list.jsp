<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>글목록</title>
<link rel="stylesheet" href="${initParam.root}css/board.css" type="text/css">
<script type="text/javascript">
	function nextPage() {
		location.href="DispatcherServlet?command=list&pageNo=${lvo.pagingBean.endPageOfPageGroup+1}";
		alert("라지혜");
		alert("강석진");
	}
	function prePage() {
		location.href="DispatcherServlet?command=list&pageNo=${lvo.pagingBean.startPageOfPageGroup-1}";
	}
</script>
</head>
<body>
	<c:import url="/template/header.jsp"></c:import>	
	<table class="list">
		<caption>목록</caption>
		<thead>
			<tr>
				<th class="no">NO</th>
				<th class="title">제목</th>
				<th class="name">이름</th>
				<th class="date">작성일</th>
				<th class="hit">HIT</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="bvo" items="${lvo.list}">	
				<c:choose>	
					<c:when test="${sessionScope.mvo==null}">	
						<tr>
						    <td>${bvo.no }</td>
							<td>${bvo.title }</td>
							<td>${bvo.memberVO.name }</td>
							<td>${bvo.time_posted }</td>
							<td>${bvo.hits }</td>
						</tr>	
					</c:when>
					<c:otherwise>
						<tr>
						    <td>${bvo.no }</td>				
							<td><a href="DispatcherServlet?command=showContent&no=${bvo.no }">${bvo.title }</a></td>
							<td>${bvo.memberVO.name }</td>
							<td>${bvo.time_posted }</td>
							<td>${bvo.hits }</td>
						</tr>	
					</c:otherwise>
				</c:choose>	
			</c:forEach>
		</tbody>					
	</table>
	<c:if test="${lvo.pagingBean.previousPageGroup }">
		<img src="${initParam.root }img/left_arrow_btn.gif" onclick="prePage()">
	</c:if>
	<c:forEach begin="${lvo.pagingBean.startPageOfPageGroup }" end="${lvo.pagingBean.endPageOfPageGroup }" var="pageNumber">
		<a href="DispatcherServlet?command=list&pageNo=${pageNumber}"> ${pageNumber } </a>
	</c:forEach>
	<c:if test="${lvo.pagingBean.nextPageGroup }">
		<img src="${initParam.root }img/right_arrow_btn.gif" onclick="nextPage()">
	</c:if>
	<br></br>	
	<c:choose>	
		<c:when test="${sessionScope.mvo==null}">
		</c:when>
		<c:otherwise>
			<a href="${initParam.root}board/write.jsp"><img  src="${initParam.root}img/write_btn.jpg" border="0"></a>
		</c:otherwise>
	</c:choose>
	<br><br>
</body>
</html>









