<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="join.dto.AniDto" %>
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
</head>
<body>
	<%
	AniDto res = (AniDto)session.getAttribute("dto");

%>

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
                    <form action="AdoptController.do" method="post">
                    <input type="hidden" name="no" value="${dto.adopt_no }">
                    <input type="hidden" name="command" value="updateform" >
                    <table border="1">
                    	<tr>
                    		<th>글 제목</th>
                    		<td><input type="text" name="title" value="${dto.adopt_title }"></td>
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
                    		<th>동물 성별</th>
                    		<td><input type="text" name="sex" value="${dto.adopt_sex }"></td>
                    	</tr>
                 		
                    	<tr>
                    		<th>입양 장소</th>
                    		<td><input type="text" name="place" value="${dto.adopt_place }"></td>
                    	</tr>
                    	
                    	<tr>
                    		<th>전화번호</th>
                    		<td><input type="text" name="phone" value="${dto.adopt_phone }"></td>
                    	</tr>
                    	
                    	<tr>
                    		<th>작성자</th>
                    		<td><input type="text" name="writer" value="${dto.adopt_writer }" readonly="readonly"></td>
                    	</tr>
                		
               			<tr>
                    		<th>이메일</th>
                    		<td><input type="text" name="email" value="${dto.adopt_email }"></td>
                    	</tr>
               			
                    	<tr>
                    		<th>글 작성</th>
                    		<td><textarea cols="60" rows="10" name="content" placeholder="ex) &#13;&#10; 자세한 내용은 보호소에 전화 문의 바랍니다." 
                    		required="required"></textarea></td>
                    	</tr>
                    	
                    	<tr>
                    		<td><input type="submit" value="작성완료"></td>
                    		<td><input type="button" value="목록" onclick="location.href='../AdoptController.do?command=AdouptMain'"></td>
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