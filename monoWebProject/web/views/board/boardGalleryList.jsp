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
		text-align:center;
		}
	.image-list{
		width:240px;
		height:280px;
		border:1px solid black;
		display:inline-block;
		margin:10px;
		align:center;
	}
	.image-list:hover{
		cursor:pointer;
	}
	#nullDiv{
		width:240px;
		height:200px;
	}
	.thumnailArea{
		display:inline-block;
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
				<div class="image-list" onclick="attachDetailPage('<%=av.getPost_code()%>');">
					<div class="imgDiv">
						<%if(av.getContent().length() < 5){ %>
							<div id="nullDiv"></div>
						<%}else{ %>
							<%=av.getContent()%>
						<%} %>
					</div>
					<p>
						<%=av.getNum() %>. <%=av.getTitle() %><br>
						조회수 : <%=av.getViews_count() %>
					</p>
				</div>
			<%} %>
		</div>
	<button id="writeBtn">글 작성</button>
	<br><br><br><br><br><br>
	</div>
	<script>
		$("#writeBtn").click(function(){
			location.href = "/mono/views/board/writeGalleryBoard.jsp";
		});
		function attachDetailPage(pCode){
			location.href = "/mono/detailGallery.do?pCode="+pCode;
		}
		$(function(){
			$(".imgDiv img").css("width",240).css("height",200);
		});
		
	</script>

</body>
</html>