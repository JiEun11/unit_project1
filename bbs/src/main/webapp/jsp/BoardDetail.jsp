<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.BoardVO, java.util.ArrayList, model.vo.MembersVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<link rel="stylesheet" href="css/boardStyle.css" />
<title>BoardDetail.jsp</title>
</head>
<body>

<%
BoardVO vo = (BoardVO)request.getAttribute("detailPage");
MembersVO voMem = (MembersVO)session.getAttribute("user");
session.setAttribute("detail", vo);
%>

<div class="detailpage">
	<h1>"상세 페이지"</h1>
	<h3>제목 : ${ requestScope.detailPage.title }</h3>
	<br>
	<!-- Board Num : ${ requestScope.detailPage.num }<br> -->
	작성자 : ${ requestScope.detailPage.writer }<br>
	작성일 : ${ requestScope.detailPage.writedate }<br>
	조회수 : ${ requestScope.detailPage.cnt }<br>
	<hr>
	내용 : ${ requestScope.detailPage.content }<br>

	<hr>
	<form method="get" action="/bbs/detail">
		<button type="submit" name="detail_btn" value="list">목록</button>
		
		
		<% if(vo.getWriter().equals(voMem.getId())) {%>
		<button type="submit" name="detail_btn" value="update">수정</button>
		<button type="submit" name="detail_btn" value="delete">삭제</button>
		<%} %>
		
		
	</form>
</div>
<jsp:include page="./bottom.jsp" flush='false' />
</body>
</html>