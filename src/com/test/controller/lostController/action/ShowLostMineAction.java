package com.test.controller.lostController.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;

public class ShowLostMineAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl dao = LostDaoImpl.getInstance();
		int        page = Integer.valueOf(request.getParameter("page"));
		String     writer= request.getParameter("writer");
		
		int        totPage=dao.totalMyLostArticlePage(writer);
		List<LostDto> list = dao.selectMyLostArticle(writer, page);
		
		request.setAttribute("userLostArticle", list);
		request.setAttribute("pageNum", totPage);
		request.setAttribute("writer", writer);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./template/userLostArticle.jsp");
		dispatcher.forward(request, response);
	}

}
