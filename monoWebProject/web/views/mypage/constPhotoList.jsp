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
<script type="text/javascript" src="/mono/js/jquery-3.3.1.min.js"></script>
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

</head>
<body>
<%@include file="/views/common/header.jsp" %>
	<div id="aside">
		<%@include file="/views/mypage/maside.jsp" %>
	</div>
	<div class="outer">
		<%for(int i =0;i<list.size();i++){ %>
			<%if(null != list.get(i)){ %>
				<div class="photoDiv">
					<input type="checkbox" name="chk" value="<%=i%>">
					<img class="imgFile" src = "/mono/upload/const_photo/<%=list.get(i)%>" >
				</div>
			<%}else{ %>
				<div class="photoDiv"></div>
			<%} %>
		<%} %>
		<form action="/mono/photoInsert.do?num=<%=list.size()%>" method="post" enctype="Multipart/form-data">
			<input type="file" name="fileName">
			<input type="submit" value="사진 업로드">
		</form>
		<button id="deleteBtn">사진 삭제</button>
	</div>
	<script>
	$(function(){
		$("#deleteBtn").click(function(){
			var list = [];
			$("input[name='chk']").each(function(){
				if($(this).prop("checked")){
					list.push($(this).val());
				}
			});
			var str = list.join(",");
			console.log(str);
			location.href = "/mono/deletePhoto.do?chk="+str+"&mCode=P_2";
			
		});
	});
	
	</script>
	
</body>
</html>
















