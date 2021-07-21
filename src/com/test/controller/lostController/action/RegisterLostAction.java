package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import join.dto.AniDto;

public class RegisterLostAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		AniDto user = (AniDto)session.getAttribute("dto");
		String url = "";
		String msg="";
		//String context = request.getContextPath();
		
		if(user==null) {
			msg="회원가입 및 로그인을 부탁드립니다";
			url="lost.do?command=lostMain&page=1";
		}else {
			msg="실종신고 등록 페이지로 이동합니다";
			url="./template/registerLost.jsp";
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
