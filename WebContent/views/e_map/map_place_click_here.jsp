<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
</head>
<body>

<h3>▶발견한 장소를 클릭해주세요!</h3>
<button type="button" onclick="getCurrentPosBtn();" style="background-color:#D9E5FF; border-radius : 5px; ">현재 나의 위치 보기</button>
<div id="map" style="width:100%;height:500px;"></div>



<div id="clickLatlng"></div> <!-- 위도, 경도가 나와서 opener.document~ 로 받아서 . 잘라서 db에 넣기 -->
<div id="resultDiv"></div> <!-- 지번주소, 도로명주소  잘라서 db에 넣기 -->

<script type="text/javascript" src="http://dapi.kakao.com/v2/maps/sdk.js?appkey=724b81fc00a64486288dc0698b9a4edd&libraries=services"></script>
<script type="text/javascript">


var mapContainer = document.getElementById('map'), // 지도를 표시할 div
	mapOption = {
	    center: new kakao.maps.LatLng(37.56646, 126.98121), // 지도의 중심좌표
	    level: 3, // 지도의 확대 레벨
	    mapTypeId : kakao.maps.MapTypeId.ROADMAP // 지도종류
	};

//지도를 생성한다
var map = new kakao.maps.Map(mapContainer, mapOption);


/*===클릭하면 지도에 마커 표시하는 코드===*/

// 지도를 클릭한 위치에 표출할 마커입니다
var marker = new kakao.maps.Marker({ 
	
	//마커 이미지 설정 
	image: new kakao.maps.MarkerImage('https://image.flaticon.com/icons/png/512/4987/4987748.png', imageSize = new kakao.maps.Size(64, 69))
	
}); 

// 지도에 마커를 표시합니다
marker.setMap(map);

// 지도에 클릭 이벤트를 등록합니다
// 지도를 클릭하면 마지막 파라미터로 넘어온 함수를 호출합니다
kakao.maps.event.addListener(map, 'click', function(mouseEvent) {        
    
    // 클릭한 위도, 경도 정보를 가져옵니다 
    var latlng = mouseEvent.latLng; 
    
    // 마커 위치를 클릭한 위치로 옮깁니다
    marker.setPosition(latlng);
    
    
    //지번주소, 번지수주소
    var lat = latlng.getLat(); //위도
	var lng = latlng.getLng(); //경도
	getAddr(lat,lng);
	
	//위도, 경도를 이용해 지번 도로명 주소를 나타내주는 함수 
	function getAddr(lat,lng){
		var geocoder = new kakao.maps.services.Geocoder(); 

		var coord = new kakao.maps.LatLng(lat, lng);
		var callback = function(result, status) {
	        if (status === kakao.maps.services.Status.OK) {
	            console.log(result);
	            
	            //지번 도로명 주소를 div안에 표시 
	           document.getElementById("resultDiv").innerText= result[0].address['address_name'] + "/"+result[0].road_address['address_name'];

	        }
	    };

	    geocoder.coord2Address(coord.getLng(), coord.getLat(), callback);
	
	}
    
    /*
    var message = '발견한 장소의 위도 : ' + latlng.getLat() + ', ';
    message += '경도 : ' + latlng.getLng() + ' 입니다.';
    */
    
    
    var message = latlng.getLat()+","+latlng.getLng(); 
    
    var resultDiv = document.getElementById('clickLatlng'); 
    resultDiv.innerHTML = message;
    
    
    
    
});




/*==============================*/
 
function locationLoadSuccess(pos){
	// 현재 위치 받아오기
	var currentPos = new kakao.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
	
	// 지도 이동(기존 위치와 가깝다면 부드럽게 이동)
	map.panTo(currentPos);
	
	
	
	
	//'https://image.flaticon.com/icons/png/512/3754/3754739.png'
	
	// 마커 생성
	var marker = new kakao.maps.Marker({
	    position: currentPos, //현재위치 
	    image: new kakao.maps.MarkerImage('https://image.flaticon.com/icons/png/512/1321/1321437.png', imageSize = new kakao.maps.Size(64, 69)) //마커 이미지 설정 
	    
	});
	
	
	// 기존에 마커가 있다면 제거
	marker.setMap(null);
	marker.setMap(map);
};

function locationLoadError(pos){
	alert('위치 정보를 가져오는데 실패했습니다.');
};

//위치 가져오기 버튼 클릭시
function getCurrentPosBtn(){
	navigator.geolocation.getCurrentPosition(locationLoadSuccess,locationLoadError);
}; 





//확인 버튼 클릭시 
function confirm(){
	
	
	
	var result = document.getElementById("clickLatlng").innerText; //div에 나타나는 위도경도값 가져옴 
	//console.log(result);
	
	var result_address = document.getElementById("resultDiv").innerText; //div에 나타나는 지번, 도로명주소값 가져옴 
	
	opener.document.getElementById("pick_result").value = result; //원래(부모) 창의 인풋에 result 넘겨줌 
	opener.document.getElementById("pick_result_address").value = result_address; //원래(부모) 창의 인풋에 result_address 넘겨줌 
	
	//opener.document.getElementById("specialmark").focus();//부모창의 특이사항에 focus
	close();
}

//취소 버튼 클릭시 
function cancle(){
	opener.document.getElementById("specialmark").focus(); //부모창의 특이사항에 focus
	
	close();
	
}


</script>

<input type="button" value="확인" onclick="confirm();" style="background-color:#D9E5FF; border-radius : 5px; ">
<input type="button" value="취소" onclick="cancle();" style="background-color:#D9E5FF; border-radius : 5px; ">




</body>
</html>