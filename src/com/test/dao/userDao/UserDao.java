package com.test.dao.userDao;

import com.test.dto.userDto.UserDto;

public interface UserDao {
	/*
	 * MYNO NUMBER CONSTRAINT PK_ANI_USER_MYNO PRIMARY KEY,
	MYID VARCHAR2(500) NOT NULL,
	MYPW VARCHAR2(500) NOT NULL,
	MYNAME VARCHAR2(1000) NOT NULL,
	MYADDR VARCHAR2(4000) NOT NULL,
	MYPHONE VARCHAR2(20) NOT NULL,
	MYEMAIL VARCHAR2(100) NOT NULL,
	MYROLE VARCHAR2(50) NOT NULL
	 * 
	 * */
	//회원가입
	//최대 NO찾기
	//로그인<= 아이디, 비밀번호 일치로 확인
	//1.해당 아이디와 연락처, 이메일을 갖는 no가 있는지 탐색
	String existSearchQuery = "SELECT MYNO FROM ANI_USER WHERE MYID=? OR MYPHONE=? OR MYEMAIL=?";
	//2.없다면 회원가입
	String signUpQuery ="INSERT INTO ANI_USER VALUES(?,?,?,?,?,?,?,?)";
	String signInQuery = "SELECT MYPW FROM ANI_USER WHERE MYID=? AND MYROLE=?";
	String getMaxUserNoQuery = "SELECT MAX(MYNO) FROM ANI_USER";
	//세션에 공유될 사용자
	String selectSpecificUserQuery = "SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYROLE FROM ANI_USER WHERE MYID=?";
	
	//회원가입전 탐색
	public int       searchBeforeSignUp(UserDto target);
	//회원가입
	public int		 executeSignUp(UserDto target);
	//로그인 쿼리를 통해 일치한다면 1, 아니라면 0 반환
	public int       authProcess(UserDto target);
	//최대 no탐색
	public int       getMaxMyNO();
	//로그인 성공시 세션에 공유될 회원의 정보
	public UserDto   selectSpecificUser(String id);

}
