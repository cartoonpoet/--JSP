package net.note.db;

public class Note_Step2_Day_List_Bean {
	String Travel_Area_Name;
	int Travel_Area_Day;
	String day;
	String date;
	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Note_Step2_Day_List_Bean(String area_name, int area_day) {
		this.Travel_Area_Name=area_name;
		this.Travel_Area_Day=area_day;
	}
	
	public String getTravel_Area_Name() {
		return Travel_Area_Name;
	}
	public void setTravel_Area_Name(String travel_Area_Name) {
		Travel_Area_Name = travel_Area_Name;
	}
	public int getTravel_Area_Day() {
		return Travel_Area_Day;
	}
	public void setTravel_Area_Day(int travel_Area_Day) {
		Travel_Area_Day = travel_Area_Day;
	}
}
