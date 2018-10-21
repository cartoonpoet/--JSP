<%@page import="java.util.ArrayList"%>
<%@page import="net.search.db.Top100_Member_Bean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%String ID = null, PW = null;

Cookie cookies[] = request.getCookies();

if(cookies!=null) {
      for(int i = 0; i<cookies.length; i++){
         String name = cookies[i].getName();
         if(name.equals("ID")){
            ID = cookies[i].getValue();
         } 
         else if (name.equals("PW")){
            PW = cookies[i].getValue();
         }
      }
 }
   if(ID!=null&&PW!=null){
      session.setAttribute("ID", ID);
      session.setAttribute("PW", PW);
   }
   ArrayList<Top100_Member_Bean> data=(ArrayList<Top100_Member_Bean>) request.getAttribute("data");
   int page_num=(int) request.getAttribute("page_num");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Top 100 : 내일러</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/Top100%20Member.css">
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
 <style>
.page_num_group{
    overflow: hidden;
    width: 100%;
    text-align: center;
    margin-top: 50px;
}
.page_num_group a{
    margin: 0 auto;
    text-align: center;
    display: inline-block;
    font-size: 1.2rem;
    font-family: 'NanumSquare', sans-serif;
    font-weight: bold;
    padding: 3px 6px 4px 6px;
    margin-right: 5px;
    vertical-align: middle;
    color: #4b4b4b;
    border: 1px solid #ccc;
}
.page_num_group>.selected{
    color: rgb(0, 186, 255);
    border: 2px solid  rgb(0, 186, 255);
}
    </style>

</head>
<body>
    <div id="wrap"> <!-- 전체를 감싸는 부분 -->
        <header><!--상단 메뉴 -->
            <section id="head-top"> <!-- 상단메뉴의 검은색 메뉴 -->
                <div class="section">
                    <div class="topnavi"> <!-- 메뉴명 -->
                        <ul>
                            <li>
                                <a href="./Main.me">홈으로</a>
                                <span></span>
                            </li>
                            <li>
                                <a href="#">즐겨찾기</a>
                                <span></span>
                            </li>
                            <li>
                                <a href="loginform.html">로그인</a>
                                <span></span>
                                </li>
                            <li>
                                <a href="sign_up_from.html">회원가입</a>
                                <span></span>
                                </li>
<!--
                            <li>
                                <a href="#">여행바구니</a>
                            </li>
-->
                        </ul>
                    </div>
                    
                    <div class="snsicon"> <!-- 상단아이콘 -->
<!--                        <a href="#" class="sns1">안드로이드</a>-->
                        <a href="#" class="sns2">카페</a>
                        <a href="#" class="sns3">코레일</a>
                    </div>
                </div>
            </section>
            <section id="head-bot">
                       <div class="section">
                    <div id="logo"> <!-- 로고 -->
                        <h1><a href="./index.html">
                            <img src="./jpg/RailroTour%20LOGO.png" alt="">
                            </a>
                        </h1>
                    </div>
                    <nav>
                        <ul class="m-menu"><!--메인메뉴-->
                            <li class="list01 list" onmouseover="bgcolor(1)" onmouseout="removecolor(1)">
                                <a href="#" class="list_a1">내일로 소개</a>
                                
                            </li>
                            <li class="list02 list" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <a href="#" class="list_a2">TOP 100</a>
                            </li>
                            <li class="list03 list" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">
                                <a href="#" class="list_a3">플래너</a>
                            </li>
<!--
                            <li class="list04 list" onmouseover="bgcolor(4)" onmouseout="removecolor(4)">
                                <a href="#" class="list_a4">내 주변</a>
                            </li>
-->
                            <li class="list05 list" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <a href="#" class="list_a5">고객센터</a>
                            </li>
                        </ul>
                    </nav>
                    <div class="s-menu">
                        <div class="section">
                           <div class="float">
                            <dl class="hoverbg1 hoverbg" onmouseover="bgcolor(1)" onmouseout="removecolor(1)">
<!--                                            <dt>지우지 말것</dt>-->
                                <dd><a href="./index.html">내일로 소개</a></dd>
                            </dl>
                            <dl class="hoverbg2 hoverbg" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <dd><a href="#">관광지</a></dd>
                                <dd><a href="#">맛집</a></dd>
                                <dd><a href="#">코스</a></dd>
                                <dd><a href="#">트레버</a></dd>
                            </dl>
                            <dl class="hoverbg3 hoverbg" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">

                                <dd><a href="#">새 플래너 작성</a></dd>
                                <dd><a href="#">내 플래너 목록</a></dd>
                            </dl>
