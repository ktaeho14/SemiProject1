package com.test.controller.lostController.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;

public class LostMainAction implements Action{


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		LostDaoImpl 	  dao  = LostDaoImpl.getInstance();
		int           page = Integer.valueOf(request.getParameter("page"));
		//총 페이지 갯수도 세어서 제공
		int           total = dao.totalPage();
		List<LostDto> list = dao.selectAllLost(page); 
	//	System.out.println(list.toString());
	//	System.out.println("now:"+page);
		request.setAttribute("list", list);
		request.setAttribute("pageNum",total);
		RequestDispatcher dispatcher = request.getRequestDispatcher("./lostMain.jsp");
		dispatcher.forward(request, response);
	//	System.out.println("page: "+total);
	}

}
