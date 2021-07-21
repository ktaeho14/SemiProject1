<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sightingDetail.css">
<link rel="stylesheet" href="./css/sighting.css">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">


<!-- Bootstrap CSS -->
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
    
</head>
<body>
	<jsp:include page="../logtop.jsp"/>
	<jsp:include page="../header.jsp"/>

    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
               
            
                <ul>
                    <li><a class="left_a" href="witness.do?command=witnessmain">목격 게시판</a></li>
                    <li><a class="left_a" href="sighting.do?command=sightingmain">보호 게시판</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title" style="margin-bottom:50px;">
                    <h3>보호 게시판</h3>
                    <h4>전국 유기동물 보호소에 보호중인 아이들을 소개해주는 페이지입니다</h4>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                <div class="sighting_explanation">
            		<p>
            			<b class="sighting_explanation_content">
          					 담당자명 또는 담당기관 : ${res.chargenm }</b></p><br>
          			<p>	
          				<b class="sighting_explanation_content">
          					발견위치 : ${res.happenplace }</b></p>			
            		
            	</div>
                  <table class="table" border="1" style="margin-top:30px;">
  <thead>
    <tr>
      <th colspan="4" style="background-color:pink; text-align:center; font-size: 20px; color:white">유기견 상세 보기</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"  style="background-color:#fcf1f4; font-size:20px;">나이</th>
      <td>${res.age }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">보호소 </th>
      <td>${res.carenm }</td>
    </tr>
    <tr>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">색상</th>
      <td>${res.colorcd }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">보호소 위치</th>
      <td>${res.careaddr }</td>
    </tr>
     <tr>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">성별</th>
      <td>${res. sexcd }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">보호소 전화번호</th>
      <td>${res.caretel }</td>
    </tr>
     <tr>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">특이사항</th>
      <td>${res.specialmark }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">체중</th>
      <td>${res.weight }</td>
    </tr>
   	
  </tbody>
  
  
</table>
	<h2>유기견 사진</h2>
	<div class="detail_img">
	<img src="http://${res.popfile }">
    </div>
    
 

                </div>
            </div>


    </section>

    <footer>
        <div id="fin">
            <p>Copyright @ KH교육원 디지털 콘텐츠 웹 융합 응용SW 개발자 양성과정(8)-세미프로젝트 3조. All rights reserved.</p> 
        </div>
    </footer>
	
</body>
</html>