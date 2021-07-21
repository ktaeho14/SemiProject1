package ksh.witness.biz;

import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.List;

import ksh.witness.dao.WitnessDao;
import ksh.witness.dao.WitnessDaoImpl;
import ksh.witness.dto.WitnessDto;
public class WitnessBizImpl implements WitnessBiz {

	@Override
	public List<WitnessDto> selectAll(int startRow,int endRow) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		List<WitnessDto> wnlist = dao.selectAll(con,startRow,endRow);
		close(con);
		
		
		return wnlist;
	}

	@Override
	public WitnessDto selectOne(int no) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		WitnessDto dto = dao.selectOne(con, no);
		close(con);
		return dto;
	}

	@Override
	public int insert(WitnessDto dto) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		int res = dao.insert(con, dto);
		
		
		
		close(con);
		return res;
	}

	@Override
	public int update(WitnessDto dto) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		int res = dao.update(con, dto);
		
		if(res>0) {
			commit(con);
		}
		
		close(con);
		return res;
	}

	@Override
	public int delete(int no) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		int res = 0;
		
		 res = dao.delete(con, no);
		 
		 if(res>0) {
			 commit(con);
		 }else {
			 rollback(con);
		 }
		 
		 close(con);
		return res;
	}

	@Override
	public List<WitnessDto> selectAllCity(String city, String kind) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		List<WitnessDto> wnCityList = dao.selectAllCity(con, city, kind);
		close(con);
		return wnCityList;
	}

	@Override
	public List<WitnessDto> selectAllContent(String content) {
		Connection con = getConnection();
		WitnessDao dao = new WitnessDaoImpl();
		List<WitnessDto> wnContentList = dao.selectAllContent(con, content);
		close(con);
		return wnContentList;
	}

}
