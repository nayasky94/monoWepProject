package ein.mono.board.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;

public class PostDao {
	Properties prop = new Properties();

	public PostDao(){
		String filename = PostDao.class.getResource("/post/post_sql.properties").getPath();
		try {
			prop.load(new FileReader(filename));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<PostVo> selectPostList(Connection con, String post_type) {
		ArrayList<PostVo> list = new ArrayList<PostVo>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String query = "";
		
		query = prop.getProperty("selectPostList");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, post_type);
			rs = pstmt.executeQuery();			
			
			PostVo temp = null;
			while(rs.next()) {
				int num = rs.getInt("num");
				String pCode = rs.getString("post_code");
				String title = rs.getString("title");
				int vCount = rs.getInt("VIEWS_COUNT");
				String clobString = readClobData(rs.getCharacterStream("CONT"));
				String nName = rs.getString("member_nname");
				Date wDate = rs.getDate("written_Date");
				
				temp = new PostVo();
				temp.setNum(num);
				temp.setPost_code(pCode);
				temp.setTitle(title);
				temp.setViews_count(vCount);
				temp.setContent(clobString);
				temp.setWriter_nickname(nName);
				temp.setWritten_date(wDate);
				
				list.add(temp);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rs);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}
	public static String readClobData(Reader reader) throws IOException {
        StringBuffer data = new StringBuffer();
        char[] buf = new char[1024];
        int cnt = 0;
        if (null != reader) {
            while ( (cnt = reader.read(buf)) != -1) {
                data.append(buf, 0, cnt);
            }
        }
        return data.toString();
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
				String mCdoe = rs.getString("member_code");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int vCount = rs.getInt("views_count");
				Date wDate = rs.getDate("written_date");
				
				post.setPost_code(post_code);
				post.setPost_type(pType);
				post.setWriter_nickname(nName);
				post.setWriter_code(mCdoe);
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

	public int insertPost(Connection con, PostVo p) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		
		String pType = p.getPost_type();
		String mCode = p.getWriter_code();
		String title = p.getTitle();
		String content = p.getContent();
		
		query = prop.getProperty("insertPost");
		String sequence = "";
		switch(pType){
		case "SHO":
			sequence = "'"+pType+"'||POST_SHOW_SEQ.NEXTVAL";
			break;
		case "FRE":
			sequence = "'"+pType+"'||POST_FREE_SEQ.NEXTVAL";
			break;
		case "MAR":
			sequence = "'"+pType+"'||POST_TRADE_SEQ.NEXTVAL";
			break;
		case "REV":
			sequence = "'"+pType+"'||POST_REVIEW_SEQ.NEXTVAL";
			break;
		}
		
		query = query.replaceAll("SEQ", sequence);
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pType);
			pstmt.setString(2, mCode);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(pstmt);
		}
//		if(result == 1){
//			query = "SELECT CONTENT FROM POST WHERE POST_CODE = ? FOR UPDATE";
//			try {
//				pstmt = con.prepareStatement(query);
//				pstmt.setString(1, mCode);
//				ResultSet rs = pstmt.executeQuery();
//				
//				String strClob = content;
//				if(rs.next()){
//					CLOB clob = ((OracleResultSet)rs).getCLOB("content");
//					Writer writer = clob.getCharacterOutputStream();
//					Reader reader = new CharArrayReader(strClob.toCharArray());
//					char[] buffer = new char[1024];
//					int read = 0;
//					
//					try {
//						while((read = reader.read(buffer, 0, 1024)) != -1){
//							writer.write(buffer,0,read);
//						}
//					} catch (IOException e) {
//						e.printStackTrace();
//					} finally{
//						try {
//							reader.close();
//							writer.close();
//						} catch (IOException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
				
		return result;
	}

	public int deletePost(Connection con, String pCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		
		query = prop.getProperty("deletePost");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pCode);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateVCount(Connection con, String pCode) {
		int result = 0;
		PreparedStatement pstmt = null;
		String query = "";
		query = prop.getProperty("updateVCount");
		try {
			pstmt = con.prepareStatement(query);
			pstmt.setString(1, pCode);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}	

}
