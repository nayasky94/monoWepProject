package ein.mono.qna.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;
import ein.mono.mypage.model.dao.MypageDao;
import ein.mono.qna.model.vo.QnAVo;

public class QnADao {
	Properties prop = new Properties();
	public QnADao(){
		String filename = MypageDao.class.getResource("/qna/qna_sql.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ArrayList<QnAVo> selectQnaList(Connection con){
		return null;
	}
	public PostVo selectQna(Connection con,String qCode){
		PreparedStatement pstmt = null;
		PostVo result = new PostVo();
		ResultSet rs = null;
		String query = "";
		
		query = prop.getProperty("selectQnaList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1,qCode);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){			
				result.setPost_code(qCode);
				result.setnName(rs.getString("member_nname"));
				result.setTitle(rs.getString("title"));
				result.setContent(rs.getString("content"));
				result.setViews_count(rs.getInt("views_count"));
				result.setWritten_date(rs.getDate("written_date"));
				result.setPtn_name(rs.getString("member_name"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	public int insertQuestion(Connection con,QnAVo question){
		return 0;
	}
	
	public int insertAnswer(Connection con,QnAVo answer){
		return 0;
	}

}
