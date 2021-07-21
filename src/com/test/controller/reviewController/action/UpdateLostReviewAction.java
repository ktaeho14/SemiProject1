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

public class UpdateLostReviewAction implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ReviewDaoImpl dao = ReviewDaoImpl.getInstance();
		MultipartRequest multi = null;
		//파일용량 제한 10mb
		int limit = 10 * 1024* 1024;
		//encoding
		String encType="UTF-8";
		//folder name
		String path = "LostReview";
		//서블릿에서는 바로 ServletContext c = getServletContext해도 되는데 일반 자바에서는 request객체를 이용해야!
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath(path);
		int rvNo = Integer.valueOf(request.getParameter("rvNo"));//form에서 커맨드랑 같이 실어주었음
		int num = Integer.valueOf(request.getParameter("num"));
		ReviewDto target = new ReviewDto();
		int result = 0;
		String msg = "";
		String url = "";
		
		try {
			
			multi = new MultipartRequest(
					request,
					realPath,
					limit,
					encType,
					new DefaultFileRenamePolicy()
					
			);
			
			String title = multi.getParameter("title");
			String id    = multi.getParameter("id");
			String rvPic = multi.getFilesystemName("rvPic");
			String lostDate= multi.getParameter("lostDate");
			String province = multi.getParameter("province");
			String reserv = multi.getParameter("reserveAddr");
			String lostPlace= new StringBuilder(province+" "+reserv).toString();
			String species  = multi.getParameter("species");
			String cate     = multi.getParameter("cate");
			String content = multi.getParameter("content");
			String regDate = multi.getParameter("regDate");
			
			//어차피 수정폼이 있는 곳으로 이동을 한번 차단했기 때문에 여기서는 유효성검사를 하지 않아도 될것!!
			
			target.setRvNo(rvNo);
			target.setNum(num);
			target.setTitle(title);
			target.setRvPic(rvPic);
			target.setId(id);
			target.setLostDate(lostDate);
			target.setProvince(province);
			target.setReserv(reserv);
			target.setLostPlace(lostPlace);
			target.setSpecies(species);
			target.setCate(cate);
			target.setContent(content);
			target.setRegDate(regDate);
			
			result = dao.updateLostReview(target);
			
			if(result > 0) {
				msg = "후기 수정 성공";
				url = "review.do?command=reviewLostMain&page=1";
			}else {
				msg = "후기 수정 실패";
				url = "review.do?command=showSpecificReview&rvNo="+rvNo;
			}
			alertByJavascript(msg,url,response);
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("후기 수정 중 multipart request 연결 오류");
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
