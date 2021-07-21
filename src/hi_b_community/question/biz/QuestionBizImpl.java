package hi_b_community.question.biz;
import static hi_a_common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import hi_b_community.complain.dto.complainDto;
import hi_b_community.question.dao.QuestionDao;
import hi_b_community.question.dao.QuestionDaoImpl;
import hi_b_community.question.dto.questionDto;

public class QuestionBizImpl implements QuestionBiz{
	
	private QuestionDao dao = new QuestionDaoImpl();

	@Override
	public List<questionDto> selectAll(int currentPage) {
		Connection con = getConnection();
		
		List<questionDto> list =  dao.selectAll(con, currentPage);
		
		close(con);
		
		return list;
	}

	@Override
	public questionDto selectOne(int q_boardno) {
		Connection con = getConnection();
		
		questionDto dto = dao.selectOne(con, q_boardno);
		
		close(con);
		
		return dto;
		
	}

	@Override
	public int insert(questionDto dto) {
		Connection con = getConnection();
		
		int res = dao.insert(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public int update(questionDto dto) {
		Connection con = getConnection();
		
		int res = dao.update(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public int updateAnswer(int groupno, int groupsq) {
		Connection con = getConnection();
		
		int res = dao.updateAnswer(con, groupno, groupsq);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public int insertAnswer(questionDto dto) {
		Connection con = getConnection();
		
		int res = dao.insertAnswer(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		return res;
	}

	@Override
	public int delete(int boardno) {
		Connection con = getConnection();
		
		int res = dao.delete(con, boardno);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public int increase(int no) {
		Connection con = getConnection();
		int res= dao.increase(con, no);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public int getListCount() {
		Connection con = getConnection();
		
		int res = dao.getListCount(con);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public List<questionDto> searchList(String select, String keyword) {
		Connection con = getConnection();
		
		List<questionDto> list2 = dao.searchList(con, select, keyword);
		
		close(con);
		
		return list2;
	}	
}
