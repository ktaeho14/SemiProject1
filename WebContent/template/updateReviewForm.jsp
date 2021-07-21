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
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/register.css">
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
                    <form
                        action="<%=request.getContextPath() %>/review.do?command=updateLostReview&rvNo=${defaultLostReview.rvNo}&num=${defaultLostReview.num}"
                        enctype="multipart/form-data" method="post" name="frm">
                        <input type="hidden" name="regDate" value="${defaultLostReview.regDate }">
                        <table border="1">
                            <tbody>
                                <tr>
                                    <th>제목</th>
                                    <td><input type="text" name="title" value="${defaultLostReview.title }" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th>작성자</th>
                                    <td><input type="text" name="id" value="${dto.myid}" readonly></td>
                                </tr>
                                <tr>
                                    <th>사진</th>
                                    <td><input type="file" name="rvPic" required></td>
                                </tr>
                                <tr>
                                    <th>잃어버린 날짜</th>
                                    <td><input type="text" name="lostDate" value="${defaultLostReview.lostDate}"
                                            required readonly></td>
                                </tr>
                                <tr>
                                    <th>잃어버린 장소</th>
                                    <td>
                                        <label>도/특별시/광역시:<input type="text" name="province"
                                                value="${defaultLostReview.province }" readonly></label>
                                        <br>
                                        <label for="reserveAddr">나머지 주소:&nbsp;</label>
                                        <input type="text" name="reserveAddr" id="reserveAddr"
                                            value="${defaultLostReview.reserv }" readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <th>종류</th>
                                    <td>
                                        <label>분류:&nbsp;&nbsp;<input type="text" name="species"
                                                value="${defaultLostReview.species}" readonly></label>
                                        <br>
                                        <label for="상세분류">상세분류:&nbsp;&nbsp;</label>
                                        <input type="text" name="cate" value="${defaultLostReview.cate}" readonly>
                                        <p class="cateExp">(※삽살개 등과 같은 분류를 의미합니다)</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>후기</th>
                                    <td><textarea name="content" rows="10" cols="10"
                                            required>${defaultLostReview.content}</textarea></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <span>※ 이 후기글을 작성한 사용자는 위의 내용이 허위 사실이 아님을 명백히 밝힙니다.</span>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <div class="btnBundle">
                            <input type="submit" value="후기 수정하기">
                            <input type="reset" title="작성한 내용을 모두 지웁니다" value="초기화">
                            <input type="button" value="목록"
                                onclick="location.href='<%=request.getContextPath() %>/review.do?command=reviewLostMain&page=1';">
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>

    <%@include file="/footer.jsp" %>




</body>

</html>