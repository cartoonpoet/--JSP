<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>내일로 노트 :: 상세일정 만들기</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/Planner_STEP2.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
    


</head>
<body>
    <header>
            <img src="./planner_Step2_JPG/LOGO.png" alt="">
                <div class="Note_title">내일로 노트명</div>
                <div class="Note_edit_title">
                    <input type="text" value="" class="Note_title_input" maxlength="20">
                    <input type="submit" value="확인" name="Note_title_submit" class="Note_title_submit">
                </div>
            <a href=""><input type="button" value="저장 후 닫기"></a>
    </header>
    <section>
        <div class="side_left">
            <div class="edit_day">
<!--전체일정란-->
                <button class="date_change" id="datepicker">
                    06.7~16.11
                    <img src="./planner_Step2_JPG/settings.png" alt="" width="15px">
                    <span>EDIT</span>
                </button>
                <div class="day_arrange">
                    <button>
                        <ul>
                            <li class="day">
                                <div class="day_num">DAY1</div>
                                <div class="day_str">목요일</div>
                            </li>
                            <li class="date">
                                <div class="date_num">06.07</div>
                                <div class="date_area">서울</div>
                            </li>
                        </ul>
                    </button>
                    <button>
                        <ul>
                            <li class="day">
                                <div class="day_num">DAY2</div>
                                <div class="day_str">금요일</div>
                            </li>
                            <li class="date">
                                <div class="date_num">06.08</div>
                                <div class="date_area">대구</div>
                            </li>
                        </ul>
                    </button>
                    <button>
                        <ul>
                            <li class="day">
                                <div class="day_num">DAY3</div>
                                <div class="day_str">토요일</div>
                            </li>
                            <li class="date">
                                <div class="date_num">06.09</div>
                                <div class="date_area">여수</div>
                            </li>
                        </ul>
                    </button>
                    <button>
                        <ul>
                            <li class="day">
                                <div class="day_num">DAY4</div>
                                <div class="day_str">일요일</div>
                            </li>
                            <li class="date">
                                <div class="date_num">06.10</div>
                                <div class="date_area">전주</div>
                            </li>
                        </ul>
                    </button>
                    <button>
                        <ul>
                            <li class="day">
                                <div class="day_num">DAY5</div>
                                <div class="day_str">월요일</div>
                            </li>
                            <li class="date">
                                <div class="date_num">06.11</div>
                                <div class="date_area">포항</div>
                            </li>
                        </ul>
                    </button>
                    
                </div>
            </div>
            <div class="edit_route">
                 <div class="top">
                   <div class="day_group">
                        <span class="day">DAY1</span>
                        <span class="wall">|</span>
                        <span class="date">06.04</span>
                        <span class="week">(목요일)</span>
                        <img src="./planner_Step2_JPG/refresh.png" alt="" width="20px" class="refresh">
                   </div>
                 </div>
                 <div class="bottom" id="route_add">
<!--
                     <div class="route">
                        <div class="curcle">1</div>
                         <img src="./jpg/bn01.jpg" alt="" width="80px" height="75px">
                         <ul class="route_info">
                             <li class="title">북촌 한옥 마을</li>
                             <li class="kind">관광지</li>
                             <li class="time">10:00 ~ 11:30</li>
                         </ul>
                        <div class="btn_group">
                             <img src="./jpg/cancel_btn.png" alt="" class="delete_btn">
                         </div>
                     </div>
-->
                     
                 </div>
            </div>
        </div>
        
        <div id="map"></div>
        <div class="route_search">
            <div class="search_area">
                <span class="area_name">
                    <ul>
                        <li class="area">대구</li>
                        <li class="area_change">
                            도시변경
                        </li>
                    </ul>
                </span>
                <span class="search_form">
                    <input type="text" maxlength="30" placeholder="장소 검색" class="area_search">
                    <div class="checks">
                        <input type="radio" id="ex_rd1" name="search_type" checked>
                        <label for="ex_rd1">도시내 검색</label>
                        <input type="radio" id="ex_rd2" name="search_type">
                        <label for="ex_rd2">전체 검색</label> 
                    </div>
                </span>
                <span class="kind_select">
                    <div class="all"><img src="./planner_Step2_JPG/ALL2.png" alt="" width="50px"></div>
                    <div class="camera"><img src="./planner_Step2_JPG/camera1.png" alt="" width="50px"></div>
                    <div class="food"><img src="./planner_Step2_JPG/food1.png" alt="" width="50px"></div>
                    <div class="cart"><img src="./planner_Step2_JPG/cart1.png" alt="" width="50px"></div>
                    <div class="tag"><img src="./planner_Step2_JPG/tag1.png" alt="" width="50px"></div>
                </span>
            </div>
            <div class="search_result">
                <div class="search_data">
                    <div class="img">
                        <img src="./jpg/bn01.jpg" alt="" width="100px" height="100px">
                    </div>
                    <ul class="info_group">
                       <input type="hidden" class="content_id" value="0">
                        <li class="title">북촌 한옥 마을</li>
                        <li class="sub_title">관광지</li>
                        <li class="comment_like_group">
                            <div>
                                <img src="./planner_Step2_JPG/comment.png" alt="" width="15px">
                                523
                            </div>
                            <div>|</div>
                            <div>
                                <img src="./planner_Step2_JPG/stamp.png" alt="" width="15px">
                                24
                            </div>
                            <div style="margin-left: 20px; color: #0062ff; font-weight: bold">
                                DAY1
                            </div>
                        </li>
                        <li class="check">
                            <input type="text" placeholder="출발시간" class="start">
                            ~
                            <input type="text" placeholder="도착시간" class="end">
                        </li>
                    </ul>
                    <div class="add_btn">
                        <img src="./map_image/add.png" alt="" class="route_add_btn">
                    </div>
                </div>
                
            </div>
        </div>
    </section>
        <script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=c75ebef98aa832875a335d779a7dc27a"></script>
    <script src="./js/Planner_STEP2_Daum_map.js"></script>
        <script src="./datepicker/jquery-ui.js"></script>
    <link rel="stylesheet" href="./datepicker/jquery-ui.css">
    <link rel="stylesheet" href="./timepicker/css/timepicker.css">
    <script src="./timepicker/js/timepicker.js"></script>
    <script src="./js/Planner_STEP2.js"></script>
</body>
</html>