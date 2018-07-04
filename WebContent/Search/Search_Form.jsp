<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="net.search.db.TouristBean" %>
<%@ page import="net.search.db.RestaurantBean" %>
<%@ page import="javax.servlet.http.*" %>
<%
	ArrayList<TouristBean> Tour=(ArrayList)request.getAttribute("Tour");
	ArrayList<RestaurantBean> Food=(ArrayList)request.getAttribute("Food");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Railro Tour - 전라도편</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="bxslide/dist/jquery.bxslider.css">
    <link rel="stylesheet" href="./css/Search_from.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    <script src="bxslide/dist/jquery.bxslider.min.js">
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
                                <a href="./html/loginform.html">로그인</a>
                                <span></span>
                                </li>
                            <li>
                                <a href="./html/sign_up_from.html">회원가입</a>
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

                                <dd><a href="#">새 플래너 작성</a></dd>
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

        <section id="content">
             <div class="result_bar">
                <div>
                    	<%=request.getAttribute("KeyWord") %>의 검색 결과입니다. 
                </div>
            </div>
            <ul class="itemlist">
                <li class="itembox selected"><a href="#">전체</a></li>
                <li class="itembox"><a href="#">관광지</a></li>
                <li class="itembox"><a href="#">음식점</a></li>
                <li class="itembox"><a href="#">여행코스</a></li>
                <li class="itembox"><a href="#">내일로 노트</a></li>
                <li class="itembox"><a href="#">내일러</a></li>
                <li class="itembox Accuracy"><a href="#">정확도순</a></li>
                <li class="itembox Latest"><a href="#">최신순</a></li>
            </ul>
            <div class="Contents">
                <div class="Tourist">
                   <div class="Tourist_Title">
                        <h1>관광지</h1>
                        <h2>더보기<img src="./jpg/plus.png" alt=""></h2>
                   </div>
                    <%if(Tour!=null){
                    	if(Tour.size()>3){
                    	for(int i=0; i<4; i++) {
                    		if(Tour.get(i)!=null){%>
                    			<ul class="Tour_group">
                        			<li class="TourImage"><img src=<%=Tour.get(i).getImg() %> alt="" width="148px" height="111px"></li>
                        			<div class="TourInfo">
                            		<li class="TourTitle"><%=Tour.get(i).getTitle() %></li>
                            		<li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            		<li class="TourContent"><%=Tour.get(i).getAddress1() %></li>
                        			</div>
                   				 </ul>
                    <%		}
                    		else{ break;}
                    		}
                    	}
                    	else if(Tour.size()>1){
                    		for(int i=0; i<Tour.size(); i++){%>
                    		     <ul class="Tour_group">
                        			<li class="TourImage"><img src=<%=Tour.get(i).getImg() %> alt="" width="148px" height="111px"></li>
                        			<div class="TourInfo">
                            		<li class="TourTitle"><%=Tour.get(i).getTitle() %></li>
                            		<li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            		<li class="TourContent"><%=Tour.get(i).getAddress1() %></li>
                        			</div>
                   				 </ul>
                    	<%}
                    	}else{%>
                    			  <ul class="Tour_group">
                        			<li class="TourImage"><img src=<%=Tour.get(0).getImg() %> alt="" width="148px" height="111px"></li>
                        			<div class="TourInfo">
                            		<li class="TourTitle"><%=Tour.get(0).getTitle() %></li>
                            		<li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            		<li class="TourContent"><%=Tour.get(0).getAddress1() %></li>
                        			</div>
                   				 </ul>
                    	<%} %>
                    <%}else{%>
                    	<h1>정보가 존재하지 않습니다.</h1>
                    <%} %>
                </div>
                <div class="Tourist Restaurant">
                    <div class="Tourist_Title">
                        <h1>음식점</h1>
                        <h2>더보기<img src="./jpg/plus.png" alt=""></h2>
                   </div>
                   <%if(Food!=null){
                	   if(Food.size()>3){
                    	for(int i=0; i<4; i++) {
                    		if(Food.get(i)!=null){%>
                    			<ul class="Tour_group">
                        			<li class="TourImage"><img src=<%=Food.get(i).getImg() %> alt="" width="148px" height="111px"></li>
                        			<div class="TourInfo">
                            		<li class="TourTitle"><%=Food.get(i).getTitle() %></li>
                            		<li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            		<li class="TourContent"><%=Food.get(i).getAddress1() %></li>
                        			</div>
                   				 </ul>
                    <%		}
                    		else if(Food.get(i)==null){ break;}
                    	} 
                    	%>
                    	
                    	
                    <%}
                	   else if(Food.size()>1){
                	         for(int i=0; i<Food.size(); i++){%>
                	   			<ul class="Tour_group">
                        			<li class="TourImage"><img src=<%=Food.get(i).getImg() %> alt="" width="148px" height="111px"></li>
                        			<div class="TourInfo">
                            		<li class="TourTitle"><%=Food.get(i).getTitle() %></li>
                            		<li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            		<li class="TourContent"><%=Food.get(i).getAddress1() %></li>
                        			</div>
                   				 </ul>
                	   <%}
                	   }else{%>
                    	<ul class="Tour_group">
                        	<li class="TourImage"><img src=<%=Food.get(0).getImg() %> alt="" width="148px" height="111px"></li>
                        	<div class="TourInfo">
                            <li class="TourTitle"><%=Food.get(0).getTitle() %></li>
                            <li class="shopping_add"><img src="./jpg/plus.png" alt=""></li>
                            <li class="TourContent"><%=Food.get(0).getAddress1() %></li>
                        	</div>
                   		</ul>
                    <%}
                	 }else{%>
                    	<h1>정보가 존재하지 않습니다.</h1>
                    <%} %>
                </div>
                <div class="Travel_Course Tourist">
                    <div class="Tourist_Title">
                        <h1>여행코스</h1>
                        <h2>더보기<img src="./jpg/plus.png" alt=""></h2>
                   </div>
                    <div class="Tour_Course">
                        <div class="top">
                            <span class="circle">1코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">2코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">3코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">4코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">5코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">6코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">7코스</span>
                        </div>
                        <div class="area_info">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                    </div>
                    <div class="Tour_Course">
                        <div class="top">
                            <span class="circle">1코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">2코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">3코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">4코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">5코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">6코스</span>
                            <span class="line">
                                <img src="./jpg/dot_line.png" alt="" width="90px">
                            </span>
                            <span class="circle">7코스</span>
                        </div>
                        <div class="area_info">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                        <div class="area_info second">
                            <ul>
                                <li class="map_name">여수</li>
                                <li class="map_img"><img src="./map_image/%EA%B4%91%EC%A3%BC.jpg" alt="" width="55px"></li>
                            </ul>
                        </div>
                    </div>
                </div>
<!--
                <div class="Tourist Railro_Note">
                    <div class="Tourist_Title">
                        <h1>내일로 노트</h1>
                        <h2>더보기<img src="./jpg/plus.png" alt=""></h2>
                   </div>
                    
                </div>
                <div class="Railro"></div>
-->
            </div>
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