package ein.mono.board.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;

public class PostDao {
	Properties prop = new Properties();

	public PostDao(){
		String filename = AttachmentDao.class.getResource("/post/post_sql.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

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
		PostVo post = new PostVo();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		query = prop.getProperty("selectPost");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, post_code);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String pType = rs.getString("post_type");
				String nName = rs.getString("member_nname");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int vCount = rs.getInt("views_count");
				Date wDate = rs.getDate("written_date");
				
				post.setPost_code(post_code);
				post.setPost_type(pType);
				post.setWriter_nickname(nName);
				post.setTitle(title);
				post.setContent(content);
				post.setViews_count(vCount);
				post.setWritten_date(wDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
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
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		String pCode = post.getPost_code();
		String title = post.getTitle();
		String content = post.getContent();
		
		query = prop.getProperty("updatePost");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, title);
			pstmt.setString(2, content);
			pstmt.setString(3, pCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(pstmt);
		}
		
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
