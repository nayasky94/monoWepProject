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
import ein.mono.qna.model.vo.QnAVo;

/**
 * Servlet implementation class MyQnAListServlet
 */
@WebServlet("/myQnaList.do")
public class MyQnAListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQnAListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mCode = request.getParameter("member_code");
		
		ArrayList<PostVo> list = new MypageService().getQnaList("P_2");
		

		RequestDispatcher view = null;
		if(0 != list.size()){
			request.setAttribute("list", list);
			view = request.getRequestDispatcher("views/mypage/myQnAList.jsp");
		}else{
			System.out.println("error");
		}
		view.forward(request, response);
	}

}
