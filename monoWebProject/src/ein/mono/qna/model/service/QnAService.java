package ein.mono.qna.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import ein.mono.board.model.vo.PostVo;
import ein.mono.common.JDBCTemplate;
import ein.mono.qna.model.dao.QnADao;
import ein.mono.qna.model.vo.QnAVo;

public class QnAService {
	public ArrayList<QnAVo> getQnaList(){
		return null;
	}
	
	
	public PostVo getQna(String qCode){
		Connection con = JDBCTemplate.getConnection();
		
		PostVo post = new QnADao().selectQna(con, qCode);
		
//		if(null != post){
//			int result = new NoticeDao().updateNoticeCount(con, noticeNo);
			if(null != post){
				JDBCTemplate.commit(con);
			}else{
				JDBCTemplate.rollback(con);
			}
			
//		}
		return post;
	}
	
	public int writeQuestion(QnAVo question){
		return 0;
	}
	public int writeAnswer(QnAVo answer){
		return 0;
	}
	

}
