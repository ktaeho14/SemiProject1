package hi_b_community.notice.dao;

import java.sql.Connection;
import java.util.List;

import hi_b_community.notice.dto.noticeDto;

public interface NoticeDao {
	// 전체조회 : DELETEYN='N'인 경우 보여주도록 
	String selectAllSql = " SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_NAME, NOTICE_CONTENT, NOTICE_REGDATE, NOTICE_HIT, NOTICE_DELETEYN, NOTICE_MYID " + 
						  " FROM( SELECT ROW_NUMBER() OVER (ORDER BY NOTICE_REGDATE DESC) NUM, NOTICE.* FROM NOTICE) " + 
						  " WHERE (NUM BETWEEN ? AND ? )AND(NOTICE_DELETEYN = 'N') ORDER BY NUM ASC ";
	
	String selectOneSql = " SELECT * FROM NOTICE WHERE NOTICE_NO=? ";
	String insertSql = " INSERT INTO NOTICE VALUES(NOTICESQ.NEXTVAL, ?, ?, ?, SYSDATE, 0, 'N', ?) ";
	String updateSql = " UPDATE NOTICE SET NOTICE_TITLE=?, NOTICE_CONTENT=? WHERE NOTICE_NO=? ";
	
	// 삭제 : DELETEYN을 'Y'로 변경 
	String deleteSql = " UPDATE NOTICE SET NOTICE_DELETEYN='Y' WHERE (NOTICE_NO=?)AND(NOTICE_MYID=?) ";
	
	//조회수 증가 
	String increaseSql = " UPDATE NOTICE SET NOTICE_HIT = NOTICE_HIT + 1 WHERE NOTICE_NO=? ";
	
	//게시글 수 
	String getListCountSql = " SELECT COUNT(*) FROM NOTICE ";
	
	
	//검색
	//	1. NOTICE_NAME(작성자)로 검색
	String search_name_ListSql = " SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_NAME, NOTICE_CONTENT, NOTICE_REGDATE, NOTICE_HIT, NOTICE_DELETEYN, NOTICE_MYID   "+
						   " FROM( SELECT ROW_NUMBER() OVER (ORDER BY NOTICE_REGDATE DESC) NUM, NOTICE.* FROM NOTICE) "+
						   " WHERE (NOTICE_NAME LIKE ?)AND(NOTICE_DELETEYN = 'N')  "+
						   " ORDER BY NUM ASC ";
	//	2. NOTICE_TITLE(제목)으로 검색	
	String search_title_ListSql = " SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_NAME, NOTICE_CONTENT, NOTICE_REGDATE, NOTICE_HIT, NOTICE_DELETEYN, NOTICE_MYID   "+
			   " FROM( SELECT ROW_NUMBER() OVER (ORDER BY NOTICE_REGDATE DESC) NUM, NOTICE.* FROM NOTICE) "+
			   " WHERE (NOTICE_TITLE LIKE ?)AND(NOTICE_DELETEYN = 'N')  "+
			   " ORDER BY NUM ASC ";
	//	3. NOTICE_CONTENT(내용)으로 검색
	String search_content_ListSql = " SELECT NOTICE_NO, NOTICE_TITLE, NOTICE_NAME, NOTICE_CONTENT, NOTICE_REGDATE, NOTICE_HIT, NOTICE_DELETEYN, NOTICE_MYID   "+
			   " FROM( SELECT ROW_NUMBER() OVER (ORDER BY NOTICE_REGDATE DESC) NUM, NOTICE.* FROM NOTICE) "+
			   " WHERE (NOTICE_CONTENT LIKE ?)AND(NOTICE_DELETEYN = 'N')  "+
			   " ORDER BY NUM ASC ";
	
	public List<noticeDto> selectAll(Connection con, int currentPage);
	public noticeDto selectOne(Connection con, int no);
	public int insert(Connection con, noticeDto dto);
	public int update(Connection con, noticeDto dto);
	public int delete(Connection con, int no, String myid);
	public int increase(Connection con, int no);
	public int getListCount(Connection con);
	public List<noticeDto> searchList(Connection con, String select, String keyword);
	
}
