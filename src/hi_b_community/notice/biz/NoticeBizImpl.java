package hi_b_community.notice.biz;

import static hi_a_common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import hi_b_community.notice.dao.NoticeDao;
import hi_b_community.notice.dao.NoticeDaoImpl;
import hi_b_community.notice.dto.noticeDto;

public class NoticeBizImpl implements NoticeBiz{

	private NoticeDao dao = new NoticeDaoImpl();
	
	@Override
	public List<noticeDto> selectAll(int currentPage) {
		Connection con = getConnection();
		
		List<noticeDto> list = dao.selectAll(con, currentPage);
		
		close(con);
		
		return list;
		
	}

	@Override
	public noticeDto selectOne(int no) {
		Connection con = getConnection();
		
		noticeDto dto = dao.selectOne(con, no);
		
		close(con);
		
		return dto;
	}

	@Override
	public int insert(noticeDto dto) {
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
	public int update(noticeDto dto) {
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
	public int delete(int no, String myid) {
		Connection con = getConnection();
		
		int res = dao.delete(con, no, myid);
		
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
		
		int res = dao.increase(con, no);
		
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
	public List<noticeDto> searchList(String select, String keyword) {
		Connection con = getConnection();
		
		List<noticeDto> list = dao.searchList(con, select, keyword);
		
		close(con);
		
		return list;
		

		
	}

	
}
