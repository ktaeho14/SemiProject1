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
        margin-bottom: 50px;
    }
    
    
    .mypli {
    	padding: 10px;
        list-style: none;
    }
    
    .mypli li{
    	float: left;
    	width: 350px;
    	height: 210px;
        padding: 30px;
        margin-left: 20px;
        margin-bottom: 20px;
        border: 1px solid;
    }
    
    .iconbox{
     	display: block;
     	height: 140px;
     	width: 100%;
        
    }
    
    .icon{
        float: left;
        width: 75px;
        margin-top: 50px;
    }

    .iconc{
        float: left;
        width: 220px;
        margin-left: 40px;
        margin-top: 10px;
    }
    
    .minibtn{
    	display: block;
        background-color: #19ce60;
        border: 1px solid #15c654;
        padding: 10px 0;
        margin: 10px 0;
        border-radius: 5px;
        font-size: 15px;
        color: white;
        font-weight: 900;
        text-align: center;
        float: right;
        width: 120px;
    }
    
    
    
</style>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.14.0/css/all.css" integrity="sha384-HzLeBuhoNPvSl5KYnjx0BT+WB0QEEqLprO+NBkkk5gbc67FTaL7XIGa2w1L0Xbgc" crossorigin="anonymous">
</head>
<body>
<%
	AniDto dto = (AniDto)session.getAttribute("dto");
%>
	
	<%@include file="/header.jsp" %>
	
	
	<section>
		<div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>회원정보</h2>
            
                <ul>
                    <li><a class="left_a" href=""><%= dto.getMyname() %> 님</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h1>회원정보</h1>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <ul class="mypli">
                    	<li>
                    		<div>
                    			<div class="iconbox">
                                    <div class="icon"><i class="far fa-address-card fa-5x"></i></div>
                                    <div class="iconc">
                                        <h3>회원 정보</h3>
                                        <p>회원 정보를 변경하실수 있습니다.</p>

                                    </div>
                                </div>
                    		</div>                  
                    		<a class="minibtn" href="controller.do?command=infoch">회원정보 수정</a>
                    	</li>
                    	<li>
                    		<div>
                    			<div class="iconbox">
                                    <div class="icon"><i class="fas fa-lock fa-5x"></i></div>
                                    <div class="iconc">
                                        <h3>비밀번호</h3>
                                        <p>주기적으로 비밀번호를 변경하여
                                            계정을 보호하세요.</p>

                                    </div>
                    			</div>
                    		</div>                  
                    		<a class="minibtn" href="controller.do?command=pwch">비밀번호 변경</a>
                    	</li>
                    	<li>
                    		<div>
                    			<div class="iconbox">
                                    <div class="icon"><i class="fas fa-exclamation-circle fa-5x"></i></div>
                    				<div class="iconc">
                                        <h3>회원탈퇴</h3>
                                        <p>정말 탈퇴하시겠습니까? 탈퇴 시 정보는  복구되지 않습니다.</p>
                                    </div>
								</div>
                    		</div>                  
                    		<a class="minibtn" href="controller.do?command=leaveuserform">회원 탈퇴</a>
                    	</li>
                    </ul>
                </div>
            </div>

		</div>
        
    </section>
	
	<%@include file="/footer.jsp" %>
	
	
	
	
</body>
</html>