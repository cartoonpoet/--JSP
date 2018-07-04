<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
%>

<%String ID = null, PW = null;

Cookie cookies[] = request.getCookies();

if(cookies!=null) {
      for(int i = 0; i<cookies.length; i++){
         String name = cookies[i].getName();
         if(name.equals("ID")){
            ID = cookies[i].getValue();
         } else if (name.equals("PW")){
            PW = cookies[i].getValue();
         }
      }
   }
   if(ID!=null&&PW!=null){
      session.setAttribute("ID", ID);
      session.setAttribute("PW", PW);
   }
   
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Railro Tour - 전라도편</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./bxslide/dist/jquery.bxslider.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="./bxslide/dist/jquery.bxslider.min.js">
    </script>
    <script> /* 배너 슬라이드 */
            $(document).ready(function(){
                $('.slider').bxSlider({
                    auto:true,
                    adaptiveHeight:true,
                    mode:'fade'
                });
            });
    </script>
    
    <style>
.snsicon #search_form{
    float: left;
    width: 260px;
    margin: 7px 5px;
    border: 2px solid #4887ff;
    height: 28px;
}
.snsicon #search_form #search_input{
    width: 195px;
    height: 28px;
    float: left;
    border: none;
    padding-left: 5px;
}
.snsicon #search_form #search_btn{
    height: 28px;
    float: left;
    width: 60px;
    border:none;
    background-color: #0093ff;
    color: white;
    font-weight: bold;
    font-family: 'NanumSquare', sans-serif;
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
                                   <a href="#">마이페이지</a>
                                <%}
                                }else {%>
                                	<a href="#">마이페이지</a>
                                   <%}%>
                                <span></span>
                                </li>
                            <li>
                                <a href="#">여행바구니</a>
                            </li>
                        </ul>
                    </div>
                    
                    <div class="snsicon"> <!-- 상단아이콘 -->
                       <form action="./SearchAction.se" method="get" id="search_form">
                           <input type="text" id="search_input" placeholder="통합검색" name="SEARCH_WORD">
                           <input type="submit" id="search_btn" value="검색">
                       </form>
                        <a href="#" class="sns1">안드로이드</a>
                        <a href="#" class="sns2">카페</a>
                        <a href="#" class="sns3">코레일</a>
                    </div>
                </div>
            </section>
            <section id="head-bot">
                       <div class="section">
                    <div id="logo"> <!-- 로고 -->
                        <h1><a href="./Main.me">
                            <img src="jpg/RailroTour%20LOGO.png" alt="">
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
                            <li class="list04 list" onmouseover="bgcolor(4)" onmouseout="removecolor(4)">
                                <a href="#" class="list_a4">내 주변</a>
                            </li>
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
                                <dd><a href="./html/sub01.html">내일로 소개</a></dd>
                                <dd><a href="#">발권지 혜택</a></dd>
                            </dl>
                            <dl class="hoverbg2 hoverbg" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <dd><a href="#">관광지</a></dd>
                                <dd><a href="#">맛집</a></dd>
                                <dd><a href="#">코스</a></dd>
                                <dd><a href="#">트레버</a></dd>
                            </dl>
                            <dl class="hoverbg3 hoverbg" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">

                                <dd><a href="./Railro_Note_Step1.pl">새 플래너 작성</a></dd>
                                <dd><a href="#">내 플래너 목록</a></dd>
                            </dl>
                            <dl class="hoverbg4 hoverbg" onmouseover="bgcolor(4)" onmouseout="removecolor(4)">

                                <dd><a href="#">지도</a></dd>
                                <dd><a href="#">타임라인</a></dd>
                            </dl>
                            <dl class="hoverbg5 hoverbg" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <dd><a href="./NoticeList.no">공지사항</a></dd>
                                <dd><a href="#">자주묻는질문</a></dd>
                                <dd><a href="#">불량사용자 신고</a></dd>
                            </dl>
                            </div>
                        </div>
                    </div>
                </div>
			</section>
        </header>
        <main>
            <ul class="slider">
                <li class="slideimg1"></li>
                <li class="slideimg2"></li>
            </ul>
        </main>
        <section id="content">
            <section id="banner">
                <div class="section">
                    <section id="ban01"><!--내일로 소개 부분 -->
                        <div class="ban-textbox">
                            <a href="#">
                                <span class="text1">내일로 소개</span>
                                <span class="title">Introduce<br><b>Railro</b></span>
                                <span class="bar"></span>
                                <span class="text2">한학기 동안 고생한 대학생, 떠나라!<br>청춘들의 기차여행, 내일로</span>
                            </a>
                        </div>
                        <div class="ban-imgbox">
                            <a href="#">
                                <img src="jpg/bn01.jpg" alt="">
                            </a>
                        </div>
                    </section> 
                    <section id="ban02">
                        <div id="ban02-1">
                            <div class="ban-iconbox">
                                <ul>
                                    <li class="one"><a href="#">
                                        <span>관광지</span>
                                    </a></li>
                                    <li class="two"><a href="#">
                                        <span>맛집</span>
                                    </a></li>
                                    <li class="three"><a href="#">
                                        <span>코스</span>
                                    </a></li>
                                    <li class="four"><a href="#">
                                        <span>트레버</span>
                                    </a></li>
                                </ul>
                            </div>
                             <div class="ban-textbox">
                             <span class="bottom">
                                 
                             </span>
                            <a href="#">
                                <span class="text1">Top 100</span>
                                <span class="title">Introduce<br><b>Top of Top</b></span>
                                <span class="bar"></span>
                                <span class="text2">여행자들이 직접 뽑은 Top은?<br>최고의 여행지로 떠나라!</span>
                            </a>
                        </div>
                        </div>
                        <div id="ban02-2">
                            <div class="rank">
                                <div id="rank-list">
                                    <dl>
                                        <dt>실시간 급상승 검색어</dt>
                                        <dd>
                                            <ol>
                                                <li>
                                                   <a href="#">
                                                        <span class="rank-color">&nbsp;1</span>
                                                        &nbsp;
                                                        <script>
                                                            var top1='테마여행';
                                                            document.write(top1);
                                                       </script>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;2</span>
                                                        &nbsp;
                                                        <script>
                                                            var top2='여수 특산물';
                                                            document.write(top2);
                                                       </script>
                                                    </a>
                                                 </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;3</span>
                                                        &nbsp;
                                                        <script>
                                                            var top3='영화 촬영지';
                                                            document.write(top3);
                                                       </script>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;4</span>
                                                        &nbsp;
                                                        <script>
                                                            var top4='5위';
                                                            document.write(top4);
                                                       </script>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;5</span>
                                                        &nbsp;
                                                        <script>
                                                            var top5='여수 여행지';
                                                            document.write(top5);
                                                        </script>
                                                        
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;6</span>
                                                        &nbsp;
                                                        <script>
                                                            var top6='6위';
                                                            document.write(top6);
                                                       </script>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;7</span>
                                                        &nbsp;
                                                        <script>
                                                            var top7='7위';
                                                            document.write(top7);
                                                       </script>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;8</span>
                                                        &nbsp;
                                                        <script>
                                                            var top8='8위';
                                                            document.write(top8);
                                                       </script>
                                                     </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">&nbsp;9</span>
                                                        &nbsp;
                                                        <script>
                                                            var top9='9위';
                                                            document.write(top9);
                                                       </script>
                                                    </a>
                                                </li>
                                                <li>
                                                    <a href="#">
                                                        <span class="rank-color">10</span>
                                                         &nbsp;
                                                         <script>
                                                            var top10='10위';
                                                            document.write(top10);
                                                       </script>
                                                    </a>
                                                </li>
                                            </ol>
                                        </dd>
                                    </dl>
                                  </div>
                                      <div class="hoverrank-list">
                                        <ol>
                                            <li class="real_time_rank">실시간 인기 검색어</li>
                                            <li class="top"><a href="#"><b>1</b> <script>document.write(top1);</script></a></li>
                                            <li class="top"><a href="#"><b>2</b> <script>document.write(top2);</script></a></li>
                                            <li class="top"><a href="#"><b>3</b> <script>document.write(top3);</script></a></li>
                                            <li class="top"><a href="#"><b>4</b> <script>document.write(top4);</script></a></li>
                                            <li class="top"><a href="#"><b>5</b> <script>document.write(top5);</script></a></li>
                                            <li class="top"><a href="#"><b>6</b> <script>document.write(top6);</script></a></li>
                                            <li class="top"><a href="#"><b>7</b> <script>document.write(top7);</script></a></li>
                                            <li class="top"><a href="#"><b>8</b> <script>document.write(top8);</script></a></li>
                                            <li class="top"><a href="#"><b>9</b> <script>document.write(top9);</script></a></li>
                                            <li class="top"><a href="#"><b>10</b> <script>document.write(top10);</script></a></li>
                                            <li class="last_update">2018.05.02. 19:30:00 마지막 업데이트</li>
                                       </ol>
                                  </div>
                            </div>
                            <div class="CustomSearch">
                                <ul>
                                    <li class="Search-Title">
                                        <img src="jpg/search.png" alt="">
                                        사용자 맞춤 여행코스 검색
                                    </li>
                                    <li class="People">인원</li>
                                    <li>
                                        
                                         <form class="Peo-select" name="form">
                                            <button type="button" onclick="change(-1)" id="minus">-</button>
                                            <div class="input_cover">
                                            <img src="jpg/사람.png" alt="">x
                                            <input type="text" name='count' value="1" size="3" id="count" readonly>
                                            
                                             </div>
                                            <button type="button" onclick="change(1)" id="plus">+</button>
                                        </form>
                                        
                                    </li>
                                    <li class="day">기간</li>
                                    <li class="r-button">
                                        <input type="radio" value="5" name="day"> 5일
                                        <input type="radio" value="7" name="day"> 7일
                                    </li>
                                    <li class="tema">테마</li>
                                    <li>
                                        <span class="tema_group">
                                            <img src="jpg/down_icon.jpg" alt="" class="Left_icon" onclick="plus(-1)">
                                            <div class="tema_slide">
                                               <div class="tema_slide_group tema1 myS">
                                               		<img src="jpg/slide_icon1.png" 	alt="">
                                               		<span class="tema_name">관광</span>
                                               </div>
                                                <div class="tema_slide_group tema2 myS">
                                                	<img src="jpg/slide_icon2.png" alt="">
                                                	<span class="tema_name">음식</span>
                                                </div>
                                                <div class="tema_slide_group tema3 myS">
                                                	<img src="jpg/sleep.png" alt="" width="45px">
                                                	<span class="tema_name">휴식</span>
                                                </div>
                                            </div>
                                            <img src="jpg/down_icon.jpg" alt="" class="Right_icon" onclick="plus(1)">
                                        </span>
                                    </li>
									<li class="People area">지역</li>
                                    <li>
                                        
                                         <form class="Peo-select" name="area_form">
                                            <button type="button" onclick="area_change(-1)" id="minus">-</button>
                                            <div class="input_cover">
                                            <img src="jpg/area.png" alt="">x
                                            <input type="text" name='area_count' value="1" size="3" id="count" readonly>
                                            
                                             </div>
                                            <button type="button" onclick="area_change(1)" id="plus">+</button>
                                        </form>
                                    </li>
                                    <li class="search">
                                    	검색
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </section>
                    <section id="ban03">
                        <div class="textbox03">
