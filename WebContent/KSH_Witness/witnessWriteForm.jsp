<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import="join.dto.AniDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	body{
        margin:0;
        padding:0;
    }

    header{
        min-width: 1160px;
        text-align: center;
    }

	a{
        text-decoration: none;
    }

    ul{
        list-style: none;
    }

    #toptoptop{
    	height: 200px;
    	margin-top: 10px;
    
    }

    .topmenu{
	    width: 100%;
	    margin-top: 43px;
	    border-bottom: 4px solid #19ce60;

    }

    .topmenu ul{
	    margin: 0;
	    padding: 0;
    }

    .topmenu > ul{
	    width: 1100px;
	    margin: 0 auto;
	    padding-top: 15px;
    }

    .topmenu > ul > li {
	    display: inline-block;
        width: 215px;
        padding: 0;
        text-align: center;
    }

    .topmenu > ul > li > a {
	    font-weight: 700;
	    padding: 15px 0;
	    color: #999;
	    display: inline-block;
        font-size: 18px;
    }

    .topmenu .largeb {
	    display: none;
	    position: absolute;
	    background: #19ce60;
	    width: 100%;
	    left: 0;
        height: 150px;
    }

    .largeb > ul {
        display: inline-block;
        width: 215px;
        text-align: left;
    }

    .largeb > ul > li{
        display: inline-block;
        width: 215px;
        text-align: left;
        margin-top: 15px;
        color: white;
    }

    .largeb > ul > li > a{
        font-size: 14px;
        color: white;
        font-weight: 600;
        width: 210px;
    }

    .ul1 > li > a{
        padding-left: 60px;
    }
    .ul2 > li > a{
        padding-left: 53px;
    }
    .ul3 > li > a{
        padding-left: 53px;
    }
    .ul4 > li > a{
        padding-left: 70px;
    }
    .ul5 > li > a{
        padding-left: 70px;
    }

    .topmenu ul:hover .largeb{
        display: block;
    }

	.sloginbox {
	    float: right;
        margin-top: 7.4px;
        margin-bottom: 7.4px;
        width: 300px;
    }

	.logBtn {
		float: right;
		margin-right: 30px
	}
	</style>
<link rel="stylesheet" href="../css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="../css/UI.css">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">

</head>


<body>


<%
	AniDto dto = (AniDto)session.getAttribute("dto");

%>	
	<header>
        
        
        <div class="main">
        	<div id="toptoptop">
        		<a href="#"class="imglink"><img alt="배너" src="../image/teamlogo.png"></a>
        	</div>
        
        
        
            <nav class="topmenu">
                <ul>
                    <li>
                        <a href="<%=request.getContextPath() %>/lost.do?command=lostMain&page=1">찾아주세요</a>
                        <div class="largeb">
                                <ul class="ul1">
                                    <li><a href="<%=request.getContextPath() %>/lost.do?command=lostMain&page=1">찾아주세요</a></li>
                                    <li><a href="<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1">실종 아이 찾은 후기</a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul2">
                                    <li><a href="witness.do?command=witnessmain">목격게시판</a></li>
                                    <li><a href="sighting.do?command=sightingmain">보호게시판</a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul3">
                                    <li><a href="PIJ_Adopt/AdoptGiude.jsp">입양안내</a></li>
                                    <li><a href="AdoptController.do?command=adoptMain">입양게시판</a></li>
                                    <li><a href="">입양후기</a></li>
                                </ul>
                                <ul class="ul4">
                                    <li><a href="">봉사활동신청</a></li>
                                    <li><a></a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul5">
                                    <li><a href="">공지사항</a></li>
                                    <li><a href="">불만접수</a></li>
                                    <li><a href="">Q&A</a></li>
                                </ul>
                        </div>
                    </li>
                    <li>
                        <a href="sighting.do?command=sightingmain">유기동물공고</a>
                    </li>
                    <li>
                        <a href="">유기동물입양</a>
                    </li>
                    <li>
                        <a href="">봉사활동</a>
                    </li>
                    <li>
                        <a href="">커뮤니티</a>
                    </li>
                </ul>
            </nav>
        </div>

    </header>
	
    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>유기동물 공고</h2>
            
                <ul>
                    <li><a class="left_a" href="../witness.do?command=witnessmain">목격 게시판</a></li>
                    <li><a class="left_a" href="../sighting.do?command=sightingmain">보호 게시판</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h3>목격 게시판</h3>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                <form action="../witness2.do" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="writeform" >
                    <table border="1">
                    	<tr>
                    		<th>색상</th>
                    		<td><input type="text" name="color"></td>  
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
                    		<td><input type="text" name="place"></td>
                    	</tr>
                    	<tr>
                    		<th>특이 사항</th>
                    		<td><input type="text" name="specialmark"></td>
                    	</tr>
                    	<tr>
                    		<th>전화번호</th>
                    		<td><input type="text" name="phone_no"></td>
                    	</tr>
                    	<tr>
                    		<th>작성자ID</th>
                    		<td><input type="text" name="writer" value="${dto.myid }" readonly="readonly"></td>
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
                    		<td><input type="button" value="목록" onclick="location.href='../witness.do?command=witnessmain'"></td>
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