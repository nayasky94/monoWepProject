<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	HashMap<Integer, String> list = (HashMap<Integer, String>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>업체 시공 사례</title>
<style>
	#aside{
		width:200px;
		height:100px;
	}
	.outer {
		width: 500px;
		height: 500px;
		margin-left: auto;
		margin-right: auto;
		padding: 20px;
	}
	.imgFile{
		width:150px;
		height:100px;
	}
</style>
<script>
	function upload(){
		location.href = "/mono/photoInsert.do?lastNum=<%=list.size()-1%>";
	}
</script>
</head>
<body>
<%@include file="/views/common/header.jsp" %>
	<div id="aside">
		<%@include file="/views/mypage/maside.jsp" %>
	</div>
	<div class="outer">
		<%for(int i =0;i<list.size();i++){ %>
			<div>
				<img class="imgFile" src = "/mono/upload/const_photo/<%=list.get(i)%>" >
			</div>		
		<%} %>
		
		<input type="button" value="사진 업로드" onclick="upload();">
	</div>
</body>
</html>
















