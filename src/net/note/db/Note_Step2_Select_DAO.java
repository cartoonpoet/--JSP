package net.note.db;

import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

public class Note_Step2_Select_DAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String Key="JXL40bCK2WGOu%2FE1WOGjuALpADt64Wb2mQVwNpxiA0bre%2FV8GozZggM2O01%2FPaTTyNm0A2JahebDf%2FPGwW8jbg%3D%3D";
	URL url;
	
	public Note_Step2_Select_DAO() {
		try {
			Context init=new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/CUBRIDDS");
            con = ds.getConnection();   
		}catch(Exception ex) {
			System.out.println("DB 접속에러:"+ex);
			return;
		}
	}
	
	public String Search_Note_Name(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		String Note_name="";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next())
				Note_name=rs.getString("note_name");
			

		}catch(Exception ex) {
			System.out.println("Search_Note_Name ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return Note_name;
	}
	
	public Date Search_Travel_Start_Day(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		String Start_Day = "";
		Date to = null;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Start_Day=rs.getString("travel_start_day");
			}
			
		
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

			to = transFormat.parse(Start_Day);
			
		}catch(Exception ex) {
			System.out.println("Search_Travel_Start_Day ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}


		return to;
	}
	public int Search_Travel_Day(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		int Travel_day = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Travel_day=rs.getInt("travel_day");
			}
			

			
		}catch(Exception ex) {
			System.out.println("Search_Travel_Day ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return Travel_day;
	}
	
	public String Search_Travel_Tema(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		String Tema="";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Tema=rs.getString("travel_tema");
			}

		}catch(Exception ex) {
			System.out.println("Search_Travel_Tema ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return Tema;
	}
	public int Search_Travel_People(int Travel_ID, String Email_ID) {
		String sql="select * from note_info1 where travel_id=? and email_id=?";
		
		int people=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			pstmt.setString(2, Email_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				people=rs.getInt("travel_people");
			}

		}catch(Exception ex) {
			System.out.println("Search_Travel_People ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return people;
	}
	
	public ArrayList<Note_Step2_Day_List_Bean> Search_Day_List(int Travel_ID){
		ArrayList<Note_Step2_Day_List_Bean> Day_List=new ArrayList<Note_Step2_Day_List_Bean>();
		String sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Travel_ID);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				Day_List.add(new Note_Step2_Day_List_Bean(rs.getString("travel_area_name"), rs.getInt("travel_area_day")));
			}

		}catch(Exception ex) {
			System.out.println("Search_Travel_People ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return Day_List;
	}
	public int do_code_Action(String area) {
		switch(area) {
			case "광산구": return 5;
			case "남구": return 5;
			case "북구": return 5;
			case "동구": return 5;
			case "서구": return 5;
			case "고창군": return 37;
			case "군산시": return 37;
			case "김제시": return 37;
			case "남원시": return 37;
			case "무주군": return 37;
			case "부안군": return 37;
			case "순창군": return 37;
			case "완주군": return 37;
			case "익산시": return 37;
			case "임실군": return 37;
			case "장수군": return 37;
			case "전주시": return 37;
			case "정읍시": return 37;
			case "진안군": return 37;
			case "강진군": return 38;
			case "고흥군": return 38;
			case "곡성군": return 38;
			case "광양시": return 38;
			case "구례군": return 38;
			case "나주시": return 38;
			case "담양군": return 38;
			case "목포시": return 38;
			case "무안군": return 38;
			case "보성군": return 38;
			case "순천시": return 38;
			case "신안군": return 38;
			case "여수시": return 38;
			case "영광군": return 38;
			case "영암군": return 38;
			case "완도군": return 38;
			case "장성군": return 38;
			case "장흥군": return 38;
			case "진도군": return 38;
			case "함평군": return 38;
			case "해남군": return 38;
			case "화순군": return 38;
		}
		return -1;
	}
	public int area_code_Action(String area) {
		switch(area) {
		case "광산구": return 1;
		case "남구": return 2;
		case "북구": return 4;
		case "동구": return 3;
		case "서구": return 5;
		case "고창군": return 1;
		case "군산시": return 2;
		case "김제시": return 3;
		case "남원시": return 4;
		case "무주군": return 5;
		case "부안군": return 6;
		case "순창군": return 7;
		case "완주군": return 8;
		case "익산시": return 9;
		case "임실군": return 10;
		case "장수군": return 11;
		case "전주시": return 12;
		case "정읍시": return 13;
		case "진안군": return 14;
		case "강진군": return 1;
		case "고흥군": return 2;
		case "곡성군": return 3;
		case "광양시": return 4;
		case "구례군": return 5;
		case "나주시": return 6;
		case "담양군": return 7;
		case "목포시": return 8;
		case "무안군": return 9;
		case "보성군": return 10;
		case "순천시": return 11;
		case "신안군": return 12;
		case "여수시": return 13;
		case "영광군": return 16;
		case "영암군": return 17;
		case "완도군": return 18;
		case "장성군": return 19;
		case "장흥군": return 20;
		case "진도군": return 21;
		case "함평군": return 22;
		case "해남군": return 23;
		case "화순군": return 24;
	}
		return -1;
	}
	
	public ArrayList<Note_Step2_ALL_INFO_Bean> Area_Info_Select_Action(int areacode, int sigungucode){
		ArrayList<Note_Step2_ALL_INFO_Bean> Info_List=new ArrayList<Note_Step2_ALL_INFO_Bean>();

		try {
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			String total=obj.get("totalCount").toString();
			int totalCount=Integer.parseInt(total);
			
			System.out.println("관광정보 데이터 개수 : "+totalCount+"개");
			
			for(int i=0; i<totalCount; i++) {
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo="+(i+1)+"&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
				InputStreamReader inputStream = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				
				JSONObject All_Items = (JSONObject) JSONValue.parseWithException(inputStream); 
				
				JSONObject Part_Item=(JSONObject) All_Items.get("response");
				Part_Item=(JSONObject) Part_Item.get("body");
				Part_Item=(JSONObject) Part_Item.get("items");
				Part_Item=(JSONObject) Part_Item.get("item");

				Info_List.add(new Note_Step2_ALL_INFO_Bean());
				Info_List.get(i).setAddr1(Part_Item.get("addr1").toString());
				
				if(Part_Item.get("addr2")!=null) {
					Info_List.get(i).setAddr2(Part_Item.get("addr2").toString());
				}
				else {
					Info_List.get(i).setAddr2(null);
				}
				Info_List.get(i).setAreacode(Integer.parseInt(Part_Item.get("areacode").toString()));

				
				if(Part_Item.get("cat1")!=null) {
					Info_List.get(i).setCat1(Part_Item.get("cat1").toString());
				}
				else {
					Info_List.get(i).setCat1(null);
				}
			
				if(Part_Item.get("cat2")!=null) {
					Info_List.get(i).setCat2(Part_Item.get("cat2").toString());
				}
				else {
					Info_List.get(i).setCat2(null);
				}
				
				if(Part_Item.get("cat3")!=null) {
					Info_List.get(i).setCat3(Part_Item.get("cat3").toString());
				}
				else {
					Info_List.get(i).setCat3(null);
				}
				
				Info_List.get(i).setContent_id(Integer.parseInt(Part_Item.get("contentid").toString()));
				Info_List.get(i).setContenttype_id(Integer.parseInt(Part_Item.get("contenttypeid").toString()));

				
				if(Part_Item.get("firstimage")!=null) {
					Info_List.get(i).setFirstimage(Part_Item.get("firstimage").toString());
				}
				else {
					Info_List.get(i).setFirstimage("./jpg/no_image.gif");
				}
				
				if(Part_Item.get("firstimage2")!=null) {
					Info_List.get(i).setFirstimage2(Part_Item.get("firstimage2").toString());
				}
				else {
					Info_List.get(i).setFirstimage2("./jpg/no_image.gif");
				}
				
				Info_List.get(i).setMapx(Double.parseDouble(Part_Item.get("mapx").toString()));
				Info_List.get(i).setMapy(Double.parseDouble(Part_Item.get("mapy").toString()));
				Info_List.get(i).setSigungucode(Integer.parseInt(Part_Item.get("sigungucode").toString()));
				Info_List.get(i).setTitle(Part_Item.get("title").toString());
			}
		}catch(Exception e) {
			System.out.println("Area_Info_Select_Action ERROR1  : "+e);
		}
		
		try {
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			String total=obj.get("totalCount").toString();
			int size=Info_List.size();
			int totalCount=size+Integer.parseInt(total);
			System.out.println("음식점 데이터 개수 : "+totalCount);
			
			for(int i=Info_List.size(); i<totalCount; i++) {
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo="+((i%size)+1)+"&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
				InputStreamReader inputStream = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				
				JSONObject All_Items = (JSONObject) JSONValue.parseWithException(inputStream); 
				
				JSONObject Part_Item=(JSONObject) All_Items.get("response");
				Part_Item=(JSONObject) Part_Item.get("body");
				Part_Item=(JSONObject) Part_Item.get("items");
				Part_Item=(JSONObject) Part_Item.get("item");

				Info_List.add(new Note_Step2_ALL_INFO_Bean());
				Info_List.get(i).setAddr1(Part_Item.get("addr1").toString());
				
				if(Part_Item.get("addr2")!=null) {
					Info_List.get(i).setAddr2(Part_Item.get("addr2").toString());
				}
				else {
					Info_List.get(i).setAddr2(null);
				}
				Info_List.get(i).setAreacode(Integer.parseInt(Part_Item.get("areacode").toString()));

				
				if(Part_Item.get("cat1")!=null) {
					Info_List.get(i).setCat1(Part_Item.get("cat1").toString());
				}
				else {
					Info_List.get(i).setCat1(null);
				}
			
				if(Part_Item.get("cat2")!=null) {
					Info_List.get(i).setCat2(Part_Item.get("cat2").toString());
				}
				else {
					Info_List.get(i).setCat2(null);
				}
				
				if(Part_Item.get("cat3")!=null) {
					Info_List.get(i).setCat3(Part_Item.get("cat3").toString());
				}
				else {
					Info_List.get(i).setCat3(null);
				}
				
				Info_List.get(i).setContent_id(Integer.parseInt(Part_Item.get("contentid").toString()));
				Info_List.get(i).setContenttype_id(Integer.parseInt(Part_Item.get("contenttypeid").toString()));

				
				if(Part_Item.get("firstimage")!=null) {
					Info_List.get(i).setFirstimage(Part_Item.get("firstimage").toString());
				}
				else {
					Info_List.get(i).setFirstimage("./jpg/no_image.gif");
				}
				
				if(Part_Item.get("firstimage2")!=null) {
					Info_List.get(i).setFirstimage2(Part_Item.get("firstimage2").toString());
				}
				else {
					Info_List.get(i).setFirstimage2("./jpg/no_image.gif");
				}
				
				Info_List.get(i).setMapx(Double.parseDouble(Part_Item.get("mapx").toString()));
				Info_List.get(i).setMapy(Double.parseDouble(Part_Item.get("mapy").toString()));
				Info_List.get(i).setSigungucode(Integer.parseInt(Part_Item.get("sigungucode").toString()));
				Info_List.get(i).setTitle(Part_Item.get("title").toString());
			}
		}catch(Exception e) {
			System.out.println("Area_Info_Select_Action ERROR2  : "+e);
		}
		
		return Info_List;
	}
}
