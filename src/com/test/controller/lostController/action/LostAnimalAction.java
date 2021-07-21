package com.test.controller.lostController.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;
import com.test.dto.replyDto.ReplyDto;

public class LostAnimalAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl dao = LostDaoImpl.getInstance();
		int         no  = Integer.valueOf(request.getParameter("no"));
		
		//조회수 수정
		dao.updateWatch(no);
		
		LostDto   target = dao.selectOne(no);
		int boardId = Integer.valueOf(request.getParameter("boardId"));
		int lev     = Integer.valueOf(request.getParameter("lev"));
		int levSeq     = Integer.valueOf(request.getParameter("seq"));
		//댓글 찾아오기
		//int page = Integer.valueOf(request.getParameter("repPage"));
		
		ReplyDto  parent = dao.selectSpecificReply(boardId, no, lev, levSeq);
		
		List<ReplyDto>  targetRp = dao.selectAllReply(boardId, no);
		
		System.out.println("target: "+target);
		System.out.println("rpList: "+targetRp);
		System.out.println("parent: "+parent);
		
		request.setAttribute("targetToShow", target);
		request.setAttribute("reply", targetRp);
		request.setAttribute("parent", parent);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./template/showTarget.jsp?no="+no);
		dispatcher.forward(request, response);
		
	}

}
