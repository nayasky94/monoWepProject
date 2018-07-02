package ein.mono.qna.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.board.model.vo.PostVo;
import ein.mono.qna.model.service.QnAService;

/**
 * Servlet implementation class QnADetailServlet
 */
@WebServlet("/qnaDetail.do")
public class QnADetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QnADetailServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String qCode = request.getParameter("code");
		
		PostVo result = new QnAService().getQna(qCode);
		System.out.println(qCode);
		
		RequestDispatcher view = null;
		if(null != result){
			//조회 성공 -> 해당 detail view로 이동
			request.setAttribute("result", result);
			view = request.getRequestDispatcher("views/qna/qnaDetail.jsp");
		}else{
			System.out.println("error");
		}
		view.forward(request, response);
	}
}
