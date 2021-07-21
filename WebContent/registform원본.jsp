<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html;charset=UTF-8"); %> 

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    section{
        display: grid;
        place-items: center;
    }
    
    .bannerbox{
        height: 100px;
        width: 100px;
        background-color: coral;
    }
    .regtop{
        margin-top: 50px;
        padding: 10px;
        width: 1000px;
        height: 150px;
        text-align: center;
        display: grid;
        place-items: center;
    }
    h1{
        font-size: 30px;
        margin: 0;
    }

    table{
        border: 0;
        border-spacing: 10px;
    }

    table tr th{
        width: 140px;
        padding: 20px 0 0 20px;
        font-weight: 700;
        font-size: 14px;
        color: #333;
        line-height: 20px;
        vertical-align: top;
        text-align: left;
    }
    table tr td{
        padding: 10px 0;
        border-top: 0;
        font-size: 14px;
        vertical-align: top;
        text-align: left;
    }

    .intext{
        width: 330px;
        height: 44px;
        padding: 0 14px;
        border: 1px solid #ccc;
        font-size: 14px;
        color: #333;
        line-height: 20px;
        border-radius: 3px;
        background: #fff;
        outline: none;
        vertical-align: top;
    }

    .semibox{
        width: 120px;
        height: 44px;
        padding: 0 14px;
        border: 1px solid #ccc;
        font-size: 14px;
        color: #19ce60;
        line-height: 20px;
        border-radius: 3px;
        background: #fff;
        outline: none;
        vertical-align: top;
        font-weight:700;
        margin-left: 10px;
    }

    .semisub{
        width: 240px;
        height: 56px;
        font-size: 14px;
        background-color: #19ce60;
        border: 1px solid #15c654;
        border-radius: 5px;
        font-size: 18px;
        font-weight: 900;
        color: #fff;
        margin-top: 20px;
    }

    .lastsub{
        text-align: center;
    }

    .logbot{
        text-align: center;
    }

    .logbot p{
        margin-top: 50px;
    }
    
    .pwck{
    	color: red;
    	font-size: 12px;
    }
    
    .idck{
    	color: red;
    	font-size: 12px;
    }
    
    #toptoptop{
    	height: 200px;
    	margin-top: 10px;
    	margin-bottom: 30px;
    }
    
    .regmain h1{
    	text-align: center;
    }
</style>

<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">

	function idChk(){
		var doc = document.getElementsByName("myid")[0];
		var num = /[0-0]/;
		var eng = /[a-z]/;
		
		if(doc.value.trim()==""||doc.value==null){
			alert("아이디를 입력해주세요");			
		} else if (doc.value.length < 5 || doc.value.length > 12){
			alert("아이디형식을 확인해주세요");	
		} else if (num.test(doc.value) || eng.test(doc.value)){
			var target = "controller.do?command=idchk&id="+doc.value.trim();
			
			
			var popupX = (window.screen.width/2) - (400/2);
			var popupY= (window.screen.height/2) - (300/2);
			open(target,"",'status=no, height=300, width=400, left='+ popupX + ', top='+ popupY);
		} else {			
			alert("아이디형식을 확인해주세요");
		}
	}
	
	
	
	function emailChk(){
		var doc = document.getElementsByName("myemail")[0];
		if(doc.value.trim()==""||doc.value==null){
			alert("이메일을 입력해주세요");			
		}else{
			var target = "controller.do?command=emailchk&email="+doc.value.trim();
			
			
			var popupX = (window.screen.width/2) - (400/2);
			var popupY= (window.screen.height/2) - (300/2);
			open(target,"",'status=no, height=300, width=400, left='+ popupX + ', top='+ popupY);
		}
	}

	function finChk(){
		var pw = $("input[name=mypw]").val();
		
		
		
		if($("input[name=myid]").attr("title") == "n"){
			alert("아이디 중복확인을 해주세요")			
			return false;
		} else if($("input[name=myemail]").attr("title") == "n"){
			alert("이메일 중복확인을 해주세요");			
			return false;
		} else if(pw.length < 8 || pw.length > 20){	
			alert('비밀번호 조건을 확인해주세요.');
			return false;
		} else if(pw.search(/\s/) != -1){
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		} else {
			return ture;
		}
	}
	
			
	$(document).ready(function(){
		$("input[name=myid]").change(function(){
			$("input[name=myid]").attr("title","n");
		});
	});
	$(function(){
		$("input[name=myid]").blur(function(){
		
			var myid = $("input[name=myid]").val();
		
			$.ajax({
				url:"controller.do?command=idajax&myid="+myid,
				dataType:"json",
				success: function(obj){
					
					if (obj.id != null) {
						/*아이디가 중복됨*/
						$(".idck").html("사용중인 아이디입니다");
						$(".idck").css("color", "red");
					} else {
						
					}
				},
				error:function(){
				alert("실패");
				}
			});
		
		
	});
	});
	
	
	
	
</script>




</head>
<body>

    <section> 
    <div class="regtop">
        <div id="toptoptop">
        		<a href="controller.do?command=index" class="imglink"><img alt="배너" src="./image/teamlogo.png"></a>
        </div>
    </div>
		<br>
		<br>
		<br>
        <hr width="800">

    <div class="regmain">
    		<h1>회원가입</h1><br>
    <form action="controller.do" method="POST" id="login" onsubmit="return finChk();">
        <input type="hidden" name="command" value="joinuser">
        <table>
            
			<tr>
				<th>아이디</th>
				<td>
					<input type="text" name="myid" title="n" required="required" class="intext" placeholder="6자 이상의 영문 혹은 영문과 숫자를 조합">
					<input type="button" value="중복확인" class="semibox" onclick="idChk();"><br>
					<span class="idck">아이디는 5~12자야 하며, 숫자/영문소문자로만 이루어집니다.</span>
				</td>	
			</tr>
			<tr>
				<th>비밀번호</th>
				<td>
					<input type="password" name="mypw" required="required" class="intext" placeholder="비밀번호를 입력해주세요"><br>
					<span class="pwck">비밀번호는 8~20자야 하며, 공백없이 입력해주세요.</span>
				</td>
			</tr>
            <tr>
				<th>이름</th>
				<td><input type="text" name="myname" required="required" class="intext" placeholder="이름을 입력해주세요"></td>
			</tr>
			<tr>
				<th>주소</th>
				<td><input type="text" name="myaddr" required="required" class="intext" placeholder="상세 주소를 입력해주세요"></td>
			</tr>
			<tr>
				<th>전화번호</th>
				<td><input type="text" pattern="[0-9]+" name="myphone" required="required" class="intext" placeholder="-없이 숫자만 입력해주세요"></td>
			</tr>
			<tr>
				<th>이메일</th>
				<td>
                    <input type="text" name="myemail" title="n" required="required" class="intext" placeholder="예:asdf@naver.com">
                    <input type="button" value="중복확인" class="semibox" onclick="emailChk();">
                </td>

			</tr>
			<tr>
				<td colspan="2" class="lastsub">
                    <input type="submit" class="semisub" id="joins" value="가입하기" >                
                </td>
			</tr>
		
		</table>
    </form>
    </div>
    <br><br>
    <hr width="800">

    <div class="logbot">
        <p>Copyright @ KH교육원 디지털 콘텐츠 웹 융합 응용SW 개발자 양성과정(8)-세미프로젝트 3조. All rights reserved.</p>
    </div>
	</section>




</body>
</html>