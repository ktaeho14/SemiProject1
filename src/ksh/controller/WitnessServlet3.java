package ksh.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import ksh.witness.biz.WitnessBiz;
import ksh.witness.biz.WitnessBizImpl;
import ksh.witness.dto.WitnessDto;


@WebServlet("/witness3.do")
public class WitnessServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
   

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		WitnessDto dto = new WitnessDto();
		WitnessBiz biz = new WitnessBizImpl();
		 RequestDispatcher rd = null;
	        String fileName = "";
	        File file = null;
	        String savePath = "image"; //<- 요기를 바꿔주면 다운받는 경로가 바뀝니다.
	        MultipartRequest multi;
	        Enumeration files = null;
	        
	        ServletContext context = getServletContext();
			String uploadFilePath = context.getRealPath(savePath);
			
			System.out.println(uploadFilePath);
	        File folder = new File(uploadFilePath);
			if(!folder.exists()) {
				//폴더 생성(폴더가 없으면)
				folder.mkdir();
			}
	        
	        int maxSize = 5 * 1024 * 1024; // 최대 업로드 파일 크기 5MB(메가)로 제한
	        try {
	        	multi= new MultipartRequest(request,
	        			uploadFilePath, maxSize, "UTF-8", new DefaultFileRenamePolicy());
	         fileName = multi.getFilesystemName("witness_pic"); // 파일의 이름 얻기
	         String color = multi.getParameter("color");
	         String kind = multi.getParameter("kind");
	         String place = multi.getParameter("place");
	         String specialmark = multi.getParameter("specialmark");
	         String content = multi.getParameter("content");
	         String phone_no = multi.getParameter("phone_no");
	         String city = multi.getParameter("city");
	         String writer = multi.getParameter("writer");
	         int no = Integer.parseInt(multi.getParameter("no"));
	         
	         dto.setColor(color);
	         dto.setKind(kind);
	         dto.setPlace(place);
	         dto.setSpecialmark(specialmark);
	         dto.setWitness_pic(fileName);
	         dto.setContent(content);
	         dto.setPhone_no(phone_no);
	         dto.setCity(city);
	         dto.setNo(no);
	         System.out.println(multi.getParameter("color"));
	         System.out.println(multi.getParameter("kind"));
	         System.out.println(multi.getParameter("place"));
	         System.out.println(multi.getParameter("specialmark"));
	         System.out.println(multi.getParameter("witness_date"));
	         System.out.println(multi.getParameter("content"));
	         System.out.println(fileName);
	         
	         
	         int res = biz.update(dto);
	         
	         
	         
	         System.out.println(res);
	         
	         
	         files = multi.getFileNames();
	         String name = (String)files.nextElement();         
	         file = multi.getFile(name);
	         
	         
	         System.out.println(files+"\\"+file);
	         if (fileName == null) { // 파일이 업로드 되지 않았을때
	          System.out.print("파일 업로드 되지 않았음");
	         } else { // 파일이 업로드 되었을때
	                     
	             System.out.println("File Name  : " + fileName);
	         }//else
	        } catch (Exception e) {
	            System.out.print("예외 발생 : " + e);
	        }//catch
	   //     jsResponse("글 수정 성공", "witness.do?command=witnessSelectOne&no="+dto.getNo(), response);        
	      
	        rd = getServletContext().getRequestDispatcher("/witness.do?command=witnessSelectOne&no="+dto.getNo());

	        rd.forward(request, response);
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
