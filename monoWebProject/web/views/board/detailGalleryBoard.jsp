<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@page import="ein.mono.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo member = (MemberVo) session.getAttribute("");
	PostVo post = (PostVo)request.getAttribute("post");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
.outer {
	width: 1000px;
	height: 500px;
	margin-left: auto;
	margin-right: auto;
}

#sky {
	width: 1000px;
	height: 30px;
	border: solid 1px gray;
	margin-left: auto;
	margin-right: auto;
}

#btnDiv {
	width: 90px;
	height: 40px;
	margin-left: auto;
	margin-right: 350px;
}

#titleDiv {
	border: 1px solid gray;
	font-size: 20px;
}
</style>
<script>
	function modifyGallery(){
		location.href = "/mono/modifyBoardForm.do?pCode=<%=post.getPost_code()%>";
	}
</script>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br>
	<br>
	<br>
	<h1 align="center">마이룸 게시글 보기</h1>
	<div class="outer">
		<table id="sky">
			<tr>
				<th colspan="2">글 제목</th><td colsapn="4"><%=post.getTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th><td><%=post.getWriter_nickname() %></td>
				<th>작성일</th><td><%=post.getWritten_date() %></td>
				<th>조회수</th><td><%=post.getViews_count() %></td>			
			</tr>
			<tr>
				<td colspan="6"><%=post.getContent() %></td>
			</tr>
		</table>
		<button onclick="modifyGallery();">수정</button>
		<br><br><br><br><br>
	</div>

</body>
</html>