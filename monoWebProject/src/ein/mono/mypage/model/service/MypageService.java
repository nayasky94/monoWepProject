package ein.mono.mypage.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;
import ein.mono.mypage.model.dao.MypageDao;
import ein.mono.partners.model.dao.PartnersDao;
import ein.mono.profil.model.vo.ProfileVo;
import ein.mono.qna.model.dao.QnADao;
import ein.mono.qna.model.vo.QnAVo;
import ein.mono.report.model.vo.ReportVo;
import ein.mono.request.model.vo.RequestVo;

public class MypageService {
	public ArrayList getFBList(String mCode,String fbType){
		return null;
	}
	public int removeFB(String mCode,String tCode, String fbType){
		return 0;		
	}
	public ArrayList<PostVo> getPostList(String mCode){
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<PostVo> list = new MypageDao().selectPostList(con, mCode);
		
		JDBCTemplate.close(con);
		
		return list;
	}
	public HashMap<Integer, String> getConstList(String mCode){
		Connection con = JDBCTemplate.getConnection();
		
		HashMap<Integer, String> photo = new PartnersDao().selectConstPhoto(con, mCode);
		
		JDBCTemplate.close(con);
		
		return photo;
	}
	public ArrayList<PostVo> getQnaList(String mCode){
		Connection con = JDBCTemplate.getConnection();
		ArrayList<PostVo> list = new MypageDao().selectQnaList(con,mCode);
		JDBCTemplate.close(con);
		return list;
	}
	public ArrayList<RequestVo> getOderList(String mCode) {
		Connection con = JDBCTemplate.getConnection();
		ArrayList<RequestVo> list = new MypageDao().selectOderList(con,mCode);
		JDBCTemplate.close(con);
		return list;
	}
}
