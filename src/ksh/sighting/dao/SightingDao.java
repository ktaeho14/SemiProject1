package ksh.sighting.dao;

import static common.JDBCTemplate.close;
import static common.JDBCTemplate.commit;
import static common.JDBCTemplate.getConnection;
import static common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ksh.sighting.dto.SightingDto;

public class SightingDao {
	
	

	
	
	public List<SightingDto> selectAll(int startRow, int endRow){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SightingDto> list = new ArrayList<SightingDto>();
		
		String sql = "SELECT * FROM "
				+ "(SELECT ROWNUM RN, ANIMAL_NO, AGE, CAREADDR, CARENM, CARETEL, CHARGENM, COLORCD, DESERTIONNO, FILENAME, HAPPENDT, HAPPENPLACE, KINDCD, NEUTERYN, NOTICEEDT, NOTICENO, NOTICESDT, OFFICETEL, ORGNM, POPFILE, PROCESSSTATE, SEXCD, SPECIALMARK, WEIGHT FROM "
				+ "(SELECT * FROM ANIMAL ORDER BY ANIMAL_NO DESC)) WHERE RN BETWEEN ? AND ?";
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비");
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);
			rs = pstm.executeQuery();
			System.out.println("04.query 실행");
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					SightingDto dto = new SightingDto();
					dto.setAnimal_no(rs.getInt(2));
					dto.setAge(rs.getString(3));
					dto.setCareaddr(rs.getString(4));
					dto.setCarenm(rs.getString(5));
					dto.setCaretel(rs.getString(6));
					dto.setChargenm(rs.getString(7));
					dto.setColorcd(rs.getString(8));
					dto.setDesertionno(rs.getString(9));
					dto.setFilename(rs.getString(10));
					dto.setHappendt(rs.getString(11));
					dto.setHappenplace(rs.getString(12));
					dto.setKindcd(rs.getString(13));
					dto.setNeuteryn(rs.getString(14));
					dto.setNoticeedt(rs.getString(15));
					dto.setNoticeno(rs.getString(16));
					dto.setNoticesdt(rs.getString(17));
					dto.setOfficetel(rs.getString(18));
					dto.setOrgnm(rs.getString(19));
					dto.setPopfile(rs.getString(20));
					dto.setProcessstate(rs.getString(21));
					dto.setSexcd(rs.getString(22));
					dto.setSpecialmark(rs.getString(23));
					dto.setWeight(rs.getString(24));
					
					
					list.add(dto);
				}while(rs.next());
			}
			

			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			
			System.out.println("05. db 종료");
		}
		
		
		return list;
		
	}
	
	
	public int getCount() {
		int count=0;
		
		String sql = "SELECT COUNT(*) FROM ANIMAL";
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		con = getConnection();
		
		try {
			pstm=con.prepareStatement(sql);
			rs = pstm.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		
		return count;
	}
	
	
	
	
	
	
	
	
	public SightingDto selectOne(int animal_no) {
		SightingDto dto = new SightingDto();
		
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		
		String sql = "SELECT * FROM ANIMAL WHERE ANIMAL_NO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, animal_no);
			System.out.println("03. query 준비");
			
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			
			
			if(rs.next()) {
			dto = new SightingDto();
			dto.setAnimal_no(rs.getInt(1));
			dto.setAge(rs.getString(2));
			dto.setCareaddr(rs.getString(3));
			dto.setCarenm(rs.getString(4));
			dto.setCaretel(rs.getString(5));
			dto.setChargenm(rs.getString(6));
			dto.setColorcd(rs.getString(7));
			dto.setDesertionno(rs.getString(8));
			dto.setFilename(rs.getString(9));
			dto.setHappendt(rs.getString(10));
			dto.setHappenplace(rs.getString(11));
			dto.setKindcd(rs.getString(12));
			dto.setNeuteryn(rs.getString(13));
			dto.setNoticeedt(rs.getString(14));
			dto.setNoticeno(rs.getString(15));
			dto.setNoticesdt(rs.getString(16));
			dto.setOfficetel(rs.getString(17));
			dto.setOrgnm(rs.getString(18));
			dto.setPopfile(rs.getString(19));
			dto.setProcessstate(rs.getString(20));
			dto.setSexcd(rs.getString(21));
			dto.setSpecialmark(rs.getString(22));
			dto.setWeight(rs.getString(23));
			
			
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			
			System.out.println("05.db종료");
		}
		
		
		
		
		
		
		return dto;
	}
	
	
	public int insert(SightingDto dto) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res =0;
		
		String sql = "INSERT INTO ANIMAL_TEST VALUES(ANIMAL_NO.NEXTVAL,?,?,?,?,'TEST',?,'TEST','TEST','TEST','TEST','TEST','TEST','TEST','TEST','TEST','TEST','TEST','TEST','TEST',?,?,?,?,?,?)";
		
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getAge());
			pstm.setString(2, dto.getCareaddr());
			pstm.setString(3, dto.getCarenm());
			pstm.setString(4, dto.getCaretel());
			pstm.setString(5, dto.getColorcd());
			pstm.setString(6, dto.getSexcd());
			pstm.setString(7, dto.getSpecialmark());
			pstm.setString(8, dto.getWeight());
			pstm.setString(9, dto.getSightingpic());
			pstm.setString(10, dto.getContext());
			System.out.println("03. query 준비");
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			if(res>0) {
				commit(con);
			}else {
				rollback(con);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			close(con);
		}
		return res;
	}
	
	public List<SightingDto> selectAllKind(String kind){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<SightingDto> list = new ArrayList<SightingDto>();
		
//		String sql = "SELECT * FROM "
//				+ "(SELECT ROWNUM RN, ANIMAL_NO, AGE, CAREADDR, CARENM, CARETEL, CHARGENM, COLORCD, DESERTIONNO, FILENAME, HAPPENDT, HAPPENPLACE, KINDCD, NEUTERYN, NOTICEEDT, NOTICENO, NOTICESDT, OFFICETEL, ORGNM, POPFILE, PROCESSSTATE, SEXCD, SPECIALMARK, WEIGHT FROM "
//				+ "(SELECT * FROM ANIMAL ORDER BY ANIMAL_NO DESC)) WHERE KINDCD LIKE ? AND RN BETWEEN ? AND ?";
		
		String sql = "SELECT * FROM ANIMAL WHERE KINDCD LIKE '%'||?||'%' ";
		System.out.println(kind);
		
		try {
			pstm = con.prepareStatement(sql);
			System.out.println("03. query 준비");
			pstm.setString(1,kind);
			rs = pstm.executeQuery();
			System.out.println("04.query 실행");
			
			if(rs.next()) {
				list = new ArrayList<>();
				do {
					SightingDto dto = new SightingDto();
					dto.setAnimal_no(rs.getInt(2));
					dto.setAge(rs.getString(3));
					dto.setCareaddr(rs.getString(4));
					dto.setCarenm(rs.getString(5));
					dto.setCaretel(rs.getString(6));
					dto.setChargenm(rs.getString(7));
					dto.setColorcd(rs.getString(8));
					dto.setDesertionno(rs.getString(9));
					dto.setFilename(rs.getString(10));
					dto.setHappendt(rs.getString(11));
					dto.setHappenplace(rs.getString(12));
					dto.setKindcd(rs.getString(13));
					dto.setNeuteryn(rs.getString(14));
					dto.setNoticeedt(rs.getString(15));
					dto.setNoticeno(rs.getString(16));
					dto.setNoticesdt(rs.getString(17));
					dto.setOfficetel(rs.getString(18));
					dto.setOrgnm(rs.getString(19));
					dto.setPopfile(rs.getString(20));
					dto.setProcessstate(rs.getString(21));
					dto.setSexcd(rs.getString(22));
					dto.setSpecialmark(rs.getString(23));
					dto.setWeight(rs.getString(24));
					
					
					list.add(dto);
					
				}while(rs.next());
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			
			System.out.println("05. db 종료");
		}
		return list;
	}
	
	
	public String getPopFile(int animal_no) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs =null;
		String res =null;
		
		String sql = "SELECT POPFILE FROM ANIMAL WHERE ANIMAL_NO=?";
		
		try {
			pstm=con.prepareStatement(sql);
			pstm.setInt(1, animal_no);
			System.out.println("03.query 준비");
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			
			
			if(rs.next()) {
				res = "http://"+rs.getString(1);
				System.out.println(res);
			}
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05. db종료\n");
		}
		
		
		return res;
	}
	
	
	public SightingDto mainSelectOne(int no){
		Connection con = getConnection();
		PreparedStatement pstm = null;
		ResultSet rs = null;
		SightingDto dto = new SightingDto();
		
		String sql = "SELECT * FROM ANIMAL WHERE ANIMAL_NO=?";
		
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, no);
			System.out.println("03.query 준비: " + sql);
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				
				dto.setAnimal_no(rs.getInt(1));
				dto.setAge(rs.getString(2));
				dto.setCareaddr(rs.getString(3));
				dto.setCarenm(rs.getString(4));
				dto.setCaretel(rs.getString(5));
				dto.setChargenm(rs.getString(6));
				dto.setColorcd(rs.getString(7));
				dto.setDesertionno(rs.getString(8));
				dto.setFilename(rs.getString(9));
				dto.setHappendt(rs.getString(10));
				dto.setHappenplace(rs.getString(11));
				dto.setKindcd(rs.getString(12));
				dto.setNeuteryn(rs.getString(13));
				dto.setNoticeedt(rs.getString(14));
				dto.setNoticeno(rs.getString(15));
				dto.setNoticesdt(rs.getString(16));
				dto.setOfficetel(rs.getString(17));
				dto.setOrgnm(rs.getString(18));
				dto.setPopfile(rs.getString(19));
				dto.setProcessstate(rs.getString(20));
				dto.setSexcd(rs.getString(21));
				dto.setSpecialmark(rs.getString(22));
				dto.setWeight(rs.getString(23));
				
				
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			close(con);
			System.out.println("05.db 종료 \n");
		}
		
		
		
		
		
		return dto;
	}
	
	
	
	
	
	
	
	
	
	
}
