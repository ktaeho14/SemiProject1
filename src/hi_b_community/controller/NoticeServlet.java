package hi_b_community.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import hi_a_common.PageInfo;
import hi_b_community.notice.biz.NoticeBiz;
import hi_b_community.notice.biz.NoticeBizImpl;
import hi_b_community.notice.dto.noticeDto;
import join.dto.AniDto;




@WebServlet("/notice.do")/*[공지사항] 컨트롤러*/
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public NoticeServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command : "+ command);
		
		NoticeBiz notice = new NoticeBizImpl(); 
		
		
		if(command.equals("notice_listboard")) {
			
			//***페이징***
			int listCount = notice.getListCount(); // 게시판 리스트 개수
			
			int currentPage;	// 현재 페이지 표시
			int limit;			// 한 페이지에 표시될 페이징 수
			int maxPage;		// 전체 페이지 중 가장 마지막 페이지
			int startPage;		// 페이징 된 페이지 중 시작 페이지
			int endPage;		// 페이징 된 페이지 중 마지막 페이지
			
			currentPage = 1;
			
			if(request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
				// 페이지 전환 시 전달 받은 페이지로 currentPage 적용
			}
			
			limit = 10;
			
			maxPage = (int)((double)listCount/limit + 0.9);
			startPage = (((int)((double)currentPage/limit + 0.9)) - 1) * limit + 1;
			endPage = startPage + limit - 1;
			if(maxPage < endPage) {
				endPage = maxPage;
			}
			
			PageInfo pi = new PageInfo(currentPage, listCount, limit, maxPage, startPage, endPage);
			
		
			List<noticeDto> list = notice.selectAll(currentPage);
			String page = null;
			
			if(list != null) {
				page = "views/b_notice/notice_listboard.jsp";
				request.setAttribute("list", list);
				request.setAttribute("pi", pi);
			}
			
			dispatch(page, request, response);

			
			
		}else if(command.equals("notice_insertform")) {
			
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			
			System.out.println("등급 :"+user.getMyrole());
			
			dispatch("views/b_notice/notice_insertform.jsp",request, response);
			
			
		}else if(command.equals("notice_insert")) {
			
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String myid = request.getParameter("myid");
			
			noticeDto dto = new noticeDto();
			dto.setNotice_name(name);
			dto.setNotice_title(title);
			dto.setNotice_content(content);
			dto.setNotice_myid(myid);
			
			int res = notice.insert(dto);
			
			if(res>0) {
				dispatch("/notice.do?command=notice_listboard",request, response);
			}else {
				dispatch("/notice.do?command=notice_insertform",request, response);
			}
			
			
			
		}else if(command.equals("notice_detail")) {
			
		
			int no = Integer.parseInt(request.getParameter("notice_no"));
			
			notice.increase(no); //조회수 1증가 
			noticeDto dto2 = notice.selectOne(no);

			request.setAttribute("dto2", dto2);
			dispatch("views/b_notice/notice_detailform.jsp",request,response);
		
			
		}else if(command.equals("notice_update")) {
			
			
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			
			System.out.println("등급 :"+user.getMyrole());
			
		
			int no = Integer.parseInt(request.getParameter("notice_no"));
					
			noticeDto dto = notice.selectOne(no);
			request.setAttribute("dto2", dto);

			dispatch("views/b_notice/notice_updateform.jsp",request, response);
					
		
		
			
			
		}else if(command.equals("notice_updateform")) {
			
			
			int no = Integer.parseInt(request.getParameter("notice_no"));
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			noticeDto dto = new noticeDto();
			dto.setNotice_no(no);
			dto.setNotice_name(name);
			dto.setNotice_title(title);
			dto.setNotice_content(content);

			
			int res = notice.update(dto);
			
			
			if(res>0) {
				dispatch("/notice.do?command=notice_detail",request, response);
			}else {
				jsResponse("글 수정을 실패하였습니다.", response);
			}
			
			
			
			
		}else if(command.equals("notice_delete")) {
			
			String myid = request.getParameter("myid");//현재 접속된 id(Session값) 
			
			
			int no = Integer.parseInt(request.getParameter("notice_no"));
					
			int res = notice.delete(no, myid);
					
					
			if(res>0) {
				dispatch("/notice.do?command=notice_listboard",request, response);
			}else {
				jsResponse("작성자만 글을 삭제할 수 있습니다.", response);
			}
						
						
				

		//검색기능 	
		}else if(command.equals("notice_search")) {
			
			String select = request.getParameter("search");
			String keyword = request.getParameter("word");
			
			
			List<noticeDto> list2 = notice.searchList(select, keyword);
			
			
			for(int i=0; i<list2.size(); i++) {
				//확인용 
				System.out.println(list2.get(i).getNotice_name());
				System.out.println(list2.get(i).getNotice_title());
				System.out.println(list2.get(i).getNotice_content());
				System.out.println(list2.get(i).getNotice_regdate());
			}
			
			String page = null;
			
			if(list2 != null) {
				page = "views/b_notice/notice_searchresult.jsp";
				request.setAttribute("list2", list2);
			}
			
			dispatch(page, request, response);
			
			
	
		}
	}
		
		
		
		
		
		
		
		
		
		
		

	public void jsResponse(String msg,  HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				+ "alert('"+msg+"');"
				+ "</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
			
	}	
		
		
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
