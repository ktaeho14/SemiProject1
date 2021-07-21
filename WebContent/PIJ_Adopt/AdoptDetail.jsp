<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
    
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="pij.adopt.dao.AdoptDao" %>
<%@ page import ="pij.adopt.dto.AdoptDto" %>
<%@ page import ="pij.adopt.dao.AdoptDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import ="pij.adopt.biz.AdoptBizImpl" %>
<%@ page import ="pij.adopt.biz.AdoptBiz" %>
    
 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
   
    <link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sightingDetail.css">
    
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
	<script src="https://code.jquery.com/jquery-3.2.0.min.js">	
	</script>
	<script type="text/javascript">
	function checkLogin2() {
	    var id = '${dto.myid}'; // 수정 ''처리
	    var writer = '${adDto.adopt_writer}'
	    // 수정 ''공백 비교
	    if (id != writer) {
	        alert("작성자가 아닙니다.");
	        return false;
	    } else {
	        location.href = 'AdoptController.do?command=update&no=${adDto.adopt_no}';
	        
	    }
	}

	function checkLogin1() {
	    var id = '${dto.myid}'; // 수정 ''처리
	    var writer = '${adDto.adopt_writer}'
	    // 수정 ''공백 비교
	    if (id != writer) {
	        alert("작성자가 아닙니다.");
	        return false;
	    } else {
	        location.href = 'AdoptController.do?command=delete&no=${adDto.adopt_no}';
	        alert("삭제가 완료되었습니다.")
	    }
	}

	</script>
</head>
<body>
		<jsp:include page="../header.jsp"/>
	<section>

        <div class="content_layout">
            
            
            
            <div id="leftmenu">
                
            
                
            
                <ul>
                  
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h1>입양 공고</h1>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <table class="table" border="1">
  <thead>
    <tr>
      <th colspan="4" style="text-align:center"><h1>입양동물 상세보기</h1></th>
    </tr>
  </thead>
  			<tbody>
    <tr>
      <th scope="row">글번호</th>
      <td>${adDto.adopt_no }</td>
      <th scope="row">글 제목</th>
      <td>${adDto.adopt_title }</td>
     
    </tr>
    
   	<tr>
      
      <th scope="row">작성자 </th>
      	<td>${adDto.adopt_writer }</td>
      <th scope="row">전화번호</th>
      <td>${adDto.adopt_phone }</td>
    </tr>
   	<tr>
   		<th scope="row">성별</th>
      	<td>${adDto.adopt_sex }</td>
      	<th scope="row">종류</th>
      	<td>${adDto.adopt_kind }</td>
   	</tr>
   
   	<tr>
   	  <th>글 내용</th>
   	  <td colspan="3"><textarea cols="60" rows="10">${adDto.adopt_content }</textarea></td>
   	</tr>
   	
  
   	
  			</tbody>
  		</table>
  	</div>
  	</div>
  	
  	 	<span class="detailButton">
 	<button type="button" class="btn btn-primary" onclick="checkLogin1()">글 삭제</button>
 	<button type="button" class="btn btn-primary" onclick="checkLogin2()">글 수정</button>
 		</span>
 
 
  	</div>
  </section>
  <footer>
        <div id="fin">
            <p>Copyright @ KH교육원 디지털 콘텐츠 웹 융합 응용SW 개발자 양성과정(8)-세미프로젝트 3조. All rights reserved.</p> 
        </div>
    </footer>
</body>
</html>