package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;

import join.dto.AniDto;

public class DeleteLostArticleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//글이 삭제되면 댓글들도 모두 삭제되어야 함!
		LostDaoImpl dao = LostDaoImpl.getInstance();
		int num = Integer.valueOf(request.getParameter("num"));
		HttpSession session = request.getSession();
		AniDto user= (AniDto)session.getAttribute("dto");
		LostDto dto = dao.selectOne(num);
		String compId = dto.getWriter();
		String sessionId = null;
		String msg ="";
		String url ="";
		String connect = request.getContextPath();
		int    mid  = 0;
		
		url = connect+"/lost.do?command=lostMain&page=1";
		
		if(user!=null && (sessionId=user.getMyid()).equals(compId)) {
			
			mid = dao.deleteLost(1, dto);
			
			if(mid > 0) {
				msg = "해당 글을 삭제하였습니다";
				
			}else {
				msg = "요청하신 작업 처리 중 문제가 발생하였습니다";

			}
			
		}else {
			msg = "잘못된 접근입니다";
		}
		
		alertByJavascript(msg,url,response);
		
	}
	
	private void alertByJavascript(String msg, String url , HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String    alert = "<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		
		out.print(alert);
	}
}
