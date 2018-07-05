<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<PostVo> list = (ArrayList<PostVo>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사진 게시판</title>
<script type="text/javascript" src="/mono/js/jquery-3.3.1.min.js"></script>
<style>
	.outer{
			width:1000px;
			height:500px;
			margin-left:auto;
			margin-right:auto;
		}

</style>
</head>
<body>
<%@ include file="../common/header.jsp" %>
	<div class="outer">
		<h1>이미지 게시판</h1>
		<div class="thumnailArea">
			<%for(int i = 0;i < list.size(); i++){ %>
				<%PostVo av = list.get(i); %>
				<div class="image-list" onclick="attachDetailPage(<%=av.getPost_code()%>);" align="center">
					<div>
						<%=av.getContent()%>
					</div>
					<p>
						<%=av.getNum() %>. <%=av.getTitle() %><br>
						조회수 : <%=av.getViews_count() %>
					</p>
				</div>
			<%} %>
		</div>
	</div>
	<button id="writeBtn">글 작성</button>
	<script>
		$("#writeBtn").click(function(){
			location.href = "/mono/views/board/writeGalleryBoard.jsp";
		});
	</script>

</body>
</html>