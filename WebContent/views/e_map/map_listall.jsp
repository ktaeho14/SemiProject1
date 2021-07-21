<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head><!-- 찍은 위치를 모두 보여줌 / 위도,경도 클릭하면 map_detail.jsp로 넘어감 -->
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/community_section.css"><!-- section css -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<style type="text/css">

	.tableone {
	  border-collapse: collapse;
	}  
	.tableone th {
	  
	  color: #168;
	  border-bottom: 3px solid #168;
	  text-align: left;
	}
	.tableone td {
	  color: #669;
	  border-bottom: 1px solid #ddd;
	}
	.tableone tr:hover td {
	  color: #004;
	}
	

.box1{border-radius: 20px; text-align: center; background: rgb(74, 204, 201); float:left; padding:7px;}
.box2{border-radius: 20px; text-align: center; background: rgb(248, 201, 36); float:left; padding:7px;}
.box3{border-radius: 20px; text-align: center; background: rgb(254, 192, 202); float:right; padding:7px;}
.box4{border-radius: 20px; text-align: center; background: rgb(101, 177, 254); float:right; padding:7px;}
</style>
<script type="text/javascript">
	
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
                    <h1>전국 보호소 지도</h1>
                    <h4>[전국 보호소 지도] - 보호소를 확인하세요.</h4>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <h1> </h1>
	
	<!--  <h1>지도</h1>-->

   <div>
<h3>유기견이 목격된 장소입니다.</h3>
<b style="color:red;">▶위도 경도 클릭하여 지도상에서 확인하세요!</b> 

<table border="1" class="tableone">
<col width="50">
<col width="180">
<col width="300">
<col width="300">
<col width="200">
<col width="100">
<tr>

	<td colspan="7" align="right"><div class="box4" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_place_click'">제보하기</div>
	<div class="box3" onclick="location.href='<%= request.getContextPath()%>/map.do?command=practice_one'">지도로 보기</div></td>
</tr>
<tr>
	<th>번호</th>
	<th>위도,경도</th>
	<th>지번</th>
	<th>도로명</th>
	<th>작성자</th>
	<th>전화번호</th>
	<th>특</th>
	
</tr>
<c:choose>
	<c:when test="${empty list }">
		<tr>
			<td colspan="4">작성된 글이 없습니다.</td>
		</tr>
	</c:when>
	
	<c:otherwise>
		<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.map_no }</td>
			<td><a href="<%= request.getContextPath()%>/map.do?command=map_detail&map_no=${dto.map_no }">${dto.latitude}, ${dto.longtitude }</a> </td>
			<td>${dto.land_number_address }</td>
			<td>${dto.road_name_address }</td>
			<td>${dto.map_writer }</td>
			<td>${dto.map_phone }</td>
			<td>${dto.map_content }</td>
		</tr>
		
		</c:forEach>
	</c:otherwise>
	
</c:choose>
</table>
	<div class="box1" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_search'">유기동물 관련 장소 검색하기</div>
	<div class="box2" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_guide'">전국 보호소 지도 보기</div>

</div>

</body>
    </section>
<%@include file="/footer.jsp" %>
	


</body>
</html>