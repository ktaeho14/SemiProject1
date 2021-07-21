<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html;charset=UTF-8"); %> 
<%@ page import="join.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    body{
        margin:0;
        padding:0;
    }

    section{
        display: grid;
        place-items: center;
    }

    a{
        text-decoration: none;
    }

    ul{
        list-style: none;
    }



    .wrapper{
        width: 1160px;
        margin-top: 40px;
        display: grid;
        grid-template-columns: repeat(3, 1fr);
        gap: 30px;
        grid-auto-rows: minmax(40px, auto);
        margin-bottom: 40px;
    }

    .wrapper div {
        padding-top: 10px;
    }

    .wrapper div h2{
        font-size: 16px;
        width: 200px;
        margin: 13px 0;
        display: inline;
    }

    .aplus{
        margin-top: 3px;
        font-size: 13px;
        color: #e96211;;
        float: right;
        text-decoration: underline;
        font-weight: 600;
    }

    .board1{
        grid-column: 1 / 3;
        grid-row: 1/7;
    }
    
    .board2{
        grid-column: 1 / 3;
        grid-row: 8/14;
    }

    .boc1 ul,
    .boc2 ul{
        padding: 0;
        list-style: none;

    }

    .boc1 ul li ,
    .boc2 ul li {
        width: 148px;
        padding: 0;
        float: left;
        margin-right: 1.8px;
    }

    .boc1 ul li img,
    .boc2 ul li img{
        width: 145px;
        height: 145px;
    }



    .board3{
        grid-column: 1 / 2;
        grid-row: 15/21;
    }

    

    .board4{
        grid-column: 2 / 3;
        grid-row: 15 / 21;
    }

    

    .boc3 ul,
    .boc4 ul{
        list-style: square;
        width: 340px;
        padding-left: 20px;
    }

    .boc3 ul li a,
    .boc4 ul li a{
        display: inline-block;
        text-decoration: none;
        color: black;
        max-width: 240px;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap;
        font-size: 14px;
    }



    .boc3 ul li span,
    .boc4 ul li span{
        float: right;
        padding-right: 0;
        font-size: 14px;
    }




    .board5{
        grid-column: 3 / 4;
        grid-row: 15 / 21;
    }


    .loginbox {
        grid-column: 3 / 4;
        grid-row: 1 / 3;
        background-color: #f7f9fa;
        border: 1px solid #dae1e6;
        padding: 15px;
    }
    
    .loginboxtop {
        color: grey;
        font-size: 12px;
        margin: 0;

    }
    .loginboxin{
        display: block;
        background-color: #19ce60;
        border: 1px solid #15c654;
        padding: 15px 0;
        margin: 10px 0;
        border-radius: 5px;
        font-size: 15px;
        color: white;
        font-weight: 900;
        text-align: center;
        width: 345px;
    }

    .loginboxin i{
        font-size: 17px;
        padding-right: 5px;
    }

    .loginboxbotdiv{
        margin-top: 15px;
    }

    .loginboxbot{
        color: grey;
        font-size: 12px;
        float: left;
    }

    .loginboxjoin{
        color: grey;
        font-size: 12px;
        float: right;
    }





    .chatbox{
        grid-column: 3 / 4;
        grid-row: 4 / 14;
        border: 1px solid;
        border-radius: 50px;
        padding: 40px;
        

    }

    .chatbot{
        background-color: yellow;
        margin-top: 40px;
        height: 600px;
        border: 1px solid;
    }

    .chatchat{
        border: 1px solid;
        height: 30px;
    }









</style>
</head>
<body>
<%
	AniDto dto = (AniDto)session.getAttribute("dto");
