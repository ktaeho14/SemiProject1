package hi_b_community.notice.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static hi_a_common.JDBCTemplate.*;
import hi_b_community.notice.dto.noticeDto;

public class NoticeDaoImpl implements NoticeDao{

	@Override
	public List<noticeDto> selectAll(Connection con, int currentPage) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<noticeDto> res = new ArrayList<noticeDto>();
		
		int posts = 10;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts -1;
		
		try {
			pstm = con.prepareStatement(selectAllSql);
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);

			
			System.out.println("쿼리문 실행 : "+selectAllSql);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				noticeDto dto = new noticeDto();
				
				dto.setNotice_no(rs.getInt(1));
				dto.setNotice_title(rs.getString(2));
				dto.setNotice_name(rs.getString(3));
				dto.setNotice_content(rs.getString(4));
				dto.setNotice_regdate(rs.getDate(5));
				dto.setNotice_hit(rs.getInt(6));
				dto.setNotice_deleteyn(rs.getString(7));
				dto.setNotice_myid(rs.getString(8));
				
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
	public noticeDto selectOne(Connection con, int no) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		noticeDto dto = null;

		try {
			pstm = con.prepareStatement(selectOneSql);
			pstm.setInt(1, no);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				dto = new noticeDto();
				
				dto.setNotice_no(rs.getInt(1));
				dto.setNotice_title(rs.getString(2));
				dto.setNotice_name(rs.getString(3));
				dto.setNotice_content(rs.getString(4));
				dto.setNotice_regdate(rs.getDate(5));
				dto.setNotice_hit(rs.getInt(6));
				dto.setNotice_deleteyn(rs.getString(7));
				dto.setNotice_myid(rs.getString(8));
				
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(rs);
			close(pstm);
			
		}
		
		return dto;
	}

	@Override
	public int insert(Connection con, noticeDto dto) {
		PreparedStatement pstm = null;
		int res =0;
		
		try {
			pstm = con.prepareStatement(insertSql);
			pstm.setString(1, dto.getNotice_title());
			pstm.setString(2, dto.getNotice_name());
			pstm.setString(3, dto.getNotice_content());
			pstm.setString(4, dto.getNotice_myid());
			
			res = pstm.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstm);
		}
		
		return res;
	}

	@Override
	public int update(Connection con, noticeDto dto) {
		PreparedStatement pstm = null;
		int res =0;
		
		try {
			pstm = con.prepareStatement(updateSql);
			pstm.setString(1, dto.getNotice_title());
			pstm.setString(2, dto.getNotice_content());
			pstm.setInt(3, dto.getNotice_no());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return res;
	}

	@Override
	public int delete(Connection con, int no, String myid) {
		PreparedStatement pstm = null;
		int res =0;
		
		try {
			pstm = con.prepareStatement(deleteSql);
			pstm.setInt(1, no);
			pstm.setString(2, myid);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return res;
	}

	//조회수 증가 
	@Override
	public int increase(Connection con, int no) {
		PreparedStatement pstm = null;
		int res =0;
		
		try {
			pstm = con.prepareStatement(increaseSql);
			pstm.setInt(1, no);
			
			res = pstm.executeUpdate();
			System.out.println("게시판 글보기 조회수 1 증가");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close(pstm);
		}
		return res;
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

	//검색 
	@Override
	public List<noticeDto> searchList(Connection con, String select, String keyword) {
		
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<noticeDto> res = new ArrayList<noticeDto>();
		String sql = null; 
		
		try {
			if(select.equals("notice_name")) {
				sql = search_name_ListSql;
			}else if(select.equals("notice_title")){
				sql = search_title_ListSql;
			}else if(select.equals("notice_content")){
				sql = search_content_ListSql;
			}
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			
			System.out.println("sql : "+sql);
			System.out.println("select: "+ select +"  keyword: "+keyword);
			
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				noticeDto dto = new noticeDto();
				
			
				dto.setNotice_no(rs.getInt(1));
				dto.setNotice_title(rs.getString(2));
				dto.setNotice_name(rs.getString(3));
				dto.setNotice_content(rs.getString(4));
				dto.setNotice_regdate(rs.getDate(5));
				dto.setNotice_hit(rs.getInt(6));
				dto.setNotice_deleteyn(rs.getString(7));
				dto.setNotice_myid(rs.getString(8));
				
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



}
