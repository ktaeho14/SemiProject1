<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import="join.dto.AniDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script type="text/javascript" src="../ckeditor/ckeditor.js"></script>
<title>Insert title here</title>

	<link rel="stylesheet" href="../css/sighting_main.css"> <!-- section css -->
	<link rel="stylesheet" href="../css/UI.css">

</head>
<body>

<%
	AniDto dto = (AniDto)session.getAttribute("dto");

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
                <form action="../AdoptController.do" method="post">
                    <input type="hidden" name="command" value="writeform" >
                    <table border="1" style="width:100%;">
                    	<tr>
                    		<th>글 제목</th>
                    		<td><input type="text" name="adopt_title"></td>
                    	</tr>
                
                    
                    	<tr>
                    		<th>종류</th>
                    		<td>
                    			<select name="adopt_kind">
                    				<option value="개">개</option>
                    				<option value="고양이">고양이</option>
                    			</select>
                    		</td>
                    	</tr>
                 		<tr>
                 			<th>입양장소</th>
                 			<td>
                 				<input type="text" name="adopt_place" placeholder="00시00구00동 00보호센터">
                 			</td>
                 		</tr>
                    	
                    	
                    	<tr>
                    		<th>전화번호</th>
                    		<td><input type="text" name="adopt_phone"></td>
                    	</tr>
                    	<tr>
                    		<th>작성자ID</th>
                    		<td><input type="text" name="writer" value="${dto.myid }" readonly="readonly"></td>
                    	</tr>
               			
               			<tr>
                    		<th>동물 성별</th>
                    		<td><input type="text" name="sex"></td>
                    	</tr>
                    	
                    	<tr>
                    		<th>이메일</th>
                    		<td><input type="text" name="email"></td>
                    	</tr>
                    	<tr>
                    		<th>글 내용</th>
                    		<td>
                    		<textarea class="form-control" id="p_content" name="adopt_content"></textarea>
								<script type="text/javascript">
								 CKEDITOR.replace('p_content'
								                , {height: 500                                                  
								                 });
								</script>
                    		</td>
                    	</tr>
                    	
                    	<tr>
                    		<td><input type="submit" value="작성완료"></td>
                    		<td><input type="button" value="목록" onclick="location.href='../AdoptController.do?command=AdouptMain'"></td>
                    	</tr>
                    		
                    </table>
                    
				</form>
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