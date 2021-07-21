package com.test.dao.userDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
//static import->빈번한 클래스 호출로 인한 부하 감소 예방
import static util.common.JDBCTemplate.*;

import com.test.dto.userDto.UserDto;

public class UserDaoImpl implements UserDao{
	//singleton-생성패턴
	private static UserDaoImpl dao = new UserDaoImpl();
	
	Connection 		conn = null;
	Statement 	    stat = null;
	PreparedStatement ps = null;
	ResultSet 		  rs = null;
	
	private UserDaoImpl() {
		
	}
	
	public static UserDaoImpl getInstance() {
		return dao;
	}
	//회원가입 전 탐색
	@Override
	public int searchBeforeSignUp(UserDto target) {
		// TODO Auto-generated method stub
		/*
		 * SELECT MYNO FROM ANI_USER WHERE MYID=? OR MYPHONE=? OR MYEMAIL=?"
		 * */
		int    resultOfComp= 0;
		int     savedInDbNo = 0;//db에 저장된 정보 중 일치하는 경우가 있다면 그 경우의 번호를 추출
		
		try {
			
			conn = getConnection();
			ps   = conn.prepareStatement(existSearchQuery);
			
			ps.setString(1, target.getMyId());
			ps.setString(2, target.getMyPhone());
			ps.setString(3, target.getMyEmail());
			
			rs   = ps.executeQuery();
			
			if(rs.next()) {
				savedInDbNo = rs.getInt(1);
			}else {
				savedInDbNo = 0;
			}
			
			if(savedInDbNo==0) {
				resultOfComp = 0;//존재하지 않는 회원 , 즉 회원가입 가능
			}else {
				resultOfComp = 1;//존재하는 회원, 즉 회원가입 불가
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]회원가입 전 기존 정보 존재 탐색 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		
		return resultOfComp;
	}
	//회원가입 전 탐색 결과를 이용한 회원가입 진행->캡슐회 encapsulation -> 각 기능간 연관성 증대
	@Override
	public int executeSignUp(UserDto target) {
		// TODO Auto-generated method stub
		int beforeSignUp = searchBeforeSignUp(target);
		int res = 0;
		//no가져오기
		int maxNo = getMaxMyNO();
		/*
		 * INSERT INTO ANI_USER VALUES(?,?,?,?,?,?,?,?)
		 * 
		 * */
		if(beforeSignUp==1) {
			//회원가입가능한 경우에만 쿼리 진행
			try {
				conn = getConnection();
				ps   = conn.prepareStatement(signUpQuery);
				
				ps.setInt(1, maxNo+1);
				ps.setString(2, target.getMyId());
				ps.setString(3, target.getMyPw());
				ps.setString(4, target.getMyName());
				ps.setString(5, target.getMyAddr());
				ps.setString(6,target.getMyPhone());
				ps.setString(7, target.getMyEmail());
				ps.setString(8, target.getMyRole());
				
				res = ps.executeUpdate();
				
				if(res > 0) {
					System.out.println("회원가입 성공!");
					commit(conn);
					res = 1;//회원가입에 성공한 경우
				}else {
					System.out.println("회원가입 실패");
					rollback(conn);
					res = 0;//존재하지 않지만 회원가입에 실패한 경우
				}
				
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("[ERR]회원가입 진행 중 문제발생");
			}finally {
				close(ps);
				close(conn);
			}
		}else {
			res = -1;//존재하는 회원인 경우
		}
		
		return res;
	}
	
	//로그인절차-전달된 객체의 비밀번호와 DB에 저장된 비밀번호가 일치하면 1, 아니면 -1 반환
	@Override
	public int authProcess(UserDto target) {
		// TODO Auto-generated method stub
		int regAuth			 = 0;
		String passwordSaved = "";
		String passwordIn    = target.getMyPw();
		/*
		 * SELECT MYPW FROM ANI_USER WHERE MYID=? AND MYROLE=?
		 * 
		 * */
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(signInQuery);
			
			ps.setString(1, target.getMyId());
			ps.setString(2, target.getMyRole());
			
			rs = ps.executeQuery();
			
			if(rs.next()) {
				passwordSaved = rs.getString(1);//존재한 경우
			}else {
				//존재하지 않은 경우
				regAuth	 = -2;
			}
			
			if(passwordSaved.equals(passwordIn)) {
				regAuth	 = 1;//일치한 경우
			}else {
				//db에는 있었지만 일치하지 않은 경우
				regAuth	 = -1;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]회원가입 처리 중 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return regAuth;
	}

	//최대 no값 찾기
	@Override
	public int getMaxMyNO() {
		// TODO Auto-generated method stub
		int getMaxNo = 0;
		
		try {
			conn =getConnection();
			stat = conn.createStatement();
			rs   = stat.executeQuery(getMaxUserNoQuery);
			
			if(rs.next()) {
				getMaxNo = rs.getInt(1);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]최대 회원번호값 탐색 실패");
		}finally {
			close(rs);
			close(stat);
			close(conn);
		}
		
		return getMaxNo;
	}

	@Override
	public UserDto selectSpecificUser(String id) {
		// TODO Auto-generated method stub
		/*"SELECT MYNO, MYID, MYPW, MYNAME, MYADDR, MYPHONE, MYEMAIL, MYROLE FROM ANI_USER WHERE MYID=?"*/
		UserDto user = new UserDto();
		
		try {
			
			conn = getConnection();
			
			ps   = conn.prepareStatement(selectSpecificUserQuery);
			
			ps.setString(1, id);
			
			rs    = ps.executeQuery();
			
			if(rs.next()) {
				user.setMyNo(rs.getInt(1));
				user.setMyId(rs.getString(2));
				user.setMyPw(rs.getString(3));
				user.setMyName(rs.getString(4));
				user.setMyAddr(rs.getString(5));
				user.setMyPhone(rs.getString(6));
				user.setMyEmail(rs.getString(7));
				user.setMyRole(rs.getString(8));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("[ERR]유저 추출 실패");
		}finally {
			close(rs);
			close(ps);
			close(conn);
		}
		
		return user;
	}
	
	
	
}
