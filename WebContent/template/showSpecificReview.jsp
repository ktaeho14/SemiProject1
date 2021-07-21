<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%  request.setCharacterEncoding("UTF-8");%>
<%  response.setContentType("text/html;charset=UTF-8");%>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아봐주개냥</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/targetRv.css">
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
                    <form action="review.do" method="post">
                        <input type="hidden" name="command" value="updateReviewForm">
                        <input type="hidden" name="rvNo" value="${reviewToShow.rvNo}">
                        <div class="article-container">
                            <div class="lostAnimalImg">
                                <c:choose>
                                    <c:when test="${not empty reviewToShow.rvPic}">
                                        <img src="LostReview/${reviewToShow.rvPic}" alt="${reviewToShow.rvPic }"
                                            onerror="this.src='<%=request.getContextPath()%>/basicResources/noImage.png'" />
                                    </c:when>
                                    <c:otherwise>
                                        <img src="<%=request.getContextPath()%>/basicResources/noImage.png"
                                            alt="no image" />
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="article">
                                <div class="clearfix">
                                    <table border="1">
                                        <tr>
                                            <th>등록번호</th>
                                            <td>${reviewToShow.rvNo}&nbsp;-&nbsp;${reviewToShow.num}</td>
                                        </tr>
                                        <tr>
                                            <th>제목</th>
                                            <td>${reviewToShow.title}</td>
                                        </tr>
                                        <tr>
                                            <th>작성자</th>
                                            <td>${reviewToShow.id}</td>
                                        </tr>
                                        <tr>
                                            <th>잃어버린 장소</th>
                                            <td>${reviewToShow.lostPlace}</td>
                                        </tr>
                                        <tr>
                                            <th>잃어버린 날짜</th>
                                            <td>${reviewToShow.lostDate}</td>
                                        </tr>
                                        <tr>
                                            <th>강아지 품종 정보</th>
                                            <td>${reviewToShow.species}&nbsp;-&nbsp;${reviewToShow.cate}</td>
                                        </tr>
                                        <tr>
                                            <th>후기</th>
                                            <td><textarea name="content" cols="10" rows="10"
                                                    readonly>${reviewToShow.content}</textarea></td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <!-- 수정/삭제 -->
                            <div class="mod-container">
                                <input type="submit" value="글 수정하기" class="article-update">
                                <input type="button" value="글 삭제하기" class="article-delete"
                                    onclick="location.href='<%=request.getContextPath()%>/review.do?command=deleteLostReview&rvNo=${reviewToShow.rvNo}';">
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>

    <%@include file="/footer.jsp" %>




</body>

</html>