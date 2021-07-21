<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%  request.setCharacterEncoding("UTF-8"); %>
<%  response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>찾아봐주개냥</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
<link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
</head>
<body>
	<div class="banner">
		<a href="<%=request.getContextPath()%>"><img src="<%=request.getContextPath()%>/basicResources/banner.png" alt="banner"/></a>
	</div>
	<div class="container">
	  <form action="../login.do" method="post">
		<input type="hidden" name="command" value="authCheck">
		<hr class="line"/>
		<input type="text" name="userId" placeholder="아이디를 입력해주세요"><br/>
		<input type="password" name="userPw" placeholder="비밀번호를 입력해주세요"><br/>
		<div class="role">
			<label><input type="radio" name="myRole" value="운영자">운영자</label>&nbsp;&nbsp;
			<label><input type="radio" name="myRole" value="일반회원">일반회원</label><br/>
		</div>
		<div class="login-container"><input type="submit" value="로그인"></div>
		<div class="signup-container"><input type="button" value="회원가입"></div>
		<hr class="line"/>
		<div class="finder">
			<input type="button" value="아이디 찾기">
			<input type="button" value="비밀번호 찾기">
		</div>
	 </form>
	</div>
</body>
</html>