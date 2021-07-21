package com.test.controller.reviewController.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

public class ReviewLostMainAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		int page = Integer.valueOf(request.getParameter("page"));
		int totalPage = dao.totalReviewLostPage();
		List<ReviewDto> list = dao.selectAll(page);
		
		request.setAttribute("pageNum", totalPage);
		request.setAttribute("reviewList", list);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./template/reviewLostMain.jsp");
		dispatcher.forward(request, response);
		
	}

}
