package net.Ajax.Note.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.jasper.tagplugins.jstl.core.Url;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import net.note.db.Note_Step2_ALL_INFO_Bean;

public class Note_Step2_Ajax_DAO{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String Key="JXL40bCK2WGOu%2FE1WOGjuALpADt64Wb2mQVwNpxiA0bre%2FV8GozZggM2O01%2FPaTTyNm0A2JahebDf%2FPGwW8jbg%3D%3D";

	public Note_Step2_Ajax_DAO() {
		try {
			Context init=new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/CUBRIDDS");
            con = ds.getConnection();   
		}catch(Exception ex) {
			System.out.println("DB Connect Error :"+ex);
			return;
		}
	}
	
	public void Note_Name_Update(int Travel_ID, String Change_Name) {
		String sql="update note_info1 set note_name=? where travel_id=?";
		int result=-1;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, Change_Name);
			pstmt.setInt(2, Travel_ID);
			result=pstmt.executeUpdate();
			
			System.out.println("변경결과 : "+result);
		}catch(Exception ex) {
			System.out.println("Note_Name_Update ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	public JSONObject More_Info_Select(int content_id, int content_type_id) {
		JSONObject json=new JSONObject();
		
		System.out.println("content_id : "+content_id);
		
		json.put("Content_id", content_id);
		json.put("Content_type_id", content_type_id);
		
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailCommon?ServiceKey="+Key+"&contentId="+content_id+"&defaultYN=Y&MobileOS=ETC&MobileApp=AppTest&contentTypeId="+content_type_id+"&overviewYN=Y&addrinfoYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			obj=(JSONObject) obj.get("item");
			
			json.put("Title", obj.get("title"));
			json.put("Address", obj.get("addr1"));
			json.put("Overview", obj.get("overview"));
			
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailIntro?ServiceKey="+Key+"&contentId="+content_id+"&contentTypeId="+content_type_id+"&MobileOS=ETC&MobileApp=AppTest&_type=json&introYN=Y");
			isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			items=(JSONObject) JSONValue.parseWithException(isr);
			obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			obj=(JSONObject) obj.get("item");
			
			if(content_type_id==12) {
				json.put("Infocenter", obj.get("infocenter"));
				
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey="+Key+"&contentId="+content_id+"&imageYN=Y&MobileOS=ETC&MobileApp=AppTest&sublmageYN=Y&_type=json&numOfRows=1");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				items=(JSONObject) JSONValue.parseWithException(isr);
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");

				
				if(Integer.parseInt(obj.get("totalCount").toString())>0) {
					obj=(JSONObject) obj.get("items");
					obj=(JSONObject) obj.get("item");
					json.put("Mainimg", obj.get("originimgurl"));
					//System.out.println("관광지 사진 있음");
				}
				else {
					json.put("Mainimg", "./jpg/no_image.gif");
					//System.out.println("관광지 사진 없음");
				}
			}
			else if(content_type_id==39) {
				json.put("Infocenter", obj.get("infocenterfood"));
				json.put("Opentime", obj.get("opentimefood"));
				json.put("Mainmenu", obj.get("treatmenu"));
				
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/detailImage?ServiceKey="+Key+"&contentId="+content_id+"&imageYN=N&MobileOS=ETC&MobileApp=AppTest&_type=json&numOfRows=1");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				items=(JSONObject) JSONValue.parseWithException(isr);
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");
				
				if(Integer.parseInt(obj.get("totalCount").toString())>0) {
					obj=(JSONObject) obj.get("items");
					obj=(JSONObject) obj.get("item");
					json.put("Mainimg", obj.get("originimgurl"));
					//System.out.println("음식점 사진 있음");
				}
				else {
					json.put("Mainimg", "./jpg/no_image.gif");
					//System.out.println("음식점 사진 없음");
				}
			}

			
			//System.out.println("JSON 합치기 전 : "+obj);
			//System.out.println("JSON 합치기 후 : "+json);
		}catch(Exception e) {
			System.out.println("More_Info_Select ERROR : "+e);
		}
		return json;
	}
	public JSONObject Filter_Tour_Select(int contenttypeid, int sigungucode, int areacode) {
		JSONArray jsonarray=new JSONArray();
		JSONObject result=new JSONObject();
		
		JSONObject jsonobject=new JSONObject();
		
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+Key+"&areaCode="+sigungucode+"&sigunguCode="+areacode+"&MobileOS=ETC&arrange=O&listYN=Y&MobileApp=AppTest&contentTypeId="+contenttypeid+"&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			int totalCount=Integer.parseInt(obj.get("totalCount").toString());
			
			
			for(int i=0; i<totalCount; i++) {
				result=new JSONObject();
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+Key+"&pageNo="+(i+1)+"&numOfRows=1&areaCode="+sigungucode+"&sigunguCode="+areacode+"&MobileOS=ETC&arrange=O&listYN=Y&MobileApp=AppTest&contentTypeId="+contenttypeid+"&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				items=(JSONObject) JSONValue.parseWithException(isr);
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");
				obj=(JSONObject) obj.get("items");
				obj=(JSONObject) obj.get("item");
				result.put("addr1", obj.get("addr1").toString());
				
				if(obj.get("addr2")!=null) {
					result.put("addr2",obj.get("addr2").toString());
				}
				else {
					result.put("addr2", null);
				}
				
				result.put("areacode", obj.get("areacode").toString());
				
				if(obj.get("cat1")!=null) {
					result.put("cat1", obj.get("cat1").toString());
				}
				else {
					result.put("cat1", null);
				}
			
				if(obj.get("cat2")!=null) {
					result.put("cat2", obj.get("cat2").toString());
				}
				else {
					result.put("cat2", null);
				}
				
				if(obj.get("cat3")!=null) {
					result.put("cat3", obj.get("cat3").toString());
				}
				else {
					result.put("cat3", null);
				}
				
				result.put("contentid", obj.get("contentid").toString());
				result.put("contenttypeid", obj.get("contenttypeid").toString());

				
				if(obj.get("firstimage")!=null) {
					result.put("firstimage", obj.get("firstimage").toString());
				}
				else {
					result.put("firstimage", "./jpg/no_image.gif");
				}
				
				if(obj.get("firstimage2")!=null) {
					result.put("firstimage2", obj.get("firstimage2").toString());
				}
				else {
					result.put("firstimage2", "./jpg/no_image.gif");
				}
				
				result.put("lat", obj.get("mapy").toString());
				result.put("lng", obj.get("mapx").toString());
				result.put("sigungucode", obj.get("sigungucode").toString());
				result.put("title", obj.get("title").toString());

				jsonarray.add(result);
			}
			
			
			jsonobject.put("item", jsonarray);
			jsonobject.put("totalCount", totalCount);
		}catch(Exception e) {
			System.out.println("Filter_Tour_Select ERROR : "+e);
		}
		return jsonobject;
	}
	
	public JSONObject ALL_Select_Action(int contenttypeid, int sigungucode, int areacode) {
		JSONArray jsonarray=new JSONArray();
		JSONObject result=new JSONObject();
		JSONObject jsonobject=new JSONObject();
		
		int FoodtotalCount=0;
		int TourtotalCount=0;
		try {
			URL	url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
		
			String total=obj.get("totalCount").toString();
			TourtotalCount=Integer.parseInt(total);
		
			System.out.println("AJAX 관광정보 데이터 개수 : "+TourtotalCount+"개");
		
			for(int i=0; i<TourtotalCount; i++) {
				result=new JSONObject();
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo="+(i+1)+"&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				
				items=(JSONObject) JSONValue.parseWithException(isr);
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");
				obj=(JSONObject) obj.get("items");
				obj=(JSONObject) obj.get("item");
				
				
				if(obj.get("addr1")!=null) {
					result.put("addr1", obj.get("addr1").toString());
				}
				else {
					result.put("addr1", null);
				}
				
			
				if(obj.get("addr2")!=null) {
					result.put("addr2",obj.get("addr2").toString());
				}
				else {
					result.put("addr2", null);
				}
				
				result.put("areacode", obj.get("areacode").toString());
				
		
				if(obj.get("cat1")!=null) {
					result.put("cat1", obj.get("cat1").toString());
				}
				else {
					result.put("cat1", null);
				}
			
				if(obj.get("cat2")!=null) {
					result.put("cat2", obj.get("cat2").toString());
				}
				else {
					result.put("cat2", null);
				}
		
				if(obj.get("cat3")!=null) {
					result.put("cat3", obj.get("cat3").toString());
				}
				else {
					result.put("cat3", null);
				}
				
				result.put("contentid", obj.get("contentid").toString());
				result.put("contenttypeid", obj.get("contenttypeid").toString());

				if(obj.get("firstimage")!=null) {
					result.put("firstimage", obj.get("firstimage").toString());
				}
				else {
					result.put("firstimage", "./jpg/no_image.gif");
				}

				if(obj.get("firstimage2")!=null) {
					result.put("firstimage2", obj.get("firstimage2").toString());
				}
				else {
					result.put("firstimage2", "./jpg/no_image.gif");
				}

				result.put("lat", obj.get("mapy"));

				result.put("lng", obj.get("mapx"));

				result.put("sigungucode", obj.get("sigungucode"));

				result.put("title", obj.get("title"));

				jsonarray.add(result);

			}
			
		}catch(Exception e) {
			System.out.println("ALL_Select_Action TOUR ERROR : "+e);
		}
		
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			
			JSONObject items = (JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			String total=obj.get("totalCount").toString();
			
			FoodtotalCount=Integer.parseInt(total);
			System.out.println("AJAX 음식점 데이터 개수 : "+FoodtotalCount);
			
			for(int i=0; i<FoodtotalCount; i++) {
				result=new JSONObject();
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo="+(i+1)+"&startPage=1&numOfRows=1&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				
				items=(JSONObject) JSONValue.parseWithException(isr);
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");
				obj=(JSONObject) obj.get("items");
				obj=(JSONObject) obj.get("item");
				
				if(obj.get("addr1")!=null) {
					result.put("addr1", obj.get("addr1").toString());
				}
				else {
					result.put("addr2", null);
				}
				
				if(obj.get("addr2")!=null) {
					result.put("addr2",obj.get("addr2").toString());
				}
				else {
					result.put("addr2", null);
				}
				
				result.put("areacode", obj.get("areacode").toString());
				
				if(obj.get("cat1")!=null) {
					result.put("cat1", obj.get("cat1").toString());
				}
				else {
					result.put("cat1", null);
				}
			
				if(obj.get("cat2")!=null) {
					result.put("cat2", obj.get("cat2").toString());
				}
				else {
					result.put("cat2", null);
				}
				
				if(obj.get("cat3")!=null) {
					result.put("cat3", obj.get("cat3").toString());
				}
				else {
					result.put("cat3", null);
				}
				
				result.put("contentid", obj.get("contentid").toString());
				result.put("contenttypeid", obj.get("contenttypeid").toString());

				
				if(obj.get("firstimage")!=null) {
					result.put("firstimage", obj.get("firstimage").toString());
				}
				else {
					result.put("firstimage", "./jpg/no_image.gif");
				}
				
				if(obj.get("firstimage2")!=null) {
					result.put("firstimage2", obj.get("firstimage2").toString());
				}
				else {
					result.put("firstimage2", "./jpg/no_image.gif");
				}
				
				result.put("lat", obj.get("mapy"));
				result.put("lng", obj.get("mapx"));
				result.put("sigungucode", obj.get("sigungucode"));
				result.put("title", obj.get("title"));

				jsonarray.add(result);
				
			}
			
		}catch(Exception e) {
			System.out.println("ALL_Select_Action FOOD ERROR : "+e);
		}
		
		jsonobject.put("item", jsonarray);
		jsonobject.put("totalCount", TourtotalCount+FoodtotalCount);
		
		
		return jsonobject;
	}
}
