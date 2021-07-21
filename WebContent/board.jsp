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
<style type="text/css">
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
	
	
	
	
	.content_layout{
        width: 1160px;
        padding-top: 40px;
        min-height: 700px;
    }

    #leftmenu{
        width: 20%;
        float: left;
        margin-right: 13px;
        padding-left: 8px;
    }

    #leftmenu ul{
        padding-left: 0;
        padding-right: 50px;
    }


    #leftmenu h2{
        margin-right: 50px;
    }

    #leftmenu ul li{
        border-bottom: 1px solid #2b2f3e;
    }



    #leftmenu ul li{
        padding-top: 10px;
        padding-bottom: 10px;
    }

    .left_a{
        font-size: 16px;
        font-weight: 600;
        color:black;
        padding-left: 10px;
        padding-bottom: 10px;

    }

    #rightcontent{
        width:  77%;
        display: inline-block;
        padding-left:10px;
    }

    #content_title{
        height: 130px;
    }

    #content_title h1,
    #content_title h4{
        padding-left: 30px;
    }


    #maincontent{
        margin-top: 20px;
        border: 1px dotted;
        margin-bottom: 50px;
    }
    
</style>
</head>
<body>


	<%@include file="/logtop.jsp" %>
	<%@include file="/header.jsp" %>
	
	
	
	<section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>찾아주세요</h2>
            
                <ul>
                    <li><a class="left_a" href="">찾아주세요</a></li>
                    <li><a class="left_a" href="">실종후기</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h1>게시판 타이틀</h1>
                    <h4>간략 설명</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <p>내용채우는곳</p><br><br><br><br><br><br>
                    <p>내용채우는곳</p><br><br><br><br><br><br>
                    <p>내용채우는곳</p><br><br><br><br><br><br>
                    <p>내용채우는곳</p><br><br><br><br><br><br>
                    <p>내용채우는곳</p><br><br><br><br><br><br>
                </div>
            </div>

		</div>
    </section>
	
	<%@include file="/footer.jsp" %>
	
	
	
	
</body>
</html>