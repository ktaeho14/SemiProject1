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
	.sloginbox {
		display:inline-block;
		position:absolute;
		min-width: 170px;
        margin-top: 7.4px;
        margin-bottom: 7.4px;
        margin-left: 1000px;
        z-index: 2;
    }

	.logBtn {
		float: right;
	}

	.clearfix::after{
		content:'';
		clear:both;
		display:block;
	}
	.mypagebtn{
		color: blue;
		text-decoration: underline;
	}
	
	
</style>

</head>
<body>
<%
	AniDto dto = (AniDto)session.getAttribute("dto");
%>
	
	<div class="sloginbox">
		<div class="clearfix">
    	<% if(dto == null) {%>
    		<input type="button" class="logBtn" value="로그인" onclick="location.href='controller.do?command=loginform'">
    	<% } else if(dto != null) {%>
			<span><a class="mypagebtn" href="controller.do?command=mypage"><%= dto.getMyname() %></a>님 환영합니다. </span>
			<input type="button" class="logBtn" value="로그아웃" onclick="location.href='controller.do?command=logout'">
		<%} %>      
		</div>     
    </div>



</body>
</html>