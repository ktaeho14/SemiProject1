package ksh.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ksh.sighting.dao.SightingDao;
import ksh.sighting.dto.SightingDto;

@WebServlet("/sighting.do")
public class SightingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		
		System.out.println("command: " + command);
		if(command.equals("sightingmain")) {
			
			int pageSize = 12; //한 페이지에 출력할 레코드 수
			
			SightingDao dao = new SightingDao();
			//페이지 링크를 클릭한 번호 / 현재 페이지
			String pageNum = request.getParameter("pageNum");
			System.out.println(pageNum);
			if(pageNum==null){ //클릭한게 없으면 1번 페이지
				pageNum = "1";
				
			}
			
			//연산을 하기 위한 pageNum 형변환 / 현재 페이지
			int currentPage = Integer.parseInt(pageNum);
			
			
			
			
			int startRow = (currentPage -1) * pageSize + 1;
			int endRow = currentPage * pageSize;
			
			
			
			List<SightingDto> list = dao.selectAll(startRow,endRow);
			
			request.setAttribute("list", list);
			RequestDispatcher dispatch = request.getRequestDispatcher("KSH_Sighting/sighting.jsp");
			
			dispatch.forward(request, response);
			
			
			
		}else if(command.equals("selectOne")) {
			int animal_no = Integer.parseInt(request.getParameter("animal_no"));
			
			SightingDao dao = new SightingDao();
			SightingDto res = dao.selectOne(animal_no);
			
			request.setAttribute("res", res);
			RequestDispatcher dispatch = request.getRequestDispatcher("KSH_Sighting/sightingdetail.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("mainSelectOne")) {
			
			for(int i=2;i<12;i++) {
				
				
				
				SightingDao dao = new SightingDao();
				
				SightingDto list = dao.mainSelectOne(i);
				
				String res =list.getPopfile();
				request.setAttribute("list", res);
				RequestDispatcher dispatch = request.getRequestDispatcher("main.jsp");
				dispatch.forward(request, response);
			}
			
		}
			
			
			
			
			
			
		}


			
			
			
		
		
		
		
		
			
			
			
			
			
			
			
			
			
			
		
		
	
	private void dispatch(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
