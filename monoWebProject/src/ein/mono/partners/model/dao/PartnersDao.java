package ein.mono.partners.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import ein.mono.common.JDBCTemplate;
import ein.mono.member.model.vo.MemberVo;
import ein.mono.partners.model.vo.PartnersVo;
import ein.mono.request.model.dao.RequestDao;

public class PartnersDao {

	private Properties prop = new Properties();
	
	public PartnersDao() {
		String filename = RequestDao.class.getResource("/partners/partners_sql.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public ArrayList<PartnersVo> selectPartnersListMain(Connection con) {
		// TODO Auto-generated method stub
		ArrayList<PartnersVo> list = null;
		// 쿼리는 우수 업체 TOP 3, 내 지역에 가까운 업체 이름 오름차순 TOP 3, 전체 리스트 중 이름 오름차순 TOP 3(스타일 TOP 3 할수도) 
		
		return list;
	}

	public ArrayList<PartnersVo> selectPartnersList(Connection con, MemberVo member, String category) {
		// TODO Auto-generated method stub
		ArrayList<PartnersVo> list = null;
		// 쿼리는 전달된 카테고리에 따라 달라진다.
		return list;
	}

	public ArrayList<PartnersVo> selectPartnersListByKeyword(Connection con, String condition, String keyword) {
		// TODO Auto-generated method stub
		ArrayList<PartnersVo> list = null;
		// 쿼리는 전달된 검색 조건에 따라 달라진다.
		return list;
	}
	public HashMap<Integer, String> selectConstPhoto(Connection con, String ptnCode) {
		HashMap<Integer, String> ptnPhoto = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = null;
		try {
			//1. 쿼리 전송 객체 생성
			query = prop.getProperty("selectConstPhoto");
			pstmt = con.prepareStatement(query);
			//2. 쿼리 작성
			pstmt.setString(1, ptnCode);
			//3. 쿼리 실행
			rs = pstmt.executeQuery();
			//4. 결과 처리(resultSet-list parsing)
			ptnPhoto = new HashMap<Integer, String>();
			while(rs.next()){
				ptnPhoto.put(rs.getInt("PTN_PHOTO_NUM"), rs.getString("PTN_PHOTO"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//5. 자원 반납(close)
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		//6. 결과 리턴	
		return ptnPhoto;
	}

}
