package hi_b_community.complain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import static hi_a_common.JDBCTemplate.*;


import hi_b_community.complain.dto.complainDto;
import hi_b_community.notice.dto.noticeDto;

public class ComplainDaoImpl implements ComplainDao{

	@Override
	public List<complainDto> selectAll(Connection con, int currentPage) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<complainDto> res = new ArrayList<complainDto>();
		
		int posts = 10;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts -1;
		
		try {
			pstm = con.prepareStatement(selectAllSql);
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				complainDto dto = new complainDto();
				dto.setComplain_boardno(rs.getInt(1));
				dto.setComplain_groupno(rs.getInt(2));
				dto.setComplain_groupsq(rs.getInt(3));
				dto.setComplain_titletab(rs.getInt(4));
				dto.setComplain_title(rs.getString(5));
				dto.setComplain_content(rs.getString(6));
				dto.setComplain_name(rs.getString(7));
				dto.setComplain_regdate(rs.getDate(8));
				dto.setComplain_hit(rs.getInt(9));
				dto.setComplain_deleteyn(rs.getString(10));
				dto.setComplain_myid(rs.getString(11));
				
				
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
	public complainDto selectOne(Connection con, int boardno) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		complainDto dto = new complainDto();
		
		try {
			pstm = con.prepareStatement(selectOneSql);
			pstm.setInt(1, boardno);
		
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				
				dto.setComplain_boardno(rs.getInt(1));
				dto.setComplain_groupno(rs.getInt(2));
				dto.setComplain_groupsq(rs.getInt(3));
				dto.setComplain_titletab(rs.getInt(4));
				dto.setComplain_title(rs.getString(5));
				dto.setComplain_content(rs.getString(6));
				dto.setComplain_name(rs.getString(7));
				dto.setComplain_regdate(rs.getDate(8));
				dto.setComplain_hit(rs.getInt(9));
				dto.setComplain_deleteyn(rs.getString(10));
				dto.setComplain_myid(rs.getString(11));
				
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
	public int insert(Connection con, complainDto dto) {
		
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insertSql);
			pstm.setString(1, dto.getComplain_title());
			pstm.setString(2, dto.getComplain_content());
			pstm.setString(3, dto.getComplain_name());
			pstm.setString(4, dto.getComplain_myid());
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstm);
		}
		
		return res;
	}

	@Override
	public int update(Connection con, complainDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updateSql);
			pstm.setString(1, dto.getComplain_title());
			pstm.setString(2, dto.getComplain_content());
			pstm.setInt(3, dto.getComplain_boardno());
			

			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
			close(pstm);
		}
		
		return res;
	}

	@Override
	public int updateAnswer(Connection con, int groupno, int groupsq) {
		
		PreparedStatement pstm = null;
		int res = 0; 
		
		try {
			pstm = con.prepareStatement(updateAnswerSql);
			pstm.setInt(1, groupno);
			pstm.setInt(2, groupsq);
			System.out.println("03. query준비 :"+ updateAnswerSql);
			res = pstm.executeUpdate();
			System.out.println("04. query실행 및 리턴  ");
		} catch (SQLException e) {
			System.out.println("3/4단계 에러");
			e.printStackTrace();
		}finally {
			
			close(pstm);
		}
		
		return res;
		
	
	}

	@Override
	public int insertAnswer(Connection con, complainDto dto) {
		PreparedStatement pstm = null;
		int res = 0; 
		
		try {
			pstm = con.prepareStatement(insertAnswerSql);
			pstm.setInt(1, dto.getComplain_groupno());
			pstm.setInt(2, dto.getComplain_groupsq()+1);
			pstm.setInt(3, dto.getComplain_titletab()+1);
			pstm.setString(4, dto.getComplain_title());
			pstm.setString(5, dto.getComplain_content());
			pstm.setString(6, dto.getComplain_name());
			pstm.setString(7, dto.getComplain_myid());
			
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstm);
		}
		
		return res;
	}

	@Override
	public int delete(Connection con, int boardno) {
		
		PreparedStatement pstm = null;
		int res = 0; 
		try {
			pstm = con.prepareStatement(deleteSql);
			pstm.setInt(1, boardno);
			
			
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
			close(pstm);
		}
		
		return res;
	}

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

	@Override
	public int getListCount(Connection con) {
		Statement stmt = null;
		ResultSet rset = null;
		int result = 0;
		
		try {
			stmt = con.createStatement();
			rset = stmt.executeQuery(getListCountSql);
			
			if(rset.next()) {
				result = rset.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(stmt);
		}
		
		return result;
	}

	@Override
	public List<complainDto> searchList(Connection con, String select, String keyword) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<complainDto> res = new ArrayList<complainDto>();
		String sql = null; 
		
		try {
			
			
			if(select.equals("complain_name")) {
				sql = search_name_ListSql;
			}else if(select.equals("complain_title")){
				sql = search_title_ListSql;
			}else if(select.equals("complain_content")){
				sql = search_content_ListSql;
			}
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			
			
			System.out.println("sql : "+sql);
			System.out.println("select: "+ select +"  keyword: "+keyword);
			
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				complainDto dto = new complainDto();
				
				dto.setComplain_boardno(rs.getInt(1));
				dto.setComplain_groupno(rs.getInt(2));
				dto.setComplain_groupsq(rs.getInt(3));
				dto.setComplain_titletab(rs.getInt(4));
				dto.setComplain_title(rs.getString(5));
				dto.setComplain_content(rs.getString(6));
				dto.setComplain_name(rs.getString(7));
				dto.setComplain_regdate(rs.getDate(8));
				dto.setComplain_hit(rs.getInt(9));
				dto.setComplain_deleteyn(rs.getString(10));
				dto.setComplain_myid(rs.getString(11));
				
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
