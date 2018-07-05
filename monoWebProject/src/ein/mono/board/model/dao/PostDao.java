package ein.mono.board.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;

public class PostDao {

	public ArrayList<PostVo> selectPostList(Connection con, String post_type) {
		PreparedStatement pstmt = null;
		String query = null;
		ResultSet rs = null;
		ArrayList<PostVo> list = new ArrayList<PostVo>();
		try {
			// post_type과 일치하는 게시글만 가져오기. 
			// 회원 테이블과 조인해서 작성자의 닉네임도 가져오기.
			query = "SELECT POST_CODE, POST_TYPE, WRITER_CODE, TITLE, CONTENT, VIEWS_COUNT," 
					 +"WRITTEN_DATE, POST.DELFLAG, MEMBER_NAME "
					 +"FROM POST "
					 +"join member on( WRITER_CODE = MEMBER_CODE) "
					 + "where POST_TYPE = ?";

			pstmt = con.prepareStatement(query);
			pstmt.setString(1, post_type);
			
			rs = pstmt.executeQuery();
			
			// 결과 처리(select -> resultSet)
			PostVo temp = null;
			while(rs.next()) {
                temp = new PostVo();
                temp.setPost_code(rs.getString("post_code"));
                temp.setPost_type(rs.getString("post_type"));
                temp.setWriter_code(rs.getString("writer_code"));
                temp.setTitle(rs.getString("title"));
                temp.setContent(rs.getString("content"));
                temp.setViews_count(rs.getInt("views_count"));
                temp.setTitle(rs.getString("title"));
                temp.setWritten_date(rs.getDate("written_date"));
                temp.setDel_flag(rs.getString("delflag"));
                temp.setMember_name(rs.getString("member_name"));
                
                
               
                
                
				list.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// close 처리
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public PostVo selectPost(Connection con, String post_code) {
		// post_code를 where조건에서 사용하여 게시글 하나 select해서 리턴
		PostVo post = new PostVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		//1. 쿼리 작성
		query = "SELECT POST_CODE, POST_TYPE, WRITER_CODE, TITLE, CONTENT, VIEWS_COUNT, WRITTEN_DATE, POST.DELFLAG, MEMBER_NAME " 
				+"FROM POST, MEMBER "  
				+"WHERE POST.WRITER_CODE = MEMBER.MEMBER_CODE "
                +"AND POST_CODE = ?";
		try {
			//2. 쿼리 실행 객체 생성
			pstmt = con.prepareStatement(query);
			//3. 파라미터 설정
			pstmt.setString(1, post_code);
			//4. 쿼리 실행
			rs = pstmt.executeQuery();
			//5. 결과 처리(resultset)
			while(rs.next()){
				post = new PostVo();
				post.setPost_code(rs.getString("POST_CODE"));
				post.setPost_type(rs.getString("POST_TYPE"));
				post.setWriter_code(rs.getString("WRITER_CODE"));
				post.setTitle(rs.getString("TITLE"));
				post.setContent(rs.getString("CONTENT"));
				post.setViews_count(rs.getInt("VIEWS_COUNT"));
				post.setWritten_date(rs.getDate("WRITTEN_DATE"));
				post.setDel_flag(rs.getString("DELFLAG"));
				post.setMember_name(rs.getString("MEMBER_NAME"));
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			//6. 자원 반납
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		//7. 결과 값 return
		return post;
	}

	public int insertPost(Connection con, PostVo post) {
		// post 객체 게시글 테이블에 insert
		// post_code는 sql 쿼리에서 sequence이용해서 넣어줘야함
		// 		-> post_type에 따라서 if문 또는 switch문 사용하고 
		//			게시판 종류별 스트링 값을 sequence 앞에 붙여줘야함
		// 작성일과 조회수는 default 값 (sysdate, 0).
		int result = 0;
		
		return result;
	}

	public int deletePost(Connection con, String post_code) {
		// post_code에 해당하는 게시글 삭제
		// delflag를 y로 바꾸는 update문 실행
		
		int result = 0;
		
		return result;
	}

	public int updatePost(Connection con, PostVo post) {
		// post 내용 변경.
		int result = 0;
		
		return result;
	}

	// 검색조건과 키워드로 해당되는 게시글 select
	public ArrayList<PostVo> searchPost(Connection con, int condition, String keyword) {
		ArrayList<PostVo> list = null;
		
/*		switch(condition) {
		case 1:
			// 제목
			query += "WHERE NTITLE LIKE '%" + keyword + "%'";
			break;
		case 2:
			query += "WHERE NCONTENT LIKE '%" + keyword + "%'";
			break;
		case 3:
			query += "WHERE USERNAME LIKE '%" + keyword + "%'";
			break;
		case 0:
			query += "WHERE (USERNAME LIKE '%" + keyword + "%'";
			query += "OR NCONTENT LIKE '%" + keyword + "%'";
			query += "OR NTITLE LIKE '%" + keyword + "%')";
			break;
		}
*/		
		
		return list;
	}

	public int selectPostTotalCount(Connection con, String post_type) {
		int result = -1;
		Statement stmt = null;
		ResultSet rs = null;
		String query = "";
		try {
			stmt = con.createStatement();
			query = "SELECT COUNT(*) AS LISTCOUNT "
						+ "FROM Post "
						+ "WHERE DELFLAG != 'Y' AND POST_TYPE='"+post_type+"'";
			rs = stmt.executeQuery(query);
			
			while(rs.next()){
				result = rs.getInt("listcount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(stmt);
		}
		
		// TODO Auto-generated method stub
		return result;
	}

	public ArrayList<PostVo> selectPostLListServlet(Connection con) {
		// TODO Auto-generated method stub
		return null;
	}

}
