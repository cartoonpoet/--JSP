package net.note.db;

import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import etc.function.DB_Connection;

public class Train_Select_DAO extends DB_Connection{
	
	public ArrayList<Train_Select_Bean> getTrainTime(String date, String start_code, String end_code){
		ArrayList<Train_Select_Bean> data=new ArrayList<Train_Select_Bean>();
		
		try {
			URL url=new URL("http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?ServiceKey="+Train_Key+"&numOfRows=1&pageNo=1&depPlaceId="+start_code+"&arrPlaceId="+end_code+"&depPlandTime="+date+"&_type=json");
			InputStreamReader isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
			JSONObject items=(JSONObject) JSONValue.parseWithException(isr); 
			JSONObject obj=(JSONObject) items.get("response");
			obj=(JSONObject) obj.get("body");
			int totalCount=Integer.parseInt(obj.get("totalCount").toString());
			
			for(int i=1; i<=totalCount; i++) {
				url=new URL("http://openapi.tago.go.kr/openapi/service/TrainInfoService/getStrtpntAlocFndTrainInfo?ServiceKey="+Train_Key+"&numOfRows=1&pageNo="+i+"&depPlaceId="+start_code+"&arrPlaceId="+end_code+"&depPlandTime="+date+"&_type=json");
				isr = new InputStreamReader(url.openConnection().getInputStream(),"UTF-8");
				items=(JSONObject) JSONValue.parseWithException(isr);
				obj=(JSONObject) items.get("response");
				obj=(JSONObject) obj.get("body");
				obj=(JSONObject) obj.get("items");
				obj=(JSONObject) obj.get("item");
				System.out.println(obj);
				data.add(new Train_Select_Bean());
				data.get(data.size()-1).setTrain_kind(obj.get("traingradename").toString()); //차량 종류
				data.get(data.size()-1).setStart_time(obj.get("depplandtime").toString()); //출발시간
				data.get(data.size()-1).setEnd_time(obj.get("arrplandtime").toString());//도착시간
				data.get(data.size()-1).setStart_station(obj.get("depplacename").toString());//출발지
				data.get(data.size()-1).setEnd_station(obj.get("arrplacename").toString());//도착지
				data.get(data.size()-1).setPrice(Integer.parseInt(obj.get("adultcharge").toString()));//가격
			}
			
		}catch(Exception e) {
			System.out.println("getTrainTime ERROR : "+e);
		}
		
		return data;
	}
}
