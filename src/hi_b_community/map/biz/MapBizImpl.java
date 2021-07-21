package hi_b_community.map.biz;

import java.sql.Connection;
import java.util.List;
import static hi_a_common.JDBCTemplate.*;

import hi_b_community.complain.dto.complainDto;
import hi_b_community.map.dao.MapDao;
import hi_b_community.map.dao.MapDaoImpl;
import hi_b_community.map.dto.mapDto;
import hi_b_community.map.dto.mapshelterDto;

public class MapBizImpl implements MapBiz{
	//나의 위치 찍기 + 목격 위치 찍기 를 위한 BIZIMPL입니다 
	MapDao dao = new MapDaoImpl();
	
	@Override
	public int map_insert(mapDto dto) {
		Connection con = getConnection();
		
		int res = dao.map_insert(con, dto);
		
		if(res>0) {
			commit(con);
		}else {
			rollback(con);
		}
		
		close(con);
		
		return res;
	}

	@Override
	public List<mapDto> selectAll() {
		Connection con = getConnection();
		
		List<mapDto> res = dao.selectAll(con);
		
		close(con);
		
		return res;
	}

	@Override
	public mapDto selectOne(int map_no) {
		Connection con = getConnection();
		
		mapDto dto = dao.selectOne(con, map_no);
		
		close(con);
		
		return dto;
	}

	//수정할 경우 
	@Override
	public int update(mapDto dto) {
		Connection con = getConnection();
		
		int res = dao.update(con, dto);
		
		close(con);
		
		return res;
	}


	//삭제
	@Override
	public int delete(int map_no) {
		Connection con = getConnection();
		
		int res = dao.delete(con, map_no);
		
		close(con);
		
		return res;
	}

	@Override
	public List<mapshelterDto> shelter_selectAll() {
		Connection con = getConnection();
		
		List<mapshelterDto> res = dao.shelter_selectAll(con);
		
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



}
