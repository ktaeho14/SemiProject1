package hi_b_community.complain.dao;

import java.sql.Connection;
import java.util.List;

import hi_b_community.complain.dto.complainDto;


public interface ComplainDao {
	// 전체조회 : DELETEYN='N'인 경우 보여주도록 
	String selectAllSql = " SELECT COMPLAIN_BOARDNO, COMPLAIN_GROUPNO, COMPLAIN_GROUPSQ, COMPLAIN_TITLETAB, COMPLAIN_TITLE, COMPLAIN_CONTENT, COMPLAIN_NAME, COMPLAIN_REGDATE, COMPLAIN_HIT, COMPLAIN_DELETEYN, COMPLAIN_MYID " + 
						  "  FROM( SELECT ROW_NUMBER() OVER (ORDER BY COMPLAIN_GROUPNO DESC, COMPLAIN_GROUPSQ ASC) NUM, COMPLAIN.* FROM COMPLAIN) " + 
						  " WHERE (NUM BETWEEN ? AND ?)AND(COMPLAIN_DELETEYN = 'N') ORDER BY NUM ASC ";
	
	String selectOneSql = " SELECT * FROM COMPLAIN WHERE COMPLAIN_BOARDNO=? ";
	String insertSql = " INSERT INTO COMPLAIN "+
					   " VALUES(COMPLAIN_BOARDSQ.NEXTVAL, COMPLAIN_GROUPSQ.NEXTVAL, 1, 0, ?, ?, ?, SYSDATE, 0, 'N', ?) ";
	
	String updateSql = " UPDATE COMPLAIN SET COMPLAIN_TITLE=?, COMPLAIN_CONTENT=? WHERE (COMPLAIN_BOARDNO=?) ";
	
	//답글 작성시 groupsq변경 
	String updateAnswerSql = " UPDATE COMPLAIN SET COMPLAIN_GROUPNO = COMPLAIN_GROUPSQ + 1 "+
							 " WHERE COMPLAIN_GROUPNO=? AND COMPLAIN_GROUPSQ>? ";

	//답글 작성 
	String insertAnswerSql = " INSERT INTO COMPLAIN VALUES(COMPLAIN_BOARDSQ.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE, 0, 'N', ?) ";
	
	// 삭제 : DELETEYN을 'Y'로 변경 
	String deleteSql = " UPDATE COMPLAIN SET COMPLAIN_DELETEYN='Y' WHERE (COMPLAIN_BOARDNO=?) ";
	
	//조회수 증가 
	String increaseSql = " UPDATE COMPLAIN SET COMPLAIN_HIT = COMPLAIN_HIT + 1 WHERE COMPLAIN_BOARDNO=? ";
	
	//게시글 수 
	String getListCountSql = " SELECT COUNT(*) FROM COMPLAIN ";
	

	
	
	//검색
	//	1. COMPLAIN_NAME(작성자)로 검색
	String search_name_ListSql = " SELECT COMPLAIN_BOARDNO, COMPLAIN_GROUPNO, COMPLAIN_GROUPSQ, COMPLAIN_TITLETAB, COMPLAIN_TITLE, COMPLAIN_CONTENT, COMPLAIN_NAME, COMPLAIN_REGDATE, COMPLAIN_HIT, COMPLAIN_DELETEYN, COMPLAIN_MYID   "+
			   " FROM( SELECT ROW_NUMBER() OVER (ORDER BY COMPLAIN_REGDATE DESC) NUM, COMPLAIN.* FROM COMPLAIN) "+
			   " WHERE (COMPLAIN_NAME LIKE ?)AND(COMPLAIN_DELETEYN = 'N')  "+
			   " ORDER BY NUM ASC ";
	//	2. COMPLAIN_TITLE(제목)로 검색
	String search_title_ListSql = " SELECT COMPLAIN_BOARDNO, COMPLAIN_GROUPNO, COMPLAIN_GROUPSQ, COMPLAIN_TITLETAB, COMPLAIN_TITLE, COMPLAIN_CONTENT, COMPLAIN_NAME, COMPLAIN_REGDATE, COMPLAIN_HIT, COMPLAIN_DELETEYN, COMPLAIN_MYID   "+
			   " FROM( SELECT ROW_NUMBER() OVER (ORDER BY COMPLAIN_REGDATE DESC) NUM, COMPLAIN.* FROM COMPLAIN) "+
			   " WHERE (COMPLAIN_TITLE LIKE ?)AND(COMPLAIN_DELETEYN = 'N')  "+
			   " ORDER BY NUM ASC ";
	//	3. COMPLAIN_CONTENT(내용)로 검색
	String search_content_ListSql = " SELECT COMPLAIN_BOARDNO, COMPLAIN_GROUPNO, COMPLAIN_GROUPSQ, COMPLAIN_TITLETAB, COMPLAIN_TITLE, COMPLAIN_CONTENT, COMPLAIN_NAME, COMPLAIN_REGDATE, COMPLAIN_HIT, COMPLAIN_DELETEYN, COMPLAIN_MYID   "+
			   " FROM( SELECT ROW_NUMBER() OVER (ORDER BY COMPLAIN_REGDATE DESC) NUM, COMPLAIN.* FROM COMPLAIN) "+
			   " WHERE (COMPLAIN_CONTENT LIKE ?)AND(COMPLAIN_DELETEYN = 'N')  "+
			   " ORDER BY NUM ASC ";


	

	
	
	public List<complainDto> selectAll(Connection con, int currentPage);
	public complainDto selectOne(Connection con, int boardno);
	public int insert(Connection con,complainDto dto);
	public int update(Connection con, complainDto dto);
	public int updateAnswer(Connection con, int groupno, int groupsq);
	public int insertAnswer(Connection con, complainDto dto);
	public int delete(Connection con, int boardno);
	public int increase(Connection con, int no);
	public int getListCount(Connection con );
	public List<complainDto> searchList(Connection con, String select, String keyword);
	


}
