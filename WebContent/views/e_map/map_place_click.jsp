<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../../css/community_section.css"><!-- section css -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<style type="text/css">
	.tableone {
	  border-collapse: collapse;
	  
	}  
	.tableone th {
	  padding: 5px;
	  color: #168;
	  
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
<script type="text/javascript" >
	function openMap(){
		
		//window.open("views/e_map/map_place_click_here.jsp", 1000, 700 ); //textarea클릭하면 위치 찍을 수 있는 지도창이 나옴 
		window.open("map_place_click_here.jsp", 1000, 700 ); //textarea클릭하면 위치 찍을 수 있는 지도창이 나옴 
		
	} 
	
	
	//특이사항을 클릭해야 수행하는 함수이므로 db에 저장이 됨
	//지도에서 위치찍고, 특이사항 input을 누르면 위도경도, 지번주소 도로명주소를 각각 split을 함 => 위도, 경도, 지번주소, 도로명주소 
	
	
	function check(){
		
		
		/*위도 경도*/
		var pick = document.getElementById("pick_result").value;//위도경도input의  값 가져온 다음
		//console.log(pick);
		var pickArr = pick.split(","); // ','기준으로 잘라서 각각 hidden타입으로 서블릿으로 넘긴다
		//console.log(pickArr[0]);
		
		document.getElementsByName("latitude_result")[0].value = pickArr[0]; //위도값 input value(hidden)에 넣기
		document.getElementsByName("longtitude_result")[0].value = pickArr[1]; //경도값 input value(hidden) 넣기
		
		
		/*지번주소 도로명주소*/
		var pick2 = document.getElementById("pick_result_address").value; //지번도로명주소 input의 값 가져온 다음
		var pickArr2 = pick2.split("/"); // '/'기준으로 잘라서 각각 hidden타입으로 서블릿으로 넘긴다
		
		document.getElementsByName("land_number_address")[0].value = pickArr2[0]; //지번주소 input value(hidden)에 넣기
		document.getElementsByName("road_name_address")[0].value = pickArr2[1]; //도로명주소 input value(hidden)에 넣기
		
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
<h3>▶제보하기</h3>
 <form action="<%=request.getContextPath() %>/map.do" method="post" >
                    <input type="hidden" name="command" value="map_insert">
                    <table border="1" class="tableone">
                    <col width="150px">
                    	
                    	<tr>
                    		<th >장소</th>
                    		<td>&nbsp;<input style="border:0;" size="35" type="text" readonly="readonly" onclick="openMap();" value="▶목격한 장소를 지도에 찍어주세요 (클릭)"><br>
                    		-위도,경도 : &nbsp; <input type="text" id="pick_result" style="width:300px;">
							-주소 : &nbsp;<input type="text" id="pick_result_address" style="width:300px;"></td>
                    	</tr>
                    	<tr>
                    		<th>폰번호</th>
                    		<td><input type="text" size="100" name="phone_no" value="${dto.myphone }" onclick="check();"  style="border:0;"></td>
                    	</tr>
                    	<tr>
                    		<th>작성자</th>
                    		<td><input type="text" name="writer" value="${dto.myid }(${dto.myname })님" readonly="readonly"  style="border:0;"></td>
							
                    	</tr>
               			
                    	<tr>
                    		<th>글 작성</th>
                    		<td><textarea cols="100" rows="20" name="content"   style="border:0;" id="specialmark" placeholder="
                     이곳은 자세히 기억 안 나지만 목격을 하신분들이 적어주시는 곳입니다. 
                    작은 정보라도 반려동물의 주인에게는 큰 도움이 됩니다. 
                    자세하게 아시는 분은 [목격/보호] 게시판을 이용 부탁드립니다." 
                    		required="required" onclick="check();"></textarea></td>
                    	</tr>
                    	<tr>
                    		<td colspan="2" align="right"><input type="submit" value="작성완료">
                    		<input type="button" value="목록" onclick="window.history.back();"></td>
                    	</tr>
                    		
                    </table>
                    <br>
                    <pre style="color:red;">                  
                     이곳은 자세히 기억 안 나지만 목격을 하신분들이 적어주시는 곳입니다. 
                    작은 정보라도 반려동물의 주인에게는 큰 도움이 됩니다. 
                    자세하게 아시는 분은 [목격/보호] 게시판을 이용 부탁드립니다. </p>
                    </pre>
               
                    <b style="background-color:yellow;">※자세하게 (특징,위치,보호 등)아시는 분은 [목격/보호] 게시판을 이용 부탁드립니다.※</b>&nbsp;
                    <input type="button" value="👉🐶[목격/위치] 게시판으로 이동하기" onclick="" style="background-color:#D9E5FF; border-radius : 5px; " ></td>
                    
                    <input type="hidden" name="latitude_result" value="">
					<input type="hidden" name="longtitude_result" value="">
					<input type="hidden" name="land_number_address" value="">
					<input type="hidden" name="road_name_address" value="">
                    	
                    
				</form>
</body>
</section>

<%@include file="/footer.jsp" %>
</html>