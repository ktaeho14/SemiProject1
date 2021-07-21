<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8"><!-- map_place_detail에서 확인하기 버튼 눌렀을 때 나오는 창  -->
<title>Insert title here</title>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
</head>
<body>
<h3>▶목격된 장소입니다.</h3>


<button type="button" onclick="getCurrentPosBtn();" style="background-color:#D9E5FF; border-radius : 5px; ">현재 나의 위치 보기</button>

<div id="map" style="width:100%;height:500px;"></div>

<div id="roadview" style="width:100%;height:300px;"></div>



<div id="clickLatlng"></div> <!-- 위도, 경도가 나와서 opener.document~ 로 받아서 . 잘라서 db에 넣기 -->

<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=724b81fc00a64486288dc0698b9a4edd&libraries=services"></script>
<script type="text/javascript">

	/* ===================  */
	//부모창의 input(hidden)의 value값 가져오기 
	var lat = opener.document.getElementById("latitude").value; //위도
	var lon = opener.document.getElementById("longtitude").value; //경도
	
	/* ===================  */
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };

	var map = new kakao.maps.Map(mapContainer, mapOption);
	

	//컨트롤러 
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();
	
	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	
	
	
	

// 마커가 표시될 위치입니다 
var markerPosition  = new kakao.maps.LatLng(lat, lon); 

// 마커를 생성합니다
var marker = new kakao.maps.Marker({
    position: markerPosition,
    image: new kakao.maps.MarkerImage('https://image.flaticon.com/icons/png/512/4987/4987748.png', imageSize = new kakao.maps.Size(64, 69))
});

// 마커가 지도 위에 표시되도록 설정합니다
marker.setMap(map);

var iwContent = '<div style="padding:5px;">여기서 발견(목격)되었습니다! <br><a href="https://map.kakao.com/link/map/여기서 발견(목격)되었습니다!,'+lat+','+lon+'" style="color:blue" target="_blank">큰지도보기</a> <a href="https://map.kakao.com/link/to/여기서 발견(목격)되었습니다!,'+lat+','+lon+'" style="color:blue" target="_blank">길찾기</a></div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
    iwPosition = new kakao.maps.LatLng(lat,lon); //인포윈도우 표시 위치입니다

// 인포윈도우를 생성합니다
var infowindow = new kakao.maps.InfoWindow({
    position : iwPosition, 
    content : iwContent 
});
  
// 마커 위에 인포윈도우를 표시합니다. 두번째 파라미터인 marker를 넣어주지 않으면 지도 위에 표시됩니다
infowindow.open(map, marker); 


function locationLoadSuccess(pos){
	// 현재 위치 받아오기
	var currentPos = new kakao.maps.LatLng(pos.coords.latitude,pos.coords.longitude);

	// 지도 이동(기존 위치와 가깝다면 부드럽게 이동)
	map.panTo(currentPos);




	// 마커 생성
	var marker = new kakao.maps.Marker({
	    position: currentPos,//현재위치
	    image: new kakao.maps.MarkerImage('https://image.flaticon.com/icons/png/512/1321/1321437.png', imageSize = new kakao.maps.Size(64, 69))//마커 이미지 설정
	    
	});


	// 기존에 마커가 있다면 제거
	marker.setMap(null);
	marker.setMap(map);
	};

	function locationLoadError(pos){
	alert('위치 정보를 가져오는데 실패했습니다.');
	};

	//현재 나의 위치보기 버튼 클릭시
	function getCurrentPosBtn(){
	navigator.geolocation.getCurrentPosition(locationLoadSuccess,locationLoadError);
	}; 

	/* ===================  */
	//로드뷰 
	
	
	var roadviewContainer = document.getElementById('roadview'); //로드뷰를 표시할 div
	var roadview = new kakao.maps.Roadview(roadviewContainer); //로드뷰 객체
	var roadviewClient = new kakao.maps.RoadviewClient(); //좌표로부터 로드뷰 파노ID를 가져올 로드뷰 helper객체

	var position = new kakao.maps.LatLng(lat, lon);

	// 특정 위치의 좌표와 가까운 로드뷰의 panoId를 추출하여 로드뷰를 띄운다.
	roadviewClient.getNearestPanoId(position, 50, function(panoId) {
	    roadview.setPanoId(panoId, position); //panoId와 중심좌표를 통해 로드뷰 실행
	});

</script>




</body>
</html>