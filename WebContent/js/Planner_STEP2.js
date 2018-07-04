var NoteName;
var day_array=new Array(0);

for(var i=0; i<$('.day_arrange>button').length; i++){
    day_array.push(new Array());
}

$(document).ready(function(){
    $(".day_group>.refresh").on("click", function(){
        /* 각 Day에 맞게 일정 추가 */
        var day_cnt=$('.day_arrange>button').length;

        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        var input=confirm('해당 일정을 초기화 하시겠습니까?');
        
        if(input==true){
            day_array[num]=new Array(0);
        }
        display(day_array[num]);
    })
    
    $(".add_btn>.route_add_btn").on("click", function(){
        /* 추가 전에 유효성 검사 */
        if($(this).parent().siblings('.info_group').find('.start').val()==''){
            alert('출발시간을 입력해주세요.');
            return false;
        }
        else if($(this).parent().siblings('.info_group').find('.end').val()==''){
            alert('도착시간을 입력해주세요.');
            return false;
        }
        
        /* 각 Day에 맞게 일정 추가 */
        var day_cnt=$('.day_arrange>button').length;
//        alert($(".day_arrange>button").eq(0).find(".date_area").text());
        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }
        
        // 콘텐츠 ID, 이름, 종류, 출발시각, 도착시각(방문시각)    
        Content_ID=$(this).parent().siblings('.info_group').find('.content_id').val();
        Title=$(this).parent().siblings('.info_group').find('.title').text();
        Kind=$(this).parent().siblings('.info_group').find('.sub_title').text();
        Start_Time=$(this).parent().siblings('.info_group').find('.start').val();
        End_Time=$(this).parent().siblings('.info_group').find('.end').val();
        image=$(this).parent().siblings('.img').find('img').attr('src');
        
        //시를 : 부분을 토큰 분리
        Start_Token=Start_Time.split(':');
        End_Token=End_Time.split(':');
        
        //시를 분으로 환산하는 과정
        Start_Minute=(Number(Start_Token[0])*60)+Number(Start_Token[1]); 
        End_Minute=(Number(End_Token[0])*60)+Number(End_Token[1]);
        
        if(End_Minute-Start_Minute<10){
            alert('출발시간과 도착시간은 10분이상 차이나야 합니다.');
            return;
        }
        //해당 일차의 일정이 없으면 그냥 추가한다.
        if(day_array[num].length==0){
            day_array[num].push({
                ID:Content_ID,
                Title:Title,
                Kind:Kind,
                img:image,
                Start_Time:Start_Time,
                End_Time:End_Time,
                lat:'0',
                lng:'0',
                Start_M:Start_Minute,
                End_M:End_Minute
            })
        }
        // 해당 일차에 일정이 있으면 검토 후 비교한 후에 추가한다.
        else{
            var count=0;
            
            for(var i=0; i<day_array[num].length; i++){
                for(var o=Start_Minute+1; o<End_Minute; o++){
                    
                }
            }
            day_array[num].splice(count, 0, {
                ID:Content_ID,
                Title:Title,
                Kind:Kind,
                img:image,
                Start_Time:Start_Time,
                End_Time:End_Time,
                lat:'0',
                lng:'0',
                Start_M:Start_Minute,
                End_M:End_Minute
            })
        }
        
        display(day_array[num]);
    })
    
    $(".start").timepicker();
    $(".end").timepicker();
    $(".Note_title").on("click", function(){ //클릭시 수정할 수 있는 폼으로 변환
        NoteName=$(this).text();
        $(this).css("display", "none");
        $(".Note_edit_title .Note_title_input").val(NoteName);
        $(".Note_edit_title").css("display", "block");
    })
    $(".Note_title_submit").on("click", function(){ //수정폼에서 확정 폼
        NoteName=$(this).siblings(".Note_title_input").val();
        
        if(NoteName==""){
            alert("공백은 입력하실 수 없습니다");
            return false;
        }
        
        $(this).parent().css("display", "none");
        $(".Note_title").text(NoteName);
        $(".Note_title").css("display", "block");
    })
    $(".day_arrange>button").on("click", function(){
        
        var position=$(this).index();
        
        $(this).css("backgroundColor", "#1a7ad9");
        $(this).siblings().css("backgroundColor", "#203341");
        
        day_num=$(this).find(".day_num").text(); //DAY 숫자
        day_str=$(this).find(".day_str").text(); //요일
        date_num=$(this).find(".date_num").text(); //날짜
        date_area=$(this).find(".date_area").text(); //지역명
        
        $(".area_name>ul>.area").text(date_area);
        $(".day_group>.day").text(day_num);
        $(".day_group>.date").text(date_num);
        $(".day_group>.week").text('('+day_str+')');
       
        display(day_array[position]);
    })
    $(".kind_select>div").on("click", function(){
        var class_name=$(this).attr('class');
        
        if(class_name=='all'){
            $(this).find('img').attr('src', './planner_Step2_JPG/ALL2.png');
        $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
        }
        else if(class_name=='camera'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/camera2.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
        }
        else if(class_name=='food'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
        $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/food2.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
        }
        else if(class_name=='cart'){
        $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
        $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/cart2.png');
            $('.kind_select>.tag').find('img').attr('src', './planner_Step2_JPG/tag1.png');
        }
        else if(class_name=='tag'){
            $('.kind_select>.all').find('img').attr('src', './planner_Step2_JPG/ALL1.png');
        $('.kind_select>.camera').find('img').attr('src','./planner_Step2_JPG/camera1.png');
            $('.kind_select>.food').find('img').attr('src', './planner_Step2_JPG/food1.png');
            $('.kind_select>.cart').find('img').attr('src', './planner_Step2_JPG/cart1.png');
            $(this).find('img').attr('src', './planner_Step2_JPG/tag2.png');
        }
    })
    $('.search_form .area_search').keydown(function(key){
        if (key.keyCode == 13) {
            alert('엔터키 입력 시 작업할 내용');
        }

    })

    $(document).on("click", ".delete_btn",function(){

        var day_cnt=$('.day_arrange>button').length;

        var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드
        var num; // 선택되어 있는 index 번호저장
        for(var i=0; i<day_cnt; i++){
            compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
            if(Color_Hex==compareColor){
                num=i;
                break;
            }
        }

        var time_compare=$(this).parent().siblings('.route_info').find('.time').text();

        for(var i=0; i<day_array[num].length; i++){
           if(time_compare==(day_array[num][i].Start_Time+' ~ '+day_array[num][i].End_Time)){
               day_array[num].splice(i, 1);
               display(day_array[num]);
               break;
            }
        }
    })
})



