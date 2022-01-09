<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.MembersVO, model.vo.BoardVO, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="../css/boardStyle.css" />
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<title>WriteBoard.jsp</title>
</head>
<body>
<div class="writepage">
	<h2>내용을 작성해주세요</h2>
	<div class="write_btns">
	<form method="post" action="/bbs/board">
		<input type="hidden" name="isBoard" value="write">
		작성자 : <input readonly id="b_writer" name="b_writer" value=${ sessionScope.user.id }><p>
		
		제목 : <textarea id="b_title" rows="2" cols="30" name="b_title" required></textarea><br> 
		
		<input type="hidden" name="action" value="insert"> <br> 
		내용 : <textarea id="b_content" rows="4" cols="30" name="b_content" required></textarea>
		<br><br>
		<button type="submit" value="submit">작성</button>
		<button type="reset" value="reset">재작성</button>
	</form>
	<form method="get" action="/bbs/board">
		<button type="submit" name="detail_btn" value="main">메인 페이지</button>
	</form>
	</div>
</div>
<jsp:include page="./bottom.jsp" flush='false' />
</body>
</html>