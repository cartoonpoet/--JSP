package net.note.db;

import java.util.ArrayList;

import net.train.db.Train_Bean;

public class Note_Detail_Info_Bean {
	int Day_num, Content_ID, Content_Type_ID, Sigungu_code, Area_code, like_cnt;
	// 일차, 콘텐츠 아이디, 콘텐츠 타입 아이디, 시군구 코드, 지역 코드
	String Route_name, Date, Kinds_1, Kinds_2, Memo, Area_name, Days, Img, Node_ID;
	// 지역명, 날짜, 분류1, 분류2, 메모
	ArrayList<Train_Bean> Start_Station=new ArrayList<Train_Bean>();
	ArrayList<Train_Bean> End_Station=new ArrayList<Train_Bean>();
	//출발역 도착역
	
	public void add_Start_Station(String nodename, String nodeid) {
		Start_Station.add(new Train_Bean(nodename, nodeid));
	}
	public void add_End_Station(String nodename, String nodeid) {
		End_Station.add(new Train_Bean(nodename, nodeid));
	}
	public int Start_Station_Size() {
		return Start_Station.size();
	}
	public int End_Station_Size() {
		return End_Station.size();
	}
	public int getDay_num() {
		return Day_num;
	}
	public String getNode_ID() {
		return Node_ID;
	}
	public void setNode_ID(String node_ID) {
		Node_ID = node_ID;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public String getImg() {
		return Img;
	}
	public void setImg(String img) {
		Img = img;
	}
	public String getDays() {
		return Days;
	}
	public void setDays(String days) {
		Days = days;
	}
	public String getArea_name() {
		return Area_name;
	}
	public void setArea_name(String area_name) {
		Area_name = area_name;
	}
	public void setDay_num(int day_num) {
		Day_num = day_num;
	}
	public int getContent_ID() {
		return Content_ID;
	}
	public void setContent_ID(int content_ID) {
		Content_ID = content_ID;
	}
	public int getContent_Type_ID() {
		return Content_Type_ID;
	}
	public void setContent_Type_ID(int content_Type_ID) {
		Content_Type_ID = content_Type_ID;
	}
	public int getSigungu_code() {
		return Sigungu_code;
	}
	public void setSigungu_code(int sigungu_code) {
		Sigungu_code = sigungu_code;
	}
	public int getArea_code() {
		return Area_code;
	}
	public void setArea_code(int area_code) {
		Area_code = area_code;
	}
	public String getRoute_name() {
		return Route_name;
	}
	public void setRoute_name(String area_name) {
		Route_name = area_name;
	}
	public String getDate() {
		return Date;
	}
	public void setDate(String date) {
		Date = date;
	}
	public String getKinds_1() {
		return Kinds_1;
	}
	public void setKinds_1(String kinds_1) {
		Kinds_1 = kinds_1;
	}
	public String getKinds_2() {
		return Kinds_2;
	}
	public void setKinds_2(String kinds_2) {
		Kinds_2 = kinds_2;
	}
	public String getMemo() {
		return Memo;
	}
	public void setMemo(String memo) {
		Memo = memo;
	}
	
}
