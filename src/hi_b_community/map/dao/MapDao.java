package hi_b_community.map.dao;

import java.sql.Connection;
import java.util.List;

import hi_b_community.map.dto.mapDto;
import hi_b_community.map.dto.mapshelterDto;

public interface MapDao {
	//나의 위치 찍기 + 목격 위치 찍기 
	String insertSql = " INSERT INTO MAP_PLACE_CLICK VALUES(MAPSQ.NEXTVAL, ?, ?, ?, ?, ?, ?, ?) ";
	String selectAllSql = " SELECT * FROM MAP_PLACE_CLICK ORDER BY MAP_NO DESC ";
	String selectOneSql = " SELECT * FROM MAP_PLACE_CLICK WHERE MAP_NO=? ";
	
	//수정
	String updateSql = " UPDATE MAP_PLACE_CLICK SET LATITUDE=?, LONGTITUDE=?, LAND_NUMBER_ADDRESS=?, ROAD_NAME_ADDRESS=?, MAP_WRITER=?, MAP_PHONE=?, MAP_CONTENT=? WHERE MAP_NO=? ";
	
	//삭제
	String deleteSql = " DELETE FROM MAP_PLACE_CLICK WHERE MAP_NO=? ";
	
	//보호소  전체조회
	String shelter_selectAllSql = " SELECT * FROM MAP_SHELTER_GUIDE "; 
	
	//게시글 수 
	String getListCountSql = " SELECT COUNT(*) MAP_SHELTER_GUIDE ";
	
	public int map_insert(Connection con, mapDto dto);
	public List<mapDto> selectAll(Connection con);
	public mapDto selectOne(Connection con, int map_no);
	
	//수정
	public int update(Connection con, mapDto dto);
	
	//삭제
	public int delete(Connection con, int map_no);
	
	//보호소 전체 조회 
	public List<mapshelterDto> shelter_selectAll(Connection con);
	public int getListCount(Connection con);
}
