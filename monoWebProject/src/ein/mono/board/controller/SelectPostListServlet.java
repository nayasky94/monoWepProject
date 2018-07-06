package ein.mono.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.board.model.service.AttachmentService;
import ein.mono.board.model.service.PostService;
import ein.mono.board.model.vo.AttachmentVo;
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
		
		//페이징 처리 변수
		int currentPage;	//현재 페이지의 번호
		int limitPage;		//한페이지에 출력할 페이지 갯수
		//1~10
		int maxPage;		//가장 마지막 페이지
		int startPage;		//시작 페이지 변수
		int endPage;		//마지막 페이지 변수
		int limit;				//한페이지에 출력할 글에 갯수
		
		limit = 10;
		limitPage = 10;
		
		if(request.getParameter("currentPage") != null){
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}else{
			currentPage = 1;
		}
		
		//게시글의 총 갯수
		int listCount = new PostService().selectPostTotalCount(post_type);
		//134 -> 14
		maxPage = (int)((double)listCount / limit + 0.9);
		
		//현재 페이지 번호
		//12 - 10
		startPage = (int)(currentPage / limitPage * limitPage) + 1;
		
		//11~20  -> 134 -> 14
		endPage = startPage + limitPage - 1;
		if(maxPage < endPage){
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, limitPage, maxPage,startPage, endPage, listCount);
		
		
		
		
		ArrayList<PostVo> postList = null;
		ArrayList<PostVo> thumnailList = null;
		// 썸네일 이미지로 사용할 첨부파일 리스트 가져와서 화면에 전달.
		
		String url="";
		
		if(post_type.equals("SHO") || post_type.equals("후기게시판")) {
			thumnailList = new AttachmentService().selectThumnailAttachmentList(post_type);
			if(null != thumnailList){
				url = "views/board/boardGalleryList.jsp";
				request.setAttribute("list", thumnailList);
				
			}else{
				System.out.println("error");
			}
		}else if(post_type.equals("자유게시판")){
			
			url = "views/board/boardList.jsp";		
			postList = new PostService().selectPostList(post_type);			
		}else if(null != postList) {
			// 게시판 페이지로 이동
				url = "views/board/boardList.jsp";
				request.setAttribute("posttype", post_type);
				request.setAttribute("postList", postList);
				request.setAttribute("pi", pi);
		} else {
			// 에러 페이지로 이동
			url = "views/board/errorPage.jsp";
			request.setAttribute("postList", postList);
		}
		request.getRequestDispatcher(url).forward(request, response);
	}

}

