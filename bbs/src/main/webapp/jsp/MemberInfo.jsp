<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="/css/boardStyle.css" />
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<meta charset="UTF-8">
<title>MemberInfo.jsp</title>
</head>
<body>
<div class="memberinfopage">
	<h1>"개인정보 수정"</h1>
	<form method="post" action="/bbs/board">
		ID : <input readonly id="id" name="id" value=${ sessionScope.user.id }><br>
		PW : <input type="text" name="password" value=${ sessionScope.user.password } required><br>
		이름 : <input type="text" name="name" value=${ sessionScope.user.name } required><br>
		<button type="submit" name="mem_btn" value="mem_update">정보수정</button>
		<button type="reset" value="reset">재작성</button>
		<button type="submit" name="mem_btn" value="sign-out">회원탈퇴</button>
	</form>
	
	<form method="get" action="/bbs/board">
		<button type="submit" name="detail_btn" value="main">메인페이지</button>
	</form>
</div>

<jsp:include page="./bottom.jsp" flush='false' />
</body>
</html>