<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<% request.setCharacterEncoding("UTF-8"); %>
<% response.setContentType("text/html;charset=UTF-8"); %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>찾아봐주개냥</title>
    <link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/updateLost.css">
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
                    <form
                        action="<%=request.getContextPath()%>/lost.do?command=updateLostAnimalArticle&num=${toUpdate.num}"
                        enctype="multipart/form-data" method="post">
                        <div class="img-container">
                            <c:choose>
                                <c:when test="${not empty toUpdate.lostPic}">
                                    <img src="lostAnimal/${toUpdate.lostPic}" alt="등록했던 이미지" />
                                </c:when>
                                <c:otherwise>
                                    <img src="<%=request.getContextPath() %>/basicResources/noImage.png"
                                        alt="등록한 이미지를 찾을 수 없습니다" />
                                </c:otherwise>
                            </c:choose>
                        </div>
                        <table>
                            <tbody>
                                <tr>
                                    <th>게시글 번호</th>
                                    <td><input type="text" name="num" value="${toUpdate.num}" readonly></td>
                                </tr>
                                <tr>
                                    <th>제목</th>
                                    <td><input type="text" name="title" value="${toUpdate.title}" required></td>
                                </tr>
                                <tr>
                                    <th>작성자</th>
                                    <td><input type="text" name="writer" value="${toUpdate.writer}" required readonly>
                                    </td>
                                </tr>
                                <tr>
                                    <th>연락처</th>
                                    <td><input type="text" name="tel" value="${toUpdate.tel}" required readonly></td>
                                </tr>
                                <tr>
                                    <th>잃어버린 날짜</th>
                                    <td>
                                        <input type="date" name="lostDate" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th>잃어버린 장소</th>
                                    <td>
                                        <label for="province">도/특별시/광역시:</label>
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
                                        <br>
                                        <label for="reserveAddr">나머지 주소:&nbsp;</label>
                                        <input type="text" name="reserveAddr" id="reserveAddr"
                                            value="${toUpdate.reserv }" placeholder="OO시 OO구 OO동 OO아파트 놀이터" required>
                                    </td>
                                </tr>
                                <tr>
                                    <th>반려견 사진</th>
                                    <td><input type="file" name="lostPic" value="lostAnimal/${toUpdate.lostPic}"
                                            required></td>
                                </tr>
                                <tr>
                                    <th>특이사항</th>
                                    <td><textarea name="detail" cols="10" rows="10" maxlength="1000"
                                            placeholder="공백포함 최대 1000자">${toUpdate.detail}</textarea></td>
                                </tr>
                                <tr>
                                    <th>종류</th>
                                    <td>
                                        <label for="species">분류:&nbsp;&nbsp;</label>
                                        <select name="species" required>
                                            <option value="강아지" class="sp-opt">강아지</option>
                                            <option value="고양이" class="sp-opt">고양이</option>
                                        </select>
                                        <br>
                                        <label for="상세분류">상세분류:&nbsp;&nbsp;</label>
                                        <input type="text" name="cate" value="${toUpdate.cate}" required>
                                        <p class="cateExp">(※삽살개 등과 같은 분류를 의미합니다)</p>
                                    </td>
                                </tr>
                                <tr>
                                    <th>기타 상세 설명</th>
                                    <td><textarea name="etc" rows="10" cols="10" maxlength="1000"
                                            placeholder="공백 포함 1000자까지 입력">${toUpdate.etc}</textarea></td>
                                </tr>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="2">
                                        <span>※ 이 신고글을 작성한 사용자는 위의 내용이 허위 사실이 아님을 명백히 밝힙니다.</span>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                        <div class="btn-bundle">
                            <input type="submit" value="수정하기">
                            <input type="button" value="다시 목록으로"
                                onclick="location.href='<%=request.getContextPath()%>/lost.do?command=lostMain&page=1';">
                            <input type="reset" value="다시 작성하기">
                        </div>
                    </form>
                </div>
            </div>

        </div>
    </section>

    <%@include file="/footer.jsp" %>

    <script src="<%=request.getContextPath() %>/js/header.js"></script>
    <script src="<%=request.getContextPath() %>/js/login.js"></script>
    <!-- jstl, el을 사용해야 해서 내장해야만 함..! -->
    <script>
        var options = document.getElementsByClassName("prov-opt");
        var opt = `<c:out value="${toUpdate.province}"/>`;
        var spOpts = document.getElementsByClassName("sp-opt");
        var spOpt = `<c:out value="${toUpdate.species}"/>`;

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


        function selectSp() {
            switch (spOpt) {
                case "강아지":
                    spOpts[0].selected = true;
                    break;
                case "고양이":
                    spOpts[1].selected = true;
                    break;
            }
        }

        selectProvince(); //로딩될때 셀렉트박스 중 관할지역부분 설정
        selectSp(); //로딩될때 셀렉트박스 중 species 설정
    </script>


</body>

</html>