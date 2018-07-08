package ein.mono.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;


import ein.mono.board.model.dao.PostDao;
import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;

public class PostService {
	public ArrayList<PostVo> selectPostList(String post_type){
		Connection con = JDBCTemplate.getConnection();
		
		ArrayList<PostVo> list = new PostDao().selectPostList(con, post_type);
		
		JDBCTemplate.close(con);
		
		return list;
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
		JDBCTemplate.close(con);
		
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
	public int selectPostTotalCount(String post_type, int condition, String keyword) {
		// TODO Auto-generated method stub
		return 0;
	}

	public PostVo getPost(String pCode) {
		Connection con = JDBCTemplate.getConnection();
		
		PostVo result = new PostDao().selectPost(con, pCode);
		
		if(null != result){
			int up = new PostDao().updateVCount(con,pCode);
			if(0 < up){
				JDBCTemplate.commit(con);
			}else{
				JDBCTemplate.rollback(con);
			}
		}
		
		JDBCTemplate.close(con);
		
		return result;
	}

	public int removePost(String pCode) {
		Connection con = JDBCTemplate.getConnection();
		
		int result = new PostDao().deletePost(con,pCode);
		
		if(0 < result){
			JDBCTemplate.commit(con);
		}else{
			JDBCTemplate.rollback(con);
		}
		return result;
	}

	
}
