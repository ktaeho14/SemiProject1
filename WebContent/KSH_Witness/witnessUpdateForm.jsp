<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import="ksh.sighting.dto.SightingDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ksh.sighting.dao.SightingDao" %>
<%@ page import="join.dto.AniDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sightingDetail.css">


 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<%
	AniDto res = (AniDto)session.getAttribute("dto");

%>
	
	<jsp:include page="../header.jsp"/>
	
    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>유기동물 공고</h2>
            
                <ul>
                    <li><a class="left_a" href="witness.do?command=witnessmain">목격 게시판</a></li>
                    <li><a class="left_a" href="sighting.do?command=sightingmain">보호 게시판</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h3>목격 게시판</h3>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <form action="witness3.do" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="no" value="${dto.no }">
                    <input type="hidden" name="command" value="updateform" >
                    <table border="1">
                    	<tr>
                    	<th colspan="2" style="background-color:pink; text-align:center; font-size:25px;">
                    		유기동물 정보 수정
                    	</th>
                    	</tr>
                    	
                    	<tr>
                    		<th>색상</th>
                    		<td><input type="text" name="color" value="${dto.color }"></td>  
                    	</tr>
                    	<tr>
                    		<th>종류</th>
                    			<td>
									<select name="kind">
										<option value="개">개</option>
										<option value="고양이">고양이</option>
									</select>
								</td>
                    	</tr>
                 		<tr>
                 			<th>City</th>
                 			<td>
                 				<select name="city">
                 					<option value="서울특별시">서울특별시</option>
                 					<option value="강원도">강원도</option>
                 					<option value="충청도">충청도</option>
                 					<option value="전라도">전라도</option>
                 					<option value="경상도">경상도</option>
                 					<option value="경기도">경기도</option>
                 					<option value="제주도">제주도</option>
                 				</select>
                 			</td>
                 		</tr>
                    	<tr>
                    		<th>상세 주소</th>
                    		<td><input type="text" name="place" value="${dto.place }"></td>
                    	</tr>
                    	<tr>
                    		<th>특이 사항</th>
                    		<td><input type="text" name="specialmark" value="${dto.specialmark }"></td>
                    	</tr>
                    	<tr>
                    		<th>전화번호</th>
                    		<td><input type="text" name="phone_no" value="${dto.phone_no }"></td>
                    	</tr>
                    	<tr>
                    		<th>작성자</th>
                    		<td><input type="text" name="writer" value="${dto.writer }" readonly="readonly"></td>
                    	</tr>
                
               			
                    	<tr>
                    		<th>글 작성</th>
                    		<td><textarea cols="60" rows="10" name="content" placeholder="ex) &#13;&#10; 추가 연락 가능한번호 : 010-xxxx-xxxx &#13;&#10; 목격자 이름:  박xx &#13;&#10; 내용 : 삼전지구대 앞에서  07월 02일  14:20 보았습니다." 
                    		required="required"></textarea></td>
                    	</tr>
                    	<tr>
                    		<td><input type="file" name="witness_pic"></td>
                    	</tr>
                    	<tr>
                    		<td><input type="submit" value="작성완료"></td>
                    		<td><input type="button" value="목록" onclick="location.href='witness.do?command=witnessmain'"></td>
                    	</tr>
                    	
                    		
                    </table>
                    
				</form>
                    

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