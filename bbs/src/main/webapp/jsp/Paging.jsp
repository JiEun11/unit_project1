<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.BoardVO, model.dao.BoardDAO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<style>
* {
	font-family:"Poor Story";
}

td{
	border-bottom: 2px dotted green;
	padding-left:20px;
}
tr:hover{
	background-color: pink;
	font-weight: bold;
}
td: nth-child(2){
	width: 300px;
}

li{
	display: inline;
}
form{

	display: inline-block;
}
</style>
<title>Pagination.jsp</title>
</head>
<body>
	<%
		BoardDAO dao = new BoardDAO();
		int count = dao.pageCnt("board");
	/* 	String tempStart = request.getParameter("page");
		
		// 현재 페이지를 받아옴.
		int startPage = 0;  // limit 시작값, 첫 limit 0, 10
		int onePageCnt = 10; // 한 페이지에 출력할 행의 개수
		
		count = (int)Math.ceil((double)count/(double)onePageCnt);
		
		//페이지 수 저장 
		if(tempStart != null){
			// 처음에는 실행 되지 않는다.
			startPage = (Integer.parseInt(tempStart)-1)*onePageCnt;
		} */
		
		//ArrayList<BoardVO> blist = dao.pagenation("board", startPage, onePageCnt);
		ArrayList<BoardVO> blist = (ArrayList<BoardVO>)request.getAttribute("list");
		if(blist!=null){
	%>
	<!-- Navbar -->
	<nav id="navbar">
	<div class="nav_logo">
		<i class="fas fa-birthday-cake"></i>
		<h2>Board Main Page</h2>
	</div>
		
		<div class="nav_login">
			<ul class="nav_login">
				<li class="nav_login">Login</li>
				<li class="nav_login">Sign</li>
			</ul>
		</div>
		<hr>
	</nav>
	<table>
	
<%
	for(int i=0; i< blist.size(); i++){
%>
	
	<tr>
	<th>글 번호</th><th>작성자</th><th>작성자</th><th>작성일</th><th>조회수</th>
	<td> <%= blist.get(i).getNum() %> </td>
	<td> <%= blist.get(i).getWriter() %></td>
	<td><a href="/bbs/detail?num=<%= blist.get(i).getNum() %>"><%= blist.get(i).getTitle() %></a></td>
	<td><%= blist.get(i).getWritedate() %></td>
	<td><%= blist.get(i).getCnt() %></td>
	</tr>
<%
	}
%>
	</table><br>
<%
	for(int i=1; i<=count; i++){ %>
		<a href="/bbs/board?page=<%= i%>">[<%= i %>]</a>
		
	<%}; %>	

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