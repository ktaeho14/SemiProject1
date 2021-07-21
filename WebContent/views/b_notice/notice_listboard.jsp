<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, hi_a_common.*, hi_b_community.notice.dto.noticeDto, join.dto.*" %>
<%request.setCharacterEncoding("UTF-8"); %>
<%response.setContentType("text/html; charset=UTF-8"); %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 		
	
	String msg = (String)request.getAttribute("msg");
	noticeDto dto = new noticeDto();
	List<noticeDto> list = (List<noticeDto>)request.getAttribute("list");

	PageInfo pi = (PageInfo)request.getAttribute("pi");
	int listCount = pi.getListCount();
	int currentPage = pi.getCurrentPage();
	int maxPage = pi.getMaxPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
%> 
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
	
	#title_a:link{color: black;}
	#title_a:visited{color: black;}
	
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	//user인 경우 or 비회원인 경우, 글쓰기 버튼이 안보이도록 설정
	window.onload = function(){
		var role = '<c:out value="${dto.getMyrole()}"/>';
		console.log("role: "+ role);
		
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
                    <h4 style="color:#168;">[찾아주개냥] - 공지사항 게시판입니다.</h4>
                    
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
	<col width="150">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>작성일</th>
			<th>조회수</th>  <!-- 여기  -->
		</tr>
		
		<tbody>
		<c:choose>
		
		<c:when test="${empty list }">
			<tr>
				<td colspan="6">=====작성된 글이 없습니다=====</td>
			</tr>
		</c:when>
		
		<c:otherwise>
		
			<c:forEach items="${list }" var="dto"> 
				<tr class="dataRow">
					<td class="no" id="no">${dto.notice_no }</td>
					<td id="title"><a id="title_a" href="<%= request.getContextPath()%>/notice.do?command=notice_detail&notice_no=${dto.notice_no}">${dto.notice_title}</a></td>
					<td>${dto.notice_name }</td>
					<td>${dto.notice_regdate }</td>
					<td>${dto.notice_hit }</td>  <!-- 여기  -->
				</tr>
			</c:forEach>
		</c:otherwise>
	
	</c:choose>
	</tbody>

			<td colspan="6" align="right">
				<input type="button" id="insert" value="글쓰기" onclick="location.href='<%= request.getContextPath()%>/notice.do?command=notice_insertform'"
					style="background-color:#D9E5FF; border-radius : 5px; ">
			</td>

		
	<!-- 페이징 -->
	<div class='pagingArea' align='center'>
		<% if(!list.isEmpty()){ %>
		<!-- 맨 처음으로 -->
		<button onclick="location.href='list.gy?currentPage=1'">&lt;&lt;</button>
		
		<!-- 이전 페이지로 -->
		<button onclick="location.href='<%= request.getContextPath() %>/list.gy?currentPage=<%= currentPage-1 %>'" id="beforeBtn">&lt;</button>
		<script>
			if(<%= currentPage %> <= 1){
				var before = $('#beforeBtn');
				before.attr('disabled', 'true');
			}
		</script>
			<!-- 10개의 페이지 목록 -->
		<% for(int p = startPage; p <= endPage; p++){ %>
			<% if(p == currentPage){ %>
				<button id="choosen" disabled><%= p %></button>
			<% } else{ %>
				<button id="numBtn" onclick="location.href='<%= request.getContextPath() %>/list.gy?currentPage=<%= p %>'"><%= p %></button>
			<% } %>
		<% } %>
		
		<!-- 다음 페이지로 -->
		<button onclick="location.href='<%= request.getContextPath() %>/list.gy?currentPage=<%= currentPage + 1 %>'" id="afterBtn">&gt;</button>
		<script>
			if(<%= currentPage %> >= <%= maxPage %>){
				var after = $("#afterBtn");
				after.attr('disabled', 'true');
			}
		</script>
		
		<!-- 맨 끝으로 -->
		<button onclick="location.href='<%= request.getContextPath() %>/list.gy?currentPage=<%= maxPage %>'">&gt;&gt;</button>
		
		<% } %>
		</div>
	
	<b> 총 게시글 수 : <%=listCount%></b>
	
	
	
	</table>
</div>
                </div>
            </div>


    </section>
</div>
  <%@include file="/footer.jsp" %>
 
 
	
</body>
</html>