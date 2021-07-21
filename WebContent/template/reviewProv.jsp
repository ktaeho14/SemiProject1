<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%  request.setCharacterEncoding("UTF-8");%>
<%  response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>찾아봐주개냥</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/lostMain.css">
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
    <style type="text/css">
        body {
            margin: 0;
            padding: 0;
        }

        section {
            display: grid;
            place-items: center;
        }

        a {
            text-decoration: none;
        }

        ul {
            list-style: none;
        }




        .content_layout {
            width: 1160px;
            padding-top: 40px;
            min-height: 700px;
        }

        #leftmenu {
            width: 20%;
            float: left;
            margin-right: 13px;
            padding-left: 8px;
        }

        #leftmenu ul {
            padding-left: 0;
            padding-right: 50px;
        }


        #leftmenu h2 {
            margin-right: 50px;
        }

        #leftmenu ul li {
            border-bottom: 1px solid #2b2f3e;
        }



        #leftmenu ul li {
            padding-top: 10px;
            padding-bottom: 10px;
        }

        .left_a {
            font-size: 16px;
            font-weight: 600;
            color: black;
            padding-left: 10px;
            padding-bottom: 10px;

        }

        #rightcontent {
            width: 77%;
            display: inline-block;
            padding-left: 10px;
        }

        #content_title {
            height: 130px;
        }

        #content_title h3,
        #content_title h4 {
            padding-left: 30px;
        }
    </style>
</head>

<body>


    <%@include file="/logtop.jsp" %>
    <%@include file="/header.jsp" %>



    <section>

        <div class="content_layout">

            <div id="leftmenu">


                <h2>찾아주세요</h2>

                <ul>
                   <li><a class="left_a"
                            href="<%=request.getContextPath() %>/lost.do?command=lostMain&page=1">찾아주세요</a></li>
                    <li><a class="left_a"
                            href="<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1">실종 아이 찾은
                            후기</a></li>
                </ul>
            </div>
            <div id="rightcontent">
                <div id="content_title">
                    <h3>실종 아이 찾은 후기</h3>
                    <h4>실종신고 게시판에 등록된 게시글에 대한 후기입니다</h4>
                    <hr width="890">
                </div>

                <div id="maincontent">
                    <form action="review.do" method="get">
                        <div class="contents">
                            <h1>실종되었던 아이 찾은 후기</h1>
                            <div class="guide">
                                <iframe src="<%=request.getContextPath() %>/lostCommon/lostCommon.jsp"
                                    frameBorder="0"></iframe>
                            </div>
                            <div class="register">
                                <!-- 글 등록 & 확인하기 , 검색-->
                                <!-- 지역별 검색 -->
                                <label for="province">관할별 검색(도/특별시/광역시) :
                                    <select name="province" id="province">
                                        <option value="서울특별시" class="prov-opt">서울특별시</option>
                                        <option value="인천광역시" class="prov-opt">인천광역시</option>
                                        <option value="부산광역시" class="prov-opt">부산광역시</option>
                                        <option value="대구광역시" class="prov-opt">대구광역시</option>
                                        <option value="대전광역시" class="prov-opt">대전광역시</option>
                                        <option value="울산광역시" class="prov-opt">울산광역시</option>
                                        <option value="광주광역시" class="prov-opt">광주광역시</option>
                                        <option value="세종특별자치시" class="prov-opt">세종특별자치시</option>
                                        <option value="경기도" class="prov-opt">경기도</option>
                                        <option value="강원도" class="prov-opt">강원도</option>
                                        <option value="충청북도" class="prov-opt">충청북도</option>
                                        <option value="충청남도" class="prov-opt">충청남도</option>
                                        <option value="전라북도" class="prov-opt">전라북도</option>
                                        <option value="전라남도" class="prov-opt">전라남도</option>
                                        <option value="경상북도" class="prov-opt">경상북도</option>
                                        <option value="경상남도" class="prov-opt">경상남도</option>
                                        <option value="제주특별자치도" class="prov-opt">제주특별자치도</option>
                                    </select>
                                </label>
                                <input type="button" value="검색" id="review-prov">
                                <input type="button" value="내가 등록한 후기" id="my-lost-review" title="내가 작성한 후기 확인하기"
                                    onclick="location.href='<%=request.getContextPath()%>/review.do?command=showMyLostReview&page=1'">
                            </div>
                            <div class="contents-container">
                                <c:choose>
                                    <c:when test="${empty provReview}">
                                        <p>---등록된 글이 없습니다---</p>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="list" items="${provReview}">
                                            <div class="item"
                                                onclick="location.href='<%=request.getContextPath() %>/review.do?command=showSpecificReview&rvNo=${list.rvNo}';">
                                                <img src="LostReview/${list.rvPic}" alt="${list.rvPic}" />
                                                <div class="title">제목: ${list.title}</div>
                                                <div class="abbr">
                                                    <p>종류 : ${list.species}&nbsp;&nbsp;${list.cate}</p>
                                                    <fmt:parseDate var="lostDate" type="date" value="${list.lostDate}"
                                                        pattern="yyyy/MM/dd" />
                                                    <p>잃어버린 날짜 :
                                                        <fmt:formatDate value="${lostDate}" pattern="yyyy년 MM월 dd일 " />
                                                    </p>
                                                    <p>잃어버린 장소 : ${list.lostPlace}</p>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>

                            </div>

                            <!-- 리스트 버튼 -->
                            <div class="pageBtn">
                                <c:choose>
                                    <c:when test="${pageNum !=0}">
                                        <c:forEach var="page" begin="1" end="${pageNum}">
                                            <input type="button" value="${page}"
                                                onclick="location.href='<%=request.getContextPath() %>/review.do?command=searchReviewByProv&page=${page}';">
                                        </c:forEach>
                                    </c:when>
                                </c:choose>
                            </div>

                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>

    <%@include file="/footer.jsp" %>

    <script src="<%=request.getContextPath() %>/js/searchLostRv.js"></script>
    <script>
        var options = document.getElementsByClassName("prov-opt");
        var opt = `<c:out value="${province}"/>`;

        function selectProvince() {
            switch (opt) {
                case "서울특별시":
                    options[0].selected = true;
                    break;
                case "인천광역시":
                    options[1].selected = true;
                    break;
                case "부산광역시":
                    options[2].selected = true;
                    break;
                case "대구광역시":
                    options[3].selected = true;
                    break;
                case "대전광역시":
                    options[4].selected = true;
                    break;
                case "울산광역시":
                    options[5].selected = true;
                    break;
                case "광주광역시":
                    options[6].selected = true;
                    break;
                case "세종특별자치시":
                    options[7].selected = true;
                    break;
                case "경기도":
                    options[8].selected = true;
                    break;
                case "강원도":
                    options[9].selected = true;
                    break;
                case "충청북도":
                    options[10].selected = true;
                    break;
                case "충청남도":
                    options[11].selected = true;
                    break;
                case "전라북도":
                    options[12].selected = true;
                    break;
                case "전라남도":
                    options[13].selected = true;
                    break;
                case "경상북도":
                    options[14].selected = true;
                    break;
                case "경상남도":
                    options[15].selected = true;
                    break;
                case "제주특별자치도":
                    options[16].selected = true;
                    break;
            }
        }


        selectProvince(); //로딩될때 셀렉트박스 중 관할지역부분 설정
    </script>


</body>

</html>