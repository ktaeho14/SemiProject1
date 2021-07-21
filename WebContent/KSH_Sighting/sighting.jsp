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
<%@ page import="ksh.sighting.dto.SightingDto" %>
<%@ page import="java.util.List" %>
<%@ page import="ksh.sighting.dao.SightingDao" %>



<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/sighting.css">
<link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
<script>
    
    
    
    
</script>    






</head>
<body>
	<jsp:include page="../logtop.jsp"/>	
	<jsp:include page="../header.jsp"/>

    <section>

        <div class="content_layout" style="padding-bottom:200px">
            
            <div id="leftmenu">
                
            
                
            
                <ul>
                    <li><a class="left_a" href="witness.do?command=witnessmain">목격 게시판</a></li>
                    <li><a class="left_a" href="sighting.do?command=sightingmain">보호 게시판</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h3>보호 게시판</h3>
                    <h4>전국 유기동물 보호소에 보호중인 아이들을 소개해주는 페이지입니다</h4>
                    
                    <hr width="890">
                    
                    
                </div>
    
                <div id="maincontent">
                  
       <section>
    	<article class="sighting_main">
    		<div class="wrapper">
    			<div class="bord1">
            <div class="sighting_explanation" style="margin-top:50px;">
            	<p style="margin-top:50px; margin-bottom: 50px;">
            		<b class="sighting_explanation_content">
           해당 게시판은 보호소에 보호중인 유기동물을 확인할 수 있는 게시판입니다.<br>
        	
        			-주인님을 찾고있어요-
            		</b>			
            	</p>
            </div>
    		
    		
              <div class="boc1">
              	<c:choose>
              		<c:when test="${empty list }">
              			<p>----등록된 글이 없습니다----</p>
              		</c:when>
              		<c:otherwise>
              			<c:forEach var="list" items="${list}">
              				 <ul>
                 				 <li>
                    				<a href="sighting.do?command=selectOne&animal_no=${list.animal_no }">
                      				<span>
                        				<img class="sighting_main_img" src="http://${list.popfile }">
                      				</span>
                    				</a>
                 				 </li>
                    
                  				 
                  				<li>
                    				<span><b>나이 :</b> ${list.age }</span>
                  				</li>
                  				<li>
                  					<span><b>보호소 위치:</b> ${list.careaddr }</span>
                  				</li>
                  				<li>
                  					<span><b>보호소 이름 :</b> ${list.carenm }</span>
                  				</li>
                  				<li>
                  					<span><b>보호소 전화번호 :</b> ${list.caretel }</span>
                  				</li>
                  				<li>
                  					<span><b>보호견 색상:</b> ${list.colorcd }</span>
                  				</li>
                  				<li>
                  					<span><b>성별 :</b> ${list.sexcd }</span>
                  				</li>
                			</ul>
              			</c:forEach>
              		</c:otherwise>
              	</c:choose>
              
             			 </div>

              
           			</div>
           			
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
	SightingDao dao = new SightingDao();
	count = dao.getCount();
	
	
	List<SightingDto> list = null;
	if(count>0){
		//getList()메서드 호출 /해당 레코드 반환
		list = dao.selectAll(startRow, endRow);
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
			<a href="sighting.do?command=sightingmain&pageNum=<%=startPage-10 %>">[이전]</a>
			<%
		}
		for(int i =startPage; i<=endPage; i++){
			if(i== currentPage){
				%>
					[<%=i %>]
				<%
					}else{
				%>		
					<a href="sighting.do?command=sightingmain&pageNum=<%=i %>">
					[<%=i %>]</a>
					<%
		}
	}
		if(endPage< pageCount){
			
		%>
			<a href="sighting.do?command=sightingmain&pageNum=<%=startPage + 10 %>">[다음]</a>
		<%
		}
	}
		%>
</span>
		
	







  
    
</body>
</html>