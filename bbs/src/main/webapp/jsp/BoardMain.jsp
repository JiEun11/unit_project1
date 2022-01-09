<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.BoardVO, model.dao.BoardDAO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/3e595d06f7.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="css/boardStyle.css" />
<title>의진 +지은</title>
</head>
<body>
<%
	BoardDAO dao = new BoardDAO();
	int count = dao.pageCnt("board");
	ArrayList<BoardVO> blist = (ArrayList<BoardVO>)request.getAttribute("list");
	if(blist != null){
%>
	<!-- Navbar -->
	<nav id="navbar">
		<div class="nav_logo">
			<i class="fas fa-birthday-cake"></i>
			<h2>Board Main Page</h2>
		</div>

		<div class="nav_login">
			<ul class="nav_login">

				<%
				if (session.getAttribute("user") == null) {
				%>
				<form method="post" action="/bbs/jsp/LogIn.jsp">
					<button type="submit" class="nav_login" name="mem_btn"
						value="login">로그인/회원가입</button>
				</form>

				<%
				} else {
				%>

				id : ${sessionScope.user.id }

				<form method="post" action="/bbs/memcheck">
					<button type="submit" class="nav_login" name="mem_btn"
						value="mem_info">개인정보설정</button>
					<button type="submit" class="nav_login" name="mem_btn"
						value="logout">로그아웃</button>
				</form>
				<%
				}
				%>
			</ul>
		</div>
		<hr>
	</nav>
	<table>
	<tr>
	<th>글 번호</th><th>작성자</th><th>작성자</th><th>작성일</th><th>조회수</th>
	</tr>
<%
	for(BoardVO vo : blist){
%>
	
	
	<tr>
	<td> <%= vo.getNum() %> </td>
	<td> <%= vo.getWriter() %></td>
	<td><a href="/bbs/detail?num=<%= vo.getNum() %>"><%= vo.getTitle() %></a></td>
	<td><%= vo.getWritedate() %></td>
	<td><%= vo.getCnt() %></td>
	</tr>
<%
	}
%>
	</table>
<%
	}
	if(request.getAttribute("msg") != null){
%>	
	<script>
		alert('${ msg }');
	</script>
<%
	}
%>
<%
	for(int i=1; i<= count; i++){ %>
		<a href="/bbs/board?page=<%= i%>">[<%= i %>]</a>
		
	<%}; %>	
<hr>

<form method="get" action="/bbs/detail">
	<button type="submit" name="detail_btn" value="write">작성</button>	
</form>

<form method="get" action="/bbs/board">
	<select name="search_tag">
		<option value="title">제목</option>
		<option value="writer">작성자</option>
		<option value="content">내용</option>
	</select>
	<input type="search" name="keyword">
	<button type="submit"name="detail_btn" value="search">검색</button> 
</form>

</body>
</html>