<%@page import="ein.mono.member.model.vo.MemberVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberVo member = (MemberVo) session.getAttribute("");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 게시판 글쓰기</title>
<script type="text/javascript" src="/mono/js/jquery-3.3.1.min.js"></script>
<!-- Main Quill library -->
<script src="//cdn.quilljs.com/1.3.6/quill.js"></script>
<script src="//cdn.quilljs.com/1.3.6/quill.min.js"></script>

<!-- Theme included stylesheets -->
<link href="//cdn.quilljs.com/1.3.6/quill.snow.css" rel="stylesheet">
<link href="//cdn.quilljs.com/1.3.6/quill.bubble.css" rel="stylesheet">

<!-- Core build with no theme, formatting, non-essential modules -->
<link href="//cdn.quilljs.com/1.3.6/quill.core.css" rel="stylesheet">
<script src="//cdn.quilljs.com/1.3.6/quill.core.js"></script>

<!-- Include the Quill library -->
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<link href="https://cdn.quilljs.com/1.3.6/quill.snow.css"
	rel="stylesheet">

<style>
#editor-container {
	width: 1000px;
	height: 400px;
	margin-left: auto;
	margin-right: auto;
}

.ql-toolbar, .ql-snow {
	width: 1000px;
	height: 50px;
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
</style>

</head>
<body>
	<%@ include file="../common/header.jsp"%>
	<br>
	<br>
	<br>
	<h1 align="center">마이룸 게시글 쓰기</h1>
	<form action="/mono/writeGallery.do" method="post" enctype="multipart/form-data">
		<table id="sky">
			<tr>
				<th>글 제목</th>
				<td><input type="text" size="100" name="titleText"></td>
			</tr>
		</table>
		<!-- Include stylesheet -->

		<!-- Create the editor container -->
		<div id="editor-container" id="content"></div>

		<br> <br>
		<div id="btnDiv">
			<input type="submit" value="작성완료">
		</div>
	</form>

	<!-- Initialize Quill editor -->
	<script>
		var quill = new Quill('#editor-container', {
			modules : {
				toolbar : [ [ {
					header : [ 1, 2, false ]
				} ], [ 'bold', 'italic', 'underline' ],
						[ 'image', 'code-block' ] ]
			},
			placeholder : 'Compose an epic...',
			theme : 'snow' // or 'bubble'

		});
	</script>
</body>
</html>