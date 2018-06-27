package ein.mono.partners.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.partners.model.service.PartnersService;
import ein.mono.partners.model.vo.PartnersVo;

/**
 * Servlet implementation class SelectPartnersListMainServlet
 */
@WebServlet("/selectPartnersListMain.do")
public class SelectPartnersListMainServlet extends HttpServlet { // 회원이 업체지정 들어왔을 때 뜨는 메인 페이지
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectPartnersListMainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		ArrayList<PartnersVo> list = new PartnersService().selectPartnersListMain();
		// 메인 페이지는 앞에서 3개만 프리뷰로 보여준다.
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
}
