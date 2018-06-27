<%@page import="java.util.ArrayList"%>
<%@page import="ein.mono.request.model.vo.RequestVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<RequestVo> list = (ArrayList<RequestVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인테리어 신청 내역</title>
<style>
	#aside{
		width:200px;
		height:100px;
	}
	.outer{
			width:800px;
			height:500px;
			margin-left:auto;
			margin-right:auto;
			padding:20px;
	}
	
</style>
</head>
<body>
<%@include file="/views/common/header.jsp" %>
<div id="aside">
	<%@include file="/views/mypage/maside.jsp" %>
</div>
<div class="outer">
	<h3>인테리어 신청 내역</h3>
	<table>
		<tr>
			<th>no</th><th>작성일</th><th>분류</th><th>견적명</th><th>평수</th><th>현황</th>			
		</tr>		
		<%if(null != list){ %>
			<%for(int i = 0; i<list.size();i++){ %>
				<tr>
					<td>i+1 </td>
					<td><%=list.get(i).getStartDate() %></td>
					<td><%=list.get(i).getReqType() %></td>
					<td><%=list.get(i).getReqTitle()%></td>
					<td><%=list.get(i).getSize() %></td>
					<td><%=list.get(i).getReqCheck() %></td>				
				</tr>
			<%} %>
		<%}else{ %>
			<tr>
				<td colspan="6">조회 된 내역이 없습니다.</td>
			</tr>
		<%} %>
	</table>
</div>

</body>
</html>