package com.test.dao.reviewDao;

import java.util.List;

import com.test.dto.reviewDto.ReviewDto;

public interface ReviewDao {
	//후기등록
	String insertLostReviewQuery="INSERT INTO LOST_ANIMALS_REVIEW(RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, CONTENT) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
	//후기수정
	String updateLostReviewQuery="UPDATE LOST_ANIMALS_REVIEW SET TITLE=?, RV_PIC=?,CONTENT=? WHERE RV_NO=?";
	//후기삭제
	String deleteLostReviewQuery="DELETE FROM LOST_ANIMALS_REVIEW WHERE RV_NO=?";
	//최대RV_NO찾기
	String getMaxRvNoQuery="SELECT MAX(RV_NO) FROM LOST_ANIMALS_REVIEW";
	//전체게시글갯수 카운트
	String countReivewLostQuery="SELECT COUNT(RV_NO) FROM LOST_ANIMALS_REVIEW";
	//전체게시글 페이징조회
	String  selectAllLostReviewQuery="SELECT T.* FROM (SELECT ROWNUM R, TARGET.* FROM (SELECT RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, CONTENT, REGDATE FROM LOST_ANIMALS_REVIEW) TARGET)T WHERE  T.R BETWEEN ? AND ?";
	//특정 후기 하나만  보여주기!
	String  selectSpecificReviewQuery="SELECT RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, CONTENT, REGDATE FROM LOST_ANIMALS_REVIEW WHERE RV_NO=?";
	//지역별 총게시물 갯수 카운트
	String  countProvReviewLostQuery="SELECT COUNT(RV_NO) FROM  LOST_ANIMALS_REVIEW WHERE PROVINCE=?";
	//지역별 총게시물 페이징조회-등록번호 내림차순으로 조회
	String  selectProvReviewQuery= "SELECT T.* FROM (SELECT ROWNUM R, TARGET.* FROM (SELECT RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, CONTENT, REGDATE FROM   LOST_ANIMALS_REVIEW WHERE  PROVINCE=? ORDER  BY RV_NO DESC)TARGET)T WHERE  T.R BETWEEN ? AND ?";
	//회원별 게시글 총 갯수 카운트
	String  countUserRegReviewQuery="SELECT COUNT(RV_NO) FROM   LOST_ANIMALS_REVIEW WHERE  ID=?";
	//회원별 게시글 페이징조회
	String  selectUserReviewQuery="SELECT T.* FROM (SELECT ROWNUM R, TARGET.* FROM (SELECT RV_NO, NUM, TITLE, RV_PIC, ID, LOSTDATE, PROVINCE, RESERV, LOSTPLACE, SPECIES, CATE, CONTENT, REGDATE FROM   LOST_ANIMALS_REVIEW WHERE  ID=? ORDER  BY RV_NO DESC) TARGET) T WHERE T.R BETWEEN ? AND ?";
	
	//후기등록
	public int  insertLostReview(ReviewDto dto);
	//최대 RV_NO찾기
	public int  getMaxRvNo();
	//전체 후기글 갯수 세기
	public int  countReviewLost();
	//전체 후기글이 가질 수 있는 총 페이지수 파악하기
	public int  totalReviewLostPage();
	//전체게시글 페이징조회
	public List<ReviewDto> selectAll(int page);
	//상세조회
	public ReviewDto selectOneReview(int rvNo);
	//후기수정
	public int  updateLostReview(ReviewDto dto);
	//후기 삭제
	public int  deleteLostReview(int rvNo);
	//지역별 총 게시물 카운트
	public int  countReviewProv(String province);
	//지역별 모든 게시물을 표시할 수 있는 페이지수 계산
	public int  totalPageProvReview(String province);
	//지역별 후기 게시글 페이징 조회
	public List<ReviewDto> selectReviewProv(String province, int page);
	//회원별 게시글 총 갯수 카운트
	public int  countReviewByUser(String id);
	//회원별 게시글에 따른 모든 페이지수 계산
	public int  totalPageUser(String id);
	//회원별 게시글 페이징 조회
	public List<ReviewDto> selectReviewUser(String id, int page);
}
