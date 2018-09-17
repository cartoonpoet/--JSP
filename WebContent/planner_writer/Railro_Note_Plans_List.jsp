<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.ArrayList"%>
    <%@ page import="net.note.db.Note_Plans_List_Bean"%>
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
   
   ArrayList<Note_Plans_List_Bean> Plans_List=(ArrayList) request.getAttribute("PlanList");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>서브페이지</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/style.css">
    <link rel="stylesheet" href="./css/note_plans.css">
   
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
                                <dd><a href="../index.html">내일로 소개</a></dd>
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
                                <dd><a href="./Note_Plans_List.pl">내 플래너 목록</a></dd>
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
                        <a href="../index.html">
                            <img src="../jpg/home.jpg" alt="">
                        </a>
                    </div>
                    <div class="listmenu">
                        <button>플래너</button>
                        <ul class="listbox">
                            <li><a href="#">내일로 소개</a></li>
                            <li><a href="#">TOP 100</a></li>
                            <li><a href="#">플래너</a></li>
                            <li><a href="#">내 주변</a></li>
                            <li><a href="sub02.html">고객센터</a></li>
                        </ul>
                    </div>
                    <div class="listmenu">
                         <button>내 플래너 목록</button>
                        <ul class="listbox">
                            <li><a href="#">새 플래너 작성</a></li>
                            <li><a href="#">플래너 목록</a></li>
                        </ul>
                    </div>
                    
                </div>
            </div>
            <div id="sub-con-body" class="section">
                <div class="s-c-b-title">
                    <h3>Railro Note Plans List</h3>
                    <h2>내일로 노트 목록</h2>
                </div>
                <div class="s-c-b-content">
                   
                    <div class="plans_list_rows">
                       <%
                      	 int size=Plans_List.size();
                       	if(size<9){
                       		for(int i=0; i<Plans_List.size(); i++){
                       			
                       %>
                        <a href="./NoticeDetailAction.no?num=<%=Plans_List.get(i).getNote_ID()%>"><ul class="list_item">
                            <li>
                                <img src="./note_plans_list/seoul.jpg" alt="" width="346px" height="200px">
                                <div class="note_info">
                                    <h1><%=Plans_List.get(i).getTravel_Day()%> (<%=Plans_List.get(i).getDay()%>일)</h1>
                                    <h1><%=Plans_List.get(i).getNote_Name() %></h1>
                                </div>
                            </li>
                            <li>
                                <div class="like">
                                 <span class="tema">
                                     	<%=Plans_List.get(i).getTema_Name() %>여행
                                 </span>
                                  <span>
                                      <%=Plans_List.get(i).getView() %>
                                  </span>
                                   <img src="./note_plans_list_jpg/eye.png" alt="" width="20px">
                                    <span>
                                        <%=Plans_List.get(i).getLike() %>
                                    </span>
                                    <img src="./note_plans_list_jpg/foot.png" alt="" width="20px">
                                </div>
                                <div class="route">
                                    <%=Plans_List.get(i).getArea() %>
                                </div>
                                <div class="person">
                                    <img src="./note_plans_list_jpg/user.png" alt="" width="20px">
                                    <span><%=Plans_List.get(i).getName() %></span>
                                    <div class="btn">
                                        <span class="revise" data-num=<%=Plans_List.get(i).getNote_ID()%>>수정</span>
                                        <span style="color: black">|</span>
                                        <span class="remove">삭제</span>
                                    </div>
                                </div>
                            </li>                    
                        </ul></a>
                        <%
                       			}
                        	} 
                       	else{
                       		for(int i=0; i<9; i++){
                        %>
                        	<a href="./NoticeDetailAction.no?num=<%=Plans_List.get(i).getNote_ID()%>"><ul class="list_item">
                            <li>
                                <img src="./note_plans_list/seoul.jpg" alt="" width="346px" height="200px">
                                <div class="note_info">
                                    <h1><%=Plans_List.get(i).getTravel_Day()%> (<%=Plans_List.get(i).getDay()%>일)</h1>
                                    <h1><%=Plans_List.get(i).getNote_Name() %></h1>
                                </div>
                            </li>
                            <li>
                                <div class="like">
                                 <span class="tema">
                                     	<%=Plans_List.get(i).getTema_Name() %>여행
                                 </span>
                                  <span>
                                      <%=Plans_List.get(i).getView() %>
                                  </span>
                                   <img src="./note_plans_list_jpg/eye.png" alt="" width="20px">
                                    <span>
                                        <%=Plans_List.get(i).getLike() %>
                                    </span>
                                    <img src="./note_plans_list_jpg/foot.png" alt="" width="20px">
                                </div>
                                <div class="route">
                                    <%=Plans_List.get(i).getArea() %>
                                </div>
                                <div class="person">
                                    <img src="./note_plans_list_jpg/user.png" alt="" width="20px">
                                    <span><%=Plans_List.get(i).getName() %></span>
                                    <div class="btn">
                                        <span class="revise" data-num=<%=Plans_List.get(i).getNote_ID()%>>수정</span>
                                        <span style="color: black">|</span>
                                        <span class="remove">삭제</span>
                                    </div>
                                </div>
                            </li>                    
                        </ul></a>
                        <%
                       			}
                        	} %>
                    </div>
                    
                    <div id="viewLoading">
                        <img src="./note_plans_list_jpg/ajax-loader.gif" alt="" width="80px">
                    </div>
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
                            <img src="../jpg/RailroTour%20LOGO.png" alt="">
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
        $(document).ready(function () {
        	
        	
            // $.ajax() 메서드를 이용한다.
            $(document).ajaxStart(function(){
              $('#viewLoading').show();
            }).ajaxStop(function() {
              $('#viewLoading').hide();
            })
            
            $(window).scroll(function() {

                if ($(window).scrollTop() == $(document).height() - $(window).height()) {
                    
                }
            });
            
            

            $('.person .btn .revise').on('click', function(e){
                e.preventDefault();  
                var url = "./Note_Step2_SelectAction.pl?num="+$(this).data("num");  
                window.open(url, "_blank"); 

            })
            $('.person .btn .remove').on('click', function(e){
            	e.preventDefault(); 
            	$(this).closest('a').remove();
            	$.ajax({
            		type:'POST',
            		url:'./Note_Remove.pl',
            		data: {
						NoteID:$(this).siblings('.revise').data('num')
            		},
            		async: false,
            		success:function(data){
            			
            		},
            		error:function(data){
            			alert('해당 노트 삭제에 실패하였습니다.');
            		}
            	})
            })
        });
  </script>
</body>
</html>