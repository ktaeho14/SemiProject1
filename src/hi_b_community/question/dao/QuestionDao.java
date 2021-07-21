package hi_b_community.question.dao;

import java.sql.Connection;
import java.util.List;

import hi_b_community.complain.dto.complainDto;
import hi_b_community.question.dto.questionDto;

public interface QuestionDao {
	// 전체조회 : DELETEYN='N'인 경우 보여주도록 
	String selectAllSql = "  SELECT QUESTION_BOARDNO, QUESTION_GROUPNO, QUESTION_GROUPSQ, QUESTION_TITLETAB, QUESTION_TITLE, QUESTION_CONTENT, QUESTION_NAME, QUESTION_REGDATE, QUESTION_HIT, QUESTION_DELETEYN, QUESTION_MYID FROM( SELECT ROW_NUMBER() OVER (ORDER BY QUESTION_GROUPNO DESC, QUESTION_GROUPSQ ASC) NUM, QUESTION.* FROM QUESTION) WHERE (NUM BETWEEN ? AND ?)AND(QUESTION_DELETEYN ='N')  ORDER BY NUM ASC  "; 
	
	String selectOneSql = " SELECT * FROM QUESTION WHERE QUESTION_BOARDNO=? ";
	String insertSql = " INSERT INTO QUESTION VALUES(QUESTION_BOARDSQ.NEXTVAL, QUESTION_GROUPSQ.NEXTVAL, 1, 0, ?, ?, ?, SYSDATE, 0, 'N', ?) ";
	String updateSql = " UPDATE QUESTION SET QUESTION_TITLE=?, QUESTION_CONTENT=? WHERE (QUESTION_BOARDNO=?) ";
	
	//답글 작성시 groupsq변경 
	String updateAnswerSql = " UPDATE QUESTION SET QUESTION_GROUPNO = QUESTION_GROUPSQ + 1 "+
							 " WHERE QUESTION_GROUPNO=? AND QUESTION_GROUPSQ>? ";
	
	//답글 작성 
	String insertAnswerSql = " INSERT INTO QUESTION VALUES(QUESTION_BOARDSQ.NEXTVAL, ?, ?, ?, ?, ?, ?, SYSDATE, 0, 'N', ?) ";

	// 삭제 : DELETEYN을 'Y'로 변경 
	String deleteSql = " UPDATE QUESTION SET QUESTION_DELETEYN='Y' WHERE (QUESTION_BOARDNO=?) "; 
	
	//조회수 증가 
	String increaseSql = " UPDATE QUESTION SET QUESTION_HIT = QUESTION_HIT + 1 WHERE QUESTION_BOARDNO=? ";
	
	//게시글 수 
	String getListCountSql = " SELECT COUNT(*) FROM QUESTION ";
	
	
	//검색 
	//	1. QUESTION_NAME(작성자)로 검색
	String search_name_ListSql = " SELECT QUESTION_BOARDNO, QUESTION_GROUPNO, QUESTION_GROUPSQ, QUESTION_TITLETAB, QUESTION_TITLE, QUESTION_CONTENT, QUESTION_NAME, QUESTION_REGDATE, QUESTION_HIT,  QUESTION_DELETEYN, QUESTION_MYID  "+
				" FROM( SELECT ROW_NUMBER() OVER (ORDER BY QUESTION_REGDATE DESC) NUM, QUESTION.* FROM QUESTION) "+
				" WHERE (QUESTION_NAME LIKE ?) AND (QUESTION_DELETEYN='N') "+
				" ORDER BY NUM ASC ";
	//	2. QUESTION_TITLE(제목)로 검색	
	String search_title_ListSql = " SELECT QUESTION_BOARDNO, QUESTION_GROUPNO, QUESTION_GROUPSQ, QUESTION_TITLETAB, QUESTION_TITLE, QUESTION_CONTENT, QUESTION_NAME, QUESTION_REGDATE, QUESTION_HIT,  QUESTION_DELETEYN, QUESTION_MYID  "+
			" FROM( SELECT ROW_NUMBER() OVER (ORDER BY QUESTION_REGDATE DESC) NUM, QUESTION.* FROM QUESTION) "+
			" WHERE (QUESTION_TITLE LIKE ?) AND (QUESTION_DELETEYN='N') "+
			" ORDER BY NUM ASC ";
	//	3. QUESTION_CONTENT(내용)로 검색	
	String search_content_ListSql = " SELECT QUESTION_BOARDNO, QUESTION_GROUPNO, QUESTION_GROUPSQ, QUESTION_TITLETAB, QUESTION_TITLE, QUESTION_CONTENT, QUESTION_NAME, QUESTION_REGDATE, QUESTION_HIT,  QUESTION_DELETEYN, QUESTION_MYID  "+
			" FROM( SELECT ROW_NUMBER() OVER (ORDER BY QUESTION_REGDATE DESC) NUM, QUESTION.* FROM QUESTION) "+
			" WHERE (QUESTION_CONTENT LIKE ?) AND (QUESTION_DELETEYN='N') "+
			" ORDER BY NUM ASC ";
	
	public List<questionDto> selectAll(Connection con, int currentPage);
	public questionDto selectOne(Connection con, int q_boardno);
	public int insert(Connection con, questionDto dto);
	public int update(Connection con, questionDto dto);
	public int updateAnswer(Connection con, int groupno, int groupsq);
	public int insertAnswer(Connection con, questionDto dto);
	public int delete(Connection con, int boardno);
	public int increase(Connection con, int no);
	public int getListCount(Connection con);
	public List<questionDto> searchList(Connection con, String select, String keyword);
	
}
