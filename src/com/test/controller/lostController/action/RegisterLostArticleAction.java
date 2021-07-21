package com.test.controller.lostController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.dao.lostDao.LostDaoImpl;
import com.test.dto.lostDto.LostDto;
import com.test.dto.replyDto.ReplyDto;

import join.dto.AniDto;

public class RegisterLostArticleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		LostDaoImpl dao = LostDaoImpl.getInstance();
		LostDto     dto = new LostDto();
		ReplyDto  target = new ReplyDto();
		
		int maxLimit    = 10 * 1024 * 1024;//10mb
		
		
		ServletContext context = request.getServletContext();
		String dir      = context .getRealPath("lostAnimal");
		System.out.println(dir);
		String encType  = "UTF-8";
		HttpSession session= null;
		
		try {
			MultipartRequest multi = new MultipartRequest(
					request,
					dir,
					maxLimit,
					encType,
					new DefaultFileRenamePolicy()
			);
			
			String    title = multi.getParameter("title");
			String   writer = multi.getParameter("writer");
			String   tel    = multi.getParameter("tel");
			
			String   lostDate= multi.getParameter("lostDate");
			
			String province = multi.getParameter("province");
			String reserv= multi.getParameter("reserveAddr");
			String lostPlace= new StringBuilder(province+" "+reserv).toString();
			
			String lostPic = multi.getFilesystemName("lostPic");
			String detail  = multi.getParameter("detail");
			
			String species = multi.getParameter("species");
			String cate    = multi.getParameter("cate");
			
			String etc     = multi.getParameter("etc");
			int   regRes   = 0;
			String msg     = "";
			int   num      = 0;
			int   ord	   = dao.selectMaxOrderOfBoard();
			AniDto user   = null;
			String url	   ="";
			
			session = request.getSession();
			
			user = (AniDto)session.getAttribute("dto");
			
			
			if(detail==null) {
				detail="-";
			}
			
			if(etc==null) {
				etc = "-";
			}
			
			num = dao.selectMaxNum();
			
			dto.setNum(num+1);
			dto.setWriter(writer);
			dto.setTitle(title);
			dto.setTel(tel);
			dto.setLostDate(lostDate);
			dto.setProvince(province);
			dto.setReserv(reserv);
			dto.setLostPlace(lostPlace);
			dto.setLostPic(lostPic);
			dto.setDetail(detail);
			dto.setSpecies(species);
			dto.setCate(cate);
			dto.setEtc(etc);
			
			
			target.setBoardId(1);
			target.setReplyOrder(ord+1);
			target.setNum(num+1);
			target.setLev(0);
			target.setLevSeq(1);
			target.setReplyTab(0);
			target.setId(writer);
			target.setContent(title);
			target.setRegDate(lostDate);
			
			System.out.println(dto);
			regRes= dao.registerLost(dto, target);
			
			
			/*
			 * (1,1,1,0,1,NULL,0,'ADMIN','테스트글1','21/06/01')
			 * */
			
			System.out.println("root: "+target);
			String connect = request.getContextPath();
			//권한이 있을 경우에만 글작성할 수 있도록!
			if(user==null) {
				msg = "회원가입 및 로그인을 부탁드립니다";
				url = connect+"/login.do?command=logIn";
			}else {
				if(regRes > 0) {
					msg = new StringBuilder(writer+"님,실종신고 등록이 완료되었습니다!").toString();
					url = connect+"/lost.do?command=lostMain&page=1";
				}else {
					msg = new StringBuilder(writer+"님,실종신고 등록을 다시 시도해주세요").toString();
					url = connect+"/lost.do?command=lostMain&page=1";
					
				}
			}
			
			alertByJavascript(msg,url,response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}

	
	private void alertByJavascript(String msg, String url, HttpServletResponse response) throws IOException {
		
		PrintWriter out = response.getWriter();
		
		String    alert = "<script>"
				+ "alert('"+msg+"');"
				+ "location.href='"+url+"';"
				+ "</script>";
		
		out.print(alert);
	}
	
	
}
