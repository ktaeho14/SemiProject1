package hi_b_community.question.biz;

import java.util.List;

import hi_b_community.complain.dto.complainDto;
import hi_b_community.question.dto.questionDto;



public interface QuestionBiz {
	
	public List<questionDto> selectAll(int currentPage);
	public questionDto selectOne(int q_boardno);
	public int insert(questionDto dto);
	public int update(questionDto dto);
	public int updateAnswer(int groupno, int groupsq);
	public int insertAnswer(questionDto dto);
	public int delete(int boardno);
	public int increase(int no);
	public int getListCount();
	public List<questionDto> searchList(String select, String keyword);
	
}
