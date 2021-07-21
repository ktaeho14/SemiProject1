package hi_b_community.controller_paging;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hi_a_common.PageInfo;
import hi_b_community.complain.biz.ComplainBiz;
import hi_b_community.complain.biz.ComplainBizImpl;
import hi_b_community.complain.dto.complainDto;





@WebServlet("/c_list.gy")
public class com_ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public com_ListServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ComplainBiz complain = new ComplainBizImpl();
		
		
		int listCount = complain.getListCount();
		
		int currentPage;
		int limit;
		int maxPage;
		int startPage;
		int endPage;
		
		currentPage = 1;
		
		if(request.getParameter("currentPage") != null) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		
		limit = 10;
		
		maxPage = (int)((double)listCount/limit + 0.9);
		startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1;
		endPage = startPage + limit - 1;
		if(maxPage < endPage) {
			endPage = maxPage;
		}
		
		PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
		
		
		List<complainDto> list = complain.selectAll(currentPage);
		
		
		String page = null;
		if(list != null ) {
			request.setAttribute("list", list);
			request.setAttribute("pi", pi);
			page = "views/c_complain/complain_listboard.jsp";
		}
		
		request.getRequestDispatcher(page).forward(request, response);
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
