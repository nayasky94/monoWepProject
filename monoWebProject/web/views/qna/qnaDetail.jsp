<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	PostVo qna = (PostVo)request.getAttribute("result");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Q & A</title>
<style>
#aside {
	width: 200px;
	height: 100px;
}

.outer {
	width: 700px;
	height: 500px;
	margin-left: auto;
	margin-right: auto;
	padding: 20px;
}
</style>
</head>
<body>
	<%@include file="/views/common/header.jsp"%>
	<div id="aside">
		<%@include file="/views/mypage/maside.jsp"%>
	</div>
	<div class="outer">
		<table>
			<tr>
				<td>작성자</td>
				<td><input type="text" value="<%=qna.getnName()%>" readonly></td>
				<td>작성일</td>
				<td><input type="text" value="<%=qna.getWritten_date()%>" readonly></td>
				<td>조회수</td>
			</tr>
		
			<tr>
				<td>Question</td>
				<td colspan="3"><input type="text" size="50" value="<%=qna.getTitle() %>" readonly></td>
				<td align="center"><%=qna.getViews_count() %></td>
			</tr>
			<tr>
				<td colspan="5"><%=qna.getContent() %></td>
			</tr>
			
			
		</table>
	</div>
	

</body>
</html>












