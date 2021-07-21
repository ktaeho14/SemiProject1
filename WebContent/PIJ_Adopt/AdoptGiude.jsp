<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 
	<link rel="stylesheet" href="../css/sighting_main.css"> <!-- section css -->
	<link rel="stylesheet" href="../css/UI.css">
	<link rel="stylesheet" href="../css/witness.css">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
<style type="text/css">
.center { width:100%; padding-left:35%; }
.AdouptGiude { width:15%; }
.mainText { font-size:35px; font-weight: bold; color: black; text-shadow: 1px 1px 1.2px midnightblue; }
.giudeSmallTitle{ width:130px; background-color:#f7e600; padding:6%; border-radius: 30px; }
.aaa { text-align:center; marign-left:1%; }
.bbb { display: inline-block; }

</style>

</head>
<body>
<jsp:include page="../header.jsp"/>
<section>
	<div class="content_layout" style="padding-bottom:100px; padding-top:60px;">
		<div class="center">
			<img class="AdouptGiude" src="../image/AdouptGiude.jpg">
			<span class="mainText">입 양 절 차</span><br/>
		</div>
		<p>
		<div style="padding-left:3%; text-align:center;">
			단지 귀엽다는 이유로, 외롭다는 이유로 보호 중인 아이들의 입양을 허가하지 않습니다.<br/>
			각 보호소에서 꼼꼼한 상담 및 신원 확인을 통해 입양을 진행하고 있습니다.
		</div>
		<br/><br/>
		
		<div class="aaa">
		<div class="bbb">
			<div class="giudeSmallTitle">입양자격</div><br/>
		</div>
			<div>
				신원 가능한 성인<br/>
				반려동물을 사랑하는 가정<br/>
				입양 후 1년간 연락 가능한 가정<br/>
			</div>
		</div>
		
		<br/><br/>
		
		<div class="aaa">
		<div class="bbb">
			<div class="giudeSmallTitle">입양안내</div><br/>
		</div>
			<div>
				하루에도 여러마리의 아이들이 보호소에서 새로운 가정으로 입양 보내집니다.<br/>
				현황 최선화에 최선을 다하지만, 온라인에 올라가지 않은 아이들이 더욱 많이 있습니다.<br/>
				보호소는 모든 아이들에게 공정한 입양 기회를 주기 위해 특정한 아이들에 대한 상담은 진행하지 않습니다.<br/>
				정확한 부분은 각 보호소에 문의 바랍니다.<br/>
			</div>
		</div>
		
		<br/><br/>
		
		<div class="aaa">
		<div class="bbb">
			<div class="giudeSmallTitle">책임비 안내</div><br/>
		</div>
			<div>
				아이들의 입양 비용은 보통 무료 분양(용품 지원)이나 소정의 책임비용이 발생할 수 있습니다.<br/>
				책임비의 목적은 재유기 파양, 식용도용, 수익목적 재분양 등을 방지하기 위함입니다.<br/>
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