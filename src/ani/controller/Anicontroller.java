package ani.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import org.json.simple.JSONObject;

import join.dao.AniDao;
import join.dto.AniDto;
import web.mail.MailSend;


@WebServlet("/controller.do")
public class Anicontroller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Anicontroller() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		AniDao dao = new AniDao();
		
		if (command.equals("index")) {
			
			response.sendRedirect("index.jsp");
			
		} else if (command.equals("joinform")) {
			
			response.sendRedirect("registform.jsp");
			
			
			
		} else if (command.equals("emailchk")) {
			String email = request.getParameter("email");
			String res = dao.idChk(email);
			
			boolean emailnotused = true;
			
			if(res != null){
				emailnotused = false;
			}
			
			response.sendRedirect("emailChk.jsp?emailnotused="+emailnotused);
		
		
		} else if (command.equals("joinuser")) {
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			String myname = request.getParameter("myname");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			String myemail = request.getParameter("myemail");
			
			AniDto dto = new AniDto();
			dto.setMyid(myid);
			dto.setMypw(mypw);
			dto.setMyname(myname);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			dto.setMyemail(myemail);
			
			int res = dao.joinUser(dto);
			if(res>0) {				
				jsResponse("회원가입을 축하드립니다.", "controller.do?command=index" , response);
			} else {
				jsResponse("회원가입을 실패하엿습니다. 다시 시도해주세요", "controller.do?command=joinform" , response);
			}
			
			
			
		} else if (command.equals("loginform")) {
			
			loginGET(request);
			response.sendRedirect("loginform.jsp");
			
			
		} else if (command.equals("login")) {
			
			String myid = request.getParameter("myid");
			String mypw = request.getParameter("mypw");
			
			AniDto dto = dao.loginUser(myid,mypw);
			
			if(dto.getMyid() != null){
				HttpSession session = request.getSession(true);
				session.setAttribute("dto", dto);
				
				String url = (String)session.getAttribute("redirectURI");
				System.out.println(url);
				
				response.sendRedirect(url);
				
				
			} else {
				jsResponse("가입하지 않은아이디이거나 잘못된 비밀번호입니다.", "loginform.jsp", response);
			}
			
			
			
			
		} else if (command.equals("logout")) {
			HttpSession session = request.getSession(true);
			loginGET(request);
			String url = (String)session.getAttribute("redirectURI");
			System.out.println("로그아웃시 주소: "+url);
			
			request.getSession().invalidate();
			response.sendRedirect(url);
			
		} else if (command.equals("mypage")) {
			dispatch("mypage.jsp", request, response);
			
		} else if (command.equals("leaveuserform")) {
			dispatch("mypage_delete.jsp", request, response);
			
		} else if (command.equals("leaveuser")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			int res = dao.leaveUser(myno);
			
			if(res>0) {
				jsResponse("탈퇴가 완료되었습니다. 이용해주셔서 감사합니다", "controller.do?command=index", response);
			} else {
				jsResponse("탈퇴실패. 다시시도해주세요", "controller.do?command=mypage", response);
			}
			
			
		} else if (command.equals("infoch")) {
			dispatch("mypage_infoch.jsp", request, response);
			
		} else if (command.equals("infochfin")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			String myname = request.getParameter("myname");
			String myaddr = request.getParameter("myaddr");
			String myphone = request.getParameter("myphone");
			
			AniDto dto = new AniDto();
			dto.setMyno(myno);
			dto.setMyname(myname);
			dto.setMyaddr(myaddr);
			dto.setMyphone(myphone);
			
			int res = dao.infoch(dto);
			
			if(res>0) {
				request.getSession().invalidate();
				/*갱신을 위해 세션 끊고 리로그인으로 대체..보안은 망했습니다?  회원정보 수정에 아이디 비번 숨어있음*/
				String myid = request.getParameter("myid");
				String mypw = request.getParameter("mypw");
				
				AniDto relogin = dao.loginUser(myid,mypw);
				
				HttpSession session = request.getSession(true);
				session.setAttribute("dto", relogin);
				
				jsResponse("회원정보가 변경되었습니다.", "controller.do?command=mypage", response);
			} else {
				jsResponse("회원정보 변경이 실패하였습니다.", "controller.do?command=mypage", response);
			}
			
			
		} else if (command.equals("pwch")) {
			dispatch("mypage_pwch.jsp", request, response);
			
		} else if (command.equals("pwchfin")) {
			int myno = Integer.parseInt(request.getParameter("myno"));
			String mypw = request.getParameter("pw1");
			
			AniDto dto = new AniDto();
			dto.setMyno(myno);
			dto.setMypw(mypw);
			
			int res = dao.pwch(dto);
			
			if(res>0) {
				request.getSession().invalidate();
				jsResponse("비밀번호가 변경되었습니다. 다시 로그인해주세요", "controller.do?command=index", response);
			} else {
				jsResponse("비밀번호 변경이 실패하였습니다. 다시 시도해주세요", "controller.do?command=mypage", response);
			}
			
			
		} else if (command.equals("findidform")) {
			response.sendRedirect("find_id.jsp");
			
		} else if (command.equals("findid")) {
			String myname = request.getParameter("myname");
			String myemail = request.getParameter("myemail");
			
			String myid = dao.findid(myname, myemail);
			
			if(myid == null) {
				jsResponse("일치하는 정보가 없습니다. 다시 시도해보세요.", "controller.do?command=findidform", response);
			} else {
				MailSend ms = new MailSend();
				ms.MailSend("찾아주게냥 아이디조회 결과입니다.", "찾으시는 아이디는 "+myid+" 입니다." , myemail);
				
				jsResponse("이메일을 전송하였습니다. 가입된 이메일을 확인해보세요.", "controller.do?command=index", response);
			}
			
			
		} else if (command.equals("findpwform")) {
			response.sendRedirect("find_pw.jsp");
			
		} else if (command.equals("findpw")) {
			String myid = request.getParameter("myid");
			String myname = request.getParameter("myname");
			String myemail = request.getParameter("myemail");
			
			String temppw = dao.findpw(myid, myname, myemail);
			
			
			if(temppw == null) {
				jsResponse("일치하는 정보가 없습니다. 다시 시도해보세요.", "controller.do?command=findidform", response);
			} else {
				AniDto dto = new AniDto();
				dto.setMyname(myname);
				dto.setMyid(myid);
				dto.setMypw(temppw);
				
				int res = dao.temppwch(dto);
				
				if(res>0) {
					MailSend ms = new MailSend();
					ms.MailSend("찾아주게냥 비밀번호찾기 결과입니다.", "임시발급된 비밀번호는 "+temppw+" 입니다. 로그인후 비밀번호를 수정해주세요" , myemail);
					
					jsResponse("이메일을 전송하였습니다. 가입된 이메일을 확인해보세요.", "controller.do?command=index", response);
				} else {
					jsResponse("오류가 발생하였습니다. 다시한번 시도해주세요.", "controller.do?command=findpwform", response);
				}
				
				
			}
			
			
		} else if (command.equals("boarda")) {
			dispatch("board.jsp", request, response);
		} else if (command.equals("boarda")) {
			dispatch("board.jsp", request, response);
		} else if (command.equals("idajax")) {
			String myid = request.getParameter("myid");
			String res = dao.idChk(myid);
			
			JSONObject obj = new JSONObject();
			obj.put("id", res);
			
			PrintWriter out = response.getWriter();
			out.println(obj.toJSONString());
			System.out.println("서블릿에서 보내는 id확인 데이터: "+ obj.toJSONString());
		} else if (command.equals("pwajax")) {
			String mypw = request.getParameter("mypw");
			int res = 0;
			
			if(mypw.length() < 8 || mypw.length() > 20){	
				res = 1;
			} else if(mypw.replaceAll(" ", "").length() != mypw.length()){
				res = 1;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("pw", res);
			
			PrintWriter out = response.getWriter();
			out.println(obj.toJSONString());
			System.out.println("서블릿에서 보내는 pw양식 데이터: "+ obj.toJSONString());
		} else if (command.equals("pwajaxre")) {
			String oripw = request.getParameter("oripw");
			String mypw = request.getParameter("mypw");
			
			int res = 0;
			if(mypw.equals(oripw)){	
				res = 0;
			} else {
				res = 1;
			}
			
			JSONObject obj = new JSONObject();
			obj.put("pwck", res);
			
			PrintWriter out = response.getWriter();
			out.println(obj.toJSONString());
			System.out.println("서블릿에서 보내는 pw재확인양식 데이터: "+ obj.toJSONString());
		}
			
			
			
			
			
			
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public void jsResponse(String msg, String url, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		
		PrintWriter out = response.getWriter();
		out.print(s);
	}
	
	
	public void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
		
	}
	
	
	public void loginGET(HttpServletRequest request) {
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("redirectURI", referer);
	}
	
	
	
	
}
