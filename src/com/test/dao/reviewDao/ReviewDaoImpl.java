package com.test.dao.reviewDao;

import static util.common.JDBCTemplate.close;
import static util.common.JDBCTemplate.commit;
import static util.common.JDBCTemplate.getConnection;
import static util.common.JDBCTemplate.rollback;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.test.dto.reviewDto.ReviewDto;

public class ReviewDaoImpl implements ReviewDao{
	
	Connection conn = null;
	Statement  stat = null;
	PreparedStatement ps = null;
	ResultSet  rs = null;
	SimpleDateFormat sf = new SimpleDateFormat("yyyy/MM/dd");
	
	private ReviewDaoImpl() {
		
	}
	
	private static ReviewDaoImpl dao = new ReviewDaoImpl();
	
	
	public static ReviewDaoImpl getInstance() {
		return dao;
	}


	@Override
	public int insertLostReview(ReviewDto dto) {
		// TODO Auto-generated method stub
		/*
		 * String insertLostReviewQuery="INSERT INTO LOST_ANIMALS_REVIEW VALUES(?,?,?,?,?,?,?,?,?,?,?,?,SYSDATE)";
		 * 
		 * */
		int resIns = 0;
		int maxNo = getMaxRvNo();
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(insertLostReviewQuery);
			
			ps.setInt(1, maxNo+1);
			ps.setInt(2, dto.getNum());
			ps.setString(3, dto.getTitle());
			ps.setString(4, dto.getRvPic());
			ps.setString(5, dto.getId());
			ps.setString(6, dto.getLostDate());
			ps.setString(7, dto.getProvince());
			ps.setString(8, dto.getReserv());
			ps.setString(9, dto.getLostPlace());
			ps.setString(10, dto.getSpecies());
			ps.setString(11, dto.getCate());
			ps.setString(12, dto.getContent());
			
			resIns = ps.executeUpdate();
			
			if(resIns > 0) {
				System.out.println("후기글 등록 성공");
				commit(conn);
			}else {
				System.out.println("후기글 등록 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]후기 등록 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		
		return resIns;
	}


	@Override
	public int getMaxRvNo() {
		// TODO Auto-generated method stub
		/*
		 * String getMaxRvNoQuery="SELECT MAX(RV_NO) FROM LOST_ANIMALS_REVIEW";
		 * 
		 * */
		int cnt = 0;
		
		try {
			conn = getConnection();
			stat = conn.createStatement();
			
			rs = stat.executeQuery(getMaxRvNoQuery);
			
			if(rs.next()) {
				cnt = rs.getInt(1);
				System.out.println("후기 게시판 최대 RV_NO 조회 완료");
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]최대 RV_NO조회 실패");
		}finally {
			close(rs);
			close(stat);
			close(conn);
		}
		
		return cnt;
	}


	@Override
	public int countReviewLost() {
		// TODO Auto-generated method stub
		/*
		 * String countReivewLostQuery="SELECT COUNT(RV_NO) FROM LOST_ANIMALS_REVIEW";
		 * */
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			stat = conn.createStatement();
			
			rs   = stat.executeQuery(countReivewLostQuery);
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]모든 후기글 갯수 카운트 실패");
		}finally {
			close(rs);
			close(stat);
			close(conn);
		}
		
		return cnt;
	}


	@Override
	public int totalReviewLostPage() {
		// TODO Auto-generated method stub
		//9개씩 끊어서 보여줄것
		final int unit = 9;
		int res = countReviewLost();
		int tot = (int)(Math.ceil((double)res/unit));
		return tot;
	}


	@Override
	public List<ReviewDto> selectAll(int page) {
		// TODO Auto-generated method stub
		List<ReviewDto> list = new ArrayList<>();
		/*
		 * 
		 * String  selectAllLostReviewQuery="SELECT T.* FROM (SELECT ROWNUM R, TARGET.* FROM 
		 * (SELECT RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, 
		 * CATE, CONTENT, REGDATE 
		 * FROM LOST_ANIMALS_REVIEW) TARGET)T WHERE  T.R BETWEEN ? AND ?";
		 * */
		//1,10,..=>등차수열로 i=1부터 접근한다면 9(i-1)+1=9*i-8
		final int unit= 9;
		int stIdx = unit * page-8;
		int finIdx = unit*page;
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectAllLostReviewQuery);
			
