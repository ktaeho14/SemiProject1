package ksh.witness.biz;

import java.util.List;

import ksh.witness.dto.WitnessDto;

public interface WitnessBiz {
	public List<WitnessDto> selectAll(int startRow,int endRow);
	public WitnessDto selectOne(int no);
	public int insert (WitnessDto dto);
	public int update (WitnessDto dto);
	public int delete(int no);
	
	public List<WitnessDto> selectAllCity(String city,String kind);
	public List<WitnessDto> selectAllContent(String content);
}
