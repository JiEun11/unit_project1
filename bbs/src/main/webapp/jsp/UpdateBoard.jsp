<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>UpdateBoard.jsp</title>
</head>
<body>
	<h1 class="update_title">방명록 수정</h1>
	<hr>
	<form method="post" action="/bbs/board">
		<input type="hidden" name="num" value="${ requestScope.updatePage.num }">
		제목 : <br>
		<textarea id="b_title" rows="2" cols="30" name="b_title">${ requestScope.updatePage.title }</textarea>
		
		<input type="hidden" name="b_writer" value="${ requestScope.updatePage.writer }">
		<br> Writer : ${ requestScope.updatePage.writer } <br>
		
		<input type="hidden" name="writedate" value="${ requestScope.updatePage.writedate }">
		Writedate : ${ requestScope.updatePage.writedate } <br>
		
		<input type="hidden" name="cnt" value="${ requestScope.updatePage.cnt }">
		Count : ${ requestScope.updatePage.cnt } <br> <br> 내용 : <br>
		
		<textarea id="b_content" rows="4" cols="30" name="b_content">${ requestScope.updatePage.content }</textarea>
		<br>
		<input type="submit" value="수정">
		<input type="reset" value="재작성">
	</form>
	<br>
	<a href="/bbs/board">게시글 홈 화면으로 가기</a>
</body>
</html>