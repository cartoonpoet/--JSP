package net.note.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Note_Plans_List_DAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Note_Plans_List_DAO() {
		try {
			Context init=new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/CUBRIDDS");
            con = ds.getConnection();   
		}catch(Exception ex) {
			System.out.println("DB 접속에러:"+ex);
			return;
		}
	}
	
	public ArrayList<Note_Plans_List_Bean> Select_Note_List(String id){ //내일로 노트 목록 보기
		ArrayList<Note_Plans_List_Bean> TravelDay_List=new ArrayList<Note_Plans_List_Bean>();
		
		String sql="select * from note_info1 where rownum<10 and email_id LIKE ? order by travel_start_day desc";

		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int size=TravelDay_List.size();
				
				TravelDay_List.add(new Note_Plans_List_Bean());
				TravelDay_List.get(size).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
				TravelDay_List.get(size).setTravel_Day(rs.getString("travel_start_day")); //출발일
				TravelDay_List.get(size).setNote_Name(rs.getString("note_name")); //노트명
				TravelDay_List.get(size).setTema_Name(rs.getString("travel_tema")); //테마명
				TravelDay_List.get(size).setView(rs.getInt("note_view")); //조회수
				TravelDay_List.get(size).setDay(rs.getInt("travel_day")); //일수
				TravelDay_List.get(size).setImg("./note_plans_list/seoul.jpg"); //이미지
			}
			
			
			sql="select * from member where email_id LIKE ?";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			
			String nikname = null;
			
			while(rs.next()) {
				nikname=rs.getString("nikname");
			}
			
			for(int i=0; i<TravelDay_List.size(); i++) {
				TravelDay_List.get(i).setName(nikname);
				
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, TravelDay_List.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt=0; 
				while(rs.next()) {
					if(cnt==0) {
						TravelDay_List.get(i).setArea(rs.getString("travel_area_name"));
					}
					else {
						TravelDay_List.get(i).PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt++;
				}
			}
			
			for(int i=0; i<TravelDay_List.size(); i++) {
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, TravelDay_List.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					TravelDay_List.get(i).setLike(rs.getInt("total"));
				}
			}

		}catch(Exception ex) {
			System.out.println("Select_Note_List 에러 : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return TravelDay_List;
	}
	
	public void remove_noteinfo1(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_info1 where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_noteinfo1 : " + ex);
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
	public void remove_noteinfo2(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_info2 where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_noteinfo2 : " + ex);
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
	public void remove_notelikecnt(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_like_cnt where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_notelikecnt : " + ex);
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
	public void remove_note_travel_area(int NoteID) {
		int result=0;
		try {
			String sql="delete from note_travel_area where travel_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, NoteID);

			result=pstmt.executeUpdate();
			
		} catch (Exception ex) {
			System.out.println("remove_note_travel_area : " + ex);
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