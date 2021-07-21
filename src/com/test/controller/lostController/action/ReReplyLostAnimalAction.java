package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.replyDto.ReplyDto;

public class ReReplyLostAnimalAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//reparent
		LostDaoImpl  dao  = LostDaoImpl.getInstance();
		int boardId = Integer.valueOf(request.getParameter("boardId"));
		int num = Integer.valueOf(request.getParameter("num"));
		String tmp  =request.getParameter("reparent-order");
		System.out.println("reparent-order: "+tmp);
		int toFind = 0;
		
		if(tmp.equals("")) {
			toFind = 0;	
		}else {
			toFind = Integer.valueOf(tmp);
		}
		
		ReplyDto reParent = dao.getReparentReplyNode(boardId, num, toFind);
		
		String id = request.getParameter("id");
		String content= request.getParameter("reply");
		
		System.out.println("reParent: "+reParent);
		
		ReplyDto child = new ReplyDto();
		child.setId(id);
		child.setContent(content);
		int regRes = dao.registerReReplyToArticle(reParent,child);
		
		
		String msg ="";
		
		
		if(regRes > 0) {
			msg = "댓글 등록에 성공하였습니다";
		}else {
			msg ="댓글 등록에 실패하였습니다";
		}
		System.out.println("대댓글 작성하기");
		alertByJavascript(msg,"lost.do?command=lostMain&page=1",response);
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
