package pij.adopt.dao;

import java.sql.Connection;
import java.util.List;

import ksh.witness.dto.WitnessDto;
import pij.adopt.dto.AdoptDto;

public interface AdoptDao {
	
	String selectAll = "SELECT * FROM "
			+ "(SELECT ROWNUM RN, ADOPT_NO, ADOPT_WRITER, ADOPT_PHONE, ADOPT_TITLE, ADOPT_SEX, ADOPT_PLACE, ADOPT_CONTENT, ADOPT_PHOTO,ADOPT_EMAIL,ADOPT_KIND FROM "
			+ "(SELECT * FROM ADOPT ORDER BY ADOPT_NO DESC)) WHERE RN BETWEEN ? AND ?";
	String selectOne = "SELECT * FROM ADOPT WHERE ADOPT_NO=?";
	String insert = "INSERT INTO ADOPT VALUES(ADOPT_SEQ.NEXTVAL,?,?,?,?,?,?,'XxxxxX',?,?)";
	String delete = "DELETE FROM ADOPT WHERE ADOPT_NO = ?";
	String update = "UPDATE ADOPT SET ADOPT_WRITER=?, ADOPT_PHONE=?, ADOPT_TITLE=?, ADOPT_SEX=?, ADOPT_PLACE=?, ADOPT_CONTENT=?, ADOPT_EMAIL=?, ADOPT_KIND=? WHERE ADOPT_NO=?";
	public List<AdoptDto> selectAll(Connection con,int startRow,int endRow);
	public AdoptDto selectOne(Connection con,int no);
	public int insert (Connection con, AdoptDto dto);
	public int update(Connection con,AdoptDto dto);
	public int delete(Connection con,int no);
	
	
	public int getCount();
}