			ps.setInt(1, stIdx);
			ps.setInt(2, finIdx);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewDto target = new ReviewDto();
				
				target.setRvNo(rs.getInt(2));
				target.setNum(rs.getInt(3));
				target.setTitle(rs.getString(4));
				target.setRvPic(rs.getString(5));
				target.setId(rs.getString(6));
				target.setLostDate(sf.format(rs.getDate(7)));
				target.setProvince(rs.getString(8));
				target.setReserv(rs.getString(9));
				target.setLostPlace(rs.getString(10));
				target.setSpecies(rs.getString(11));
				target.setCate(rs.getString(12));
				target.setContent(rs.getString(13));
				target.setRegDate(sf.format(rs.getDate(14)));
				
				list.add(target);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]후기 게시판 전체글 페이징 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		
		return list;
	}


	@Override
	public ReviewDto selectOneReview(int rvNo) {
		// TODO Auto-generated method stub
		/*
		 * String  selectSpecificReviewQuery="SELECT RV_NO, NUM, TITLE, RV_PIC, ID, 
		 * LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, CONTENT,
		 *  REGDATE FROM LOST_ANIMALS_REVIEW WHERE RV_NO=?";
		 * */
		ReviewDto dto = new ReviewDto();
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(selectSpecificReviewQuery);
			ps.setInt(1, rvNo);
			System.out.println("rvNo: "+rvNo);
			rs   = ps.executeQuery();
			
			if(rs.next()) {
				dto.setRvNo(rs.getInt(1));
				dto.setNum(rs.getInt(2));
				dto.setTitle(rs.getString(3));
				dto.setRvPic(rs.getString(4));
				dto.setId(rs.getString(5));
				dto.setLostDate(sf.format(rs.getDate(6)));
				dto.setProvince(rs.getString(7));
				dto.setReserv(rs.getString(8));
				dto.setLostPlace(rs.getString(9));
				dto.setSpecies(rs.getString(10));
				dto.setCate(rs.getString(11));
				dto.setContent(rs.getString(12));
				dto.setRegDate(sf.format(rs.getDate(13)));
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]후기 상세보기 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return dto;
	}


	@Override
	public int updateLostReview(ReviewDto dto) {
		// TODO Auto-generated method stub
		/*
		 * String updateLostReviewQuery="UPDATE LOST_ANIMALS_REVIEW SET TITLE=?, RV_PIC=? ,
		 * CONTENT=? WHERE RV_NO=?";
		 * 
		 * */
		int upRes = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(updateLostReviewQuery);
			
			ps.setString(1, dto.getTitle());
			ps.setString(2, dto.getRvPic());
			ps.setString(3, dto.getContent());
			ps.setInt(4, dto.getRvNo());
			
			upRes = ps.executeUpdate();
			
			if(upRes>0) {
				System.out.println("찾은 후기 수정 성공");
				commit(conn);
			}else {
				System.out.println("찾은 후기 수정 실패");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]나의 아이 찾은 후기 수정 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return upRes;
	}


	@Override
	public int deleteLostReview(int rvNo) {
		// TODO Auto-generated method stub
		/*
		 * String deleteLostReviewQuery="DELETE FROM LOST_ANIMALS_REVIEW WHERE RV_NO=?";
		 * 
		 * */
		int delRes = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(deleteLostReviewQuery);
			
			ps.setInt(1, rvNo);
			
			delRes = ps.executeUpdate();
			
			if(delRes >0) {
				System.out.println("# "+rvNo+"번 후기글 삭제 성공!");
				commit(conn);
			}else {
				System.out.println("# "+rvNo+"번 후기글 삭제 실패!");
				rollback(conn);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]후기 삭제 실패");
		}finally {
			close(ps);
			close(conn);
		}
		
		return delRes;
	}


	@Override
	public int countReviewProv(String province) {
		// TODO Auto-generated method stub
		/*
		 * String  countProvReviewLostQuery="SELECT COUNT(RV_NO) FROM
		 *   LOST_ANIMALS_REVIEW WHERE PROVINCE=?";
		 * 
		 * */
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			
			ps   =conn.prepareStatement(countProvReviewLostQuery);
			
			ps.setString(1, province);
			
			rs  = ps.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]지역별 후기글 카운트 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return cnt;
	}


	@Override
	public int totalPageProvReview(String province) {
		// TODO Auto-generated method stub
		//9개씩 끊어서 보여줄것
		final int unit = 9;
		int res = countReviewProv(province);
		int tot = (int)(Math.ceil((double)res/unit));
		return tot;
	}


	@Override
	public List<ReviewDto> selectReviewProv(String province, int page) {
		// TODO Auto-generated method stub
		/*
		 * String  selectProvReviewQuery= "SELECT T.* FROM (SELECT ROWNUM R, 
		 * TARGET.* FROM (SELECT RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, 
		 * RESERV, LOSTPLACE, SPECIES, CATE, CONTENT, REGDATE FROM   
		 * LOST_ANIMALS_REVIEW WHERE  PROVINCE=? ORDER  BY RV_NO DESC)TARGET)T 
		 * WHERE  T.R BETWEEN ? AND ?";
		 * 
		 * */
		List<ReviewDto> list = new ArrayList<>();
		
		final int unit= 9;
		int stIdx = unit * page-8;
		int finIdx = unit*page;
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectProvReviewQuery);
			
			ps.setString(1, province);
			ps.setInt(2, stIdx);
			ps.setInt(3, finIdx);
			
			rs = ps.executeQuery();
			
			while(rs.next()) {
				ReviewDto target = new ReviewDto();
				
				target.setRvNo(rs.getInt(2));
				target.setNum(rs.getInt(3));
				target.setTitle(rs.getString(4));
				target.setRvPic(rs.getString(5));
				target.setId(rs.getString(6));
				target.setLostDate(sf.format(rs.getDate(7)));
				target.setProvince(rs.getString(8));
				target.setReserv(rs.getString(9));
				target.setLostPlace(rs.getString(10));
				target.setSpecies(rs.getString(11));
				target.setCate(rs.getString(12));
				target.setContent(rs.getString(13));
				target.setRegDate(sf.format(rs.getDate(14)));
				
				list.add(target);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]후기 게시판 지역별 탐색 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return list;
	}


	@Override
	public int countReviewByUser(String id) {
		// TODO Auto-generated method stub
		/*
		 * String  countUserRegReviewQuery="SELECT COUNT(RV_NO) 
		 * FROM   LOST_ANIMALS_REVIEW WHERE  ID=?";
		 * 
		 * */
		int cnt = 0;
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(countUserRegReviewQuery);
			
			ps.setString(1, id);
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				cnt = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]회원별 게시글 카운트 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return cnt;
	}


	@Override
	public int totalPageUser(String id) {
		// TODO Auto-generated method stub
		//9개씩 보여줄 것
		final int unit = 9;
		int res = countReviewByUser(id);
		int tot = (int)(Math.ceil((double)res/unit));
		return tot;
	}


	@Override
	public List<ReviewDto> selectReviewUser(String id, int page) {
		// TODO Auto-generated method stub
		/*
		 * String  selectUserReviewQuery="SELECT T.* FROM 
		 * (SELECT ROWNUM R, TARGET.* FROM (SELECT RV_NO, NUM, 
		 * TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, 
		 * CONTENT, REGDATE FROM   LOST_ANIMALS_REVIEW WHERE  ID=? ORDER  BY RV_NO DESC) TARGET)
		 * T WHERE T.R BETWEEN ? AND ?";
		 * */
		
		List<ReviewDto> list = new ArrayList<>();
		final int unit = 9;
		int stIdx = unit * page-8;
		int finIdx = unit*page;
		
		try {
			
			conn = getConnection();
			
			ps   =conn.prepareStatement(selectUserReviewQuery);
			
			ps.setString(1, id);
			ps.setInt(2, stIdx);
			ps.setInt(3, finIdx);
			
			rs = ps.executeQuery();
			
			
			while(rs.next()) {
				ReviewDto dto = new ReviewDto();
				
				dto.setRvNo(rs.getInt(2));
				dto.setNum(rs.getInt(3));
				dto.setTitle(rs.getString(4));
				dto.setRvPic(rs.getString(5));
				dto.setId(rs.getString(6));
				dto.setLostDate(sf.format(rs.getDate(7)));
				dto.setProvince(rs.getString(8));
				dto.setReserv(rs.getString(9));
				dto.setLostPlace(rs.getString(10));
				dto.setSpecies(rs.getString(11));
				dto.setCate(rs.getString(12));
				dto.setContent(rs.getString(13));
				dto.setRegDate(sf.format(rs.getDate(14)));
				
				list.add(dto);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]회원별 후기 게시글 조회 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		
		return list;
	}

	
}
