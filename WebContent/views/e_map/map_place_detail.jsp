<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html><!-- map_list.jsp에서 클릭하면 넘어오는 페이지. 확인하기버튼을 누르면  찍었던 위치를 지도와 거리뷰로 나타내줌 -->
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/community_section.css"><!-- section css -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<style type="text/css">
	.tableone {
	  border-collapse: collapse;
	}  
	.tableone th {
	  padding: 10px;
	  color: #168;
	  border-bottom: 3px solid #168;
	  text-align: left;
	}
	.tableone td {
	  color: #669;
	  padding: 10px;
	  border-bottom: 1px solid #ddd;
	}
	.tableone tr:hover td {
	  color: #004;
	}
	
	#title_a:link{color: black;}
	#title_a:visited{color: black;}
	
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//비회원인 경우, 글쓰기 버튼이 안보이도록 설정
	window.onload = function(){
		var role = '<c:out value="${dto.getMyrole()}"/>';
		console.log("role: "+ role);
		
		if(role==""){
			$("#update").hide();
			$("#delete").hide();
		}
	}
	
	//확인하기 버튼 눌렀을 때
	function lookPlace(){
		window.open("views/e_map/map_place_detail_here.jsp", 1000, 700 );
		
	}
	
	//삭제버튼 눌렀을 때 
	function deleteConfirm(){
		 if(confirm('정말 삭제하시겠습니까?')) {
            // 확인 click 글삭제
			 location.href='<%= request.getContextPath()%>/map.do?command=map_delete&map_no=${map.map_no}&map_writer=${map.map_writer} ';
       } else {
           // 취소 click 글삭제 취소
          	location.href="<%= request.getContextPath() %>/map.do?command=map_detail&map_no=${map.map_no}";
     }
   
	}
</script>
</head>
<body>

<div>
		<%@include file="/header.jsp" %>
    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>커뮤니티</h2>
            
                <ul>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/notice.do?command=notice_listboard">공지사항</a></li>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/complain.do?command=complain_listboard">불만접수</a></li>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/question.do?command=question_listboard">Q&A</a></li>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/map.do?command=map_guide">전국보호소지도</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small style='background-color:#DAD9FF; '><b>${dto.getMyname() }</b>님 안녕하세요 😊 </small>
                    <h1>전국 보호소 지도</h1>
                    <h4>[전국 보호소 지도] - 보호소를 확인하세요.</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
	<!-- 게시판에서 해당글 클릭했을 때 보여지도록 ~  -->
	<h3>▶${map.map_writer }이 작성한 내용입니다.</h3>
	
	
	
	
	
	
	<table border="1" class="tableone">
	<col width="100">
	<col width="400">
	<col width="300">
	<col width="300">
	<col width="200">
	<col width="300">
	<col width="200">
	
	
	
		<tr>
			<th>번호</th>
			<th>위도,경도</th>
			<th>지번 주소</th>
			<th>도로명 주소</th>
			<th>전화번호</th>
			<th>작성자</th>
			<th>특징</th>
	
			
			
		</tr>
		<tr>
			<td>${map.map_no }</td>
			<td>${map.latitude },${map.longtitude}&nbsp;&nbsp;<input type="button" value="지도에서 확인하기" onclick="lookPlace();" style="background-color:#D9E5FF; border-radius : 5px; "></td>
			<td>${map.land_number_address }</td>
			<td>${map.road_name_address }</td>
			<td>${map.map_phone }</td>
			<td>${map.map_writer}</td>
			<td>${map.map_content} </td>
		</tr>
		
		<tr>
			<td colspan="7" align="right">
				<input type="button" value="수정하기" id="update" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_place_updateform&map_no=${map.map_no}&map_writer=${map.map_writer }'" style="background-color:#D9E5FF; border-radius : 5px; ">
				<input type="button" value="삭제하기" id="delete" onclick="deleteConfirm();" style="background-color:#D9E5FF; border-radius : 5px; ">
			</td>
		</tr>
		<tr>
			<td colspan="7">
               
                    <b style="background-color:yellow;">※자세하게 (특징,위치,보호 등)아시는 분은 [목격/보호] 게시판을 이용 부탁드립니다.※</b>&nbsp;
			</td>
		</tr>
	
	</table>
	<input type="hidden" id="latitude" value="${map.latitude} ">
	<input type="hidden" id="longtitude" value="${map.longtitude}">
</body>
</section>

<%@include file="/footer.jsp" %>
</html>