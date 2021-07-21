package hi_b_community.complain.biz;

import java.util.List;

import hi_b_community.complain.dto.complainDto;



public interface ComplainBiz {

	public List<complainDto> selectAll(int currentPage);
	public complainDto selectOne(int boardno);
	public int insert(complainDto dto);
	public int update(complainDto dto);
	public int updateAnswer(int groupno, int groupsq);
	public int insertAnswer(complainDto dto);
	public int delete(int boardno);
	public int increase(int no);
	public int getListCount();
	

	
	//검색
	public List<complainDto> searchList(String select, String keyword);
}
