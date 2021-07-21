package hi_b_community.map.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static hi_a_common.JDBCTemplate.*;
import hi_b_community.map.dto.mapDto;
import hi_b_community.map.dto.mapshelterDto;

public class MapDaoImpl implements MapDao{

	//나의 위치 찍기 + 목격 위치 찍기  
	@Override
	public int map_insert(Connection con, mapDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insertSql);
			pstm.setDouble(1, dto.getLatitude());
			pstm.setDouble(2, dto.getLongtitude());
			pstm.setString(3, dto.getLand_number_address());
			pstm.setString(4, dto.getRoad_name_address());
			pstm.setString(5, dto.getMap_writer());
			pstm.setString(6, dto.getMap_phone());
			pstm.setString(7, dto.getMap_content());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);

		}
		return res;
	}

	@Override
	public List<mapDto> selectAll(Connection con) {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<mapDto> res = new ArrayList<mapDto>();
		
		try {
			pstm = con.prepareStatement(selectAllSql);
			
			rs = pstm.executeQuery(selectAllSql);
			
			
			while(rs.next()) {
				mapDto dto = new mapDto();
				dto.setMap_no(rs.getInt(1));
				dto.setLatitude(rs.getDouble(2));
				dto.setLongtitude(rs.getDouble(3));
				dto.setLand_number_address(rs.getString(4));
				dto.setRoad_name_address(rs.getString(5));
				dto.setMap_writer(rs.getString(6));
				dto.setMap_phone(rs.getString(7));
				dto.setMap_content(rs.getString(8));
				
				
				res.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}

		return res;
	}

	@Override
	public mapDto selectOne(Connection con, int map_no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		mapDto dto = new mapDto();
		
		try {
			pstm = con.prepareStatement(selectOneSql);
			pstm.setInt(1, map_no);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto.setMap_no(rs.getInt(1));
				dto.setLatitude(rs.getDouble(2));
				dto.setLongtitude(rs.getDouble(3));
				dto.setLand_number_address(rs.getString(4));
				dto.setRoad_name_address(rs.getString(5));
				dto.setMap_writer(rs.getString(6));
				dto.setMap_phone(rs.getString(7));
				dto.setMap_content(rs.getString(8));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
		}
		
		return dto;
	}

	//수정 
	@Override
	public int update(Connection con, mapDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updateSql);
			pstm.setDouble(1, dto.getLatitude());
			pstm.setDouble(2, dto.getLongtitude());
			pstm.setString(3, dto.getLand_number_address());
			pstm.setString(4, dto.getRoad_name_address());
			pstm.setString(5, dto.getMap_writer());
			pstm.setString(6, dto.getMap_phone());
			pstm.setString(7, dto.getMap_content());
			pstm.setInt(8, dto.getMap_no());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		
		return res;
	}

	

		@Override
		public int delete(Connection con, int map_no) {
			PreparedStatement pstm = null;
			int res = 0;
			
			try {
				pstm = con.prepareStatement(deleteSql);
				pstm.setInt(1, map_no);
				
				res = pstm.executeUpdate();
				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
			}
			return res;
		}

		@Override
		public List<mapshelterDto> shelter_selectAll(Connection con) {
			PreparedStatement pstm = null;
			ResultSet rs = null;
			List<mapshelterDto> list = new ArrayList<mapshelterDto>();
			
			try {
				pstm = con.prepareStatement(shelter_selectAllSql);
				
				rs = pstm.executeQuery();
				System.out.println("sql : "+ shelter_selectAllSql);
				while(rs.next()) {
					mapshelterDto dto = new mapshelterDto();
					dto.setShelter_name(rs.getString(1));
					dto.setShelter_latitude(rs.getDouble(2));
					dto.setShelter_longtitude(rs.getDouble(3));
					
					list.add(dto);
				}
	
				
				
			} catch (SQLException e) {
				System.out.println("3,4 단계 실패");
				e.printStackTrace();
				
			}finally {
				close(rs);
				close(pstm);
			}
			
			
			return list;
		}

		//게시글 수 
		@Override
		public int getListCount(Connection con) {
			PreparedStatement pstm = null;
			ResultSet rs = null;
			int res = 0;
			
			try {
				pstm = con.prepareStatement(getListCountSql);
				
				rs = pstm.executeQuery();
				
				if(rs.next()) {
					res = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				close(pstm);
			}
			return res;
		}


}
