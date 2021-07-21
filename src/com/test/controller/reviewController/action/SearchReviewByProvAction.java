package com.test.controller.reviewController.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

public class SearchReviewByProvAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		String    province= request.getParameter("province");
		int       page	  = Integer.valueOf(request.getParameter("page"));
		
		List<ReviewDto> list = dao.selectReviewProv(province, page);
		int pageNum = dao.totalPageProvReview(province);
		
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("provReview", list);
		request.setAttribute("province", province);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./template/reviewProv.jsp");
		dispatcher.forward(request, response);
		
	}

}
