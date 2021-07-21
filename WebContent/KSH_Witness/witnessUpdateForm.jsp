<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import="ksh.sighting.dto.SightingDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ksh.sighting.dao.SightingDao" %>
<%@ page import="join.dto.AniDto" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sightingDetail.css">


 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
 <link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</head>
<body>
	<%
	AniDto res = (AniDto)session.getAttribute("dto");

%>
	
	<jsp:include page="../header.jsp"/>
	
    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
                <h2>���⵿�� ����</h2>
            
                <ul>
                    <li><a class="left_a" href="witness.do?command=witnessmain">��� �Խ���</a></li>
                    <li><a class="left_a" href="sighting.do?command=sightingmain">��ȣ �Խ���</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h3>��� �Խ���</h3>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <form action="witness3.do" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="no" value="${dto.no }">
                    <input type="hidden" name="command" value="updateform" >
                    <table border="1">
                    	<tr>
                    	<th colspan="2" style="background-color:pink; text-align:center; font-size:25px;">
                    		���⵿�� ���� ����
                    	</th>
                    	</tr>
                    	
                    	<tr>
                    		<th>����</th>
                    		<td><input type="text" name="color" value="${dto.color }"></td>  
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
                    		<td><input type="text" name="place" value="${dto.place }"></td>
                    	</tr>
                    	<tr>
                    		<th>Ư�� ����</th>
                    		<td><input type="text" name="specialmark" value="${dto.specialmark }"></td>
                    	</tr>
                    	<tr>
                    		<th>��ȭ��ȣ</th>
                    		<td><input type="text" name="phone_no" value="${dto.phone_no }"></td>
                    	</tr>
                    	<tr>
                    		<th>�ۼ���</th>
                    		<td><input type="text" name="writer" value="${dto.writer }" readonly="readonly"></td>
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
                    		<td><input type="button" value="���" onclick="location.href='witness.do?command=witnessmain'"></td>
                    	</tr>
                    	
                    		
                    </table>
                    
				</form>
                    

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