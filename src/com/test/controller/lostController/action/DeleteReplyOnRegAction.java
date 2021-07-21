package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.replyDto.ReplyDto;

import join.dto.AniDto;

public class DeleteReplyOnRegAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl dao = LostDaoImpl.getInstance();
		//ReplyDto    dto = new ReplyDto();
		
		int boardId = Integer.valueOf(request.getParameter("boardId"));
		int num	    = Integer.valueOf(request.getParameter("num"));
		int ordIdx = Integer.valueOf(request.getParameter("ordIdx"));
		int order  = dao.getReplyOrderValue(boardId, num, ordIdx);
		
		//ReplyDto stan = dao.selectReply(order);
		//미연의 상황에 대비해서 정말 사용자가 일치하는지 한번더 확인
		ReplyDto comp = dao.selectReply(order);
		String   compId=comp.getId();
		HttpSession session = request.getSession();
		AniDto user =  ((AniDto)session.getAttribute("dto"));
		String  sessionId =null;
		String url = "";
		String msg = "";
		int delRes =0;
		//String context=request.getContextPath();
		
		
		if(user!=null && (sessionId=user.getMyid()).equals(compId)) {
			
		    delRes = dao.deleteReply(order);
			url = "lost.do?command=lostMain&page=1";
			msg = "";
			
			if(delRes > 0) {
				msg = "댓글 삭제 성공";
			}else {
				msg = "댓글 삭제 실패";
			}
			
		}else {
			msg="잘못된 접근입니다";
		}
		
		alertByJavascript(msg,url,response);
	}
	//캡슐화
	private void alertByJavascript(String msg, String url , HttpServletResponse response) throws IOException {
			
		PrintWriter out = response.getWriter();
			
		String    alert = "<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
			
		out.print(alert);
	}
}
