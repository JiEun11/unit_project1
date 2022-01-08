<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>updateBoard.jsp</title>
</head>
<body>
	<form type="">
		<!--  <input type="hidden" name="action" value="insert"> -->
		제목 : <br>
		<textarea id="b_title" rows="2" cols="30" name="b_title">${ requestScope.updatePage.title }</textarea>
		<br>

	</form>
	 Writer : ${ requestScope.updatePage.writer }
	<br> Writedate : ${ requestScope.updatePage.writedate }
	<br> Count : ${ requestScope.updatePage.cnt }
	<br>
	<form type="">
		<input type="hidden" name="action" value="insert"> 
	 <br>
	 내용 : <br>
		<textarea id="b_content" rows="4" cols="30" name="b_content">${ requestScope.updatePage.content }</textarea>

	</form>
	<br>

</body>
</html>