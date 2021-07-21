package ksh.witness.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ksh.witness.dto.WitnessDto;

import static common.JDBCTemplate.*;
public class WitnessDaoImpl implements WitnessDao{

	@Override
	public List<WitnessDto> selectAll(Connection con,int startRow, int endRow) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<WitnessDto> res = new ArrayList<WitnessDto>();
		
		try {
			pstm = con.prepareStatement(selectAll);
			System.out.println("03. query 준비");
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);
			rs = pstm.executeQuery();
			System.out.println("04.query 실행");
			
			if(rs.next()) {
				res = new ArrayList<>();
				do {
					WitnessDto dto = new WitnessDto();
					dto.setNo(rs.getInt(2));
					dto.setColor(rs.getString(3));
					dto.setKind(rs.getString(4));
					dto.setPlace(rs.getString(5));
					dto.setSpecialmark(rs.getString(6));
					dto.setWitness_pic(rs.getString(7));
					dto.setContent(rs.getString(8));
					dto.setWitness_date(rs.getDate(9));
					dto.setPhone_no(rs.getString(10));
					dto.setCity(rs.getString(11));
					dto.setWriter(rs.getString(12));
					res.add(dto);
				}while(rs.next());
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		
		
		return res;
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
	
	
	
	
	@Override
	public WitnessDto selectOne(Connection con, int no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		WitnessDto dto = new WitnessDto();
		
		try {
			pstm = con.prepareStatement(selectOne);
			pstm.setInt(1, no);
			System.out.println("03.query 준비: "+ selectOne);
			rs = pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			if(rs.next()) {
				 dto = new WitnessDto();
				dto.setNo(rs.getInt(1));
				dto.setColor(rs.getString(2));
				dto.setKind(rs.getString(3));
				dto.setPlace(rs.getString(4));
				dto.setSpecialmark(rs.getString(5));
				dto.setWitness_pic(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setWitness_date(rs.getDate(8));
				dto.setPhone_no(rs.getString(9));
				dto.setCity(rs.getString(10));
				dto.setWriter(rs.getString(11));
			}
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		return dto;
	}

	@Override
	public int insert(Connection con, WitnessDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm=con.prepareStatement(insert);
			pstm.setString(1, dto.getColor());
			pstm.setString(2, dto.getKind());
			pstm.setString(3, dto.getPlace());
			pstm.setString(4, dto.getSpecialmark());
			pstm.setString(5, dto.getWitness_pic());
			pstm.setString(6, dto.getContent());
			pstm.setString(7, dto.getPhone_no());
			pstm.setString(8, dto.getCity());
			pstm.setString(9, dto.getWriter());
			System.out.println("03. query 준비: " + insert);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05.db 종료");
		}
		return res;
	}

	@Override
	public int update(Connection con, WitnessDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm=con.prepareStatement(update);
			pstm.setString(1, dto.getColor());
			pstm.setString(2, dto.getKind());
			pstm.setString(3, dto.getPlace());
			pstm.setString(4, dto.getSpecialmark());
			pstm.setString(5, dto.getWitness_pic());
			pstm.setString(6, dto.getContent());
			pstm.setString(7, dto.getPhone_no());
			pstm.setString(8, dto.getCity());
			pstm.setInt(9, dto.getNo());
			
			System.out.println("03. query 준비" + update);
			
			res=pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. db종료\n");
		}
		
		return res;
	}

	@Override
	public int delete(Connection con, int no) {
		PreparedStatement pstm = null;
		int res = 0;
		
		
		try {
			pstm = con.prepareStatement(delete);
			pstm.setInt(1, no);
			System.out.println("03. query 준비 : "+ delete);
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
		} catch (SQLException e) {
			System.out.println("3/4 단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05. db 종료\n");
		}
		return res;
	}

	@Override
	public List<WitnessDto> selectAllCity(Connection con, String city,String kind) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<WitnessDto> res = new ArrayList<WitnessDto>();
		
		
		try {
			pstm = con.prepareStatement(selectAllCity_Kind);
			pstm.setString(1, city);
			pstm.setString(2, kind);
			System.out.println("03.query 준비: " + selectAllCity_Kind);
			rs = pstm.executeQuery();
			System.out.println("04. qurey 실행 및 리턴");
			
			while(rs.next()) {
				WitnessDto dto = new WitnessDto();
				dto.setNo(rs.getInt(1));
				dto.setColor(rs.getString(2));
				dto.setKind(rs.getString(3));
				dto.setPlace(rs.getString(4));
				dto.setSpecialmark(rs.getString(5));
				dto.setWitness_pic(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setWitness_date(rs.getDate(8));
				dto.setPhone_no(rs.getString(9));
				dto.setCity(rs.getString(10));
				res.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		return res;
	}

	@Override
	public List<WitnessDto> selectAllContent(Connection con, String content) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<WitnessDto> res = new ArrayList<WitnessDto>();
		
		try {
			pstm = con.prepareStatement(selectAllContent);
			pstm.setString(1, content);
			System.out.println("03.query 준비: " + selectAllContent);
			rs = pstm.executeQuery();
			System.out.println("04. query 실행 및 리턴");
			
			while(rs.next()) {
				WitnessDto dto = new WitnessDto();
				dto.setNo(rs.getInt(1));
				dto.setColor(rs.getString(2));
				dto.setKind(rs.getString(3));
				dto.setPlace(rs.getString(4));
				dto.setSpecialmark(rs.getString(5));
				dto.setWitness_pic(rs.getString(6));
				dto.setContent(rs.getString(7));
				dto.setWitness_date(rs.getDate(8));
				dto.setPhone_no(rs.getString(9));
				dto.setCity(rs.getString(10));
				res.add(dto);
			}
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		return res;
	}

}
