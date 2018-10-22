package net.note.db;

import java.sql.SQLException;
import java.util.ArrayList;

import etc.function.DB_Connection;

public class Custom_DAO extends DB_Connection{
	
	public ArrayList<Note_Plans_List_Bean> getCustom_Search(int people_cnt, int travel_day, String tema, int area_cnt){
		ArrayList<Note_Plans_List_Bean> data=new ArrayList<Note_Plans_List_Bean>();
		String sql="select * from note_info1 where travel_day=? and travel_tema=? and travel_people=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, travel_day);
			pstmt.setString(2, tema);
			pstmt.setInt(3, people_cnt);
			rs=pstmt.executeQuery();
			
			ArrayList<Integer> sample=new ArrayList<Integer>();
			
			while(rs.next()) {
				System.out.println("1차 : "+rs.getInt("travel_id"));
				sample.add(rs.getInt("travel_id"));
			}
			
			System.out.println("area_cnt"+area_cnt);
			
			for(int i=0; i<sample.size(); i++) {
				sql="select count(*) as cnt from note_travel_area WHERE travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, sample.get(i));
				rs=pstmt.executeQuery();
				while(rs.next()) {
					if(rs.getInt("cnt")==area_cnt) {
						System.out.println(sample.get(i));
						data.add(new Note_Plans_List_Bean());
						data.get(data.size()-1).setNote_ID(sample.get(i));
					}
				}
			}
			
			//검색결과에 맞는 데이터 찾음
			for(int i=0; i<data.size(); i++) {
				sql="select * from note_info1 where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setNote_ID(rs.getInt("travel_id")); //노트 고유 번호 
					data.get(i).setTravel_Day(rs.getString("travel_start_day")); //출발일
					data.get(i).setNote_Name(rs.getString("note_name")); //노트명
					data.get(i).setTema_Name(rs.getString("travel_tema")); //테마명
					data.get(i).setView(rs.getInt("note_view")); //조회수
					data.get(i).setDay(rs.getInt("travel_day")); //일수
					data.get(i).setImg(rs.getString("img")); //이미지
					data.get(i).setEmail_id(rs.getString("email_id"));//이메일
				}
				
				sql="select * from member where email_id LIKE ?";
				
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, data.get(i).getEmail_id());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setName(rs.getString("nikname"));
					data.get(i).setProfileimg(rs.getString("imgfile"));
				}
				
				sql="select * from note_travel_area where travel_id=? order by travel_area_day asc";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				int cnt=0; 
				while(rs.next()) {
					if(cnt==0) {
						data.get(i).setArea(rs.getString("travel_area_name"));
					}
					else {
						data.get(i).PlusArea("-"+rs.getString("travel_area_name"));
					}
					cnt++;
				}
				
				
				sql="select count(*) AS total from note_like_cnt where travel_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, data.get(i).getNote_ID());
				rs=pstmt.executeQuery();
				
				while(rs.next()) {
					data.get(i).setLike(rs.getInt("total"));
				}
			}
			
		}catch(Exception ex) {
			System.out.println("getCustom_Search ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return data;
	}
	
}
