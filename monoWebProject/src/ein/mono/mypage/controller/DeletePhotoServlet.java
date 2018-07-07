package ein.mono.mypage.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ein.mono.mypage.model.service.MypageService;

/**
 * Servlet implementation class DeletePhotoServlet
 */
@WebServlet("/deletePhoto.do")
public class DeletePhotoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeletePhotoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String chk = request.getParameter("chk");
		String mCode = request.getParameter("mCode");
		
		HashMap<Integer, String> list = new MypageService().getConstList("C_1");
		
		String[] chkList = chk.split(",");
		
		String root = request.getServletContext().getRealPath("/");
		String path = root + "upload/const_photo/";
		
		int[] keys = new int[chkList.length];
		
		for(int i = 0;i<chkList.length;i++){
			
			keys[i] = Integer.parseInt(chkList[i]);
			
			for(int key : list.keySet()){
				if(key == keys[i]){
					String filname = list.get(key);
					System.out.println(filname);
					
					File file = new File(path+filname);
					
					if( file.exists() ){
						if(file.delete()){
							System.out.println("파일삭제 성공");
						}else{
							System.out.println("파일삭제 실패");
						}
					}else{
						System.out.println("파일이 존재하지 않습니다.");
					}
				}
			}
		}
		
		int result = new MypageService().deletePhoto(mCode,chk);
		

		if(0 < result){
			response.sendRedirect("/mono/constList.do");
		}else{
			System.out.println("error");
		}
	}

}
