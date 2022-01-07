<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.BoardVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardDetail.jsp</title>
</head>
<body>
	<h2>상세 페이지</h2>
	<h3>${ requestScope.detailPage.title }</h3>
	<br>
	<!-- Board Num : ${ requestScope.detailPage.num }<br> -->
	Writer : ${ requestScope.detailPage.writer }<br>
	Writedate : ${ requestScope.detailPage.writedate }<br>
	Count : ${ requestScope.detailPage.cnt }<br>
	<hr>
	Content : ${ requestScope.detailPage.content }<br>
	
	<hr>
	<form method="get" action="">
		<button type="button" onclick="location.href='/bbs/board'">목록</button>
		<button type="button" onclick="location.href='/bbs/board'" value="">수정</button>
		<button type="button" onclick="location.href='/bbs/board'" value="">삭제</button>
	</form>

</body>
</html>