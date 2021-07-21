package hi_b_community.notice.biz;

import java.util.List;

import hi_b_community.notice.dto.noticeDto;



public interface NoticeBiz {
	
	public List<noticeDto> selectAll( int currentPage);
	public noticeDto selectOne(int no);
	public int insert(noticeDto dto);
	public int update(noticeDto dto);
	public int delete(int no, String myid);
	public int increase(int no);
	public int getListCount();
	
	public List<noticeDto> searchList(String select, String keyword);
}
