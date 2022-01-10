<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.vo.BoardVO, java.util.ArrayList, model.dao.BoardDAO, model.vo.PageVO"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="https://fonts.googleapis.com/css?family=Poor+Story:400"
	rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.2.0/css/all.min.css" integrity="sha512-6c4nX2tn5KbzeBJo9Ywpa0Gkt+mzCzJBrE1RB6fmpcsoN+b/w/euwIMuQKNyUoU/nToKN3a8SgNOtPrbW12fug==" crossorigin="anonymous" />
<link rel="stylesheet" href="css/boardStyle.css" />
<title>What's your fav restaurant? </title>
</head>
<body>

<%
	PageVO voPage = (PageVO)session.getAttribute("page");
	int count = voPage.getCount();
	//BoardDAO dao = new BoardDAO();
	//int count = (Integer)request.getAttribute("count");
	//System.out.println(count);
	ArrayList<BoardVO> blist = (ArrayList<BoardVO>)request.getAttribute("list");
	if(blist != null){
%>
<!-- Navbar -->
<div id="navbar">
	<a href="/bbs/board"><img src="${pageContext.request.contextPath }/images/logo-removebg.png" /></a>
	<div class="nav_login">

		<%
			if (session.getAttribute("user") == null) {
		%>
		<form method="post" action="/bbs/jsp/LogIn.jsp">
			<button type="submit" class="nav_login" name="mem_btn" value="login">로그인/회원가입</button>
		</form>

		<%
		} else {
		%>
		<i class="fas fa-user-circle" style="font-size: 40px; color: #4caf50;"></i> ID : ${sessionScope.user.id }
		<br>
		<form method="post" action="/bbs/board">
			<button type="submit" class="nav_login" name="mem_fbtn" value="mem_info">개인정보설정</button>
			<button type="submit" class="nav_login" name="mem_btn" value="logout">로그아웃</button>
		</form>
		<%
			}
		%>
	</div>
</div>
<hr>
	<table>
	<tr>
	<th>글 번호</th><th>작성자</th><th>제목</th><th>작성일</th><th>조회수</th>
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
	<div class="pagenation">
	<%
	for(int i=1; i<= count; i++){ %>
		<a href="/bbs/board?page=<%= i%>" style="text-align: center;">[<%= i %>]</a>
		
	<%}; %>	 
	</div>
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
<div class="main_btns">
	<form method="get" action="/bbs/detail">
		<button type="submit" name="detail_btn" value="write">작성</button>	
	</form>
	
	<form method="get" action="/bbs/board">
		<select name="search_tag">
			<option value="null">검색항목</option>	
			<option value="title">제목</option>
			<option value="writer">작성자</option>
			<option value="content">내용</option>
		</select>
		<input type="search" name="keyword">
		<button type="submit"name="detail_btn" value="search">검색</button> 
		<button type="submit" name="detail_btn" value="main">메인 페이지</button>	
	</form>
	
	<form method="post" action="/bbs/board">
		<button type="submit" name="mem_fbtn" value="list">회원 리스트</button>	
	</form>
</div>

<jsp:include page="./bottom.jsp" flush='false' />

</body>
</html>