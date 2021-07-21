<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/html; charset=UTF-8");
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
<style type="text/css">
<style type="text/css">
body:before{
  content:'';
  height:100%;
  display:inline-block;
  vertical-align:middle;
}
button{
  background:gray;
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
  color:gray;
}
button:before,button:after{
  content:'';
  position:absolute;
  top:0;
  right:0;
  height:2px;
  width:0;
  background: black;;
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

.button {
  width: 140px;
  height: 45px;
  font-family: 'Roboto', sans-serif;
  font-size: 11px;
  text-transform: uppercase;
  letter-spacing: 2.5px;
  font-weight: 500;
  color: #000;
  background-color: #fff;
  border: none;
  border-radius: 45px;
  box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
  transition: all 0.3s ease 0s;
  cursor: pointer;
  outline: none;
  }

.button:hover {
  background-color: #2EE59D;
  box-shadow: 0px 15px 20px rgba(46, 229, 157, 0.4);
  color: #fff;
  transform: translateY(-7px);
}
</style>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script> 

<link rel="stylesheet" href="./css/sighting_main.css"> <!-- section css -->
<link rel="stylesheet" href="./css/UI.css">
<link rel="stylesheet" href="./css/witness.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
</head>
<script type="text/javascript">

function checkLogin() {
    var id = '${dto.myid}'; // 수정 ''처리
    
    // 수정 ''공백 비교
    if (id == '') {
        alert("로그인 후 글쓰기가 가능합니다.");
        return false;
    } else {
        location.href = 'witness.do?command=write';
    }
}

</script>
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
                    <h3>목격 게시판</h4>
                    
                    <hr width="890">
                    <button class="btn-1" onclick="checkLogin()" style="text-align:center; font-size:12px;">글 쓰기</button>
                </div>
    
                <div id="maincontent">
                     <section>
    	<article class="sighting_main">
    		<div class="wrapper">
    			<div class="bord1" style='height:900px'>
            	
            	<div class="witness_explanation" style="margin-top:50px; margin-bottom:20px;">
            		<p style="margin-top:50px; margin-bottom: 50px;">
            			<b class="witness_explanation_content">
           해당 게시판은 목격된 유기동물을 확인할 수 있는 게시판입니다.<br>
            주인 품을 떠나간 아이들이 다시 따뜻한 집으로 돌아갈 수 있도록
            많은 제보 바랍니다.
            			</b>			
            		</p>
            	</div>
    <div class="witness_search">
    	
    	<form action="witness.do" method="post">
    		<input type="hidden" name="command" value="witness_search_content">
    			
    	
    			
    				
    					<input type="text" name="content" placeholder="글 내용 입력" style="width:650px; height:50px; border:6px solid pink; font-size:30px;">
    					<input class="button" type="submit" value="검색">
    				
    			
    	</form>
    	
    	
    	
    	
    	
    	
    	
    	
    	<form action="witness.do?" method="post">
    	
    		<input type="hidden" name="command" value="witness_search_city_kind">
    		
    <table border="1" style="margin-top:10px;">	
    	<tr>
    		<th colspan="2" style="background-color:pink; color:white; text-align:center; font-size:20px;">조건 검색 기능</th>
    	</tr>
    	<tr>
    		<td>
    			<select name="city" class="form-select" aria-label="Default select example">
  					<option selected>지역을 선택해 주세요(필수)</option>
  					<option value="서울특별시">서울특별시</option>
  					<option value="강원도">강원도</option>
  					<option value="충청도">충청도</option>
  					<option value="전라도">전라도</option>
            		<option value="경상도">경상도</option>
            		<option value="경기도">경기도</option>
            		<option value="제주도">제주도</option>
				</select>
				</td>
			<td>
				<select name="kind" class="form-select" aria-label="Default select example">
					<option selected>종류를 선택해 주세요(필수)</option>
					<option value="개">개</option>
					<option value="고양이">고양이</option>
				</select>
			</td>	
						
				</tr>
				<tr>
					<td colspan="2" style="text-align:right; background-color:pink;">
						<input class="button" type="submit" value="전체보기" style="padding-left:50px; padding-right:100px;">
					</td>
				</tr>	
			</table>	
    	</form>
    	
    	
    </div>
              <div class="boc1">
              	<c:choose>
              		<c:when test="${empty wnList }">
              			<p>----등록된 글이 없습니다----</p>
              		</c:when>
              		<c:otherwise>
              			<c:forEach var="wnList" items="${wnList}">
              				 <ul>
                 				 <li>
                    				<a href="witness.do?command=witnessSelectOne&no=${wnList.no }">
                      				<span>
                        				<img class="sighting_main_img" src="image/${wnList.witness_pic }">
                      				</span>
                    				</a>
                 				 </li>
                    
                  				 <li>
                    				<span><b>NO:</b>${wnList.no }  </span>
                  				</li>
                  				<li>
                    				<span><b>색상 :</b>${wnList.color } </span>
                  				</li>
                  				<li>
                  					<span><b>종류:</b>${wnList.kind } </span>
                  				</li>
                  				<li>
                  					<span><b>목격장소 :</b>${wnList.city } &nbsp; ${wnList.place } </span>
                  				</li>
                  				<li>
                  					<span><b>전화번호 :</b>${wnList.phone_no }</span>
                  				</li>
                  				<li>
                  					<span><b>작성자 :</b>${wnList.writer }</span>
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
			<a href="witness.do?command=witnessmain&pageNum=<%=startPage-10 %>">[이전]</a>
			<%
		}
		for(int i =startPage; i<=endPage; i++){
			if(i== currentPage){
				%>
					[<%=i %>]
				<%
					}else{
				%>		
					<a href="witness.do?command=witnessmain&pageNum=<%=i %>">
					[<%=i %>]</a>
					<%
		}
	}
		if(endPage< pageCount){
			
		%>
			<a href="witness.do?command=withnessmain&pageNum=<%=startPage + 10 %>">[다음]</a>
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