function rgb2hex(rgb) {
     if (  rgb.search("rgb") == -1 ) {
          return rgb;
     } else {
          rgb = rgb.match(/^rgba?\((\d+),\s*(\d+),\s*(\d+)(?:,\s*(\d+))?\)$/);
          function hex(x) {
               return ("0" + parseInt(x).toString(16)).slice(-2);
          }
          return "#" + hex(rgb[1]) + hex(rgb[2]) + hex(rgb[3]);
     }
}


function display(array){
    document.getElementById('route_add').innerHTML="";
    for(var i=0; i<array.length; i++){
        document.getElementById('route_add').innerHTML+=create(i, array[i].img, array[i].Title, array[i].Kind, array[i].Start_Time, array[i].End_Time, array[i].lat, array[i].lng, array[i].ID);
    }
}
function create(num, img, title, kind, start, end, lat, lng, ID){ //동적 추가 
    return '<div class="route" data-ID="'+ID+'" data-lat="'+lat+'" data-lng="'+lng+'"><div class="curcle">'+(num+1)+'</div><img src="'+img+'" alt="" width="80px" height="75px"><ul class="route_info"><li class="title">'+title+'</li><li class="kind">'+kind+'</li><li class="time">'+start+' ~ '+end+'</li></ul><div class="btn_group"><img src="./jpg/cancel_btn.png" alt="" class="delete_btn"></div></div>'
}