<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html charset=UTF-8");
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import ="java.text.SimpleDateFormat" %>
<%@ page import ="pij.adopt.dao.AdoptDao" %>
<%@ page import ="pij.adopt.dto.AdoptDto" %>
<%@ page import ="pij.adopt.dao.AdoptDaoImpl" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Connection" %>
<%@ page import ="pij.adopt.biz.AdoptBizImpl" %>
<%@ page import ="pij.adopt.biz.AdoptBiz" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<style type="text/css">
.adopt_table{
	text-align : center;
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/witness.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<script>
function checkLogin() {
    var id = '${dto.myid}'; // 수정 ''처리
    
    // 수정 ''공백 비교
    if (id == '') {
        alert("로그인 후 글쓰기가 가능합니다.");
        return false;
    } else {
        location.href = 'AdoptController.do?command=write';
    }
}
    
    
    
</script>    






</head>
<body>
		<jsp:include page="../header.jsp"/>

    <section>

        <div class="content_layout" style="padding-bottom:200px">
            
            <div id="leftmenu">
                
            
                
            
                <ul>
                    
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h1>입양공고</h1>
                    <hr width="890">
                </div>
    
                <div id="maincontent">
                  
       <section>
    	<article class="sighting_main">
    		<div class="wrapper">
    			<div class="bord1">
            		<c:choose>
              				<c:when test="${empty adList }">
              			<p>----등록된 글이 없습니다----</p>
              		</c:when>
              		<c:otherwise>
              		
              			<c:forEach var="adList" items="${adList }">
              				<table border="1" class="adopt_table">
              					<tr>
              						<th>글 번호</th>
              						<th>작성자</th>
              						<th>글 제목</th>
              						<th>입양동물 종류</th>
              						<th>입양장소</th>
              					</tr>
              					<tr>
              						<td style="width:70px; height:40px;">${adList.adopt_no }</td>
              						<td style="width:70px; height:40px;">${adList.adopt_writer }</td>
              						<td style="width:200px; height:40px;"><a href="AdoptController.do?command=adoptSelectOne&no=${adList.adopt_no }">${adList.adopt_title }</a></td>
              						<td style="width:120px; height:40px;">${adList.adopt_kind }</td>
              						<td style="width:300px; height:40px;">${adList.adopt_place }</td>
              					</tr>
              				</table>
              			
              			</c:forEach>
             		
             		</c:otherwise>
             	</c:choose>

              
           			</div>
           			<button type="button" class="btn btn-primary" onclick="checkLogin();" style="margin-top:400px">글 쓰기</button>
    			</div>
    			
    		</div>
    		
    	</article>
    </section>
			
                </div>
            </div>


    </section>
 <%
  	SimpleDateFormat df = new SimpleDateFormat("YY-MM-DD HH:mm");

            	int pageSize = 10; //한 페이지에 출력할 레코드 수
            	
            	//페이지 링크를 클릭한 번호 / 현재 페이지
            	String pageNum = request.getParameter("pageNum");
            	if(pageNum==null){ //클릭한게 없으면 1번 페이지
            		pageNum = "1";
            	}
            	
            	//연산을 하기 위한 pageNum 형변환 / 현재 페이지
            	int currentPage = Integer.parseInt(pageNum);
            	
            	//해당 페이지에서 시작할 레코드 / 마지막 레코드
            	int startRow = (currentPage -1) * pageSize + 1;
            	int endRow = currentPage * pageSize;
            	
            	int count = 0;
            	AdoptDao dao = new AdoptDaoImpl();
            	count = dao.getCount();
            	
            	
            	List<AdoptDto> list = null;
            	if(count>0){
            		//getList()메서드 호출 /해당 레코드 반환
            		AdoptBiz biz = new AdoptBizImpl();
            		list=biz.selectAll(startRow, endRow);
            	}
  %>
<span class="pagination" style="margin-left:50%">
<%
	//페이징처리 
	if(count >0){
		//총 페이지 수
		int pageCount = count/ pageSize + (count%pageSize == 0 ? 0 : 1);
		//한 페이지에 보여줄 페이지 블럭(링크) 수
		int pageBlock = 10;
		
		int startPage= ((currentPage-1)/pageBlock)*pageBlock+1;
		int endPage = startPage + pageBlock -1;
		
		//마지막 페이지가 총페이지 수 보다 크면 endPage를 pageCount로 할당
		if(endPage > pageCount){
			endPage = pageCount;
		}
		if(startPage > pageBlock){
			%>
			<a href="AdoptController.do?command=adoptMain&pageNum=<%=startPage-10 %>">[이전]</a>
			<%
		}
		for(int i =startPage; i<=endPage; i++){
			if(i== currentPage){
				%>
					[<%=i %>]
				<%
					}else{
				%>		
					<a href="AdoptController.do?command=adoptMain&pageNum=<%=i %>">
					[<%=i %>]</a>
					<%
		}
	}
		if(endPage< pageCount){
			
		%>
			<a href="AdoptController.do?command=adoptMain&pageNum=<%=startPage + 10 %>">[다음]</a>
		<%
		}
	}
		%>
</span>






	







    <footer>
        <div id="fin">
            <p>Copyright @ KH교육원 디지털 콘텐츠 웹 융합 응용SW 개발자 양성과정(8)-세미프로젝트 3조. All rights reserved.</p> 
        </div>
    </footer>
    
</body>
</html>