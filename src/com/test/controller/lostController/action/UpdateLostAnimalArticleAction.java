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

public class UpdateLostAnimalArticleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//글 수정
		LostDaoImpl dao = LostDaoImpl.getInstance();
		int  num = Integer.valueOf(request.getParameter("num"));
		LostDto     dto = new LostDto();//수정한 내용이 들어갈 것
		ReplyDto  target = dao.selectSpecificReply(1, num, 0, 1);//댓글도 업데이트해주어야 함
		
		int maxLimit    = 10 * 1024 * 1024;//10mb
		
		
		ServletContext context = request.getServletContext();
		String dir      = context .getRealPath("lostAnimal");
		System.out.println(dir);
		String encType  = "UTF-8";

		
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
			String msg     = "";
			String url	   ="";
			
			
			if(detail==null) {
				detail="-";
			}
			
			if(etc==null) {
				etc = "-";
			}
			
			target.setContent(title);
			
			dto.setNum(num);
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
			
			
			int updateLost = dao.updateLostInfo(dto,target);
			String connect = request.getContextPath();
			if(updateLost > 0) {
				msg = "실종신고글 수정 완료!";
			}else {
				msg = "실종신고글 수정 실패";
			}
			url = connect+"/lost.do?command=lostMain&page=1";
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
