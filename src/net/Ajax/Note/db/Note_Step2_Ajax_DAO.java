package net.Ajax.Note.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
			System.out.println(items);
		}catch(Exception e) {
			System.out.println("More_Info_Select ERROR : "+e);
		}
		return json;
	}
}
