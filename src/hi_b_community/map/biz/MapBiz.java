package hi_b_community.map.biz;

import java.util.List;

import hi_b_community.complain.dto.complainDto;
import hi_b_community.map.dto.mapDto;
import hi_b_community.map.dto.mapshelterDto;

public interface MapBiz {
	//나의 위치 찍기 + 목격 위치 찍기
	public int map_insert(mapDto dto);
	public List<mapDto> selectAll();
	public mapDto selectOne(int map_no);
	
	//수정할 경우 
	public int update(mapDto dto);

	
	//삭제
	public int delete(int map_no);
	
	//보호소 전체조회
	public List<mapshelterDto> shelter_selectAll();

	public int getListCount();
}
