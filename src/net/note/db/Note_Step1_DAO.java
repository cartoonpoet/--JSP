package net.note.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Note_Step1_DAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Note_Step1_DAO() {
		try {
			Context init=new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/CUBRIDDS");
            con = ds.getConnection();   
		}catch(Exception ex) {
			System.out.println("DB 접속에러:"+ex);
			return;
		}
	}
	public boolean insert_Note(Note_Step1_Bean note_Step) {
		String sql="INSERT INTO note_info1(email_id, note_name, travel_start_day, travel_day, travel_tema, travel_people)"
				+ " VALUES(?, ?, ?, ?, ?, ?)";
		int result=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, note_Step.getEmail_id());

			pstmt.setString(2, note_Step.getNote_name());

			pstmt.setString(3, note_Step.getTravel_start_day());

			pstmt.setInt(4, Integer.parseInt(note_Step.getTravel_day()));

			pstmt.setString(5, note_Step.getTema());

			pstmt.setInt(6, Integer.parseInt(note_Step.getTravel_people()));
			
			result=pstmt.executeUpdate();
			System.out.println("내일로 노트 스탭 1 입력 완료");
			if(result!=0) {
				return true; 
			}
		}catch(Exception ex) {
			System.out.println("insert_Note ERROR:"+ex);

		}finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
	           if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		
		return true;
		
	}
	public int select_travel_id(Note_Step1_Bean note_Step) {
		String sql="select travel_id from note_info1 where "
				+ "email_id=? AND note_name=? AND travel_start_day=? AND travel_day=? AND travel_tema=? AND travel_people=?";
		int travel_id = 0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, note_Step.getEmail_id());
			pstmt.setString(2, note_Step.getNote_name());
			pstmt.setString(3, note_Step.getTravel_start_day());
			pstmt.setInt(4, Integer.parseInt(note_Step.getTravel_day()));
			pstmt.setString(5, note_Step.getTema());
			pstmt.setInt(6, Integer.parseInt(note_Step.getTravel_people()));
			rs=pstmt.executeQuery();
			
			while(rs.next())
				travel_id=rs.getInt("travel_id");

		}catch(Exception ex) {
			System.out.println("select_travel_id 에러 : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return travel_id;
	}
	public boolean insert_travel_area(int Number, Note_area_Bean Note_area_Bean) {
		String sql="INSERT INTO note_travel_area(travel_id, travel_area_name, travel_area_day)"
				+ " VALUES(?, ?, ?)";
		int result=0;

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, Number);
			pstmt.setString(2, Note_area_Bean.getArea());
			pstmt.setInt(3, Integer.parseInt(Note_area_Bean.getDay()));
			result=pstmt.executeUpdate();
			
			System.out.println("여행 지역 입력 완료");
			if(result!=0) {
				return true; 
			}
		}catch(Exception ex) {
			System.out.println("insert_travel_area ERROR:"+ex);

		}finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
	           if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		
		return true;
	}
}
