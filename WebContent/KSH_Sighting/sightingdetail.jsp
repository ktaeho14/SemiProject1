<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sightingDetail.css">
<link rel="stylesheet" href="./css/sighting.css">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">


<!-- Bootstrap CSS -->
    
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    
    
</head>
<body>
	<jsp:include page="../logtop.jsp"/>
	<jsp:include page="../header.jsp"/>

    <section>

        <div class="content_layout">
            
            <div id="leftmenu">
                
            
               
            
                <ul>
                    <li><a class="left_a" href="witness.do?command=witnessmain">��� �Խ���</a></li>
                    <li><a class="left_a" href="sighting.do?command=sightingmain">��ȣ �Խ���</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title" style="margin-bottom:50px;">
                    <h3>��ȣ �Խ���</h3>
                    <h4>���� ���⵿�� ��ȣ�ҿ� ��ȣ���� ���̵��� �Ұ����ִ� �������Դϴ�</h4>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                <div class="sighting_explanation">
            		<p>
            			<b class="sighting_explanation_content">
          					 ����ڸ� �Ǵ� ����� : ${res.chargenm }</b></p><br>
          			<p>	
          				<b class="sighting_explanation_content">
          					�߰���ġ : ${res.happenplace }</b></p>			
            		
            	</div>
                  <table class="table" border="1" style="margin-top:30px;">
  <thead>
    <tr>
      <th colspan="4" style="background-color:pink; text-align:center; font-size: 20px; color:white">����� �� ����</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"  style="background-color:#fcf1f4; font-size:20px;">����</th>
      <td>${res.age }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">��ȣ�� </th>
      <td>${res.carenm }</td>
    </tr>
    <tr>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">����</th>
      <td>${res.colorcd }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">��ȣ�� ��ġ</th>
      <td>${res.careaddr }</td>
    </tr>
     <tr>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">����</th>
      <td>${res. sexcd }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">��ȣ�� ��ȭ��ȣ</th>
      <td>${res.caretel }</td>
    </tr>
     <tr>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">Ư�̻���</th>
      <td>${res.specialmark }</td>
      <th scope="row" style="background-color:#fcf1f4; font-size:20px;">ü��</th>
      <td>${res.weight }</td>
    </tr>
   	
  </tbody>
  
  
</table>
	<h2>����� ����</h2>
	<div class="detail_img">
	<img src="http://${res.popfile }">
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