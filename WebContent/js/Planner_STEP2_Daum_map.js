var mapContainer = document.getElementById('map'), // 지도를 표시할 div  
    mapOption = {
        center : new daum.maps.LatLng($('.day_arrange button').first().data('lat'), $('.day_arrange button').first().data('lng')), // 지도의 중심좌표
        level : 9
    // 지도의 확대 레벨
    };
 	
    var map = new daum.maps.Map(mapContainer, mapOption); // 지도를 생성합니다
    

	var positions=new Array(0);
	
	for(var i=0; i<$('.search_data').length; i++){ //다중 마커 찍기위해 데이터 수집
		var title=$('.search_data').eq(i).find('.title').text();
		var lat=$('.search_data').eq(i).data('lat');
		var lng=$('.search_data').eq(i).data('lng');
		var type=$('.search_data').eq(i).data('contenttypeid');
		var id=$('.search_data').eq(i).data('contentid');

		positions.push({
			title:title,
			latlng: new daum.maps.LatLng(lat, lng),
			contenttypeid:type,
			contentid:id
		});
		
	}
	
	var TourMarkerImg = "./daum_map/marker_img/TourMarker.png"; //이미지 마커 주소
	var FoodMarkerImg = "./daum_map/marker_img/FoodMarker.png"; //이미지 마커 주소
	var TourHoverMarkerImg = "./daum_map/marker_img/TourMarker_Hover.png";
	var FoodHoverMarkerImg = "./daum_map/marker_img/FoodMarker_Hover.png";
	
	var markers=new Array(0);
	
	for(var i=0; i<positions.length; i++){
		// 마커 이미지의 이미지 크기 입니다
	    var imageSize = new daum.maps.Size(42, 43); 
	    var markerImage;
	    // 마커 이미지를 생성합니다    
	    if(positions[i].contenttypeid==12){ //해당 위치가 관광지이면
	    	markerImage = new daum.maps.MarkerImage(TourMarkerImg, imageSize); 
		    
//		    markers.push(new daum.maps.Marker({
//		        map: map, // 마커를 표시할 지도
//		        position: positions[i].latlng, // 마커를 표시할 위치
//		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
//		        image : markerImage, // 마커 이미지 
//		        contenttypeid : positions[i].contenttypeid,
//		        contentid : positions[i].contentid
//		    }));
		    
	    }
	    else if(positions[i].contenttypeid==39){ //해당 위치가 음식점이면
	    	markerImage = new daum.maps.MarkerImage(FoodMarkerImg, imageSize);

//		    markers.push(new daum.maps.Marker({
//		        map: map, // 마커를 표시할 지도
//		        position: positions[i].latlng, // 마커를 표시할 위치
//		        title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
//		        image : markerImage, // 마커 이미지 
//		        contenttypeid : positions[i].contenttypeid, //타입
//		        contentid : positions[i].contentid // 고유번호
//		    }));
		    
	    }
	    
	    markers.push(addMarker(positions[i].latlng, positions[i].contenttypeid, markerImage, positions[i].title));
	    
	}
	
	// 마커를 생성하고 지도 위에 표시하고, 마커에 mouseover, mouseout, click 이벤트를 등록하는 함수입니다
	function addMarker(position, contentTypeId, markerImage, title, contentId) {

	    // 기본 마커이미지, 오버 마커이미지, 클릭 마커이미지를 생성합니다
	    var normalImage = markerImage;
	    var overImage;
	    if(contentTypeId==12){
	    	overImage = new daum.maps.MarkerImage(TourHoverMarkerImg, imageSize);
	    }
	    else if(contentTypeId==39){
	    	overImage = new daum.maps.MarkerImage(FoodHoverMarkerImg, imageSize);
	    }
	    
	    // 마커를 생성하고 이미지는 기본 마커 이미지를 사용합니다
	    var marker = new daum.maps.Marker({
	        map: map,
	        position: position,
	        image: normalImage,
	        title: title
	    });

	    // 마커 객체에 마커아이디와 마커의 기본 이미지를 추가합니다
	    marker.normalImage = normalImage;

	    // 마커에 mouseover 이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'mouseover', function() {

	        // 클릭된 마커가 없고, mouseover된 마커가 클릭된 마커가 아니면
	        // 마커의 이미지를 오버 이미지로 변경합니다
	        marker.setImage(overImage);
	    });

	    // 마커에 mouseout 이벤트를 등록합니다
	    daum.maps.event.addListener(marker, 'mouseout', function() {

	    	marker.setImage(normalImage);
	    });

	    return marker;
	}

	
	
