var NoteName;
var day_array=new Array(0);

for(var i=0; i<$('.day_arrange>button').length; i++){
    day_array.push(new Array());
}

$('.area_name .area').text($('.day_arrange button:first-child .date_area').text());

$(document).ready(function(){
    $('.hash_add>.hash_add_btn').on('click', function(){
        var hash_tag=$(this).siblings('input').val();
        var select=$('.radio_group>input[name=kind]:checked').val();
        var memo=$('.hash_add>textarea').val();
        
        if(hash_tag==''){
            alert('해시태그를 입력해주세요.');
            return;
        }
        else if(select==undefined){
            alert('음식점, 관광지, 이동수단 중 하나를 선택하여 주세요');
            return;
        }
        
        //각 일차에 맞게 일정 추가
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
        
        
        day_array[num].push({
            ID:0,
            Title:hash_tag,
            Kind:select,
            img:'./planner_Step2_JPG/hashtag.png',
            latlng : new daum.maps.LatLng(33.450705, 126.570677),
            memo:memo
        })
        
        display(day_array[num]);
        
        $(this).siblings('input').val('');
        $('.hash_add>textarea').val('');
    })
    
    $('.radio_group>label').on("click", function(){
        $(this).find('img').css('border', '1px solid black');
        $(this).siblings().find('img').css('border', '1px solid white');
    })
    
    var day, index, num;
    $(".edit_route #route_add").sortable({
      axis: 'y',
        start:function(event, ui){
            index=ui.item.index();
                /* 각 Day에 맞게 일정 추가 */
            var day_cnt=$('.day_arrange>button').length;

            var Color_Hex='#1a7ad9'; // 선택되어 있는 RGB 색상 코드

            for(var i=0; i<day_cnt; i++){
                compareColor=rgb2hex($(".day_arrange>button").eq(i).css("background-Color"));
                if(Color_Hex==compareColor){
                    num=i;
                    break;
                }
            }
        },
        stop:function(event, ui){
            var save;
            
            if(ui.item.index()<index){
                for(var i=index; ui.item.index()<i; i--){
                    save=day_array[num][i];
                    day_array[num][i]=day_array[num][i-1];
                    day_array[num][i-1]=save;
                }
            }
            else{
                for(var i=index; i<ui.item.index(); i++){
                    save=day_array[num][i];
                    day_array[num][i]=day_array[num][i+1];
                    day_array[num][i+1]=save;
                }
            }

            
            display(day_array[num]);
        }
    });
    
    $(".search_data .img").on("click", function(){
        
        var content_id=$(this).parent().data("contentid");
        var content_type_id=$(this).parent().data("contenttypeid");
        
        $.ajax({
        	type:'post',
        	url:'./Note_More_Info.pl',
        	data:{
        		contentid:content_id,
        		contenttypeid:content_type_id
        	},
        	dataType:"json",
    		success:function(data){
    			/*
    			 * JSON 만든 것
    			 * Content_id = 고유번호
    			 * Content_type_id = 타입 번호(관광지/음식점)
    			 * Title = 제목
    			 * Address = 주소
    			 * Overview = 개요
    			 * Infocenter = 전화번호
    			 * Mainimg= 메인이미지
    			 * Opentime = 오픈시각
    			 * Mainmenu=메인메뉴
    			 */
    			$('.More_Info').attr('data-contentid', data.Content_id);
    			$('.More_Info').attr('data-contenttypeid', data.Content_type_id);
    			$('.info_scrolling>.thumbnail').attr('src', data.Mainimg); //메인 이미지
    			$('.Main_Title>h1').text(data.Title); //제목
    			$('.info_scrolling .info').html(data.Overview); //내용
    			$('.description .phone').html(data.Infocenter); //전화번호
    			$('.description .addr').text(data.Address); //주소
    			
    			if(data.Content_type_id==12){
    				$('.description .open').css('display', 'none');
    				$('.description .open_time').css('display', 'none');
    				$('.description .menu').css('display', 'none');
    				$('.description .menu_list').css('display', 'none');
    			}
    			else if(data.Content_type_id==39){
    				$('.description .open_time').text(data.Opentime); //영업시간
    				$('.description .menu_list').html(data.Mainmenu); //메인메뉴
    				$('.description .open').css('display', 'block');
    				$('.description .open_time').css('display', 'block');
    				$('.description .menu').css('display', 'block');
    				$('.description .menu_list').css('display', 'block');
    			}
    		},
    		error:function(request,status,error){
    	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
    	    }
        })
        
        $(".More_Info").animate({"margin-right":'+=500'});
    })
    $(".More_Info>.top>.right").on("click", function(){
        $(".More_Info").animate({"margin-right":'-=500'});
    })
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
        image=$(this).parent().siblings('.img').find('img').attr('src');
        
        day_array[num].push({
            ID:Content_ID,
            Title:Title,
            Kind:Kind,
            img:image,
            latlng : new daum.maps.LatLng(33.450705, 126.570677)
        })

        
        display(day_array[num]);
    })
    
    $(".Note_title").on("click", function(){ //클릭시 수정할 수 있는 폼으로 변환
        NoteName=$(this).text();
        Save_Note_Name=$(this).text();
        
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
        else if(NoteName!=Save_Note_Name){
        	$.ajax({
        		type:'get',
        		url:'./Note_Name_Update.pl',
        		data: {
        			NoteName: NoteName,
        			NoteID: NoteID
        		},
        		success:function(data){
        			alert('전송완료');
        		},
        		error:function(data){
        			alert('변경에 실패하였습니다.');
        			return;
        		}
        	})
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
        lat=$(this).data('lat');//위도
        lng=$(this).data('lng');//경도
        
        map.panTo(new daum.maps.LatLng(lat, lng));
        
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
            $('.search_result>.all').css('display', 'block');
            $('.search_result>.all').siblings().css('display', 'none');
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
            $('.search_result>.hash_add').css('display', 'block');
            $('.search_result>.hash_add').siblings().css('display', 'none');
        }
    })
    $('.search_form .area_search').keydown(function(key){
        var keyword=$(this).val();
        
        if (key.keyCode == 13) {
            $.ajax({
                type : "GET",
                url : "39.127.8.42:8080/Railro_Tour_WEB/SearchAction.se",
                data:keyword,
                error : function(){
                    alert('통신실패!!');
                },
                success : function(data){
                    alert("통신데이터 값 : " + data) ;
                }
            })
            $(this).val('');
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
        
        var position=$(this).parent().parent().index();

           day_array[num].splice(position, 1);
           display(day_array[num]);
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
function create(num, img, title, kind, lat, lng, ID){ //동적 추가 
    return '<div class="route" data-ID="'+ID+'" data-lat="'+lat+'" data-lng="'+lng+'"><div class="curcle">'+(num+1)+'</div><img src="'+img+'" alt="" width="80px" height="75px"><ul class="route_info"><li class="title">'+title+'</li><li class="kind">'+kind+'</li></ul><div class="btn_group"><img src="./jpg/cancel_btn.png" class="delete_btn"></div></div>'
}

