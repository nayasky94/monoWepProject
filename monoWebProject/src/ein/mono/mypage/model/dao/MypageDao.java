package ein.mono.mypage.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;
import ein.mono.qna.model.vo.QnAVo;
import ein.mono.report.model.vo.ReportVo;
import ein.mono.request.model.vo.RequestVo;

public class MypageDao {
	Properties prop = new Properties();
	public MypageDao(){
		String filename = MypageDao.class.getResource("/mypage/mypage_sql.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList selectFBList(Connection con,String mCode,String fbType){
		return null;
	}
	public int deleteFB(Connection con,String mCode,String tCode, String fbType){
		return 0;
	}
	public ArrayList<PostVo> selectPostList(Connection con,String mCode){
		ArrayList<PostVo> result = new ArrayList<PostVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		query = prop.getProperty("selectPostList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mCode);
			rs = pstmt.executeQuery();
			PostVo temp = null;
			while(rs.next()){
				String title = rs.getString("title");
				int vCount = rs.getInt("views_count");
				Date wDate = rs.getDate("written_date");
				temp = new PostVo(title,vCount,wDate);
				
				result.add(temp);				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public ArrayList selectConstList(Connection con,String pCode){
		return null;
	}
	public ArrayList<PostVo> selectQnaList(Connection con,String mCode){
		ArrayList<PostVo> list = new ArrayList<PostVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		query = prop.getProperty("selectQnaList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mCode);
			
			rs = pstmt.executeQuery();
			PostVo temp = null;
			while(rs.next()){
				String qCode = rs.getString("post_code");
				String title = rs.getString("title");
				String nName = rs.getString("member_nname");
				int vCount = rs.getInt("views_count");
				Date wDate = rs.getDate("written_date");
				
				temp = new PostVo();
				temp.setPost_code(qCode);
				temp.setTitle(title);
				temp.setnName(nName);
				temp.setViews_count(vCount);
				temp.setWritten_date(wDate);
				
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public ArrayList<RequestVo> selectOderList(Connection con, String mCode) {
		ArrayList<RequestVo> list = new ArrayList<RequestVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		query = prop.getProperty("selectOderList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, mCode);
			
			rs = pstmt.executeQuery();
			RequestVo temp = null;
			while(rs.next()){
				Date aDate = rs.getDate("act_startdate");
				String cAddress = rs.getString("const_address");
				String nName = rs.getString("member_nname");
				int acreage = rs.getInt("acreage");
				
				temp = new RequestVo();
				temp.setConstAddress(cAddress);
				temp.setStartDate(aDate);
				temp.setnName(nName);
				temp.setSize(acreage);
				
				list.add(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}	
		
		return list;
	}
}
