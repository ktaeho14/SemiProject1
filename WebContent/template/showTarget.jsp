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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/target.css">
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
                    <h3>찾아주세요</h3>
                    <h4>나의 소중한 아이를 찾는데에 도와주세요</h4>
                    <hr width="890">
                </div>

                <div id="maincontent">
                    <form action="<%=request.getContextPath() %>/lost.do" method="post">
                        <input type="hidden" name="command" value="registerReply" id="changeMode">
                        <input type="hidden" name="num" value="${targetToShow.num}">
                        <input type="hidden" name="boardId" value="1">
                        <input type="hidden" name="lev" value="${parent.lev }">
                        <input type="hidden" name="tab" value="${parent.replyTab }">
                        <input type="hidden" name="tab" value="${parent.replyOrder }">
                        <input type="hidden" name="seq" value="${parent.levSeq }">
                        <input type="hidden" name="depth" value="${parent.depth }">
                        <%--     	  <input type="hidden" name="article" value="${targetToShow}"/> --%>
                        <c:set var="ord" value="${parent.replyOrder}" />
                        <div class="article-container">
                            <div class="lostAnimalImg">
                                <img src="lostAnimal/${targetToShow.lostPic}" alt="${targetToShow.lostPic}"
                                    onerror="this.src='<%=request.getContextPath()%>/basicResources/noImage.png'" />
                            </div>
                            <div class="article">
                                <div class="clearfix">
                                    <table border="1">
                                        <tr>
                                            <th>등록번호</th>
                                            <td>${targetToShow.num}</td>
                                        </tr>
                                        <tr>
                                            <th>제목</th>
                                            <td>${targetToShow.title}</td>
                                        </tr>
                                        <tr>
                                            <th>작성자</th>
                                            <td>${targetToShow.writer }</td>
                                        </tr>
                                        <tr>
                                            <th>잃어버린 장소</th>
                                            <td>${targetToShow.lostPlace}</td>
                                        </tr>
                                        <tr>
                                            <th>잃어버린 날짜</th>
                                            <td>${targetToShow.lostDate}</td>
                                        </tr>
                                        <tr>
                                            <th>강아지 품종 정보</th>
                                            <td>${targetToShow.species}&nbsp;-&nbsp;${targetToShow.cate}</td>
                                        </tr>
                                        <tr>
                                            <th>특이사항</th>
                                            <td>${targetToShow.detail}</td>
                                        </tr>
                                        <tr>
                                            <th>기타</th>
                                            <td>${targetToShow.etc }</td>
                                        </tr>
                                        <tr>
                                            <th>조회수</th>
                                            <td>${targetToShow.watch}</td>
                                        </tr>
                                    </table>
                                </div>
                            </div>
                            <!-- 수정/삭제 -->
                            <div class="mod-container">
                                <input type="button" value="글 수정하기" class="article-update"
                                    onclick="return validCheckUpdate(this,'${targetToShow.num}','${targetToShow.writer}','${dto.myid}');">
                                <input type="button" value="글 삭제하기" class="article-delete"
                                    onclick="return validCheckDelete(this,'${targetToShow.num}','${targetToShow.writer}','${dto.myid}');">
                            </div>
                            <!-- 댓글 -->
                            <div class="reply-container">
                                <div class="reply">
                                    <div class="reply-content">
                                        <label>작성자 : <input type="text" class="id" value="${dto.myid}" name="id"
                                                readonly></label>
                                        <textarea name="reply" class="register-content" cols="10" rows="10"></textarea>
                                        <input type="submit" value="댓글작성하기" class="register"
                                            onclick="return replyChk();">
                                    </div>
                                    <div class="attach-apply-content">
                                        <input type="hidden" name="reparent-order" value="">
                                        <c:choose>
                                            <c:when test="${not empty reply}">
                                                <c:forEach var="rpList" items="${reply}">
                                                    <c:if test="${rpList.replyOrder != ord}">
                                                        <div class="reply-content-container">
                                                            <c:choose>
                                                                <c:when test="${rpList.delFlag==1}">
                                                                    <!-- 삭제된 경우 -->
                                                                    <span>해당 글은 삭제되었습니다</span>
                                                                    <div class="specific-btns">
                                                                        <input type="button" value="댓글작성하기"
                                                                            title="댓글을 작성"
                                                                            class="regBan register-rp-to-this" disabled>
                                                                        <input type="button" value="수정"
                                                                            class="regBan update-on-this" disabled>
                                                                        <input type="button" value="삭제"
                                                                            class="regBan delete-on-this" disabled>
                                                                    </div>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <!-- 댓글이 삭제되지 않은 경우 -->
                                                                    <c:forEach begin="1" end="${rpList.replyTab}">
                                                                        <span>&nbsp;</span>
                                                                    </c:forEach>
                                                                    <span class="reply-writer">↪댓글 작성자:
                                                                        ${rpList.id}</span>
                                                                    <div class="rpContent">
                                                                        <span>${rpList.content}</span>
                                                                    </div>
                                                                    <div class="specific-btns">
                                                                        <input type="button" value="댓글작성하기"
                                                                            title="댓글을 작성"
                                                                            class="regBan register-rp-to-this"
                                                                            onclick="regReply(this);handlerCheck(this);return replyChk(); validation(this,'${rpList.delFlag}');">
                                                                        <input type="button" value="수정"
                                                                            class="regBan update-on-this"
                                                                            onclick="handleUpdate(this); return updateContent(this,'${dto.myid}','${rpList.id}','${rpList.content}');validation(this,'${rpList.delFlag}');">
                                                                        <input type="button" value="삭제"
                                                                            class="regBan delete-on-this"
                                                                            onclick="handleDelete(this); return deleteContent(this,'${dto.myid}','${rpList.id}','${parent.boardId}','${parent.num}'); validation(this,'${rpList.delFlag}');">
                                                                    </div>
                                                                </c:otherwise>
                                                            </c:choose>

                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>

    <%@include file="/footer.jsp" %>
    <script src="<%=request.getContextPath() %>/js/target.js"></script>



</body>

</html>