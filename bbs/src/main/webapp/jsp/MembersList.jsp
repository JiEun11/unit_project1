<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.MembersVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="../css/boardStyle.css" />
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<meta charset="UTF-8">
<title>MemberList.jsp</title>
</head>
<body>
<%
	ArrayList<MembersVO> mlist = (ArrayList<MembersVO>)request.getAttribute("mlist");
	if(mlist != null){
%>
	<div class="memberlistpage">
	<h1>"회원 목록"</h1>
	<table>
	
	<tr>
	<th>ID</th><th>NAME</th><th>PASSWORD</th>
	</tr>
	
<%
	for(MembersVO vo : mlist){
%>
	
	<tr>
	<td> <%= vo.getId() %> </td>
	<td> <%= vo.getName() %></td>
	<td> <%= vo.getPassword() %></td>
	</tr>
<%
	}
%>

<% 
	}
%>
	</table>

<form method="get" action="/bbs/board">
	<button type="submit" name="detail_btn" value="main">메인 페이지</button>	
</form>
</div>
<jsp:include page="./bottom.jsp" flush='false' />
</body>
</html>