<!--
                            <dl class="hoverbg4 hoverbg" onmouseover="bgcolor(4)" onmouseout="removecolor(4)">

                                <dd><a href="#">지도</a></dd>
                                <dd><a href="#">타임라인</a></dd>
                            </dl>
-->
                            <dl class="hoverbg5 hoverbg" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">

                                <dd><a href="sub02.html">공지사항</a></dd>
                                <dd><a href="#">자주묻는질문</a></dd>
                                <dd><a href="#">불량사용자 신고</a></dd>
                            </dl>
                            </div>
                        </div>
                    </div>
                </div>
            </section>
        </header>
        
        <section id="sub-imgbanner">
            <div class="section">
                <div class="sub-img-text">
                    <h3>Join tomorrow</h3>
                    <h2>
                        내일을 잇다, <b>내일로</b>
                    </h2>
                </div>
            </div>
        </section>
        <section id="sub-content">
            <div id="sub-con-navi">
                <div class="section">
                    <div class="homebtn">
                        <a href="./index.html">
                            <img src="./jpg/home.jpg" alt="">
                        </a>
                    </div>
                    <div class="listmenu">
                        <button>TOP 100</button>
                        <ul class="listbox">
                            <li><a href="#">내일로 소개</a></li>
                            <li><a href="#">플래너</a></li>
                            <li><a href="sub02.html">고객센터</a></li>
                        </ul>
                    </div>
                    <div class="listmenu">
                         <button>내일러</button>
                        <ul class="listbox">
                            <li><a href="#">관광지</a></li>
                            <li><a href="#">맛집</a></li>
                            <li><a href="#">내일로노트</a></li>
                        </ul>
                    </div>
                    
                </div>
            </div>
            <div id="sub-con-body" class="section">
                <div class="s-c-b-title">
                    <h3>Top 100</h3>
                    <h2>내일러</h2>
                </div>
                <ul class="ranking">
                    <li class="bar">
                        <span class="num">순위</span>
                        <span class="nikname">닉네임</span>
                        <span class="follow">팔로워 수</span>
                        <span class="railronote">내일로노트 수</span>
                    </li>
                    <%for(int i=0; i<data.size(); i++){ %>
                    <li class="member">
                        <span class="rank"><%=(i+1)+((page_num-1)*20) %></span>
                        <img src="<%=data.get(i).getImg() %>" alt="" class="profile">
                        <span class="name"><%=data.get(i).getNikname() %></span>
                        <span class="follow_cnt"><%=data.get(i).getFollow_cnt() %></span>
                        <span class="review_cnt"><%=data.get(i).getNote_cnt() %>개</span>
                    </li>
                    <%} %>
                </ul>
                <div class="page_num_group">
                	<%for(int a=1; a<=5; a++){ %>
                		<%if(page_num==a){ %>
                    	<a href="./Top100_Member.se?page_num=<%=a %>" class="selected"><%=(a-1)*20+1 %>~<%=a*20 %>위</a>
                    	<%}
                		else{%>
                		<a href="./Top100_Member.se?page_num=<%=a%>"><%=(a-1)*20+1 %>~<%=a*20 %>위</a>
                		<%} %>
                    <%} %>
                </div>
            </div>
        </section>
        
        
        <footer>
            <div class="section">
                <div id="foot_top">
                    <ul>
                        <li>
                            <a href="#">내일로소개</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="#">TOP 100</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="#">내일로노트</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="#">내 주변</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="#">고객센터</a>
                        </li>
                    </ul>
                </div>
                <div id="foot_bot">
                    <div id="f_logo">
                        <h2>
                            <a href="#">
                            <img src="./jpg/RailroTour%20LOGO.png" alt="">
                            </a>
                        </h2>
                    </div>
                    <address>내일로 통합 시스템<br>
주소 : 대구광역시 북구 복현동 영진전문대학 컴퓨터정보계열<br>
대표번호 : 000-0000-0000 팩스번호 : 00-0000-0000<br>
Copyright ⓒ RAILRO COMBINATION SYSTEM. All rights reserved.
</address>
                    <select name="" id="">
                        <option value="">family site</option>
                        <option value="">family site</option>
                        <option value="">family site</option>
                        <option value="">family site</option>
                        <option value="">family site</option>
                    </select>
                </div>
            </div>
        </footer>
    </div>
    
    <script src="./js/script.js"></script>
    <script>
     $(function() {
      var count = $('#rank-list li').length;
      var height = $('#rank-list li').height();

      function step(index) {
          $('#rank-list ol').delay(2000).animate({
              top: -height * index,
          }, 500, function() {
              step((index + 1) % count);
          });
      }

      step(1);
  });
  </script>
</body>
</html>