package pij.adopt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pij.adopt.dto.AdoptDto;

import static common.JDBCTemplate.*;
public class AdoptDaoImpl implements AdoptDao {

	@Override
	public List<AdoptDto> selectAll(Connection con,int startRow, int endRow) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<AdoptDto> res = new ArrayList<AdoptDto>();
		
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
					AdoptDto dto = new AdoptDto();
					dto.setAdopt_no(rs.getInt(2));
					dto.setAdopt_writer(rs.getString(3));
					dto.setAdopt_phone(rs.getString(4));
					dto.setAdopt_title(rs.getString(5));
					dto.setAdopt_sex(rs.getString(6));
					dto.setAdopt_place(rs.getString(7));
					dto.setAdopt_content(rs.getString(8));
					dto.setAdopt_photo(rs.getString(9));
					dto.setAdopt_email(rs.getString(10));
					dto.setAdopt_kind(rs.getString(11));
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
	
	@Override
	public int getCount() {
		int count=0;
		
		String sql = "SELECT COUNT(*) FROM ADOPT";
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
	public AdoptDto selectOne(Connection con, int no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		AdoptDto dto = new AdoptDto();
		
		try {
			pstm = con.prepareStatement(selectOne);
			pstm.setInt(1, no);
			System.out.println("03.query 준비 : " + selectOne);
			rs = pstm.executeQuery();
			System.out.println("04.query 실행 및 리턴");
			
			if(rs.next()) {
				 dto = new AdoptDto();
				dto.setAdopt_no(rs.getInt(1));
				dto.setAdopt_writer(rs.getString(2));
				dto.setAdopt_phone(rs.getString(3));
				dto.setAdopt_title(rs.getString(4));
				dto.setAdopt_sex(rs.getString(5));
				dto.setAdopt_place(rs.getString(6));
				dto.setAdopt_content(rs.getString(7));
				dto.setAdopt_photo(rs.getString(8));
				dto.setAdopt_email(rs.getString(9));
				dto.setAdopt_kind(rs.getString(10));
				
			}
			
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			System.out.println("05. db 종료 \n");
		}
		
		return dto;
	}

	@Override
	public int insert(Connection con, AdoptDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insert);
			pstm.setString(1, dto.getAdopt_writer());
			pstm.setString(2, dto.getAdopt_phone());
			pstm.setString(3, dto.getAdopt_title());
			pstm.setString(4, dto.getAdopt_sex());
			pstm.setString(5, dto.getAdopt_place());
			pstm.setString(6, dto.getAdopt_content());
			pstm.setString(7, dto.getAdopt_email());
			pstm.setString(8, dto.getAdopt_kind());
			System.out.println("03. query 준비 :" + insert);
			
			res = pstm.executeUpdate();
			System.out.println("04. query 실행 및 리턴");
			
			
		} catch (SQLException e) {
			System.out.println("3/4단계에러");
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		
		
		return res;
	}

	@Override
	public int update(Connection con, AdoptDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(update);
			
			pstm.setString(1, dto.getAdopt_writer());
			pstm.setString(2, dto.getAdopt_phone());
			pstm.setString(3, dto.getAdopt_title());
			pstm.setString(4, dto.getAdopt_sex());
			pstm.setString(5, dto.getAdopt_place());
			pstm.setString(6, dto.getAdopt_content());
			pstm.setString(7, dto.getAdopt_email());
			pstm.setString(8, dto.getAdopt_kind());
			pstm.setInt(9, dto.getAdopt_no());
			System.out.println("03.query 준비 :" + update);
			res = pstm.executeUpdate();
			System.out.println("04.query 실행 및 리턴");
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally{
			close(pstm);
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
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			close(pstm);
			System.out.println("05.db 종료\n");
		}
		
		return res;
	}

}
