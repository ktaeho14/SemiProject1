package ksh.witness.dao;

import java.sql.Connection;
import java.util.List;

import ksh.witness.dto.WitnessDto;

public interface WitnessDao {
	String selectAll = "SELECT * FROM "
			+ "(SELECT ROWNUM RN, NO, COLOR, KIND, PLACE, SPECIALMARK, WITNESS_PIC, CONTENT, WITNESS_DATE,PHONE_NO,CITY,WRITER FROM "
			+ "(SELECT * FROM ANIMAL_WITNESS ORDER BY NO DESC)) WHERE RN BETWEEN ? AND ?";
	String selectOne = "SELECT * FROM ANIMAL_WITNESS WHERE NO=?";
	String insert = "INSERT INTO ANIMAL_WITNESS VALUES(WITNESS_SQ.NEXTVAL,?,?,?,?,?,?,SYSDATE,?,?,?)";
	String delete = "DELETE FROM ANIMAL_WITNESS WHERE NO=?";
	String update = "UPDATE ANIMAL_WITNESS SET COLOR=?,KIND=?,PLACE=?,SPECIALMARK=?,WITNESS_PIC=?,CONTENT=?,PHONE_NO=?,CITY=? WHERE NO=?";
	
	
	String selectAllCity_Kind = "SELECT * FROM ANIMAL_WITNESS WHERE CITY=? AND KIND=?";
	String selectAllContent = "SELECT * FROM ANIMAL_WITNESS WHERE CONTENT LIKE '%'||?||'%' ";
	
	
	
	
	public List<WitnessDto> selectAll(Connection con,int startRow,int endRow);
	public WitnessDto selectOne(Connection con,int no);
	public int insert (Connection con, WitnessDto dto);
	public int update(Connection con,WitnessDto dto);
	public int delete(Connection con,int no);
	public List<WitnessDto> selectAllCity(Connection con, String city, String kind);
	public List<WitnessDto> selectAllContent(Connection con, String content);
}
