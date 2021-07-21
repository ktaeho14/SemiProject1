package pij.adopt.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ksh.witness.biz.WitnessBiz;
import ksh.witness.biz.WitnessBizImpl;
import ksh.witness.dto.WitnessDto;
import pij.adopt.biz.AdoptBiz;
import pij.adopt.biz.AdoptBizImpl;
import pij.adopt.dao.AdoptDao;
import pij.adopt.dao.AdoptDaoImpl;
import pij.adopt.dto.AdoptDto;

@WebServlet("/AdoptController.do")
public class AdoptController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		
		System.out.println("command: " + command);
		if(command.equals("adoptMain")) {
			
			int pageSize = 12; //한 페이지에 출력할 레코드 수
			
			AdoptDao dao = new AdoptDaoImpl();
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
			
			
			
			AdoptBiz biz = new AdoptBizImpl();
			List<AdoptDto> adList = biz.selectAll(startRow, endRow);
			
			request.setAttribute("adList", adList);
			RequestDispatcher dispatch = request.getRequestDispatcher("PIJ_Adopt/Adopt.jsp");
			
			dispatch.forward(request, response);
		
		}else if(command.equals("adoptSelectOne")) {
			int no = Integer.parseInt(request.getParameter("no"));
			AdoptBiz biz = new AdoptBizImpl();
			AdoptDto dto = new AdoptDto();
			
			dto = biz.selectOne(no);
			
			request.setAttribute("adDto", dto);
			RequestDispatcher dispatch = request.getRequestDispatcher("PIJ_Adopt/AdoptDetail.jsp");
			
			dispatch.forward(request, response);
			
		}else if(command.equals("write")) {
			response.sendRedirect("PIJ_Adopt/AdoptWriteForm.jsp");
		}else if(command.equals("writeform")) {
			String title = request.getParameter("adopt_title");
			String kind = request.getParameter("adopt_kind");
			String place = request.getParameter("adopt_place");
			String phone = request.getParameter("adopt_phone");
			String writer = request.getParameter("writer");
			String content = request.getParameter("adopt_content");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			AdoptDto dto = new AdoptDto();
			dto.setAdopt_title(title);
			dto.setAdopt_kind(kind);
			dto.setAdopt_place(place);
			dto.setAdopt_phone(phone);
			dto.setAdopt_writer(writer);
			dto.setAdopt_content(content);
			dto.setAdopt_sex(sex);
			dto.setAdopt_email(email);
			AdoptBiz biz = new AdoptBizImpl();
			int res = biz.insert(dto);
			
			if(res>0) {
				RequestDispatcher dispatch = request.getRequestDispatcher("AdoptController.do?command=adoptMain");
				dispatch.forward(request, response);
			}else {
				response.sendRedirect("AdoptController.do?command='write'");
			}
		}else if(command.equals("delete")) {
			int no = Integer.parseInt(request.getParameter("no"));
			AdoptBiz biz = new AdoptBizImpl();
			int res = biz.delete(no);
			
			if(res>0) {
				RequestDispatcher dispatch = request.getRequestDispatcher("AdoptController.do?command=adoptMain");
				dispatch.forward(request, response);
			}else {
				RequestDispatcher dispatch = request.getRequestDispatcher("AdoptController.do?command=adoptSelectOne");
				dispatch.forward(request, response);
			}
		}else if(command.equals("update")) {
			AdoptBiz biz = new AdoptBizImpl();
			int no = Integer.parseInt(request.getParameter("no"));
			AdoptDto dto = biz.selectOne(no);
			
			request.setAttribute("dto", dto);
			RequestDispatcher dispatch = request.getRequestDispatcher("PIJ_Adopt/AdoptUpdateForm.jsp");
			dispatch.forward(request, response);
		}else if(command.equals("updateform")) {
			AdoptBiz biz = new AdoptBizImpl();
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String kind = request.getParameter("kind");
			String place = request.getParameter("place");
			String phone = request.getParameter("phone");
			String writer = request.getParameter("writer");
			String sex = request.getParameter("sex");
			String email = request.getParameter("email");
			String content = request.getParameter("content");
			
			AdoptDto dto = new AdoptDto();
			dto.setAdopt_no(no);
			dto.setAdopt_title(title);
			dto.setAdopt_kind(kind);
			dto.setAdopt_place(place);
			dto.setAdopt_phone(phone);
			dto.setAdopt_writer(writer);
			dto.setAdopt_sex(sex);
			dto.setAdopt_email(email);
			dto.setAdopt_content(content);
			
			int res = biz.update(dto);
			
			if(res>0) {
				RequestDispatcher dispatch = request.getRequestDispatcher("AdoptController.do?command=adoptMain");
				dispatch.forward(request, response);
			}else {
				RequestDispatcher dispatch = request.getRequestDispatcher("AdoptController.do?command=update");
				dispatch.forward(request, response);
			}
		}

		
		
		
		
		
	
	}
	
	
	
	
	
	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
