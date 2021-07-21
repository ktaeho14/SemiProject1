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
<link href="css/header.css" rel="stylesheet" type="text/css">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	window.onload = function(){
		$("#content").focus();
	}
	
	//내용을 작성 안 하고 글쓰기 버튼 눌렀을 경우 경고창을 나타냄. 
	//그렇지 않은 경우 submit 
	function insertBtn(){
		var doc = document.getElementsByName("content")[0];
		if(doc.value.trim()=="" || doc.value==null){
			alert("내용을 작성하세요.");

		}else{
			document.getElementById('insertform').submit();
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
  					<h1>공지사항</h1>
                    <h4>[찾아주개냥] - 공지사항 내용을 입력하세요.</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                  
	
	<div>

	<h1><!-- 글쓰기 --></h1>
	<form action="<%= request.getContextPath() %>/notice.do" method="post" id="insertform">
	<input type="hidden" name="command" value="notice_insert">
	<input type="hidden" name="myid" value="${dto.getMyid() }">
		<table border="1" class="tableone">
			<tr>
				<th>작성자</th>
				<td><input type="text" name="name" value="${dto.getMyname() }" ></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="[공지]공지합니다."> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="40" cols="100" name="content" id="content"></textarea></td>
			</tr>
			<tr>
				<td colspan="3" align="right">
					<input type="submit" value="작성" onclick="insertBtn();" style="background-color:#D9E5FF; border-radius : 5px; " >
					<input type="button" value="취소" onclick="location.href='<%= request.getContextPath() %>/complain.do?command=complain_listboard'"
						style="background-color:#D9E5FF; border-radius : 5px; " >
				</td>
			</tr>
		</table>
	
	</form>
</div>
</div>
                </div>
            </div>


    </section>

    <footer>
        <div id="fin">
            <p>Copyright @ KH교육원 디지털 콘텐츠 웹 융합 응용SW 개발자 양성과정(8)-세미프로젝트 3조. All rights reserved.</p> 
        </div>
    </footer>
	
</body>
</html>