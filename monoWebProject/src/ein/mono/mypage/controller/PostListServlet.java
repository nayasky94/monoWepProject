package ein.mono.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.board.model.vo.PostVo;
import ein.mono.mypage.model.service.MypageService;

/**
 * Servlet implementation class PostListServlet
 */
@WebServlet("/postList.do")
public class PostListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public PostListServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mCode = request.getParameter("mCode");
		
		ArrayList<PostVo> list = new MypageService().getPostList("B_1");
		
		RequestDispatcher view = null;
		if(0 != list.size()){
			request.setAttribute("list", list);
			view = request.getRequestDispatcher("views/mypage/myBoard.jsp");
		}else{
			System.out.println("error");
		}
		view.forward(request, response);
		
		
	}


}
