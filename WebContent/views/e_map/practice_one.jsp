<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.List, hi_b_community.map.dto.mapDto "%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./css/community_section.css"><!-- section css -->
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<style type="text/css">
.box1{border-radius: 20px; text-align: center; background: rgb(74, 204, 201); float:left; padding:7px;}
.box2{border-radius: 20px; text-align: center; background: rgb(248, 201, 36); float:left; padding:7px;}
.box3{border-radius: 20px; text-align: center; background: rgb(254, 192, 202); float:right; padding:7px;}
.box4{border-radius: 20px; text-align: center; background: rgb(101, 177, 254); float:right; padding:7px;}
</style>
</head>
<body>
<%
List<mapDto> map = (List<mapDto>)request.getAttribute("map");
%>
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
<b style="color:red;">▶아이디(이름)을 클릭하면 작성한 글을 볼 수 있습니다!</b><div class="box3" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_list'">리스트로 보기</div>
<div class="box4" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_place_click'">제보하기</div>
<div id="map" style="width:100%;height:1000px;"></div>
	<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=724b81fc00a64486288dc0698b9a4edd"></script>
	<script>
		var container = document.getElementById('map'); //지도를 표시할 div
		var options = {
			center: new kakao.maps.LatLng(37.269681, 127.033539), //지도의 중심좌표
			level: 11 //지도의 확대 레벨 
		};

		//지도 생성 
		var map = new kakao.maps.Map(container, options); //지도 생성
		
		
		
		
		/* !!!!여기서부터 컨트롤!!! */
		var mapTypeControl = new kakao.maps.MapTypeControl(); //일반지도와 스카이뷰로 전환할 수 있는 지도타입 컨트롤 생성
		
		// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
		// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
		map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);

		// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
		var zoomControl = new kakao.maps.ZoomControl();
		map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
		
		
<%
	for(int i=0; i<map.size(); i++){
		
		 map.get(i).getLatitude(); //위도
		 map.get(i).getLongtitude(); //경도
		
		
%>
		
		
		
		/* !!!여기서부터 마커 생성!!!!! */
		  
		
		
		//---- 보령 유기견 보호소
		var markerPosition  = new kakao.maps.LatLng(<%= map.get(i).getLatitude()%>, <%=map.get(i).getLongtitude()%>); 
		var marker = new kakao.maps.Marker({
			
		    position: markerPosition,
		    image: new kakao.maps.MarkerImage('https://image.flaticon.com/icons/png/512/26/26129.png', imageSize = new kakao.maps.Size(34, 39))
		});
		marker.setMap(map);
		
		
		var iwContent = '<div style="padding:1px;"><a href="<%=request.getContextPath()%>/map.do?command=map_detail&map_no=<%=map.get(i).getMap_no()%>"><%=map.get(i).getMap_writer()%></a><br><a href="https://map.kakao.com/link/map/이곳에서 목격되었습니다!,<%= map.get(i).getLatitude()%>, <%=map.get(i).getLongtitude()%>" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/목격장소,<%= map.get(i).getLatitude()%>, <%=map.get(i).getLongtitude()%>" style="color:blue" target="_blank">길찾기</a></div>',  // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
	    iwPosition = new kakao.maps.LatLng(<%= map.get(i).getLatitude()%>, <%=map.get(i).getLongtitude()%>);
		
		
		var infowindow = new kakao.maps.InfoWindow({
		    position : iwPosition, 
		    content : iwContent 
		});
		
		
		infowindow.open(map, marker); 

<%
	}
%>
		
		</script>
<div class="box1" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_search'">유기동물 관련 장소 검색하기</div>
	<div class="box2" onclick="location.href='<%= request.getContextPath()%>/map.do?command=map_guide'">전국 보호소 지도 보기</div>





	</div>
	
</body>
</section>

<%@include file="/footer.jsp" %>
	
</html>