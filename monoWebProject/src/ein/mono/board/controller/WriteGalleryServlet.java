package ein.mono.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.board.model.service.AttachmentService;

/**
 * Servlet implementation class WriteGalleryServlet
 */
@WebServlet("/writeGallery.do")
public class WriteGalleryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteGalleryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		System.out.println(content);
		String mCode = request.getParameter("mCode");
		String pType = request.getParameter("pType");
		
		int result = new AttachmentService().insertGallary(title,content,mCode,pType);
		
		System.out.println(result);	
	}

}
