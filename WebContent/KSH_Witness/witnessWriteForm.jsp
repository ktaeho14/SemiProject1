<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ page import="join.dto.AniDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
	body{
        margin:0;
        padding:0;
    }

    header{
        min-width: 1160px;
        text-align: center;
    }

	a{
        text-decoration: none;
    }

    ul{
        list-style: none;
    }

    #toptoptop{
    	height: 200px;
    	margin-top: 10px;
    
    }

    .topmenu{
	    width: 100%;
	    margin-top: 43px;
	    border-bottom: 4px solid #19ce60;

    }

    .topmenu ul{
	    margin: 0;
	    padding: 0;
    }

    .topmenu > ul{
	    width: 1100px;
	    margin: 0 auto;
	    padding-top: 15px;
    }

    .topmenu > ul > li {
	    display: inline-block;
        width: 215px;
        padding: 0;
        text-align: center;
    }

    .topmenu > ul > li > a {
	    font-weight: 700;
	    padding: 15px 0;
	    color: #999;
	    display: inline-block;
        font-size: 18px;
    }

    .topmenu .largeb {
	    display: none;
	    position: absolute;
	    background: #19ce60;
	    width: 100%;
	    left: 0;
        height: 150px;
    }

    .largeb > ul {
        display: inline-block;
        width: 215px;
        text-align: left;
    }

    .largeb > ul > li{
        display: inline-block;
        width: 215px;
        text-align: left;
        margin-top: 15px;
        color: white;
    }

    .largeb > ul > li > a{
        font-size: 14px;
        color: white;
        font-weight: 600;
        width: 210px;
    }

    .ul1 > li > a{
        padding-left: 60px;
    }
    .ul2 > li > a{
        padding-left: 53px;
    }
    .ul3 > li > a{
        padding-left: 53px;
    }
    .ul4 > li > a{
        padding-left: 70px;
    }
    .ul5 > li > a{
        padding-left: 70px;
    }

    .topmenu ul:hover .largeb{
        display: block;
    }

	.sloginbox {
	    float: right;
        margin-top: 7.4px;
        margin-bottom: 7.4px;
        width: 300px;
    }

	.logBtn {
		float: right;
		margin-right: 30px
	}
	</style>
<link rel="stylesheet" href="../css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="../css/UI.css">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">

</head>


<body>


<%
	AniDto dto = (AniDto)session.getAttribute("dto");

%>	
	<header>
        
        
        <div class="main">
        	<div id="toptoptop">
        		<a href="#"class="imglink"><img alt="���" src="../image/teamlogo.png"></a>
        	</div>
        
        
        
            <nav class="topmenu">
                <ul>
                    <li>
                        <a href="<%=request.getContextPath() %>/lost.do?command=lostMain&page=1">ã���ּ���</a>
                        <div class="largeb">
                                <ul class="ul1">
                                    <li><a href="<%=request.getContextPath() %>/lost.do?command=lostMain&page=1">ã���ּ���</a></li>
                                    <li><a href="<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1">���� ���� ã�� �ı�</a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul2">
                                    <li><a href="witness.do?command=witnessmain">��ݰԽ���</a></li>
                                    <li><a href="sighting.do?command=sightingmain">��ȣ�Խ���</a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul3">
                                    <li><a href="PIJ_Adopt/AdoptGiude.jsp">�Ծ�ȳ�</a></li>
                                    <li><a href="AdoptController.do?command=adoptMain">�Ծ�Խ���</a></li>
                                    <li><a href="">�Ծ��ı�</a></li>
                                </ul>
                                <ul class="ul4">
                                    <li><a href="">����Ȱ����û</a></li>
                                    <li><a></a></li>
                                    <li><a></a></li>
                                </ul>
                                <ul class="ul5">
                                    <li><a href="">��������</a></li>
                                    <li><a href="">�Ҹ�����</a></li>
                                    <li><a href="">Q&A</a></li>
                                </ul>
                        </div>
                    </li>
                    <li>
                        <a href="sighting.do?command=sightingmain">���⵿������</a>
                    </li>
                    <li>
                        <a href="">���⵿���Ծ�</a>
                    </li>
                    <li>
                        <a href="">����Ȱ��</a>
                    </li>
                    <li>
                        <a href="">Ŀ�´�Ƽ</a>
                    </li>
                </ul>
            </nav>
        </div>

    </header>
	
    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>���⵿�� ����</h2>
            
                <ul>
                    <li><a class="left_a" href="../witness.do?command=witnessmain">��� �Խ���</a></li>
                    <li><a class="left_a" href="../sighting.do?command=sightingmain">��ȣ �Խ���</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h3>��� �Խ���</h3>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                <form action="../witness2.do" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="command" value="writeform" >
                    <table border="1">
                    	<tr>
                    		<th>����</th>
                    		<td><input type="text" name="color"></td>  
                    	</tr>
                    	
                    	<tr>
                    		<th>����</th>
                    		<td>
                    			<select name="kind">
                    				<option value="��">��</option>
                    				<option value="�����">�����</option>
                    			</select>
                    		</td>
                    	</tr>
                 		<tr>
                 			<th>City</th>
                 			<td>
                 				<select name="city">
                 					<option value="����Ư����">����Ư����</option>
                 					<option value="������">������</option>
                 					<option value="��û��">��û��</option>
                 					<option value="����">����</option>
                 					<option value="���">���</option>
                 					<option value="��⵵">��⵵</option>
                 					<option value="���ֵ�">���ֵ�</option>
                 				</select>
                 			</td>
                 		</tr>
                    	<tr>
                    		<th>�� �ּ�</th>
                    		<td><input type="text" name="place"></td>
                    	</tr>
                    	<tr>
                    		<th>Ư�� ����</th>
                    		<td><input type="text" name="specialmark"></td>
                    	</tr>
                    	<tr>
                    		<th>��ȭ��ȣ</th>
                    		<td><input type="text" name="phone_no"></td>
                    	</tr>
                    	<tr>
                    		<th>�ۼ���ID</th>
                    		<td><input type="text" name="writer" value="${dto.myid }" readonly="readonly"></td>
                    	</tr>
               			
                    	<tr>
                    		<th>�� �ۼ�</th>
                    		<td><textarea cols="60" rows="10" name="content" placeholder="ex) &#13;&#10; �߰� ���� �����ѹ�ȣ : 010-xxxx-xxxx &#13;&#10; ����� �̸�:  ��xx &#13;&#10; ���� : ���������� �տ���  07�� 02��  14:20 ���ҽ��ϴ�." 
                    		required="required"></textarea></td>
                    	</tr>
                    	<tr>
                    		<td><input type="file" name="witness_pic"></td>
                    	</tr>
                    	<tr>
                    		<td><input type="submit" value="�ۼ��Ϸ�"></td>
                    		<td><input type="button" value="���" onclick="location.href='../witness.do?command=witnessmain'"></td>
                    	</tr>
                    		
                    </table>
                    
				</form>
                </div>
            </div>
</div>

    </section>

    <footer>
        <div id="fin">
            <p>Copyright @ KH������ ������ ������ �� ���� ����SW ������ �缺����(8)-����������Ʈ 3��. All rights reserved.</p> 
        </div>
    </footer>
	
</body>
</html>