package com.test.controller.reviewController.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

import join.dto.AniDto;

public class ShowMyLostReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		int page = Integer.valueOf(request.getParameter("page"));
		HttpSession session = request.getSession();
		AniDto user = (AniDto)session.getAttribute("dto");
		String  sessionId = null;
		String  msg ="";
		String  url ="";
		List<ReviewDto> list = null;
		int pageNum = 0;
		//유효성 체크
	    if(user!=null) {
	    	
	    	sessionId = user.getMyid();
	    	list = dao.selectReviewUser(sessionId, page);
	    	pageNum = dao.totalPageUser(sessionId);
	    	
	    	request.setAttribute("userArticle", list);
	    	request.setAttribute("pageNum", pageNum);
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("./template/userReviewList.jsp");
	    	dispatcher.forward(request, response);
	    	
	    }else {
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
