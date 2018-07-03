package ein.mono.mypage.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.oreilly.servlet.MultipartRequest;

import ein.mono.common.MyRenamePolicy;
import ein.mono.mypage.model.service.MypageService;
import ein.mono.profil.model.vo.ProfileVo;

/**
 * Servlet implementation class PhotoInsertServlet
 */
@WebServlet("/photoInsert.do")
public class PhotoInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PhotoInsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int maxSize = 1024*1024*10;
		
		RequestDispatcher view = null;
		if(!ServletFileUpload.isMultipartContent(request)){
			System.out.println("error");
//			view = request.getRequestDispatcher("views/common/errorPage.jsp");
//			request.setAttribute("msg", "전송 데이터의 타입을 확인하십시오!!");
//			view.forward(request, response);
		}
		String root = request.getServletContext().getRealPath("/");
		String path = root + "upload/const_photo/";
		
		MultipartRequest mRequest = new MultipartRequest(request, path,maxSize,"UTF-8",new MyRenamePolicy());
		
		int num = Integer.parseInt(mRequest.getParameter("num"));
		//String mCode = mRequest.getParameter("mCode");
		String newName = mRequest.getFilesystemName("fileName");
		String oldName = mRequest.getOriginalFileName("fileName");
		System.out.println(newName);
		System.out.println(oldName);
		int result = new MypageService().insertPhoto("P_2",num,newName,oldName);
		
		System.out.println(result);
		if(0 < result){
			response.sendRedirect("/mono/constList.do");
		}else{
			System.out.println("error");
		}
		
	}

}
