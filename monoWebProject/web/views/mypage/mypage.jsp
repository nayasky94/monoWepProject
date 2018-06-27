<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</style>
</head>
<body>

<%@include file="/views/common/header.jsp" %>
<div id="aside">
	<%@include file="/views/mypage/maside.jsp" %>
</div>

</body>
</html>