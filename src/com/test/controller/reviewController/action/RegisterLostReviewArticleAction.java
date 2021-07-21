package com.test.controller.reviewController.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.dao.reviewDao.ReviewDaoImpl;
import com.test.dto.reviewDto.ReviewDto;

public class RegisterLostReviewArticleAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		MultipartRequest multi = null;
		//파일크기 10mb제한
		int    limit    = 10 * 1024 * 1024;
		//encoding설정
		String encType  = "UTF-8";
		//파일 경로
		String path     = "LostReview";
		//context
		//서블릿에서는 바로 getServletContext해도 됨!
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(path);
		
		ReviewDto dto = new ReviewDto();//dto객체에 담아서 등록할 것
		int regRes = 0;
		String msg="";
		String url ="";
		int num = Integer.valueOf(request.getParameter("num"));
		
		try {
			
			multi = new MultipartRequest(
					request,
					realPath,
					limit,
					encType,
					new DefaultFileRenamePolicy()
					);
			
			//제목
			String title=multi.getParameter("title");
			//작성자
			//세션에 퍼져있는 값을 가져와도 되지만 getAttribute로 인해서
			//캐스팅도 해야 하므로 그냥 파라미터로 가져올것
			String id = multi.getParameter("id");
			//사진
			String rvPic = multi.getFilesystemName("rvPic");
			//잃어버린 날짜
			String lostDate = multi.getParameter("lostDate");
			//권역
			String province = multi.getParameter("province");
			//나머지 주소
			String reserv = multi.getParameter("reserveAddr");
			//권역+나머지주소
			String lostPlace = new StringBuilder(province+" "+reserv).toString();
			//species
			String species = multi.getParameter("species");
			//cate
			String cate = multi.getParameter("cate");
			//후기글
			String content= multi.getParameter("content");
			//작성자로 유효한지 확인

			
			
			dto.setTitle(title);
			dto.setNum(num);
			dto.setId(id);
			dto.setRvPic(rvPic);
			dto.setLostDate(lostDate);
			dto.setProvince(province);
			dto.setReserv(reserv);
			dto.setLostPlace(lostPlace);
			dto.setSpecies(species);
			dto.setCate(cate);
			dto.setContent(content);
			
			System.out.println(dto);
			regRes = dao.insertLostReview(dto);
			
			if(regRes > 0) {
				msg = dto.getId()+"님의 소중한 후기에 진심으로 감사합니다!";
				url = "review.do?command=reviewLostMain&page=1";
			}else {
				msg = "잘못된 접근입니다";
				url = "lost.do?command=lostMain&page=1";
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
