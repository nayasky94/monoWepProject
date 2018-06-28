<%@page import="ein.mono.request.model.vo.RequestVo"%>
<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<RequestVo> list = (ArrayList<RequestVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	#aside{
		width:200px;
		height:100px;
	}
	.outer{
			width:500px;
			height:500px;
			margin-left:auto;
			margin-right:auto;
			padding:20px;
	}
</style>
</head>
<body>
<%@include file="/views/common/header.jsp"%>
	<div id="aside">
		<%@include file="/views/mypage/maside.jsp"%>
	</div>
	<div class="outer">
		<h3>내가 쓴글</h3>
		<table align="center">
			<tr>
				<th>no</th><th>작성일</th><th>견적명</th><th>평형</th><th>작성자</th>
			</tr>
			<%if (null != list) {%>
				<%for (int i = 0; i < list.size(); i++) {%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=list.get(i).getStartDate() %></td>
					<td><a href="#"><%=list.get(i).getConstAddress()%></a></td>
					<td><%=list.get(i).getAcreage()%></td>
					<td><%=list.get(i).getnName()%></td>
				</tr>
				<%}%>
			<%} else {%>
				<tr>
					<td colspan="5">조회 된 내역이 없습니다.</td>
				</tr>
			<%}%>
		</table>
	</div>


</body>
</html>