%>

    <section>

        <div class="wrapper">
            <div class="board1">
            <div class="bot1">
                <h2>찾아주세요</h2>
                <a class="aplus" href="">더보기</a>
            </div>
            <div class="boc1">
                <ul>
                	<c:forEach var="i" begin="0" end="9">
                		<li><a href="${a}"><span><img src="${a}"></span></a>
                        <ul>
                            <li><span>종류: </span>${a}</li>
                            <li><span>잃어버린장소:</span><br>
                                	${a}
                            </li>
                        </ul>
                    	</li> 
                    </c:forEach>
                    <!-- 최신글 목록에서  0-i 이런식으로 나열-->
                </ul>
            </div>
            </div>
            
            
            <div class="board2">
            <div class="bot2">
                <h2>목격 / 보호 게시판</h2>
                <a class="aplus" href="">더보기</a>
            </div>
            <div class="boc2">
                <ul>
                    <c:forEach var="i" begin="0" end="9">
                		<li><a href="${a}"><span><img src="${a}"></span></a>
                        <ul>
                            <li><span>종류: </span>${a}</li>
                            <li><span>잃어버린장소:</span><br>
                                	${a}
                            </li>
                        </ul>
                    	</li> 
                    </c:forEach>
                    <!-- 최신글 목록에서  0-i 이런식으로 나열-->
                </ul>
            </div>
            </div>

            
            
            <div class="board3">
            <div class="bot3">
                <h2>공지사항</h2>
                <a class="aplus" href='<%= request.getContextPath()%>/notice.do?command=notice_listboard'>더보기</a>
            </div>
            <div class="boc3">
                <ul>
                	<c:forEach var="i" begin="0" end="6">
                    	<li>
                        	<a href="">${a}제목들어감</a><span>${a}날짜들어감</span>
                    	</li>
                    </c:forEach>
                </ul>
            </div>
            </div>



            <div class="board4">
            <div class="bot4">
                <h2>입양후기</h2>
                <a class="aplus" href="">더보기</a>
            </div>
            <div class="boc4">
                <ul>
                    <c:forEach var="i" begin="0" end="6">
                    	<li>
                        	<a href="">${a}제목들어감</a><span>${a}날짜들어감</span>
                    	</li>
                    </c:forEach>
                </ul>
            </div>
            </div>

            <div class="board5">
            <div class="bot5">
                <h2>봉사활동</h2>
                <a class="aplus" href="">더보기</a>
            </div>
            <div class="boc5">
                <table border="1" width="376px">
                    <tr>
                        <th>월</th>
                        <th>화</th>
                        <th>수</th>
                        <th>목</th>
                        <th>금</th>
                        <th>토</th>
                        <th>일</th>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                    <tr>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                        <td>1</td>
                    </tr>
                </table>
               	 느낌확인용
            </div>
            </div>



            <div class="loginbox">
            
            
            
            	<% if(dto == null) {%>
                <p class="loginboxtop">찾아주개냥 이용을 위해 로그인해주세요</p>
               
                <a class="loginboxin" href="<%=request.getContextPath() %>/controller.do?command=loginform"><i>찾아주개냥</i> 로그인</a>
                <div class="loginboxbotdiv">
                    <a class="loginboxbot" href="">아이디 &nbsp</a>
                    <a class="loginboxbot" href="">비밀번호찾기</a>
                    <a class="loginboxjoin" href="<%=request.getContextPath() %>/controller.do?command=joinform">회원가입</a>
                </div>

				<% } else if(dto != null) {%>

				<span><%= dto.getMyname() %> 님 </span>
					<a class="loginboxbot" href="">작성글 &nbsp</a>
					<a class="loginboxbot" href="">봉사활동신청 &nbsp</a>
					<a class="loginboxbot" href="">마이페이지 &nbsp</a>
				<a class="logoutBtn" href="<%=request.getContextPath() %>/controller.do?command=logout">로그아웃</a>


				<%} %>

				

            </div>
            <div class="chatbox">
                <div class="chatbot">
                    뭔지 알아보기 위해
                </div>
                <div class="chatchat">
                </div>
            </div>

            
        </div>


    </section>











</body>
</html>