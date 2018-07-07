<%@page import="ein.mono.board.model.vo.ReplyVo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ein.mono.board.model.vo.PostVo"%>
<%@page import="ein.mono.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo member = (MemberVo) session.getAttribute("");
	PostVo post = (PostVo) request.getAttribute("post");
	ArrayList<ReplyVo> reply = (ArrayList<ReplyVo>) request.getAttribute("reply");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<script type="text/javascript" src="/mono/js/jquery-3.3.1.min.js"></script>
<style>
.outer {
	width: 1000px;
	height: auto;
	margin-left: auto;
	margin-right: auto;
}

#sky {
	width: 1000px;
	height: 30px;
	border: solid 1px gray;
	margin-left: auto;
	margin-right: auto;
}

#btnDiv {
	width: 90px;
	height: 40px;
	margin-left: auto;
	margin-right: 350px;
}

#titleDiv {
	border: 1px solid gray;
	font-size: 20px;
}

#replyDiv, #writeDiv {
	width: 1000px;
	height: auto;
	margin-left: auto;
	margin-right: auto;
}

#replyBtn {
	width: auto;
	height: 50px;
}
</style>
<script>
	function modifyPost(){
		location.href = "/mono/modifyPostForm.do?pCode=<%=post.getPost_code()%>";
	}
	function removePost(){
		location.href = "/mono/removePost.do?pCode=<%=post.getPost_code()%>&pType=<%=post.getPost_type()%>";
	}
	function reportPost(){
		location.href = "/mono/insertReport.do?reported=<%=post.getWriter_code()%>&post_code=<%=post.getPost_code()%>";
	}
	function reportBtn(rCode){
		location.href = "/mono/insertReport.do?reported=<%=post.getWriter_code()%>&reply_code="+rCode;
	}
	$(function(){
		$("#replyBtn").click(function(){
			 var remove = $("div#replyTable").remove();
			 $("#replyTable").html(remove);
			<%--var mCode = <%=member.getMemberCode()%>;--%>
			var pCode = "<%=post.getPost_code()%>";
			var content = $("#contents").val();
			$.ajax({
				url : "/mono/insertReply.do",
				type : "get",
				data : { content : content , pCode : pCode},
				success : function(data){
					console.log(data.length);
					var str = "";
					if(data.length > 0){
						 for(i=0; i<data.length; i++){
							 str += "<table>";
							 str += "<tr><th>"+data[i].member_nName+"</th><td>"+data[i].reply_date+"</td>";
							 str += "<td><div><a href = '/mono/insertReport.do?reported="+data[i].writer_code+"&reply_code="+data[i].reply_code+"';>신고</a></div></td></tr>";
							 str += "<tr><td colspan='3'>"+data[i].reply_content+"</td></tr>";
							 str += "</table>";
							 str += "<div>";
			             }			                
					} else {			                
			            str += "<table>";
			            str += "<tr><th colspan='3'>등록 된 댓글이 없습니다.</th></tr>";
			            str += "</table>";			                
			        }
					$("#replyDiv").html(str);
					$("#contents").val("");
				},error : function(data){
					console.log(data);
				}
			});
		});
	});
	
</script>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br>
	<br>
	<br>
	<h1 align="center">마이룸 게시글 보기</h1>
	<div class="outer">
		<table id="sky">
			<tr>
				<th colspan="2">글 제목</th>
				<td colsapn="4"><%=post.getTitle()%></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%=post.getWriter_nickname()%></td>
				<th>작성일</th>
				<td><%=post.getWritten_date()%></td>
				<th>조회수</th>
				<td><%=post.getViews_count()%></td>
			</tr>
			<tr>
				<td colspan="6"><%=post.getContent()%></td>
			</tr>
		</table>
		<button onclick="modifyPost();">수정</button>
		<button onclick="removePost();">삭제</button>
		<button onclick="reportPost();">신고</button>
		<br> <br> <br>
	</div>
	<div id="replyDiv">
		<table id="replyTable">
			<%
				for (int i = 0; i < reply.size(); i++) {
			%>
			<tr>
				<th><%=reply.get(i).getMember_nName()%></th>
				<td><%=reply.get(i).getReply_date()%></td>
				<td><div><a href = "/mono/insertReport.do?reported=<%=reply.get(i).getWriter_code()%>&reply_code=<%=reply.get(i).getReply_code()%>;">신고</a></div></td>
			</tr>
			<tr>
				<td colspan="3"><%=reply.get(i).getReply_content()%></td>
			</tr>
			<%
				}
			%>
		</table>

	</div>
	<div id="writeDiv">
		댓글 쓰기<br>
		<textarea rows="3" cols="120" id="contents"></textarea>
		<button id="replyBtn">댓글 작성</button>
	</div>

</body>
</html>