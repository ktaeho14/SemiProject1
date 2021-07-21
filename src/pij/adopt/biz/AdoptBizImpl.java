package pij.adopt.biz;

import java.sql.Connection;
import java.util.List;

import pij.adopt.dao.AdoptDao;
import pij.adopt.dao.AdoptDaoImpl;
import pij.adopt.dto.AdoptDto;

import static common.JDBCTemplate.*;
public class AdoptBizImpl implements AdoptBiz {

	@Override
	public List<AdoptDto> selectAll(int startRow,int endRow) {
		 Connection con = getConnection();
		 AdoptDao dao = new AdoptDaoImpl();
		 
		 List<AdoptDto> list = dao.selectAll(con,startRow,endRow);
		 
		return list;
	}

	@Override
	public AdoptDto selectOne(int no) {
		Connection con = getConnection();
		AdoptDao dao = new AdoptDaoImpl();
		
		AdoptDto dto = new AdoptDto();
		dto = dao.selectOne(con, no);
		close(con);
		return dto;
	}

	@Override
	public int insert(AdoptDto dto) {
		Connection con = getConnection();
		AdoptDao dao = new AdoptDaoImpl();
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
	public int update(AdoptDto dto) {
		Connection con = getConnection();
		AdoptDao dao = new AdoptDaoImpl();
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
	public int delete(int no) {
		Connection con = getConnection();
		AdoptDao dao = new AdoptDaoImpl();
		int res = dao.delete(con, no);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		return res;
	}

}
