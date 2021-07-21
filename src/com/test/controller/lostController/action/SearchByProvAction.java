package com.test.controller.lostController.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;

public class SearchByProvAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		LostDaoImpl dao = LostDaoImpl.getInstance();
		String province =request.getParameter("province");
		int    page     =Integer.valueOf(request.getParameter("page"));
		
		List<LostDto> list = dao.selectLostArticleByProv(province, page);
		String url ="";
	//	String connect= request.getContextPath();
		int   totPage = dao.totalProvPage(province);
		
		url = "./template/provSearchList.jsp";
		
		request.setAttribute("pageNum", totPage);
		request.setAttribute("provList", list);
		request.setAttribute("prov", province);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}

}
