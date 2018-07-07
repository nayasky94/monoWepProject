<%@page import="ein.mono.common.PageInfo"%>
<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<PostVo> list = (ArrayList<PostVo>)request.getAttribute("list");
	String pType = (String)request.getAttribute("pType");
	//PageInfo pi = (PageInfo)request.getAttribute("pi");
	//int listCount = pi.getTotalCount();
	//int currentPage = pi.getCurrentPage();
	//int maxPage = pi.getMaxPage();
	//int startPage = pi.getStartPage();
	//int endPage = pi.getEndPage();
%>     
<!DOCTYPE html>
<html> 
<head>
<script type="text/javascript" src="/mono/js/jquery-3.3.1.min.js"></script>
<meta charset="UTF-8">

<title>게시판 목록</title>
<style>
.outer{
	width:1000px;
		height:500px;
		margin-left:auto;
		margin-right:auto;
		text-align:center;
}
.tableArea{
	width:800px;
	height:300px;
	margin-left:auto;
	margin-right:auto;
 }
table td{
	text-align:center;
 }
.searchArea{
	width:650px;
	margin-left:auto;
	margin-right:auto;
 }
</style>
<script>
	function writeBoard(){
		location.href="views/board/boardForm.jsp";
	}
	function searchBoard(){
		var condition = $("#searchCondition").val();
		var searchText = $("#searchText").val();
		//쿼리 스트링(파라미터 값 작성 방법)
		//url?key1=value1&key2=value2;
		location.href = "/mono/searchPost.do?condition=" + condition+ "&keyword=" + searchText;
	}
</script>
</head>
<body>
<%@ include file="../common/header.jsp" %>

<div class="outer">
   <br>
   <br>
	<h1 align="center">자.유.게.시.판</h1>
	<div class="tableArea">
		<table align="center">
		<br>
        <br>
        <br>
        <br>		
			<tr>
				<th width="100">글번호</th>
				<th width="300">글제목</th>
				<th width="100">작성자</th>
				<th width="100">조회수</th>
				<th width="150">작성일</th>
			</tr>
			<%if(list.size()<=0){ %>
				<tr>
					<td colspan="5">조회 된 게시글이 없습니다.</td>
				</tr>
			<%}else{ %>
				<%for(PostVo n : list){ %>
				<tr>
					
					<td><%=n.getNum()%></td>			
					<td><a href="/mono/detailPost.do?pCode=<%=n.getPost_code()%>"><%=n.getTitle()%></td></a>				
					<td><%=n.getWriter_nickname()%></td>
					<td><%=n.getViews_count()%></td>			
					<td><%=n.getWritten_date() %></td>	
				</tr>
				<%} %>
			<%} %>
		</table>
	</div>	
	<!-- 페이징 처리 부분     <<	1 2 3 4 5 6 7 ... >>  -->	
	<%-- <div class="pageArea" align="center">
		<button onclick="movePage(1);"> </button>
		<%for(int i = startPage ; i <= endPage ; i++){ %>
			<%if(currentPage == i){ %>
				<button disabled><%=i %></button>
			<%}else{ %>
				<button onclick="movePage(<%=i%>);"><%=i %></button>
			<%} %>
		<%} %>
		<button onclick="movePage(<%=maxPage%>);"> >> </button>
	</div>--%>
	<br>
	<div class="searchArea" align="center">
		<select id="searchCondition">		
			<option value="0">전체</option>
			<option value="1">제목</option>
			<option value="2">내용</option>
			<option value="3">작성자</option>
		</select>
		
				
		<input type="text" id="searchText" placeholder="검색어 입력"/>
		<input type="button" value="검색하기" onclick="searchBoard();"/>
		
		<%-- <%if(null != request.getSession().getAttribute("member")){%> --%>
			<input type="button" value="작성하기" onclick="writePost();"/>
		<%-- <%} %>  --%>
	</div>
</div>
<script>
$(function(){
	$(".tableArea td").mouseenter(function(){
		$(this).parent().css("cursor","pointer");
	})
});
function movePage(pageNum){
	location.href = "/mono/selectPostList.do?currentPage=" + pageNum;
}
function writePost(){
	location.href = "/mono/views/post/writeGalleryPost.jsp?pType=<%=pType%>";
}
</script>
<%@ include file="../common/footer.jsp" %>
</body>
</html>









