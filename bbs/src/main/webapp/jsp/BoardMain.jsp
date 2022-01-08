<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.BoardVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/3e595d06f7.js"
	crossorigin="anonymous"></script>
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
<title>의진 +지은</title>
</head>
<body>
<%
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
				<li class="nav_login">Login</li>
				<li class="nav_login">Sign</li>
			</ul>
		</div>
		<hr>
	</nav>
	<table>
	
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