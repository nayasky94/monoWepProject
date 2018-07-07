package ein.mono.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.board.model.service.PostService;
import ein.mono.board.model.service.ReplyService;
import ein.mono.board.model.vo.PostVo;
import ein.mono.common.PageInfo;

@WebServlet("/selectPostList.do")
public class SelectPostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public SelectPostListServlet() {
        super();
    } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String post_type = request.getParameter("posttype");
		
//		//페이징 처리 변수
//		int currentPage;	//현재 페이지의 번호
//		int limitPage;		//한페이지에 출력할 페이지 갯수
//		//1~10
//		int maxPage;		//가장 마지막 페이지
//		int startPage;		//시작 페이지 변수
//		int endPage;		//마지막 페이지 변수
//		int limit;				//한페이지에 출력할 글에 갯수
//		
//		limit = 10;
//		limitPage = 10;
//		
//		if(request.getParameter("currentPage") != null){
//			currentPage = Integer.parseInt(request.getParameter("currentPage"));
//		}else{
//			currentPage = 1;
//		}
//		
//		//게시글의 총 갯수
//		int listCount = new PostService().selectPostTotalCount(post_type);
//		//134 -> 14
//		maxPage = (int)((double)listCount / limit + 0.9);
//		
//		//현재 페이지 번호
//		//12 - 10
//		startPage = (int)(currentPage / limitPage * limitPage) + 1;
//		
//		//11~20  -> 134 -> 14
//		endPage = startPage + limitPage - 1;
//		if(maxPage < endPage){
//			endPage = maxPage;
//		}
//		
//		PageInfo pi = new PageInfo(currentPage, limitPage, maxPage,startPage, endPage, listCount);
	
		
		ArrayList<PostVo> postList = null;
		// 썸네일 이미지로 사용할 첨부파일 리스트 가져와서 화면에 전달.
		
		String url="";
		
		postList = new PostService().selectPostList(post_type);
		if(post_type.equals("SHO")) {
			if(0 <= postList.size()){
				url = "views/post/shoPostList.jsp";
				request.setAttribute("list", postList);
				request.setAttribute("pType", post_type);				
			}else{
				url = "views/post/errorPage.jsp";
				request.setAttribute("list", postList);
			}
		}else if(post_type.equals("FRE")){
			if(0 <= postList.size()){
				url = "views/post/frePostList.jsp";
				request.setAttribute("list", postList);
				request.setAttribute("pType", post_type);
				
			}else{
				url = "views/post/errorPage.jsp";
				request.setAttribute("list", postList);
			}
		}else if(post_type.equals("REV")){
			if(0 <= postList.size()){
				url = "views/post/revPostList.jsp";
				request.setAttribute("list", postList);
				request.setAttribute("pType", post_type);
				
			}else{
				url = "views/post/errorPage.jsp";
				request.setAttribute("list", postList);
			}
		}else if(post_type.equals("MAR")){
			if(0 <= postList.size()){
				url = "views/post/marketPostList.jsp";
				request.setAttribute("list", postList);
				request.setAttribute("pType", post_type);
				
			}else{
				url = "views/post/errorPage.jsp";
				request.setAttribute("list", postList);
			}
		}else {
			// 에러 페이지로 이동
			url = "views/post/errorPage.jsp";
			request.setAttribute("list", postList);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}

