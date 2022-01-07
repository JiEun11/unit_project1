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
	<td class='<%= vo.getNum() %>'><%= vo.getTitle() %></td>
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
<button onclick="displayDiv(1);"> 게시판 글 작성</button>
<button onclick="displayDiv(2);"> 게시판 글 검색</button>
<script>
function displayDiv(type) {
	if(type == 1) {
		document.getElementById("search").style.display='none';
		document.getElementById("write").style.display='block';
	} else if(type == 2) {
		document.getElementById("write").style.display='none';
		document.getElementById("search").style.display='block';
	}	
}
function displayUpdateForm(cv) {
    var doms = document.getElementsByClassName(cv)
	document.getElementById("write").style.display='block';	
	document.getElementById("b_writer").value = doms[1].textContent;
	document.getElementById("b_title").value = doms[2].textContent;
	/* document.getElementById("b_content").value = doms[3].textContent;
	var str = doms[4].textContent;
	var ary = str.split(/\D+/g)
	var meeting_dt = ary[0]+"-"+ary[1]+"-"+ary[2]+"T"+ary[3]+":"+ary[4];
	document.getElementById("b_dt").value = meeting_dt; */
	document.getElementById("divT").textContent="게시글 수정";
	document.querySelector("#write [type=submit]").value="수정";
	document.querySelector("#write [type=hidden]").value=cv;
}
</script>
<div id="write" style="display:none">
<hr>
<h2 id="divT">게시판 작성</h2>
<form method="post" action="/bbs/board">
<input type="hidden" name="action" value="insert">
작성자 : <input id = "b_writer" type = "text" name="b_writer">
<br>
제목 : <br>
<textarea id = "b_title" rows="2" cols="30" name="b_title"></textarea><br>
내용 : <br>
<textarea id = "b_content" rows="4" cols="30" name="b_content"></textarea>
<br>
<input type = "submit" value="등록">
<input type="reset" value="재작성">
</form>
</div>
<div id="search" style="display:none">
<form method = "get" action="/bbs/board">
검색어 : <input type="search" name="keyword">
<input type = "submit" value="검색">
<hr>
<button type="button" onclick="location.href='/bbs/board'">전체 게시글 보기</button>
</form>
</div>
</body>
</html>