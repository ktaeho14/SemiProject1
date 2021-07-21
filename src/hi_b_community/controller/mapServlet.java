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
import hi_b_community.map.biz.MapBiz;
import hi_b_community.map.biz.MapBizImpl;
import hi_b_community.map.dto.mapDto;
import hi_b_community.map.dto.mapshelterDto;
import join.dto.AniDto;

@WebServlet("/map.do")
public class mapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public mapServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String command = request.getParameter("command");
		
		
		MapBiz dao = new MapBizImpl();
		
		if(command.equals("map_guide")) {
			//단순히 보호소들 지도에 마커로 나타내주는 부분 
			
			List<mapshelterDto> map = dao.shelter_selectAll();
			
			request.setAttribute("map", map);
			
			
			
			dispatch("views/e_map/map_guide.jsp",request, response);

			
			
		}else if(command.equals("map_search")) {
			//키워드 입력받아 해당하는 값들 리스트로 보여주고, 그 리스트들을 지도에 마커로 나타내주는 부분 
			response.sendRedirect("views/e_map/map_search.jsp");
			
			
			
			
		//=====****여기서부터 목격,보호 CRUD게시판에서 입력할 때 목격한 위치 찍을 때 사용!****=====	
		}else if(command.equals("map_place_click")) {
			//현재위치+목격위치 찍는 부분 
			response.sendRedirect("views/e_map/map_place_click.jsp");
			
			
			
		}else if(command.equals("map_insert")) {
			//map_place_click.jsp에서 넘어오는 값들을 받아 db에 insert해주는 부분 
			
			//form태그(hidden)로부터 넘어오는 값들 
			double latitude_result = Double.parseDouble(request.getParameter("latitude_result"));
			double longtitude_result = Double.parseDouble(request.getParameter("longtitude_result"));
			String land_number_address = request.getParameter("land_number_address");
			String road_name_address = request.getParameter("road_name_address");
			
			//넘어오는 값들 
			String phone = request.getParameter("phone_no");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			mapDto dto = new mapDto();
			dto.setLatitude(latitude_result);
			dto.setLongtitude(longtitude_result);
			dto.setLand_number_address(land_number_address);
			dto.setRoad_name_address(road_name_address);
			dto.setMap_writer(writer);
			dto.setMap_phone(phone);
			dto.setMap_content(content);
			
			
			int res = dao.map_insert(dto);
			
			if(res>0) {
			System.out.println("지도 정보 db에 넣기 성공!");
			dispatch("/map.do?command=map_list",request, response);

			}
			
			
			
			
		}else if(command.equals("map_list")) {
			//MAP_PLACE_CLICK테이블에 들어있는 db값들 불러오는 부분
			
			
			
			List<mapDto> list = dao.selectAll();
			
			request.setAttribute("list", list);
			
			
			dispatch("views/e_map/map_listall.jsp",request, response);

			
		
		}else if(command.equals("map_detail")) {
			//글의 번호를 받아서, 해당하는 번호글의 상세조회하는 부분 
			
			int map_no =Integer.parseInt(request.getParameter("map_no"));
			
			
			mapDto dto = dao.selectOne(map_no);
			request.setAttribute("map", dto);
			
			dispatch("views/e_map/map_place_detail.jsp",request, response);
		
			
			
		}else if(command.equals("map_place_updateform")) {
			//상세조회시, 수정하기버튼 누르면 목격위치도 수정해야하므로 만들었음 
			
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			System.out.println("user.getMyrole() : "+user.getMyrole()); //확인용 
			
			String map_writer = request.getParameter("map_writer");
			
			int writerIdx = map_writer.indexOf("("); //id만 추출하기 위해 (의 index를 찾음
			String id = map_writer.substring(0, writerIdx); //id값만 추출 
			
			
			int map_no = Integer.parseInt(request.getParameter("map_no"));//수정할 글의 번호를 받아온 후 
			

			mapDto dto = dao.selectOne(map_no);//상세조회해서 request객체에 담음 
			
			if(user.getMyid().equals(id)) {
				request.setAttribute("map", dto);
				dispatch("views/e_map/map_place_updateform.jsp",request, response); 
			
			}else if(user.getMyrole().equals("ADMIN")) {
				request.setAttribute("map", dto);
				dispatch("views/e_map/map_place_updateform.jsp",request, response); 
			
			}else {
				jsResponse2("작성자만 수정할 수 있습니다.", response); 
			}
			
			
		
			
		}else if(command.equals("map_update")) {
			//수정하는 경우, 
			
			
			
			
			int map_no = Integer.parseInt(request.getParameter("map_no"));//수정할 글의 번호와 
			
			
			
			//form태그(hidden)로부터 넘어오는 값들 <= 새로 찍은 위치(수정한)의 정보들 
			double latitude_result = Double.parseDouble(request.getParameter("latitude_result"));
			double longtitude_result = Double.parseDouble(request.getParameter("longtitude_result"));
			String land_number_address = request.getParameter("land_number_address");
			String road_name_address = request.getParameter("road_name_address");
			
			//넘어오는 값들 
			String phone = request.getParameter("phone_no");
			String writer = request.getParameter("writer");
			String content = request.getParameter("content");
			
			
			
			
			mapDto dto = new mapDto();
			dto.setMap_no(map_no);
			dto.setLatitude(latitude_result);
			dto.setLongtitude(longtitude_result);
			dto.setLand_number_address(land_number_address);
			dto.setRoad_name_address(road_name_address);
			dto.setMap_writer(writer);
			dto.setMap_phone(phone);
			dto.setMap_content(content);
			
			int res = dao.update(dto);
			
			if(res>0) {
				jsResponse2("글 수정 성공", response);
			}else {
				jsResponse2("글 수정 실패", response);
			}
		
		
		}else if(command.equals("practice_one")) {
			
			List<mapDto> list = dao.selectAll();
			
			request.setAttribute("map", list);
			
			dispatch("views/e_map/practice_one.jsp",request, response); 
			
		}else if(command.equals("map_delete")) {
			//수정하는 경우, 
			
			
			HttpSession session = request.getSession();
			AniDto user = (AniDto)session.getAttribute("dto");
			System.out.println("user.getMyrole() : "+user.getMyrole()); //확인용 
			
			int map_no = Integer.parseInt(request.getParameter("map_no"));
			String map_writer = request.getParameter("map_writer");
			
			int writerIdx = map_writer.indexOf("("); //id만 추출하기 위해 (의 index를 찾음
			String id = map_writer.substring(0, writerIdx); //id값만 추출 
			
			
			System.out.println("map_writer: "+ map_writer);
			
			mapDto dto = dao.selectOne(map_no);
			
			if(user.getMyid().equals(id)) {
			
				int res = dao.delete(map_no);
				if(res>0) {
					dispatch("/map.do?command=practice_one", request, response);
					
				}else {
					jsResponse2("글 삭제 실패.", response);
				}
				
			}else if(user.getMyrole().equals("ADMIN")) {
				int res = dao.delete(map_no);
				if(res>0) {
					
					dispatch("/map.do?command=practice_one", request, response);
					
				}else {
					jsResponse2("글 삭제 실패.", response);
				}
			}else {
				//ADMIN(관리자)도, 작성자가 아니다 = > 삭제X
				jsResponse2("작성자만 삭제할 수 있습니다.", response);
			}
			
			
			
			
		
		}
	
	}

	
	
	private void dispatch(String url, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatch = request.getRequestDispatcher(url);
		dispatch.forward(request, response);
	}
	
	
	public void jsResponse(String msg, HttpServletResponse response) throws IOException {
		String s = "<script type='text/javascript'>"
				+ "alert('"+msg+"');"
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
