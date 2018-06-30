package ein.mono.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.mypage.model.service.MypageService;
import ein.mono.qna.model.vo.QnAVo;

/**
 * Servlet implementation class QnaListServlet
 */
@WebServlet("/qnaList.do")
public class QnaListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnaListServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mCode = request.getParameter("member_code");
		mCode = "P_2";
		ArrayList<QnAVo> list = new MypageService().getQnaList(mCode);
		
		System.out.println(list.toString());
		
	}

}
