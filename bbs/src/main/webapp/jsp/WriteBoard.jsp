<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.MembersVO, model.vo.BoardVO, java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WriteBoard.jsp</title>
</head>
<body>
	<form method="post" action="/bbs/detail">
		<input type="hidden" name="action" value="insert"><br>
		<textarea id="b_title" rows="2" cols="30" name="b_title"></textarea><br> 
		Writer :<input readonly id="b_writer" name="b_writer" value=${ sessionScope.user.id }><br>
		<input type="hidden" name="action" value="insert"> <br> 
		Content : <textarea id="b_content" rows="4" cols="30" name="b_content"></textarea>
		<br><br>
		<input type="submit" value="submit">
		<input type="reset" value="reset">
	</form>
</body>
</html>