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
	<td class='<%= vo.getNum() %>'> <%= vo.getNum() %> </td>
	<td class='<%= vo.getNum() %>'> <%= vo.getWriter() %></td>
	<td class='<%= vo.getNum() %>'><a href="/bbs/detail?num=<%= vo.getNum() %>">
									<%= vo.getTitle() %></a></td>
	<td class='<%= vo.getNum() %>'><%= vo.getWritedate() %></td>
	<td class='<%= vo.getNum() %>'><%= vo.getCnt() %></td>
	<td><a href = '/bbs/board?action=delete&num=<%= vo.getNum() %>'>
	<img src = "/bbs/images/delete.png" width = '30'></a></td>
	<td><img onclick="displayUpdateForm('<%= vo.getNum() %>')" 
			       src="/bbs/images/edit.png" width = '30'></td>
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

</body>
</html>