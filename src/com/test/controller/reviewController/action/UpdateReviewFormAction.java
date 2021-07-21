package com.test.controller.reviewController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

import join.dto.AniDto;

public class UpdateReviewFormAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		int          rvNo = Integer.valueOf(request.getParameter("rvNo"));
		//접근이 유효한지 확인하기
		HttpSession session = request.getSession();
		AniDto         user = (AniDto)session.getAttribute("dto");
		
		String      sessionId= null;
		
		//특정 객체를 조회해서 기본값으로 넘겨주기&& 접근 유효성 확인
		ReviewDto    dto  = dao.selectOneReview(rvNo);
		String     compId = dto.getId();
		String     msg    = "";
		String     url    = "";
		
		if(user!=null && (sessionId=user.getMyid()).equals(compId)) {
			//유효한 경우
			request.setAttribute("defaultLostReview", dto);
			RequestDispatcher dispatcher = request.getRequestDispatcher("./template/updateReviewForm.jsp");
			dispatcher.forward(request, response);
			
		}else {
			//유효하지 않은 접근
			msg = "잘못된 접근입니다";
			url = "review.do?command=reviewLostMain&page=1";
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
