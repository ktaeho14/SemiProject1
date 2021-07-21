package ksh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ksh.sighting.dao.SightingDao;
import ksh.witness.biz.WitnessBiz;
import ksh.witness.biz.WitnessBizImpl;
import ksh.witness.dto.WitnessDto;




@WebServlet("/witness.do")
public class WitnessServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command: " + command);
		
		if(command.equals("witnessmain")) {
			
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
			
			
			
			WitnessBiz biz = new WitnessBizImpl();
			List<WitnessDto> wnList = biz.selectAll(startRow, endRow);
			
			
			
			
			
			
			
			
			request.setAttribute("wnList", wnList);
			
			RequestDispatcher dispatch = request.getRequestDispatcher("KSH_Witness/witness.jsp");
			dispatch.forward(request, response);
			
			
			
			
			
			
			
		}else if(command.equals("witnessSelectOne")) {
			int no = Integer.parseInt(request.getParameter("no"));
			WitnessBiz biz = new WitnessBizImpl();
			WitnessDto res = biz.selectOne(no);
			
			
			
			request.setAttribute("selectone", res);
			RequestDispatcher dispatch = request.getRequestDispatcher("KSH_Witness/witnessDetail.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("write")) {	
		
			response.sendRedirect("KSH_Witness/witnessWriteForm.jsp");
			
		}else if(command.equals("delete")){
			int no = Integer.parseInt(request.getParameter("no"));
			WitnessBiz biz = new WitnessBizImpl();
			int res = 0;
			 res = biz.delete(no);
			 
			 
			 if(res>0) {
					jsResponse("삭제 성공","witness.do?command=witnessmain",response);
				}else {
					dispatch("witness.do?command=witnessMain&no="+no,request,response);
				}
		}else if(command.equals("update")){
				WitnessBiz biz = new WitnessBizImpl();
				int no = Integer.parseInt(request.getParameter("no"));
				WitnessDto dto = biz.selectOne(no);
				
				request.setAttribute("dto", dto);
				dispatch("KSH_Witness/witnessUpdateForm.jsp",request,response);
				
		}else if(command.equals("witness_search_city_kind")) {
				
				String city = request.getParameter("city");
				System.out.println(city);
				String kind = request.getParameter("kind");
				System.out.println(kind);
				WitnessBiz biz = new WitnessBizImpl();
				List<WitnessDto> wnCityList = biz.selectAllCity(city,kind);
				
				
				
				
				
				
				request.setAttribute("wnList", wnCityList);
				RequestDispatcher dispatch = request.getRequestDispatcher("KSH_Witness/witness.jsp");
				dispatch.forward(request, response);
			
			
		}else if(command.equals("witness_search_content")) {
				String content = request.getParameter("content");
				System.out.println("content");
				WitnessBiz biz = new WitnessBizImpl();
				List<WitnessDto> wnContentList = biz.selectAllContent(content);
				
				request.setAttribute("wnList", wnContentList);
				RequestDispatcher dispatch = request.getRequestDispatcher("KSH_Witness/witness.jsp");
				dispatch.forward(request, response);
				
				
			
		}
	
	
	}
	

	private void dispatch(String url,HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	private void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
					+ "alert('"+msg+"');"
					+ "location.href='"+url+"';"
					+ "</script>";
					
			PrintWriter out = response.getWriter();
			out.print(s);
			
			
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
