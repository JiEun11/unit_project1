<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.vo.MembersVO, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<MembersVO> mlist = (ArrayList<MembersVO>)request.getAttribute("mlist");
	if(mlist != null){
%>

	<table>
<%
	for(MembersVO vo : mlist){
%>
	
	<tr>
	<td> <%= vo.getId() %> </td>
	<td> <%= vo.getName() %></td>
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
	
</body>
</html>