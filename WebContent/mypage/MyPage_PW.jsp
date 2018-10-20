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

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>서브페이지</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/mypage.css">
   
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
                                <a href="#" class="list_a1">내 정보 홈</a>
                                
                            </li>
                            <li class="list02 list" onmouseover="bgcolor(2)" onmouseout="removecolor(2)">
                                <a href="#" class="list_a2">내 정보 관리</a>
                            </li>
                            <li class="list03 list" onmouseover="bgcolor(3)" onmouseout="removecolor(3)">
                                <a href="#" class="list_a3 selected">비밀번호 변경</a>
                            </li>
<!--
                            <li class="list04 list" onmouseover="bgcolor(4)" onmouseout="removecolor(4)">
                                <a href="#" class="list_a4">내 주변</a>
                            </li>
-->
                            <li class="list05 list" onmouseover="bgcolor(5)" onmouseout="removecolor(5)">
                                <a href="#" class="list_a5">회원탈퇴</a>
                            </li>
                        </ul>
                    </nav>
                    <!--
                    <div class="s-menu">
                        <div class="section">
                           <div class="float">
                            <dl class="hoverbg1 hoverbg" onmouseover="bgcolor(1)" onmouseout="removecolor(1)">
                                            <dt>지우지 말것</dt>
                                <dd><a href="./index.html">내일로 소개</a></dd>
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

                                <dd><a href="sub02.html">공지사항</a></dd>
                                <dd><a href="#">자주묻는질문</a></dd>
                                <dd><a href="#">불량사용자 신고</a></dd>
                            </dl>
                            </div>
                        </div>
                    </div>
                    -->
                </div>
            </section>
        </header>
        
        <section id="sub-imgbanner">
            <div class="section">
                <div class="sub-img-text">
                    <h3>My Page</h3>
                    <h2>
                        마이페이지
                    </h2>
                </div>
            </div>
        </section>
        <section id="sub-content">
            
            <div id="sub-con-body" class="section">
<!--
                <div class="s-c-b-title">
                    <h3>My Page</h3>
                    <h2>마이페이지</h2>
                </div>
-->
                <div class="notify">
                    <span class="pwimg"></span>
                    <div class="title">주기적인<span class="textcolor">(6개월)</span> 비밀번호 변경을 통해 개인정보를 안전하게 보호하세요.</div>
                </div>
                <form action="페이지 경로" id="passwordchange">
                    <div class="pw1">
                        <span>현재 비밀번호</span>
                        <input type="password" class="password oldpassword" placeholder="현재 비밀번호를 입력해 주세요." name="oldpassword" maxlength="32">
                    </div>
                    <div class="pw1">
                        <span>새 비밀번호</span>
                        <input type="password" class="password newpassword" placeholder="새 비밀번호를 입력해 주세요." name="newpassword" maxlength="32">
                    </div>
                    <div class="tip">
                        <span class="tipimg">TIP</span>
                        <ul class="tiptext">
                            <li>&#8226; 비밀번호는 8~32자의 영문 대/소문자, 숫자, 특수문자를 조합하여 사용하실 수 있습니다.</li>
                            <li>&#8226; 쉬운 비밀번호나 자주 쓰는 사이트의 비밀번호가 같을 경우, 도용되기 쉬워 주기적으로 변경하세요.</li>
                            <li>&#8226; 비밀번호에 특수문자를 사용하면 안전도가 더욱 높아집니다.</li>
                        </ul>
                    </div>
                    <div class="btngroup">
                        <a href="#" class="previous">이전으로</a>
                        <input type="submit" value="저장" class="submit">
                    </div>
                </form>
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
    
<!--    <script src="./js/script.js"></script>-->
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
        
    $(document).ready(function(){
        $('#passwordchange').submit(function(){
            if($('.oldpassword').val().length==0){
                alert('현재 비밀번호를 입력해주세요.');
                return false;
            }
            if($('.newpassword').val().length==0){
                alert('새 비밀번호를 입력해주세요.');
                return false;
            }
            if($('.newpassword').val()==$('.oldpassword').val()){
                alert('현재 비밀번호와 새 비밀번호는 같을 수 없습니다.');
                return false;
            }
            if($('.newpassword').val().length<8){
                alert('비밀번호는 8자이상으로 해주세요.');
                return false;
            }
            return true;
        })
    });
  </script>
</body>
</html>