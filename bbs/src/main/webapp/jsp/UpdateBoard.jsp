<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/boardStyle.css" />
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<meta charset="UTF-8">
<title>UpdateBoard.jsp</title>
</head>
<body>
<div class="updatepage">
	<h1>"방명록 수정"</h1>
	<form method="post" action="/bbs/board">
		<input type="hidden" name="isBoard" value="update">
		<input type="hidden" name="num" value="${ requestScope.updatePage.num }">
		제목 : <textarea id="b_title" rows="2" cols="30" name="b_title">${ requestScope.updatePage.title }</textarea>
		
		<input type="hidden" name="b_writer" value="${ requestScope.updatePage.writer }">
		<br> 
		작성자 : ${ requestScope.updatePage.writer } <br><br>
		
		<input type="hidden" name="writedate" value="${ requestScope.updatePage.writedate }">
		작성일 : ${ requestScope.updatePage.writedate } <br><br>
		
		<input type="hidden" name="cnt" value="${ requestScope.updatePage.cnt }">
		조회수 : ${ requestScope.updatePage.cnt } <br> <br> 
		내용 : <textarea id="b_content" rows="4" cols="30" name="b_content">${ requestScope.updatePage.content }</textarea>
		<br>
		<button type="submit" value="update">수정</button>
		<button type="reset" value="reset">재작성</button>
	</form>
	<br>
	<a href="/bbs/board">게시글 홈 화면으로 가기</a>
</div>
<jsp:include page="./bottom.jsp" flush='false' />
</body>
</html>