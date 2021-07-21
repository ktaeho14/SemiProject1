package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;

import join.dto.AniDto;

public class UpdateLostArticleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//미연의 로직 충돌을 한번 더 막기 위해서 세션사용자와 지금 글의 사용자 다시 한번 확인할 필요가 있음!
		//확인 후에는 수정 페이지로 이동하기로 처리
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		
		HttpSession session= request.getSession();
		AniDto     user   = (AniDto)session.getAttribute("dto");
		String      sessionId = null;//세션아이디
		//게시글 번호
		int         num    = Integer.valueOf(request.getParameter("num"));
		//dao
		LostDaoImpl dao    = LostDaoImpl.getInstance();
		LostDto     comp   = dao.selectOne(num);
		String      compId = comp.getWriter();//db에 저장된 아이디
		String      msg    = "";
		String      url    = "";
		String     context = request.getContextPath();
		
		if(user!=null && (sessionId=user.getMyid()).equals(compId)) {
			//글 객체를 request에 싣고, 이동시키기
			request.setAttribute("toUpdate", comp);
			url = "./template/updateForm.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}else {
			msg="잘못된 접근입니다";
			url = context;
			alertByJavascript(msg,url,response);
		}
		
		
		
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
