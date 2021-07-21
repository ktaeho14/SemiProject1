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
import hi_b_community.complain.biz.ComplainBiz;
import hi_b_community.complain.biz.ComplainBizImpl;
import hi_b_community.complain.dto.complainDto;
import hi_b_community.notice.dto.noticeDto;
import join.dto.AniDto;


@WebServlet("/complain.do")/*[불만접수] 컨트롤러*/
public class ComplainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ComplainServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		System.out.println("command : "+ command);
		
		ComplainBiz complain = new ComplainBizImpl();
		
		
		if(command.equals("complain_listboard")) {
			//***페이징***
			int listCount = complain.getListCount(); // 게시판 리스트 개수
			
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
			
			List<complainDto> list = complain.selectAll(currentPage);
			
			String page = null;
			
			if(list != null) {
				page = "views/c_complain/complain_listboard.jsp";
				request.setAttribute("list", list);
				request.setAttribute("listcount", listCount);
				request.setAttribute("pi", pi);
			}
			
			RequestDispatcher dispatch = request.getRequestDispatcher(page);
			dispatch.forward(request, response);
			
			
			
			
		}else if(command.equals("complain_detail")) {
			
			int boardno = Integer.parseInt(request.getParameter("complain_boardno"));
			
			complain.increase(boardno); //조회수 1증가 
			complainDto dto = complain.selectOne(boardno);
			
			request.setAttribute("dto2", dto);
			dispatch("views/c_complain/complain_detailform.jsp", request, response);
			
			
			
			
		}else if(command.equals("complain_insertform")) {
			
			
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			
			System.out.println("등급 :"+user.getMyrole());
			
			
			if(user.getMyid()==null) {
				//로그인x 
				jsResponse2("회원만 글을 작성할 수 있습니다.", response); 
			}else {
				dispatch("views/c_complain/complain_insertform.jsp",request, response);
			}
			
			
			
		}else if(command.equals("complain_insert")) {
			
			String name = request.getParameter("name");
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String myid = request.getParameter("myid");
			
			complainDto dto = new complainDto();
			dto.setComplain_name(name);
			dto.setComplain_title(title);
			dto.setComplain_content(content);
			dto.setComplain_myid(myid);
			
			int res = complain.insert(dto);
			
			if(res>0) {
				dispatch("/complain.do?command=complain_listboard",request, response);
			}else {
				dispatch("/complain.do?command=complain_insertform",request, response);

			}
			
			
			
		}else if(command.equals("complain_detail")) {
			
			
			int no = Integer.parseInt(request.getParameter("notice_no"));
			
			complain.increase(no); //조회수 1증가 
			complainDto dto = complain.selectOne(no);
			
			
			request.setAttribute("dto", dto);
			dispatch("views/c_complain/complain_detailform.jsp",request,response);
		
			
			
		}else if(command.equals("complain_update")) {
			
			//세션을 받아와 myrole을 확인 (관리자 수정 권한을 위해)
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			
			System.out.println("user.getMyrole() : "+user.getMyrole()); //확인용
			
			
			int no = Integer.parseInt(request.getParameter("complain_boardno"));
			String myid = request.getParameter("myid");
			String writerid = request.getParameter("writerid");
			
			
			
			complainDto dto = complain.selectOne(no);
			
			if(dto.getComplain_myid().equals(myid) ) {
				//작성자와 현재 접속한 id가 동일할 경우만 수정 가능 
				
				System.out.println("dto: "+dto);
				request.setAttribute("dto2", dto);
				dispatch("views/c_complain/complain_updateform.jsp",request, response);
				
				System.out.println(dto.getComplain_myid().equals(myid));
				
				
			}else if(user.getMyrole().equals("ADMIN")) {
				//관리자는 모든 글을 수정할 수 있음 
				
				request.setAttribute("dto2", dto);
				dispatch("views/c_complain/complain_updateform.jsp",request, response);
			
			}else {

				jsResponse2("작성자만 수정할 수 있습니다.", response); 
			}
			
			
			
		}else if(command.equals("complain_updateform")) {
			
			int no = Integer.parseInt(request.getParameter("complain_boardno"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String myid = request.getParameter("myid");
			
			complainDto dto = new complainDto();
			
			dto.setComplain_boardno(no);
			dto.setComplain_title(title);
			dto.setComplain_content(content);
			dto.setComplain_myid(myid);
			
			int res = complain.update(dto);
			
			
			if(res>0) {
				
				dispatch("/complain.do?command=complain_detail",request, response);
				
			}
			
			
			
		}else if(command.equals("complain_answerform")) {
			
			int parentboardno = Integer.parseInt(request.getParameter("complain_parentboardno"));
			complain.increase(parentboardno); //조회수 1증가 
			
			
			complainDto dto = complain.selectOne(parentboardno);
			
			request.setAttribute("parent", dto);
			dispatch("views/c_complain/complain_answerwrite.jsp", request, response);
			
			
			
			
		}else if(command.equals("complain_answerwrite")) {
			
			int parentboardno = Integer.parseInt(request.getParameter("complain_parentboardno"));
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String name = request.getParameter("name");
			String myid = request.getParameter("myid");
			
			//부모의 데이터 가져와서 
			complainDto parent = complain.selectOne(parentboardno); 
			
			//부모의 groupno, groupsq, titletab 가져와서
			int parentgroupno = parent.getComplain_groupno();
			int parentgroupsq = parent.getComplain_groupsq();
			int parenttitletab = parent.getComplain_titletab();
			
			//update -> groupsq변경 
			int updateRes = complain.updateAnswer(parentgroupno, parentgroupsq);
			if(updateRes>0) {
				System.out.println("순서 변경 성공");
			}else {
				System.out.println("순서 변경 실패 or 변경할 글이 없음");
			}
			
			//insert해주기  				/*0: 굳이 새로운값 필요없어서*/
			complainDto dto = new complainDto(0, parentgroupno, parentgroupsq, parenttitletab,
					title, content, name, null, 0, "N", myid);
			
			int res = complain.insertAnswer(dto);
			
			if(res>0) {
				dispatch("/complain.do?command=complain_listboard",request, response);

			}else {
				dispatch("/complain.do?command=complain_detail&complain_boardno="+ parentboardno,request, response);
				
			}
			
			
			
			
		}else if(command.equals("complain_delete")) {
			
			//session을 받아와 myrole을 확인(관리자 [삭제] 권한을 위해)
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			System.out.println("user.getMyrole() : "+user.getMyrole()); //확인용 
			
			
			int boardno = Integer.parseInt(request.getParameter("complain_boardno"));
			String myid = request.getParameter("myid");
					
			//현재 접속한 아이디(myid)와 비교하기 위해 삭제할 글을 selectOne(1)		
			complainDto dto = complain.selectOne(boardno);
			
			if(dto.getComplain_myid().equals(myid)) {
				//(1) : 삭제할 글의 작성된 id와 현재 접속한 id가 같다  => 삭제O
				
				int res = complain.delete(boardno);
				if(res>0) {

					dispatch("/complain.do?command=complain_listboard",request, response);
					
				}else {
					jsResponse2("글 삭제 실패.", response);
				}
				
				
			}else if(user.getMyrole().equals("ADMIN")) {
				//세션의 myrole이 "ADMIN"이다 => 삭제O 
				
				int res = complain.delete(boardno);
				if(res>0) {

					dispatch("/complain.do?command=complain_listboard",request, response);
					
				}else {
					jsResponse2("글 삭제 실패.", response);
				}
				
			}else {
				//ADMIN(관리자)도, 작성자가 아니다 = > 삭제X
				jsResponse2("작성자만 삭제할 수 있습니다.", response);
			}
			
			
			

			
		//검색	
		}else if(command.equals("complain_search")) {
			
			String select = request.getParameter("search");
			String keyword = request.getParameter("word");
			
			
			List<complainDto> list2 = complain.searchList(select, keyword);
			
			
			for(int i=0; i<list2.size(); i++) {
				
				System.out.println(list2.get(i).getComplain_name());
				System.out.println(list2.get(i).getComplain_title());
				System.out.println(list2.get(i).getComplain_content());
				System.out.println(list2.get(i).getComplain_regdate());
			}
			
			String page = null;
			
			if(list2 != null) {
				page = "views/c_complain/complain_searchresult.jsp";
				request.setAttribute("list2", list2);
			}
			
			dispatch(page, request, response);
			
		}
	}

	
	public void jsResponse(String msg, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
					+ " if (confirm('"+msg+"')){" 
					+ "   location.href='notice_listboard.jsp';"
					+ " }else {"
					+ "   location.href= window.history.back(); "
					+ " }"
					+ "</script>";
			
		PrintWriter out = response.getWriter();
		out.print(s);
	
	}		
	
	
	public void jsResponse2(String msg, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
					+ "alert('"+msg+"');"
					+ "window.history.back();"
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
