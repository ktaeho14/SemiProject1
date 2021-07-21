package com.test.controller.reviewController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;

import join.dto.AniDto;

public class RegisterReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl lostDao = LostDaoImpl.getInstance();
		int         num     = Integer.valueOf(request.getParameter("num"));
		LostDto     target  = lostDao.selectOne(num);//후기 대상 찾기 완료
		String url ="";
		HttpSession session = request.getSession();
		AniDto     user    = (AniDto)session.getAttribute("dto");
		String      sessionId= null;
		String      compId  = target.getWriter();
		//세션에 로그인되어 있는 경우에만 
		//후기 작성 폼으로 이동할 것
		String msg="";
		if(user!=null && (sessionId= user.getMyid()).equals(compId)) {
			//로그인된 경우
			url ="./template/registerLostReview.jsp";
			request.setAttribute("toRegisterLostReview", target);
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {
			
			//유효하지 않은 정보에 대해서는 차단
			url = "lost.do?command=lostMain&page=1";
			msg = "잘못된 접근입니다";
			alertByJavascript(msg,url,response);
		}
		
		
		
		
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
