package com.test.controller.reviewController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

import join.dto.AniDto;

public class DeleteLostReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		int rvNo = Integer.valueOf(request.getParameter("rvNo"));
		int delRes = 0;
		//유효성검사를 위해서 글 주인찾기!
		ReviewDto comp = dao.selectOneReview(rvNo);
		String    compId = null;
		
		HttpSession session = request.getSession();
		AniDto user = (AniDto)session.getAttribute("dto");
		String  sessionId="";
		String  msg = "";
		String  url = "";
		
		if(comp!=null && user!=null && (sessionId=user.getMyid()).equals((compId=comp.getId()))) {
			//유효한 경우
			delRes = dao.deleteLostReview(rvNo);
			//유효하지만 삭제결과가 불통일 수도 있음! 확인!!
			
			if(delRes > 0) {
				msg ="후기글이 삭제되었습니다!";
				url = "review.do?command=reviewLostMain&page=1";
			}else {
				msg ="요청이 잘못되었습니다";
				url = "review.do?command=showSpecificReview&rvNo="+rvNo;
			}
			
		}else {
			//유효하지 않은 경우
			msg = "잘못된 접근입니다";
			url = "review.do?command=showSpecificReview&rvNo="+rvNo;
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
