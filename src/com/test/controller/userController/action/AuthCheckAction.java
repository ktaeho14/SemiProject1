package com.test.controller.userController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.userDao.UserDaoImpl;
import com.test.dto.userDto.UserDto;

public class AuthCheckAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		UserDaoImpl dao = UserDaoImpl.getInstance();
		UserDto    target= new UserDto();
		
		String    userId = request.getParameter("userId");
		String    userPw = request.getParameter("userPw");
		String    myRole = request.getParameter("myRole");
		int       signIn = 0;
		String    msg    = "";
		String    url    = "";
		String    context = request.getContextPath();
		HttpSession session = request.getSession();
		UserDto   shared = null;
		
		target.setMyId(userId);
		target.setMyPw(userPw);
		target.setMyRole(myRole);
		
		signIn =dao.authProcess(target);
		//존재하지 않는 회원은 -2, 비밀번호 불일치시 -1, 비밀번호 일치시 1
		if(signIn==-2) {
			msg = "회원가입을 부탁드립니다!";
			url = "login.do?command=signUp";
		}else if(signIn==-1) {
			msg = "아이디 혹은 비밀번호가 일치하지 않습니다";
			url = "login.do?command=logIn";
		}else if(signIn==1) {
			msg = "로그인되셨습니다!";
			url = context;
			shared = dao.selectSpecificUser(userId);
			session.setAttribute("user", shared);
		}
		
		alertByJavascript(msg,url,response);
		
	}

	private void alertByJavascript(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String    alert = "<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		
		out.print(alert);
	}
}
