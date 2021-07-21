package hi_b_community.complain.biz;

import static hi_a_common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import hi_b_community.complain.dao.ComplainDao;
import hi_b_community.complain.dao.ComplainDaoImpl;
import hi_b_community.complain.dto.complainDto;

public class ComplainBizImpl implements ComplainBiz{

	private ComplainDao dao = new ComplainDaoImpl();
	
	@Override
	public List<complainDto> selectAll(int currentPage) {
		Connection con = getConnection();
	
		List<complainDto> list = dao.selectAll(con, currentPage);
		
		close(con);
		
		return list;
	}

	@Override
	public complainDto selectOne(int boardno) {
		Connection con = getConnection();
		
		complainDto dto = dao.selectOne(con, boardno);
		
		close(con);
		
		return dto;
	}

	@Override
	public int insert(complainDto dto) {
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
	public int update(complainDto dto) {
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

	//답글달면 groupsq 변경되도록 
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

	//답글달기
	@Override
	public int insertAnswer(complainDto dto) {
		
		Connection con = getConnection();
		
		int res = dao.insertAnswer(con, dto);
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
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

	//조회수 증가 
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

	//게시글 수 
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

	//검색 
	@Override
	public List<complainDto> searchList(String select, String keyword) {
		Connection con = getConnection();
		
		List<complainDto> list2 = dao.searchList(con, select, keyword);
		
		close(con);
		
		return list2;
	}



}
