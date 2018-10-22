<%@page import="java.util.ArrayList"%>
<%@page import="net.note.db.Train_Select_Bean"%>
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
   ArrayList<Train_Select_Bean> data=(ArrayList<Train_Select_Bean>) request.getAttribute("data");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>기차시간 조회</title>
    <link rel="stylesheet" href="./css/commen.css">
    <link rel="stylesheet" href="./css/popup.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js">
    </script>
</head>
<body>
    <center>기차시간 조회</center>
    <!--열차종류, 출발지, 도착지, 출발시간, 도착시간, 가격-->
    <ul class="bar">
        <li class="train_kind">열차 종류</li>
        <li class="start_station">출발지</li>
        <li class="end_station">도착지</li>
        <li class="start_time">출발시간</li>
        <li class="end_time">도착시간</li>
        <li class="price">가격</li>
    </ul>
    <div class="informations">
    <%for(int i=0; i<data.size(); i++){ %>
    <ul class="train_info">
        <li class="train_kind"><%=data.get(i).getTrain_kind() %></li>
        <li class="start_station"><%=data.get(i).getStart_station() %></li>
        <li class="end_station"><%=data.get(i).getEnd_station() %></li>
        <li class="start_time"><%=data.get(i).getStart_time().substring(8, 10) %>:<%=data.get(i).getStart_time().substring(10, 12) %></li>
        <li class="end_time"><%=data.get(i).getEnd_time().substring(8, 10) %>:<%=data.get(i).getEnd_time().substring(10, 12) %></li>
        <li class="price"><%=data.get(i).getPrice() %></li>
    </ul>
    <%} %>
    </div>
    <center class="close"><button class="btn">닫기</button></center>
    <script>
        $(document).ready(function(){
            $('.btn').click(function(e){
                window.close();
            })
        })

    </script>
</body>
</html>