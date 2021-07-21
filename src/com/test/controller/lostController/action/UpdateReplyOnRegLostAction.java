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

public class UpdateReplyOnRegLostAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		LostDaoImpl dao = LostDaoImpl.getInstance();
		ReplyDto    dto = new ReplyDto();
		
		int boardId = Integer.valueOf(request.getParameter("boardId"));
		int num	    = Integer.valueOf(request.getParameter("num"));
		int ordIdx = Integer.valueOf(request.getParameter("reparent-order"));
		int order  = dao.getReplyOrderValue(boardId, num, ordIdx);
		String context= request.getContextPath();
		
		String id  = request.getParameter("id");
		String content= request.getParameter("reply");
		String msg = "";
		String url = "";
		
		//세션에 로그인한 유저와 수정을 시도하려는 사용자가 일치하는지 확인 필요(미연의 상황에 대처)
		ReplyDto comp = dao.selectReply(order);
		String compId = comp.getId();
		
		HttpSession session=request.getSession();
		AniDto user = (AniDto)session.getAttribute("dto");
		String sessionId=null;
		
		int upReply =0;
		
		if(user!=null && (sessionId=user.getMyid()).equals(compId)) {
			dto.setBoardId(boardId);
			dto.setReplyOrder(order);
			dto.setNum(num);
			dto.setId(id);
			dto.setContent(content);
			
			upReply = dao.updateReply(dto);
			
			url=context+"/lost.do?command=lostMain&page=1";
			
			if(upReply > 0) {
				msg="댓글 수정 성공";
			}else {
				msg="댓글 수정 실패";
			}
			
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
