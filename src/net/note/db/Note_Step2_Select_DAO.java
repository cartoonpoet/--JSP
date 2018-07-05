package net.note.db;

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

public class Note_Step2_Select_DAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
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
}
