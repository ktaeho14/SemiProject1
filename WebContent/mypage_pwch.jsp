<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html;charset=UTF-8"); %> 
<%@ page import="join.dto.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	body{
        margin:0;
        padding:0;
    }
    
	section{
        display: grid;
        place-items: center;
    }

    a{
        text-decoration: none;
    }

    ul{
        list-style: none;
    }
	
	
	
	
	.content_layout{
        width: 1160px;
        padding-top: 40px;
        min-height: 700px;
    }

    #leftmenu{
        width: 20%;
        float: left;
        margin-right: 13px;
        padding-left: 8px;
    }

    #leftmenu ul{
        padding-left: 0;
        padding-right: 50px;
    }


    #leftmenu h2{
        margin-right: 50px;
    }

    #leftmenu ul li{
        border-bottom: 1px solid #2b2f3e;
    }



    #leftmenu ul li{
        padding-top: 10px;
        padding-bottom: 10px;
    }

    .left_a{
        font-size: 16px;
        font-weight: 600;
        color:black;
        padding-left: 10px;
        padding-bottom: 10px;

    }

    #rightcontent{
        width:  77%;
        display: inline-block;
        padding-left:10px;
    }

    #content_title{
        height: 130px;
    }

    #content_title h1,
    #content_title h4{
        padding-left: 30px;
    }


    #maincontent{
        margin-top: 20px;
        margin-bottom: 50px;
    }
    
    table{
        border: 0;
        border-spacing: 10px;
        margin-left: 100px;
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
    
    .pwck{
    	color: red;
    	font-size: 12px;
    }
    
    .pwck2{
    	color: red;
    	font-size: 12px;
    }
    
</style>
<script type="text/javascript" src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript">
	function finChk(){
		var pw1 = $("input[name=pw1]").val();
		var pw2	= $("input[name=pw2]").val();
		
		if(pw1.length < 8 || pw1.length > 20){	
			alert('비밀번호 조건을 확인해주세요.');
			return false;
		} else if(pw1.search(/\s/) != -1){
			alert("비밀번호는 공백 없이 입력해주세요.");
			return false;
		} else if( pw1 != pw2 ){
			alert("비밀번호를 서로 다릅니다. 다시 확인해주세요");			
			return false;
		} else {
			return ture;
		}
	}
	
	$(function(){
		$("input[name=pw1]").blur(function(){
		
			var mypw = $("input[name=pw1]").val();
		
			$.ajax({
				url:"controller.do?command=pwajax&mypw="+mypw,
				dataType:"json",
				success: function(obj){
					
					if (obj.pw != 0 ) {
						$(".pwck").html("비밀번호는 8~20자야 하며, 공백없이 입력해주세요.");
						$(".pwck").css("color", "red");
					} else {
						$(".pwck").html("사용가능한 비밀번호입니다.");
						$(".pwck").css("color", "#19ce60");
					}
				},
				error:function(){
				alert("실패");
				}
			});		
	});
	});
	
	$(function(){
		$("input[name=pw2]").blur(function(){
			var oripw = $("input[name=pw1]").val();
			var mypw = $("input[name=pw2]").val();
		
			$.ajax({
				url:"controller.do?command=pwajaxre&mypw="+mypw+"&oripw="+oripw,
				dataType:"json",
				success: function(obj){
					
					if (obj.pwck != 0 ) {
						$(".pwck2").html("비밀번호를 확인해주세요");
						$(".pwck2").css("color", "red");
					} else {
						$(".pwck2").html("확인되었습니다.");
						$(".pwck2").css("color", "#19ce60");
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
	<%
	AniDto dto = (AniDto)session.getAttribute("dto");
	%>
	<%@include file="/header.jsp" %>
	<section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>회원정보</h2>
            
                <ul>
                    <li><a class="left_a" href="controller.do?command=mypage"><%= dto.getMyname() %> 님</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h1>비밀번호 변경</h1>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <form action="controller.do" method="post" onsubmit="return finChk();">
                    <input type="hidden" name="command" value="pwchfin">
                    <input type="hidden" name="myno" value="<%=dto.getMyno() %>">
                    <table>
                        <tr>
                            <th>새로운 비밀번호</th>
                            <td>
                            	<input type="password" name="pw1" required="required" class="intext" placeholder="변경할 비밀번호를 입력해주세요"><br>
                            	<span class="pwck">비밀번호는 8~20자야 하며, 공백없이 입력해주세요.</span>
                            </td>	
                        </tr>
                        <tr>
                            <th>비밀번호 확인</th>
                            <td>
                            	<input type="password" name="pw2" required="required" class="intext" placeholder="변경할 비밀번호를 다시입력해주세요"><br>
                            	<span class="pwck2">다시 입력해주세요.</span>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" class="lastsub">
                                <input type="submit" class="semisub" value="변경하기">                
                            </td>
                        </tr>
                    </table>
                    </form>
                </div>
            </div>

		</div>
    </section>
    <%@include file="/footer.jsp" %>
</body>
</html>