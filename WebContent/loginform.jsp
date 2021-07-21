<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>    
<% response.setContentType("text/html;charset=UTF-8"); %> 

    
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
        margin-bottom: 45px;
    }
    .logtop{
        margin-top: 50px;
        padding: 10px;
        width: 1000px;
        height: 150px;
        text-align: center;
        display: grid;
        place-items: center;
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

    .semisub{
        width: 360px;
        height: 44px;
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

    .semijoin{
        width: 360px;
        height: 44px;
        font-size: 14px;
        background-color: #fff;
        border: 1px solid #15c654;
        border-radius: 5px;
        font-size: 18px;
        font-weight: 900;
        color: #19ce60;
        margin: 0;
    }

    .logmain{
        margin-bottom: 50px;
    }

    .logbot{
        text-align: center;
    }

    .logbot a{
        text-decoration: none;
        margin: 0 20px;
    }

    .logbot p{
        margin-top: 50px;
    }

	#toptoptop{
    	height: 200px;
    	margin-top: 10px;
    	margin-bottom: 30px;
    }
    
    

</style>
</head>
<body>
	<section> 
    <div class="logtop">
        <div id="toptoptop">
        		<a href="controller.do?command=index" class="imglink"><img alt="배너" src="./image/teamlogo.png"></a>
        </div>
    </div>
		<br>
		<br>
		<br>
        <hr width="800">

    <div class="logmain">
    <form action="controller.do" method="POST">
        <input type="hidden" name="command" value="login">
        <table>
            
			<tr>
				<td>
					<input type="text" name="myid" required="required" class="intext" placeholder="아이디를 입력해주세요">
				</td>	
			</tr>
			<tr>
				<td>
                    <input type="password" name="mypw" required="required" class="intext" placeholder="비밀번호를 입력해주세요">
                </td>
			</tr>
			<tr>
				<td class="lastsub">
                    <input type="submit" class="semisub" value="로그인">                
                </td>
			</tr>
            <tr>
				<td class="lastsub">
                    <input type="button" class="semijoin" value="회원가입" onclick="location.href='controller.do?command=joinform'">                
                </td>
			</tr>		
		</table>
    </form>
    </div>

        <hr width="800">
    <div class="logbot">
        <a href="controller.do?command=findidform">아이디찾기</a>
        <a href="controller.do?command=findpwform">비밀번호찾기</a>

        <p>Copyright @ KH교육원 디지털 콘텐츠 웹 융합 응용SW 개발자 양성과정(8)-세미프로젝트 3조. All rights reserved.</p>
    </div>    
    </section>
</body>
</html>