<%@page import="net.search.db.Top100_Bean"%>
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
   Top100_Bean[] top100=(Top100_Bean[])request.getAttribute("top100");
   int contenttypeid=(int)request.getAttribute("contenttypeid");
   int page_num=(int)request.getAttribute("page_num");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Top 100 <%if(contenttypeid==12) out.print("관광지"); else out.print("맛집"); %></title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/top100.css?ver=1">
   
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
 

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
                                <a href="#" id="favorite">즐겨찾기</a>
                                <span></span>
                            </li>
                            <li>
                                <%if (ID == null) {
                                   if(session.getAttribute("id")==null){%>
                                   		<a href="./MemberLogin.me">로그인</a>
                                   <%} else { %>
                                   		<a href="./MemberLogoutAction.me">로그아웃</a>
                        		<%} 
                                }else {%>
                                   		<a href="./MemberLogoutAction.me">로그아웃</a>
                                  <%}%>
                                <span></span>
                                </li>
                            <li>
								<%if(ID == null) {
                                   if(session.getAttribute("id")==null){%>
                                   		<a href="./MemberJoin1.me">회원가입</a>
                        			<%}
                                else{%>
                                   <a href="./MyPageHome.me">마이페이지</a>
                                <%}
                                }else {%>
                                	<a href="./MyPageHome.me">마이페이지</a>
                                   <%}%>
                                </li>
                        </ul>
                    </div>
                    
                    <div class="snsicon"> <!-- 상단아이콘 -->
                       <form action="./All_Search.se" method="get" id="search_form">
                           <input type="text" id="search_input" placeholder="통합검색" name="search_word">
                           <input type="submit" id="search_btn" value="검색">
                       </form>
                        <!-- <a href="#" class="sns1">안드로이드</a> -->
                        <a href="http://www.letskorail.com/" class="sns2" target="_blank">카페</a>
                        <a href="https://cafe.naver.com/hkct" class="sns3" target="_blank">코레일</a>
                    </div>
                </div>
            </section>
            <section id="head-bot">
                       <div class="section">
                    <div id="logo"> <!-- 로고 -->
                        <h1><a href="./Main.me">
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
                                <dd><a href="./IntroRailro.me">내일로 소개</a></dd>
                                <!-- <dd><a href="#">발권지 혜택</a></dd> -->
                            </dl>
                            <dl class="hoverbg2 hoverbg" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <dd><a href="./Top100_Search.se?contenttypeid=12&page_num=1">관광지</a></dd>
                                <dd><a href="./Top100_Search.se?contenttypeid=39&page_num=1">맛집</a></dd>
                                <dd><a href="./Top100_Note.se?page_num=1">내일로 노트</a></dd>
                                <dd><a href="./Top100_Member.se?page_num=1">내일러</a></dd>
                            </dl>
                            <dl class="hoverbg3 hoverbg" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">

                                <dd><a href="./Railro_Note_Step1.pl">새 플래너 작성</a></dd>
                                <dd><a href="./Note_Plans_List.pl">내 플래너 목록</a></dd>
                            </dl>
                            <dl class="hoverbg5 hoverbg" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <dd><a href="./NoticeList.no">공지사항</a></dd>
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
                        <a href="./Main.me">
                            <img src="./jpg/home.jpg" alt="">
                        </a>
                    </div>
                    <div class="listmenu">
                        <button>TOP 100</button>
                        <ul class="listbox">
                            <li><a href="./IntroRailro.me">내일로 소개</a></li>
                            <li><a href="./Note_Plans_List.pl">플래너</a></li>
                            <li><a href="./NoticeList.no">고객센터</a></li>
                        </ul>
                    </div>
                    <div class="listmenu">
                         <button><%if(contenttypeid==12) out.print("관광지"); else out.print("맛집"); %></button>
                        <ul class="listbox">
                        	<%if(contenttypeid==39){ %>
                        	<li><a href="./Top100_Search.se?contenttypeid=12&page_num=1">관광지</a></li>
                        	<%} else{%>
                            <li><a href="./Top100_Search.se?contenttypeid=39&page_num=1">음식점</a></li>
                            <%} %>
                            <li><a href="./Top100_Note.se?page_num=1">내일로 노트</a></li>
                            <li><a href="./Top100_Member.se?page_num=1">내일러</a></li>
                        </ul>
                    </div>
                    
                </div>
            </div>
            <div id="sub-con-body" class="section">
                <div class="s-c-b-title">
                    <h3>Top 100</h3>
                    <h2><%if(contenttypeid==12) out.print("관광지"); else out.print("음식점"); %></h2>
                </div>
                <ul class="top">
                <%for(int i=0; i<top100.length; i++){ %>
                    <li>
                        <span class="rank"><%=(i+1)+((page_num-1)*20)%></span>
                        <a href="./Detail_Info.se?contentid=<%=top100[i].getContentid() %>&contenttypeid=<%=top100[i].getContenttypeid()%>" target="_blank"><img src="<%=top100[i].getImg() %>" alt=""></a>
                        <div class="title"><%=top100[i].getName() %></div>
                        <div>
                            <img src="./top100/heart.png" alt="" class="like"><span class="like_cnt"><%=top100[i].getLike_cnt() %></span>
                        </div>
                        <span class="review_cnt">리뷰 : <%=top100[i].getReview_cnt() %></span>
                        <div class="tags">
                        	<%for(int o=0; o<top100[i].getTags().size(); o++){ %>
                            <span>#<%=top100[i].getTags().get(o) %></span>
                            <%} %>
                        </div>
                    </li>
                    <%} %>
                </ul>
                <div class="page_num_group">
                	<%for(int a=1; a<=5; a++){ %>
                		<%if(page_num==a){ %>
                    	<a href="./Top100_Search.se?contenttypeid=<%=contenttypeid %>&page_num=<%=a%>" class="selected"><%=(a-1)*20+1 %>~<%=a*20 %>위</a>
                    	<%}
                		else{%>
                		<a href="./Top100_Search.se?contenttypeid=<%=contenttypeid %>&page_num=<%=a%>"><%=(a-1)*20+1 %>~<%=a*20 %>위</a>
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
                            <a href="./IntroRailro.me">내일로소개</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="./Top100_Search.se?contenttypeid=12&page_num=1">TOP 100</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="./Note_Plans_List.pl">내일로노트</a>
                            <span></span>
                        </li>
                        <li>
                            <a href="./NoticeList.no">고객센터</a>
                        </li>
                    </ul>
                </div>
                <div id="foot_bot">
                    <div id="f_logo">
                        <h2>
                            <a href="./Main.me">
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
                    </select>
                </div>
            </div>
        </footer>
    </div>
    
    <script src="./js/script.js"></script>
    <script>
  </script>
</body>
</html>