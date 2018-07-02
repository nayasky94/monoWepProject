<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@page import="ein.mono.qna.model.vo.QnAVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ArrayList<PostVo> list = (ArrayList<PostVo>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>QnA 리스트</title>
<style>
#aside {
	width: 200px;
	height: 100px;
}

.outer {
	width: 500px;
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
		<h3>주문 확인</h3>
		<table align="center">
			<tr>
				<th>no</th>
				<th>질문</th>
				<th>작성자</th>
				<th>작성일</th>
				<th>조회수</th>
			</tr>
			<%if (null != list) {%>
				<%for (int i = 0; i < list.size(); i++) {%>
				<tr>
					<td><%=i + 1%></td>
					<td><a href="/mono/qnaDetail.do?code=<%=list.get(i).getPost_code()%>"><%=list.get(i).getTitle()%></a></td>
					<td><%=list.get(i).getnName()%></td>
					<td><%=list.get(i).getWritten_date() %>
					<td><%=list.get(i).getViews_count() %></td>
				</tr>
				<%}	%>
			<%} else {%>
			<tr>
				<td colspan="5">조회 된 내역이 없습니다.</td>
			</tr>
			<%}%>
		</table>
	</div>


</body>
</html>