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
</style>
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
                    <h1>회원정보 변경</h1>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <form action="controller.do" method="post">
                    <input type="hidden" name="command" value="infochfin">
					<input type="hidden" name="myno" value="<%=dto.getMyno() %>">
					<input type="hidden" name="myid" value="<%=dto.getMyid() %>">
					<input type="hidden" name="mypw" value="<%=dto.getMypw() %>">
                    <table>
                        <tr>
                            <th>이름</th>
                            <td><input type="text" name="myname" required="required" class="intext" placeholder="이름을 입력해주세요" value=<%= dto.getMyname() %>></td>	
                        </tr>
                        <tr>
                            <th>주소</th>
                            <td><input type="text" name="myaddr" required="required" class="intext" placeholder="주소를 입력해주세요" value=<%= dto.getMyaddr() %>></td>
                        </tr>
                        <tr>
							<th>전화번호</th>
							<td><input type="text" pattern="[0-9]+" name="myphone" required="required" class="intext" placeholder="-없이 숫자만 입력해주세요" value=<%= dto.getMyphone() %>></td>
						</tr>
						<tr>	
							<th>이메일(변경불가)</th>
							<td>
                    			<input type="text" name="myemail" readonly="readonly" class="intext" value=<%= dto.getMyemail() %>>
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