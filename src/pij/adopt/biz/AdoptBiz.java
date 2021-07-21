package pij.adopt.biz;

import java.util.List;


import pij.adopt.dto.AdoptDto;



public interface AdoptBiz {
	public List<AdoptDto> selectAll(int startRow,int endRow);
	public AdoptDto selectOne(int no);
	public int insert (AdoptDto dto);
	public int update (AdoptDto dto);
	public int delete(int no);
}
