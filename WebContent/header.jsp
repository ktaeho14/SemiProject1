<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html;charset=UTF-8"); %> 
<%@ page import="join.dto.*" %>   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
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
</head>
<body>

	<header>
        
        
        <div class="main">
        	<div id="toptoptop">
        		<a href="<%=request.getContextPath() %>/controller.do?command=index" class="imglink"><img alt="배너" src="<%=request.getContextPath() %>/image/teamlogo.png"></a>
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
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul2">
                                    <li><a href="witness.do?command=witnessmain">목격게시판</a></li>
                                    <li><a href="sighting.do?command=sightingmain">보호게시판</a></li>
                                    <li><a></a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul3">
                                    <li><a href="PIJ_Adopt/AdoptGiude.jsp">입양안내</a></li>
                                    <li><a href="AdoptController.do?command=adoptMain">입양게시판</a></li>
                                    <li><a href=""></a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul4">
                                    <li><a href="">봉사활동신청</a></li>
                                    <li><a></a></li>
                                    <li><a></a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul5">
									<li><a href='<%= request.getContextPath()%>/notice.do?command=notice_listboard'>공지사항</a></li>
                                    <li><a href='<%= request.getContextPath()%>/complain.do?command=complain_listboard'>불만접수</a></li>
                                    <li><a href='<%= request.getContextPath()%>/question.do?command=question_listboard'>Q&A</a></li>
                                    <li><a href="<%= request.getContextPath()%>/map.do?command=map_guide">전국보호소지도</a></li>
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
</body>
</html>