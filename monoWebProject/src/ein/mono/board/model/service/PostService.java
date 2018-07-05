package ein.mono.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ein.mono.board.model.dao.PostDao;
import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;

public class PostService {
	public ArrayList<PostVo> selectPostList(String post_type){
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<PostVo> boardList = new PostDao().selectPostList(con, post_type);
		
		JDBCTemplate.close(con);
		
		return boardList;
	}

	public PostVo selectPost(String post_code) {
		Connection con = JDBCTemplate.getConnection();
		
		PostVo post = new PostDao().selectPost(con, post_code);
		
		JDBCTemplate.close(con);
		
		return post;
	}

	public int insertPost(PostVo post) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new PostDao().insertPost(con, post);
		
		if(0 < result) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		
		return result;
	}

	public int deletePost(String post_code) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new PostDao().deletePost(con, post_code);
		
		if(0 < result) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		
		return result;

	}

	public int updatePost(PostVo post) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new PostDao().updatePost(con, post);
		
		if(0 < result) {
			JDBCTemplate.commit(con);
		} else {
			JDBCTemplate.rollback(con);
		}
		
		return result;
	}

	public ArrayList<PostVo> searchPost( String keyword) {
		Connection con = JDBCTemplate.getConnection();

		ArrayList<PostVo> postList = new PostDao().searchPost(con, codition, keyword);
		
		JDBCTemplate.close(con);
		
		return postList;
	}

	public int selectPostTotalCount(String post_type) {
		
		//1. 커넥션 연결
				Connection con = JDBCTemplate.getConnection();
				//2. dao 메소드 호출
				int listCount = new PostDao().selectPostTotalCount(con,post_type);
				//3. 자원 반납(close)
				JDBCTemplate.close(con);
				//4. 해당 결과 리턴
				return listCount;
		
		// TODO Auto-generated method stub
		
	}

	public int selectPostTotalCount(String post_type, int condition, String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}
}
