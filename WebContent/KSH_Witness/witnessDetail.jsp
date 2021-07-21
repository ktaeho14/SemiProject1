<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import="ksh.sighting.dto.SightingDto" %>
<%@ page import="join.dto.AniDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ksh.sighting.dao.SightingDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
body:before{
  content:'';
  height:100%;
  display:inline-block;
  vertical-align:middle;
}
button{
  background:#1AAB8A;
  color:#fff;
  border:none;
  position:relative;
  height:60px;
  font-size:1.6em;
  padding:0 2em;
  cursor:pointer;
  transition:800ms ease all;
  outline:none;
}
button:hover{
  background:#fff;
  color:#1AAB8A;
}
button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: #1AAB8A;
  transition:400ms ease all;
}
button:after{
  right:inherit;
  top:inherit;
  left:0;
  bottom:0;
}
button:hover:before,button:hover:after{
  width:100%;
  transition:800ms ease all;
}
</style>
<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sightingDetail.css">


 <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<script src="https://code.jquery.com/jquery-3.2.0.min.js">
</script>

<script type="text/javascript">
function checkLogin2() {
    var id = '${dto.myid}'; // ���� ''ó��
    var writer = '${selectone.writer}'
    // ���� ''���� ��
    if (id != writer) {
        alert("�ۼ��ڰ� �ƴմϴ�.");
        return false;
    } else {
        location.href = 'witness.do?command=update&no=${selectone.no}';
    }
}

function checkLogin1() {
    var id = '${dto.myid}'; // ���� ''ó��
    var writer = '${selectone.writer}'
    // ���� ''���� ��
    if (id != writer) {
        alert("�ۼ��ڰ� �ƴմϴ�.");
        return false;
    } else {
        location.href = 'witness.do?command=delete&no=${selectone.no}';
    }
}


</script>

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
                <div id="content_title">
                    <h3>��� �Խ���</h3>
                    
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                    <table class="table" border="1">
  <thead>
    <tr>
      <th colspan="4" style="background-color:pink; text-align:center; font-size: 20px; color:white" >����� �� ����</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row">NO</th>
      <td>${selectone.no }</td>
      <th scope="row">���� </th>
      <td>${selectone.color }</td>
    </tr>
    <tr>
      <th scope="row">������</th>
      <td colspan="2">${selectone.city } &nbsp; ${selectone.place }</td>
      <th></th>
      
    </tr>
   	<tr>
      <th scope="row">Ư�̻���</th>
      <td>${selectone.specialmark }</td>
      <th scope="row">�� �ۼ� ��¥</th>
      <td>${selectone.witness_date }</td>
    </tr>
   	<tr>
   		<th scope="row">��ȭ��ȣ</th>
   		<td>${selectone.phone_no }</td>
   		<th scope="row">����</th>
      	<td>${selectone.kind }</td>
   	</tr>
   
   	<tr>
   	  <th scope="row">Ư�̻���</th>
      <td>${selectone.specialmark }</td>
      <th scope="row">�ۼ���</th>
      <td>${selectone.writer }</td>
   	</tr>
   	
    <tr>
    	<th scope="row">����</th>
    	<td colspan="3"><textarea cols="80" rows="10">${selectone.content }</textarea></td>
    </tr>
   	
  </tbody>
  
  
</table>
	<h2>����� ����</h2>
	<div class="detail_img">
	<img src="image/${selectone.witness_pic }">
    </div>
   

	   
    
 <span class="detailButton">
 	<button class="btn-1" onclick="checkLogin2()" style="text-align:center; font-size:12px;">�� ����</button>
 	<button class="btn-1" onclick="checkLogin1()" style="text-align:center; font-size:12px;">�� ����</button>
 	
 </span>

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