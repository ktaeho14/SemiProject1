<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%  request.setCharacterEncoding("UTF-8"); %>
<%  response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아봐주개냥</title>
    <link rel="stylesheet" href="../css/lostCommon.css">
    <link rel="shortcut icon" href="lostAnimal/favicon.ico" type="image/x-icon">
</head>
<body>
	<c:choose>
		<c:when test="${dto.myid == null}">
			<c:set var="id" value="방문자"/>
		</c:when>
		<c:otherwise>
			<c:set var="id" value="${dto.myid}"/>
		</c:otherwise>
	</c:choose>
    <div>
        ${id}님,<br>
    실종신고는 <strong>실종 신고 게시판</strong><br>
    실종되었던 아이를 찾은 후기는 <strong>실종되었던 아이 찾은 후기</strong><br>
    에서 확인해볼 수 있습니다! 혹시라도 실종신고게시판에서 보셨던 아이를 발견하시면<br>
    목격게시글을 <strong>목격/보호</strong>에 올려주시면 감사하겠습니다!<br/>
  만약, 후기를 작성하고 싶으시다면, <em>내가 작성한 글</em>에 들어가셔서 사진을 누르시면 작성하실 수 있습니다!
    </div>
</body>
</html>