<!--
                            <h2><b>내일로</b> 혜택</h2>
                            <span></span>
                            <h3>발권지 혜택 소개</h3>
                            <p>
                                 내용란
                            </p>
-->
                        </div>
                        <div class="imgbox">
                            <img src="jpg/1.jpg" alt="">
                            <img src="jpg/2.jpg" alt="">
                        </div>
                    </section>
                    <section id="ban04">
                        <section id="timeline-title">
                           <div class="real_time">
                           		<span class="time">실시간</span>
                            	<span>&nbsp;여행기</span>
                            	<a href="#">
                                	<img src="jpg/reset.png" alt="" class="reset">
                                	<span class="resettext">&nbsp;새로고침</span>
                            	</a>
                            	<span class="line"></span>
                           </div>
                            
                            <div class="timeline1">
                               <a href="#">
                                   <img src="jpg/timeline1.jpg" alt="">
                               </a>
                                <div class="bubble">
                                   <a href="#">
                                        <div class="text">바다로 가즈아~~!</div>
                                   </a>
                                   <span class="travel-bar">
                                       <i>Traveler</i>
                                       <span class="traveler">
                                           &nbsp;권재인
                                       </span>
                                       <div class="stamp_comment_cnt">
                                       	<input type="text" value="20" readonly> 
                                       	<a href="">
                                       		<img src="jpg/comment.png" 	alt="">
                                       	</a>
                                       	<input type="text" value="50" readonly>
                                       	<a href="">
                                       	<img src="jpg/stamp.png" alt="">
                                       	</a>
                                       </div>
                                   </span>
                                </div>
                            </div>
    						<div class="timeline1">
                               <a href="#">
                                   <img src="jpg/timeline1.jpg" alt="">
                               </a>
                                <div class="bubble">
                                   <a href="#">
                                        <div class="text">바다로 가즈아~~!</div>
                                   </a>
                                   <span class="travel-bar">
                                       <i>Traveler</i>
                                       <span class="traveler">
                                           &nbsp;권재인
                                       </span>
                                       <div class="stamp_comment_cnt">
                                       	<input type="text" value="20" readonly> 
                                       	<a href="">
                                       		<img src="jpg/comment.png" 	alt="">
                                       	</a>
                                       	<input type="text" value="50" readonly>
                                       	<a href="">
                                       	<img src="jpg/stamp.png" alt="">
                                       	</a>
                                       </div>
                                   </span>
                                </div>
                            </div>
                        </section>
                        
                    </section>
                    <div class="clear"></div>
                </div>
            </section>
            
            <section id="intro">
                <div id="intro-title">
                    <div id="bg_fixed">
                        <h2>추천 여행 코스</h2>
                        <p>
                             많은 여행자들의 플래너를 볼 수 있습니다.<br>
                             생생한 여행 이야기를 만나보세요.
                        </p>
                    </div>
                </div>
                <div id="intro-banner" class="section">
                       <div class="planner_view_group">
                           <ul class="planner_view">
                               <li class="title">
                                   <a href="">
                                       <img src="jpg/profil.jpg" alt="">
                                    </a>
                                   <span class="traveler">
                                       <i>traveler<br></i>
                                       <a href=""><span class="name">손준호</span></a>
                                   </span>
                                   
                                   <span class="date">2018-03-02</span>
                               </li>
                               <li>
                                   <a href="">
                                       <img src="jpg/travel.jpg" alt="" class="cover_bg">
                                   </a>
                               </li>
                               <li class="travel_title">
                                   겨울 내일로 여행 - 5일
                               </li>
                               <li class="content-center">
                                  <div class="group1 group">
                                      <div class="travel_content">
                                       <span>1일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>2일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>3일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                  </div>
                                   <div class="group2 group">
                                        <div class="travel_content">
                                       <span>4일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>5일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>6일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   </div>
                               </li>
                               <li class="round_cover">
                                   <span class="round bu1 click1" onclick="plannerbtn(1)"></span>
                                   <span class="round bu2 click2" onclick="plannerbtn(2)"></span>
                               </li>
                               <li class="bottom_title">
                                   <div class="tag">#개춥다 #케이블카 #겨울바다</div>
                                   <div class="shopping">
                                       <a href="">
                                          <br>
                                           <img src="jpg/장바구니.jpg" alt="">
                                       </a>
                                   </div>
                               </li>
                           </ul>
                           <ul class="planner_view">
                               <li class="title">
                                   <a href="">
                                       <img src="jpg/profil.jpg" alt="">
                                    </a>
                                   <span class="traveler">
                                       <i>traveler<br></i>
                                       <a href=""><span class="name">손준호</span></a>
                                   </span>
                                   
                                   <span class="date">2018-03-02</span>
                               </li>
                               <li>
                                   <a href="">
                                       <img src="jpg/travel.jpg" alt="" class="cover_bg">
                                   </a>
                               </li>
                               <li class="travel_title">
                                   겨울 내일로 여행 - 5일
                               </li>
                               <li class="content-center">
                                  <div class="group3 group">
                                      <div class="travel_content">
                                       <span>1일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>2일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>3일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                  </div>
                                   <div class="group4 group">
                                        <div class="travel_content">
                                       <span>4일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>5일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>6일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   </div>
                               </li>
                               <li class="round_cover">
                                   <span class="round bu1 click3" onclick="plannerbtn(3)"></span>
                                   <span class="round bu2 click4" onclick="plannerbtn(4)"></span>
                               </li>
                               <li class="bottom_title">
                                   <div class="tag">#개춥다 #케이블카 #겨울바다</div>
                                   <div class="shopping">
                                       <a href="">
                                          <br>
                                           <img src="jpg/장바구니.jpg" alt="">
                                       </a>
                                   </div>
                               </li>
                           </ul>
                           <ul class="planner_view">
                               <li class="title">
                                   <a href="">
                                       <img src="jpg/profil.jpg" alt="">
                                    </a>
                                   <span class="traveler">
                                       <i>traveler<br></i>
                                       <a href=""><span class="name">손준호</span></a>
                                   </span>
                                   
                                   <span class="date">2018-03-02</span>
                               </li>
                               <li>
                                   <a href="">
                                       <img src="jpg/travel.jpg" alt="" class="cover_bg">
                                   </a>
                               </li>
                               <li class="travel_title">
                                   겨울 내일로 여행 - 5일
                               </li>
                               <li class="content-center">
                                  <div class="group5 group">
                                      <div class="travel_content">
                                       <span>1일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>2일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>3일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                  </div>
                                   <div class="group6 group">
                                        <div class="travel_content">
                                       <span>4일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>5일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>6일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   </div>
                               </li>
                               <li class="round_cover">
                                   <span class="round bu1 click5" onclick="plannerbtn(5)"></span>
                                   <span class="round bu2 click6" onclick="plannerbtn(6)"></span>
                               </li>
                               <li class="bottom_title">
                                   <div class="tag">#개춥다 #케이블카 #겨울바다</div>
                                   <div class="shopping">
                                       <a href="">
                                          <br>
                                           <img src="jpg/장바구니.jpg" alt="">
                                       </a>
                                   </div>
                               </li>
                           </ul>
                           <ul class="planner_view">
                               <li class="title">
                                   <a href="">
                                       <img src="jpg/profil.jpg" alt="">
                                    </a>
                                   <span class="traveler">
                                       <i>traveler<br></i>
                                       <a href=""><span class="name">손준호</span></a>
                                   </span>
                                   
                                   <span class="date">2018-03-02</span>
                               </li>
                               <li>
                                   <a href="">
                                       <img src="jpg/travel.jpg" alt="" class="cover_bg">
                                   </a>
                               </li>
                               <li class="travel_title">
                                   겨울 내일로 여행 - 5일
                               </li>
                               <li class="content-center">
                                  <div class="group7 group">
                                      <div class="travel_content">
                                       <span>1일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>2일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>3일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                  </div>
                                   <div class="group8 group">
                                        <div class="travel_content">
                                       <span>4일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                       <span>5일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   <div class="travel_content">
                                    <span>6일차-여수<br></span>
                                       1.빅오쇼<br>
                                       2.케이블카<br>
                                       3.레일 바이크<br>
                                       4.엑스포<br>
                                       5.아쿠아리움<br>
                                       6.검은모래해변<br>
                                   </div>
                                   </div>
                               </li>
                               <li class="round_cover">
                                   <span class="round bu1 click7" onclick="plannerbtn(7)"></span>
                                   <span class="round bu2 click8" onclick="plannerbtn(8)"></span>
                               </li>
                               <li class="bottom_title">
                                   <div class="tag">#개춥다 #케이블카 #겨울바다</div>
                                   <div class="shopping">
                                       <a href="">
                                          <br>
                                           <img src="jpg/장바구니.jpg" alt="">
                                       </a>
                                   </div>
                               </li>
                           </ul>
                       </div>
                       
                    <div class="view">
                       <a href="#">
                        View More
                        </a>
                    </div>
                </div>

            </section>
            
        </section>
        <footer>
            <div class="section">
                <div id="foot_top">
                    <ul>
                        <li>
                            <a href="#">내일로 소개</a>
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
                            <img src="jpg/RailroTour%20LOGO.png" alt="">
                            </a>
                        </h2>
                    </div>
                    <address>내일로 통합 시스템<br>
제작자 : 권재인, 손준호, 사공수기, 김희규, 이슬기, 김동기<br>
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
    
    <script src="js/script.js"></script>
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
     // 버튼코드
     var sIndex = 1;
     show(sIndex)
     
     function plus(n){
       show(sIndex+=n)
     }
     
     function show(n){
       
       var slides = document.getElementsByClassName('myS')
       
       if(n > slides.length ){ sIndex = 1 }
       if(n < 1){ sIndex = slides.length }
       
       for(i=0; i<slides.length; i++){
         slides[i].style.display='none'
       }
       slides[ sIndex-1 ].style.display='block'
     }

  </script>
</body>
</html>