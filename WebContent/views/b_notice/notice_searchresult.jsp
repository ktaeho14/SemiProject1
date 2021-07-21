<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, hi_a_common.*, hi_b_community.notice.dto.noticeDto" %>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	noticeDto dto = new noticeDto();
	List<noticeDto> list2 = (List<noticeDto>)request.getAttribute("list2");
	
%>
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
	  padding: 10px;
	  color: #168;
	  border-bottom: 3px solid #168;
	  text-align: left;
	}
	.tableone td {
	  color: #669;
	  padding: 10px;
	  border-bottom: 1px solid #ddd;
	}
	.tableone tr:hover td {
	  color: #004;
	}
</style>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//user인 경우 or 비회원인 경우, 글쓰기 버튼이 안보이도록 설정
	window.onload = function(){
		var role = '<c:out value="${dto.getMyrole()}"/>';
		console.log(role);
		
		if(role==("USER") || role==""){
			$("#insert").hide();
			
		}
	}
	
	//검색버튼을 클릭했을 때 
	function searchMessage(){
		if($("#word").val().trim()==null || $("#word").val().trim()=="" ){
			alert("검색어를 입력해주세요.");
			$("#word").focus();
			event.preventDefault(); //데이터전송, 페이지 전환 방지 
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
                    <h4>[찾아주개냥] - 검색결과를 확인하세요.</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    
<!-- 7/6 검색 -->
	<form action="<%= request.getContextPath()%>/notice.do" method="post" id="searchForm">
	<input type="hidden" name="command" value="notice_search">
      <div class="searchArea">
         <div class="selectArea">
            <select name="search" id="search">
               <option ${(param.search == "NOTICE_NAME")? "selected" : ""} value="notice_name" selected>작성자</option> 
               <option ${(param.search == "NOTICE_TITLE")? "selected" : ""} value="notice_title">제목</option>
               <option ${(param.search == "NOTICE_CONTENT")? "selected" : ""} value="notice_content">내용</option>
            </select> 
            <input type="hidden" name="searchSelect" id="searchSelect">
         </div>
         <div class="wordArea">
            <input type="text" placeholder="검색어 입력" name="word" id="word">
            <button id="searchBtn" onclick="searchMessage();">검색</button>
         </div>
      </div>
	</form>	
	
	
	
<table border="1" class="tableone">
	<col width="70">
	<col width="400">
	<col width="150">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
		</tr>
		
		<tbody>
<%
	if(list2.size()==0){
%>
	<tr>
		<td colspan="4">검색하신 단어가 해당하는 게시물이 존재하지 않습니다. </td>
	</tr>		
<%		
	}


%>		
		
		
		
		
<% 
	for(int i=0; i<list2.size(); i++){
%>
		<tr>
			<td><%=list2.get(i).getNotice_no() %></td>  
			<td><a href="<%=request.getContextPath()%>/notice.do?command=notice_detail&notice_no=<%=list2.get(i).getNotice_no()%>"><%=list2.get(i).getNotice_title() %></a></td>  
			<td><%=list2.get(i).getNotice_name() %></td>  
			<td><%=list2.get(i).getNotice_regdate() %></td>  
			
		
		
		</tr>

<% 	}

%>		

	</tbody>
	<tr>
		<td colspan="5" align="right">
			<input type="button" value="글쓰기" id="insert" onclick="location.href='<%= request.getContextPath()%>/notice.do?command=notice_insertform'"
				style="background-color:#D9E5FF; border-radius : 5px; ">
		</td>
	</tr>
	</table>
	        </div>
            </div>


    </section>

<%@include file="/footer.jsp" %>
	
</body>
</html>
