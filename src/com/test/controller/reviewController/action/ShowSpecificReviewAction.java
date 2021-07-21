package com.test.controller.reviewController.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

public class ShowSpecificReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao  = ReviewDaoImpl.getInstance();
		int          rvNo  = Integer.valueOf(request.getParameter("rvNo"));
		//상세조회는 회원,비회원 모두 접근 가능
		//rvNo가 식별자로써 역할을 할 수 있어서 이를 이용해서 조회할것
		ReviewDto     dto  = dao.selectOneReview(rvNo);
		System.out.println("review: "+dto);
		request.setAttribute("reviewToShow", dto);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("./template/showSpecificReview.jsp");
		dispatcher.forward(request, response);
		
	}

}
