package net.note.db;

public class Train_Select_Bean {
	int price;
	String start_station, start_time, end_station, end_time, train_kind;
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStart_station() {
		return start_station;
	}
	public void setStart_station(String start_station) {
		this.start_station = start_station;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_station() {
		return end_station;
	}
	public void setEnd_station(String end_station) {
		this.end_station = end_station;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getTrain_kind() {
		return train_kind;
	}
	public void setTrain_kind(String train_kind) {
		this.train_kind = train_kind;
	}
	
}
