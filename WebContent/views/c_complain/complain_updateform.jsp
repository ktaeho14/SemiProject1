<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %> 
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
	window.onload = function(){
		$("#content").focus();
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
                    <h1>불만접수</h1>
                    <h4>[찾아주개냥] - ${dto2.complain_name }님의 글을 수정하세요 </h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
    
	
	<div>

	<!--  <h1>글 수정</h1>-->
	<form action="<%=request.getContextPath()%>/complain.do" method="post">
	<input type="hidden" name="command" value="complain_updateform">
	<input type="hidden" name="complain_boardno" value="${dto2.complain_boardno }">
	
	<table border="1" class="tableone">
		<tr>
			<th>작성자</th>
			<td>${dto2.complain_name }</td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input type="text" name="title" value="${dto2.complain_title}"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea rows="40" cols="100" name="content" id="content">${dto2.complain_content }</textarea></td>
		</tr>
		<tr>
			<td colspan="2" align="right">
				<input type="submit" value="수정" style="background-color:#D9E5FF; border-radius : 5px; ">
				<input type="button" value="취소"
					onclick="location.href='<%=request.getContextPath()%>/complain.do?command=complain_detail&complain_boardno=${dto2.complain_boardno}'"
					style="background-color:#D9E5FF; border-radius : 5px; ">
			</td>
		</tr>
	
	</table>
	</form>
</div>
</div>
                </div>
            </div>


    </section>

<%@include file="/footer.jsp" %>
	
</body>
</html>