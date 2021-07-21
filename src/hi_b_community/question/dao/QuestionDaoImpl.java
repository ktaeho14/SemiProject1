package hi_b_community.question.dao;

import static hi_a_common.JDBCTemplate.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import hi_b_community.complain.dto.complainDto;
import hi_b_community.question.dto.questionDto;

public class QuestionDaoImpl implements QuestionDao{

	@Override
	public List<questionDto> selectAll(Connection con, int currentPage) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<questionDto> res = new ArrayList<questionDto>();
		
		int posts = 10;
		
		int startRow = (currentPage - 1) * posts + 1;
		int endRow = startRow + posts -1;
		
		try {
			pstm = con.prepareStatement(selectAllSql);
			pstm.setInt(1, startRow);
			pstm.setInt(2, endRow);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				questionDto dto = new questionDto();
				dto.setQuestion_boardno(rs.getInt(1));
				dto.setQuestion_groupno(rs.getInt(2));
				dto.setQuestion_groupsq(rs.getInt(3));
				dto.setQuestion_titletab(rs.getInt(4));
				dto.setQuestion_title(rs.getString(5));
				dto.setQuestion_content(rs.getString(6));
				dto.setQuestion_name(rs.getString(7));
				dto.setQuestion_regdate(rs.getDate(8));
				dto.setQuestion_hit(rs.getInt(9));
				dto.setQuestion_deleteyn(rs.getString(10));
				dto.setQuestion_myid(rs.getString(11));
				
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
	public questionDto selectOne(Connection con, int q_boardno) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		questionDto dto = new questionDto();
		
		try {
			pstm = con.prepareStatement(selectOneSql);
			pstm.setInt(1, q_boardno);
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				
				dto.setQuestion_boardno(rs.getInt(1));
				dto.setQuestion_groupno(rs.getInt(2));
				dto.setQuestion_groupsq(rs.getInt(3));
				dto.setQuestion_titletab(rs.getInt(4));
				dto.setQuestion_title(rs.getString(5));
				dto.setQuestion_content(rs.getString(6));
				dto.setQuestion_name(rs.getString(7));
				dto.setQuestion_regdate(rs.getDate(8));
				dto.setQuestion_hit(rs.getInt(9));
				dto.setQuestion_deleteyn(rs.getString(10));
				dto.setQuestion_myid(rs.getString(11));
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
	public int insert(Connection con, questionDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(insertSql);
			pstm.setString(1, dto.getQuestion_title());
			pstm.setString(2, dto.getQuestion_content());
			pstm.setString(3, dto.getQuestion_name());
			pstm.setString(4, dto.getQuestion_myid());
			System.out.println("insertSql: "+insertSql);
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println("3/4단계 실패");
			e.printStackTrace();
		}finally {
			close(pstm);
		}

		
		return res;
	}

	@Override
	public int update(Connection con, questionDto dto) {
		PreparedStatement pstm = null;
		int res = 0;
		
		try {
			pstm = con.prepareStatement(updateSql);
			pstm.setString(1, dto.getQuestion_title());
			pstm.setString(2, dto.getQuestion_content());
			pstm.setInt(3, dto.getQuestion_boardno());
			
			
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
			
			res = pstm.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally { 
			close(pstm);
		}
		
		return res;
	}

	@Override
	public int insertAnswer(Connection con, questionDto dto) {
		PreparedStatement pstm = null;
		int res = 0; 
		
		try {
			pstm = con.prepareStatement(insertAnswerSql);
			pstm.setInt(1, dto.getQuestion_groupno());
			pstm.setInt(2, dto.getQuestion_groupsq()+1);
			pstm.setInt(3, dto.getQuestion_titletab()+1);
			pstm.setString(4, dto.getQuestion_title());
			pstm.setString(5, dto.getQuestion_content());
			pstm.setString(6, dto.getQuestion_name());
			pstm.setString(7, dto.getQuestion_myid());
			
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
	public List<questionDto> searchList(Connection con, String select, String keyword) {
		PreparedStatement pstm = null;
		ResultSet rs = null;
		List<questionDto> res = new ArrayList<questionDto>();
		String sql = null; 
		
		try {
			
			
			if(select.equals("question_name")) {
				sql = search_name_ListSql;
			}else if(select.equals("question_title")){
				sql = search_title_ListSql;
			}else if(select.equals("question_content")){
				sql = search_content_ListSql;
			}
			
			pstm = con.prepareStatement(sql);
			pstm.setString(1, "%"+keyword+"%");
			
			
			System.out.println("sql : "+sql);
			System.out.println("select: "+ select +"  keyword: "+keyword);
			
			
			rs = pstm.executeQuery();
			
			while(rs.next()) {
				questionDto dto = new questionDto();
				
				dto.setQuestion_boardno(rs.getInt(1));
				dto.setQuestion_groupno(rs.getInt(2));
				dto.setQuestion_titletab(rs.getInt(4));
				dto.setQuestion_title(rs.getString(5));
				dto.setQuestion_content(rs.getString(6));
				dto.setQuestion_name(rs.getString(7));
				dto.setQuestion_regdate(rs.getDate(8));
				dto.setQuestion_hit(rs.getInt(9));
				dto.setQuestion_deleteyn(rs.getString(10));
				dto.setQuestion_myid(rs.getString(11));
				
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
