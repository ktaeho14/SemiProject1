<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
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
<link rel="stylesheet" href="./css/community_section.css"><!-- section css -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//user인 경우 || 비회원인 경우, 답글 버튼이 안보이도록 설정
	window.onload = function(){
		var role = '<c:out value="${dto.getMyrole()}"/>';
		console.log(role);
		
		if(role=="USER"){
			//user인 경우 답글 버튼이 안보이도록 설정
			$("#answer").hide();
			
		}else if(role==""){
			//비회원인 경우 답글 버튼이 안보이도록 설정
			$("#answer").hide();
			$("#delete").hide();
			$("#update").hide();
		}
	}

	function deleteConfirm(){
		 if(confirm('정말 삭제하시겠습니까?')) {
            // 확인 click 글삭제
			 location.href='<%= request.getContextPath()%>/question.do?command=question_delete&question_boardno=${dto2.question_boardno}&myid=${dto.getMyid()}';
       } else {
           // 취소 click 글삭제 취소
          	 location.href="<%= request.getContextPath() %>/question.do?command=question_detail&question_boardno=${dto2.question_boardno }";
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
                	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small style='background-color:#DAD9FF; '><b>${dto.getMyname() }</b>님 안녕하세요😊 </small>
                    <h1>Q&amp;A</h1>
                    <h4>[찾아주개냥] - Q&amp;A 글을 확인하세요.</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
	
	<div>

	<!--  <h1>작성 글 보기</h1> -->
	
	<table border="1" class="tableone">
	<tr>
		<th>글번호</th>
		<td>${dto2.question_boardno }</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${dto2.question_name }</td>
	</tr>
	<tr>
		<th>작성날짜</th>
		<td>${dto2.question_regdate }</td>
	</tr>
	<tr>
		<th>제목</th>
		<td>${dto2.question_title }</td>
	</tr>
	<tr>
		<th>내용</th>
		<td><textarea rows="40" cols="100" readonly="readonly">${dto2.question_content }</textarea></td>
		
	</tr>
	<tr>
		<td colspan="2" align="right">
			<input type="button" id="delete" value="삭제" onclick="deleteConfirm();"style="background-color:#D9E5FF; border-radius : 5px; " >
			<input type="button" id="update" value="수정" onclick="location.href='<%= request.getContextPath()%>/question.do?command=question_update&question_boardno=${dto2.question_boardno}&myid=${dto.getMyid()}&writerid=${dto2.question_myid}'"
				style="background-color:#D9E5FF; border-radius : 5px; " >
			<input type="button" id="answer" value="답글" onclick="location.href='<%= request.getContextPath()%>/question.do?command=question_answerform&question_parentboardno=${dto2.question_boardno}'"
				style="background-color:#D9E5FF; border-radius : 5px; " >
			<input type="button" value="목록" onclick="location.href='<%= request.getContextPath()%>/question.do?command=question_listboard'"
				style="background-color:#D9E5FF; border-radius : 5px; " >
		</td>
	</tr>
	
	
	
	</table>
	
</div>
</div>
                </div>
            </div>


    </section>

<%@include file="/footer.jsp" %>

	
</body>
</html>