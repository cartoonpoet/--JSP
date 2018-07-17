package net.Ajax.Note.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
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
		JSONArray resultArray=new JSONArray();
		
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
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+Key+"&pageNo=1&numOfRows="+TourtotalCount+"&areaCode="+sigungucode+"&sigunguCode="+areacode+"&MobileOS=ETC&arrange=O&listYN=Y&MobileApp=AppTest&contentTypeId=12&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			items=(JSONObject) JSONValue.parseWithException(isr); 
	
			obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			jsonarray= (JSONArray) obj.get("item");
			
			for(int i=0; i<jsonarray.size(); i++) {
				result=new JSONObject();
				
				obj=(JSONObject) jsonarray.get(i);
				
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

				resultArray.add(result);
				
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
			
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey="+Key+"&pageNo=1&startPage=1&numOfRows="+FoodtotalCount+"&pageSize=1&MobileApp=RailroTour&MobileOS=ETC&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&listYN=Y&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			items=(JSONObject) JSONValue.parseWithException(isr); 
			obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			jsonarray= (JSONArray) obj.get("item");
			
			for(int i=0; i<jsonarray.size(); i++) {
				result=new JSONObject();
				obj=(JSONObject) jsonarray.get(i);
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
				
				result.put("lat", obj.get("mapy"));
				result.put("lng", obj.get("mapx"));
				result.put("sigungucode", obj.get("sigungucode"));
				result.put("title", obj.get("title"));

				resultArray.add(result);
			}
			
		}catch(Exception e) {
			System.out.println("ALL_Select_Action FOOD ERROR : "+e);
		}
		
		jsonobject.put("item", resultArray);
		jsonobject.put("totalCount", TourtotalCount+FoodtotalCount);
		
	
		return jsonobject;
	}
	
	public JSONObject TourAPI_Select(int contenttypeid, int sigungucode, int areacode) {
		JSONArray jsonarray=new JSONArray();
		JSONObject result=new JSONObject();
		
		JSONObject jsonobject=new JSONObject();
		
		JSONArray resultArray=new JSONArray();
		
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+Key+"&areaCode="+sigungucode+"&sigunguCode="+areacode+"&MobileOS=ETC&arrange=O&listYN=Y&MobileApp=AppTest&contentTypeId="+contenttypeid+"&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			int totalCount=Integer.parseInt(obj.get("totalCount").toString());
			System.out.println("totalCount : "+totalCount);
			
			url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey="+Key+"&pageNo=1&numOfRows="+totalCount+"&areaCode="+sigungucode+"&sigunguCode="+areacode+"&MobileOS=ETC&arrange=O&listYN=Y&MobileApp=AppTest&contentTypeId="+contenttypeid+"&_type=json");
			isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			items=(JSONObject) JSONValue.parseWithException(isr); 
			obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			obj=(JSONObject) obj.get("items");
			jsonarray= (JSONArray) obj.get("item");
			
			for(int i=0; i<jsonarray.size(); i++) {
				result=new JSONObject();
				obj=(JSONObject) jsonarray.get(i);
				
				
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

				resultArray.add(result);
			}
			
			jsonobject.put("item", resultArray);
			jsonobject.put("totalCount", totalCount);
			
		}catch(Exception e) {
			System.out.println("TourAPI_Select ERROR : "+e);
		}
		return jsonobject;
	}

	public JSONObject Search_within_city (String keyword, int sigungucode, int areacode) {
		JSONArray jsonarray=new JSONArray();
		JSONObject FinalData=new JSONObject();
		int total=0;
		JSONObject result=new JSONObject();
		
		JSONArray resultArray=new JSONArray();
		
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
			
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");

			int totalCount=Integer.parseInt(obj.get("totalCount").toString());
			total=totalCount;
			System.out.println("도시내 검색 관광지 개수 : "+totalCount);
			if(totalCount>0) {
				url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows="+totalCount+"&pageSize=1&listYN=Y&arrange=O&contentTypeId=12&areaCode="+sigungucode+"&sigunguCode="+areacode+"&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				items=(JSONObject) JSONValue.parseWithException(isr); 
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");
				obj=(JSONObject) obj.get("items");
				if(totalCount>1) {
					jsonarray = (JSONArray) obj.get("item");

					for (int i = 0; i < jsonarray.size(); i++) {
						result = new JSONObject();
						obj = (JSONObject) jsonarray.get(i);

						if (obj.get("addr1") != null) {
							result.put("addr1", obj.get("addr1").toString());
						} else {
							result.put("addr1", null);
						}

						if (obj.get("addr2") != null) {
							result.put("addr2", obj.get("addr2").toString());
						} else {
							result.put("addr2", null);
						}

						result.put("areacode", obj.get("areacode").toString());

						if (obj.get("cat1") != null) {
							result.put("cat1", obj.get("cat1").toString());
						} else {
							result.put("cat1", null);
						}

						if (obj.get("cat2") != null) {
							result.put("cat2", obj.get("cat2").toString());
						} else {
							result.put("cat2", null);
						}

						if (obj.get("cat3") != null) {
							result.put("cat3", obj.get("cat3").toString());
						} else {
							result.put("cat3", null);
						}

						result.put("contentid", obj.get("contentid").toString());
						result.put("contenttypeid", obj.get("contenttypeid").toString());

						if (obj.get("firstimage") != null) {
							result.put("firstimage", obj.get("firstimage").toString());
						} else {
							result.put("firstimage", "./jpg/no_image.gif");
						}

						if (obj.get("firstimage2") != null) {
							result.put("firstimage2", obj.get("firstimage2").toString());
						} else {
							result.put("firstimage2", "./jpg/no_image.gif");
						}

						result.put("lat", obj.get("mapy"));
						result.put("lng", obj.get("mapx"));
						result.put("sigungucode", obj.get("sigungucode"));
						result.put("title", obj.get("title"));

						resultArray.add(result);
					}
				}
				else {
					result = new JSONObject();
					obj = (JSONObject) obj.get("item");

					if (obj.get("addr1") != null) {
						result.put("addr1", obj.get("addr1").toString());
					} else {
						result.put("addr1", null);
					}

					if (obj.get("addr2") != null) {
						result.put("addr2", obj.get("addr2").toString());
					} else {
						result.put("addr2", null);
					}

					result.put("areacode", obj.get("areacode").toString());

					if (obj.get("cat1") != null) {
						result.put("cat1", obj.get("cat1").toString());
					} else {
						result.put("cat1", null);
					}

					if (obj.get("cat2") != null) {
						result.put("cat2", obj.get("cat2").toString());
					} else {
						result.put("cat2", null);
					}

					if (obj.get("cat3") != null) {
						result.put("cat3", obj.get("cat3").toString());
					} else {
						result.put("cat3", null);
					}

					result.put("contentid", obj.get("contentid").toString());
					result.put("contenttypeid", obj.get("contenttypeid").toString());

					if (obj.get("firstimage") != null) {
						result.put("firstimage", obj.get("firstimage").toString());
					} else {
						result.put("firstimage", "./jpg/no_image.gif");
					}

					if (obj.get("firstimage2") != null) {
						result.put("firstimage2", obj.get("firstimage2").toString());
					} else {
						result.put("firstimage2", "./jpg/no_image.gif");
					}

					result.put("lat", obj.get("mapy"));
					result.put("lng", obj.get("mapx"));
					result.put("sigungucode", obj.get("sigungucode"));
					result.put("title", obj.get("title"));

					resultArray.add(result);
				}
		}
		}catch(Exception e) {
			System.out.println("Search_within_city TOUR ERROR : "+e);
		}
		//음식점 검색
		try {
			URL url=new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="+Key+"&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows=1&pageSize=1&listYN=Y&arrange=O&contentTypeId=39&areaCode="+sigungucode+"&sigunguCode="+areacode+"&keyword="+URLEncoder.encode(keyword, "UTF-8")+"&_type=json");
			
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			
			int totalCount=Integer.parseInt(obj.get("totalCount").toString());
			System.out.println("도시내 검색 음식점 개수 : "+totalCount);
			total+=totalCount;
			if (totalCount > 0) {
				url = new URL("http://api.visitkorea.or.kr/openapi/service/rest/KorService/searchKeyword?serviceKey="
						+ Key + "&MobileApp=AppTest&MobileOS=ETC&pageNo=1&startPage=1&numOfRows=" + totalCount
						+ "&pageSize=1&listYN=Y&arrange=O&contentTypeId=39&areaCode=" + sigungucode + "&sigunguCode="
						+ areacode + "&keyword=" + URLEncoder.encode(keyword, "UTF-8") + "&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(), "UTF-8");
				items = (JSONObject) JSONValue.parseWithException(isr);
				obj = (JSONObject) items.get("response");
				obj = (JSONObject) obj.get("body");
				obj = (JSONObject) obj.get("items");
				if(totalCount>1) {
					jsonarray = (JSONArray) obj.get("item");

					for (int i = 0; i < jsonarray.size(); i++) {
						result = new JSONObject();
						obj = (JSONObject) jsonarray.get(i);

						if (obj.get("addr1") != null) {
							result.put("addr1", obj.get("addr1").toString());
						} else {
							result.put("addr1", null);
						}

						if (obj.get("addr2") != null) {
							result.put("addr2", obj.get("addr2").toString());
						} else {
							result.put("addr2", null);
						}

						result.put("areacode", obj.get("areacode").toString());

						if (obj.get("cat1") != null) {
							result.put("cat1", obj.get("cat1").toString());
						} else {
							result.put("cat1", null);
						}

						if (obj.get("cat2") != null) {
							result.put("cat2", obj.get("cat2").toString());
						} else {
							result.put("cat2", null);
						}

						if (obj.get("cat3") != null) {
							result.put("cat3", obj.get("cat3").toString());
						} else {
							result.put("cat3", null);
						}

						result.put("contentid", obj.get("contentid").toString());
						result.put("contenttypeid", obj.get("contenttypeid").toString());

						if (obj.get("firstimage") != null) {
							result.put("firstimage", obj.get("firstimage").toString());
						} else {
							result.put("firstimage", "./jpg/no_image.gif");
						}

						if (obj.get("firstimage2") != null) {
							result.put("firstimage2", obj.get("firstimage2").toString());
						} else {
							result.put("firstimage2", "./jpg/no_image.gif");
						}

						result.put("lat", obj.get("mapy"));
						result.put("lng", obj.get("mapx"));
						result.put("sigungucode", obj.get("sigungucode"));
						result.put("title", obj.get("title"));

						resultArray.add(result);
					}
				}
				else {
					result = new JSONObject();
					obj = (JSONObject) obj.get("item");

					if (obj.get("addr1") != null) {
						result.put("addr1", obj.get("addr1").toString());
					} else {
						result.put("addr1", null);
					}

					if (obj.get("addr2") != null) {
						result.put("addr2", obj.get("addr2").toString());
					} else {
						result.put("addr2", null);
					}

					result.put("areacode", obj.get("areacode").toString());

					if (obj.get("cat1") != null) {
						result.put("cat1", obj.get("cat1").toString());
					} else {
						result.put("cat1", null);
					}

					if (obj.get("cat2") != null) {
						result.put("cat2", obj.get("cat2").toString());
					} else {
						result.put("cat2", null);
					}

					if (obj.get("cat3") != null) {
						result.put("cat3", obj.get("cat3").toString());
					} else {
						result.put("cat3", null);
					}

					result.put("contentid", obj.get("contentid").toString());
					result.put("contenttypeid", obj.get("contenttypeid").toString());

					if (obj.get("firstimage") != null) {
						result.put("firstimage", obj.get("firstimage").toString());
					} else {
						result.put("firstimage", "./jpg/no_image.gif");
					}

					if (obj.get("firstimage2") != null) {
						result.put("firstimage2", obj.get("firstimage2").toString());
					} else {
						result.put("firstimage2", "./jpg/no_image.gif");
					}

					result.put("lat", obj.get("mapy"));
					result.put("lng", obj.get("mapx"));
					result.put("sigungucode", obj.get("sigungucode"));
					result.put("title", obj.get("title"));

					resultArray.add(result);
				}
			}
		}catch(Exception e) {
			System.out.println("Search_within_city FOOD ERROR : "+e);
		}
		FinalData.put("item", resultArray);
		FinalData.put("totalCount", total);
		System.out.println("FinalData : "+FinalData);
		
		return FinalData;
	}
	
	public void Plans_Save_Action(int NoteID, int Content_ID, int Content_Type_ID, String Title, String Kind1, String Kind2, int sigungucode, int areacode, String date, String week, String day, int order, String areaname, int day_orders) {
		String sql="insert into note_info2(travel_id, kinds_1, route_name, kinds_2, content_id, content_type_id, sigungu_code, area_code, area_name, weeks, days, dates, orders, day_orders) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int result=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);
			pstmt.setString(2, Kind1);
			pstmt.setString(3, Title);
			pstmt.setString(4, Kind2);
			pstmt.setInt(5, Content_ID);
			pstmt.setInt(6, Content_Type_ID);
			pstmt.setInt(7, sigungucode);
			pstmt.setInt(8, areacode);
			pstmt.setString(9, areaname);
			pstmt.setString(10, week);
			pstmt.setString(11, day);
			pstmt.setString(12, date);
			pstmt.setInt(13, order);
			pstmt.setInt(14, day_orders);
			
			result=pstmt.executeUpdate();

		}catch(Exception ex) {
			System.out.println("Plans_Save_Action ERROR : "+ex);
		}finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
	           if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	
	public void Plans_Update_Action(int StartIndex, int EndIndex, int day_orders, int sigungucode, int areacode, String date, String day, int Content_ID, int Content_Type_ID, int note_id) {
		int result=0;
		String sql="";
		if(EndIndex<StartIndex) {
			try {
				sql="update note_info2 set orders=-1 where orders=? and day_orders=? and sigungu_code=? and area_code=? and dates=? and days=? and content_id=? and content_type_id=? and travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, StartIndex);
				pstmt.setInt(2, day_orders);
				pstmt.setInt(3, sigungucode);
				pstmt.setInt(4, areacode);
				pstmt.setString(5, date);
				pstmt.setString(6, day);
				pstmt.setInt(7, Content_ID);
				pstmt.setInt(8, Content_Type_ID);
				pstmt.setInt(9, note_id);
				result=pstmt.executeUpdate();
				System.out.println("변경됫 개수1 : "+result);
				
				sql="update note_info2 set orders=orders+1 where orders<? AND orders>=? and day_orders=? and sigungu_code=? and area_code=? and dates=? and days=? and travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, StartIndex);
				pstmt.setInt(2, EndIndex);
				pstmt.setInt(3, day_orders);
				pstmt.setInt(4, sigungucode);
				pstmt.setInt(5, areacode);
				pstmt.setString(6, date);
				pstmt.setString(7, day);
				pstmt.setInt(8, note_id);
				result=pstmt.executeUpdate();
				
				System.out.println("변경됫 개수2 : "+result);
				
				sql="update note_info2 set orders=? where orders=-1 and day_orders=? and sigungu_code=? and area_code=? and dates=? and days=? and content_id=? and content_type_id=? and travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, EndIndex);
				pstmt.setInt(2, day_orders);
				pstmt.setInt(3, sigungucode);
				pstmt.setInt(4, areacode);
				pstmt.setString(5, date);
				pstmt.setString(6, day);
				pstmt.setInt(7, Content_ID);
				pstmt.setInt(8, Content_Type_ID);
				pstmt.setInt(9, note_id);
				result=pstmt.executeUpdate();
				System.out.println("변경됫 개수3 : "+result);
			}catch(Exception ex) {
				System.out.println("Note_Name_Update ERROR1 : "+ex);
			}finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
		        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
			}
		}
		else {
			try {
				sql="update note_info2 set orders=-1 where orders=? and day_orders=? and sigungu_code=? and area_code=? and dates=? and days=? and content_id=? and content_type_id=? and travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, StartIndex);
				pstmt.setInt(2, day_orders);
				pstmt.setInt(3, sigungucode);
				pstmt.setInt(4, areacode);
				pstmt.setString(5, date);
				pstmt.setString(6, day);
				pstmt.setInt(7, Content_ID);
				pstmt.setInt(8, Content_Type_ID);
				pstmt.setInt(9, note_id);
				result=pstmt.executeUpdate();
				System.out.println("변경됫 개수1 : "+result);
				
				sql = "update note_info2 set orders=orders-1 where orders>? AND orders<=? and day_orders=? and sigungu_code=? and area_code=? and dates=? and days=? and travel_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, StartIndex);
				pstmt.setInt(2, EndIndex);
				pstmt.setInt(3, day_orders);
				pstmt.setInt(4, sigungucode);
				pstmt.setInt(5, areacode);
				pstmt.setString(6, date);
				pstmt.setString(7, day);
				pstmt.setInt(8, note_id);
				result = pstmt.executeUpdate();
				System.out.println("변경됫 개수2 : "+result);

				sql = "update note_info2 set orders=? where orders=-1 and day_orders=? and sigungu_code=? and area_code=? and dates=? and days=? and content_id=? and content_type_id=? and travel_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, EndIndex);
				pstmt.setInt(2, day_orders);
				pstmt.setInt(3, sigungucode);
				pstmt.setInt(4, areacode);
				pstmt.setString(5, date);
				pstmt.setString(6, day);
				pstmt.setInt(7, Content_ID);
				pstmt.setInt(8, Content_Type_ID);
				pstmt.setInt(9, note_id);
				result=pstmt.executeUpdate();
				System.out.println("변경됫 개수3 : "+result);
			} catch (Exception ex) {
				System.out.println("Note_Name_Update ERROR2 : " + ex);
			} finally {
				if (rs != null)
					try {
						rs.close();
					} catch (SQLException ex) {
					}
				if (pstmt != null)
					try {
						pstmt.close();
					} catch (SQLException ex) {
					}
			}
		}
	}
}
