<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "join.dto.*"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>      
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/community_section.css"><!-- section css -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<style type="text/css">
	.tableone {
	  border-collapse: collapse;
	  
	}  
	.tableone th {
	  padding: 5px;
	  color: #168;
	  
	  text-align: left;
	}
	
	.tableone td {
	  color: #669;
	  padding: 5px;
	 
	}
	
	.tableone input {
	  color: #669;
	  padding: 5px;
	  border: none;
	  font-size : 14px;
	}
	.tableone textarea {
	  color: #669;
	  padding: 5px;
	  border: none;
	  font-size : 14px;
	}
	.tableone tr:hover td {
	  color: #004;
	}
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//user인 경우 or 비회원인 경우, 글쓰기 버튼이 안보이도록 설정
	window.onload = function(){
		var role = '<c:out value="${dto.getMyrole()}"/>'; //현재 접속한 유저의 myrole
		console.log(role);
		
		if(role==("USER") || role==""){
			$("#update").hide();
			$("#delete").hide();
		}
	}
	
	//삭제버튼 클릭했을 때 
	function deleteConfirm(){
		 if(confirm('정말 삭제하시겠습니까?')) {
            // 확인 click => 삭제
          	location.href='<%= request.getContextPath()%>/notice.do?command=notice_delete&notice_no=${dto2.notice_no}&myid=${dto.getMyid()}';
       	 }else{
           // 취소 click => 취소
          	location.href='<%= request.getContextPath()%>/notice.do?command=notice_detail&notice_no=${dto2.notice_no}';
     	 }
	}
	
</script>
</head>
<body>
<div>
	<%@include file="/header.jsp" %>
    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>커뮤니티</h2>
            
                <ul>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/notice.do?command=notice_listboard">공지사항</a></li>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/complain.do?command=complain_listboard">불만접수</a></li>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/question.do?command=question_listboard">Q&A</a></li>
                    <li><a class="left_a" href="<%= request.getContextPath()%>/map.do?command=map_guide">전국보호소지도</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small style='background-color:#DAD9FF; '><b>${dto.getMyname() }</b>님 안녕하세요 😊 </small>
                    <h1>공지사항</h1>
                    <h4>[찾아주개냥] - 공지사항 내용을 확인하세요.</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    
	
	<div>

	<!--  <h1>${dto2.notice_name }님의 글 보기</h1>-->
	<form action="<%= request.getContextPath()%>/notice.do" method="post">
	<input type="hidden" name="command" value="notice_update">
	<input type="hidden" name="notice_no" value="${dto2.notice_no }">
	<input type="hidden" name="myid" value="${dto.getMyid() }"> <!-- 작성자만 수정하도록  -->
	
	<table border="1" class="tableone">
	<col width="70" >
	<col width="300">
	<tr>
		<th>&nbsp;제목</th>
		<td><input type="text" name="title" value="${dto2.notice_title }" readonly="readonly"></td>
	</tr>
	<tr>	
		<th>작성자</th>
		<td><input type="text" name="name" value="${dto2.notice_name }" readonly="readonly"></td>
	</tr>
	
	<tr>	
		<th>&nbsp;내용</th>
		<td><textarea rows="40" cols="100" name="content" readonly="readonly"> ${dto2.notice_content }</textarea></td>
	</tr>
	
	
	
	<tr>
		<td colspan="2" align="right">
		<input type="submit" id="update" value="수정" onclick="clickUpdate();" style="background-color:#D9E5FF; border-radius : 5px; " >
		<input type="button" id="delete" value="삭제" onclick="deleteConfirm();"style="background-color:#D9E5FF; border-radius : 5px; ">
		<input type="button" value="목록" onclick="location.href='<%= request.getContextPath()%>/notice.do?command=notice_listboard'"style="background-color:#D9E5FF; border-radius : 5px; ">
		</td>
	</tr>
	</table>
	</form>

	</div>
	
	
                </div>
            </div>


    </section>

<%@include file="/footer.jsp" %>

</body>
</html>