package net.search.db;

import java.sql.SQLException;

import etc.function.DB_Connection;
import net.note.db.Note_Add_Bean;

public class Review_DAO extends DB_Connection{
	public Review_Bean Review_Insert(String email_id, Review_Bean review){
		int result=0;
		try {
			
			String sql="select count(*) as cnt from review where content_id=? and content_type_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, review.getContentid());
			pstmt.setInt(2, review.getContenttypeid());
			rs=pstmt.executeQuery();
			
			int cnt=0;
			
			while(rs.next()) {
				cnt=rs.getInt("cnt");
			}
			
			System.out.println('1');
			
			if(cnt>0) {
				sql = "insert into review select ?, ?, ?, ?, MAX(review_num)+1, ?, ? from review where content_id=? and content_type_id=?";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email_id);
				pstmt.setInt(2, review.getContentid());
				pstmt.setInt(3, review.getLike_yn());
				pstmt.setString(4, review.getMemo());
				pstmt.setString(5, review.getDatetime());
				pstmt.setInt(6, review.getContenttypeid());
				pstmt.setInt(7, review.getContentid());
				pstmt.setInt(8, review.getContenttypeid());
				result = pstmt.executeUpdate();
			}
			else {
				sql = "insert into review values(?, ?, ?, ?, 0, ?, ?)";

				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, email_id);
				pstmt.setInt(2, review.getContentid());
				pstmt.setInt(3, review.getLike_yn());
				pstmt.setString(4, review.getMemo());
				pstmt.setString(5, review.getDatetime());
				pstmt.setInt(6, review.getContenttypeid());
				result = pstmt.executeUpdate();
			}
			
			sql="select * from review where email_id=? and content_id=? and like_yn=? and memo=? and date_time=? and content_type_id=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			pstmt.setInt(2, review.getContentid());
			pstmt.setInt(3, review.getLike_yn());
			pstmt.setString(4, review.getMemo());
			pstmt.setString(5, review.getDatetime());
			pstmt.setInt(6, review.getContenttypeid());
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				review.setReview_num(rs.getInt("review_num"));
			}
			System.out.println("review num : "+review.getReview_num());
			
			sql="insert into review_hashtag values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review.getContentid());
			pstmt.setInt(2, review.getContenttypeid());
			pstmt.setInt(3, review.getReview_num());
			for(int i=0; i<review.getTags().size(); i++) {
				pstmt.setString(4, review.getTags().get(i));
				pstmt.executeUpdate();
			}
			
			sql="insert into review_imgfile values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, review.getContentid());
			pstmt.setInt(2, review.getContenttypeid());
			pstmt.setInt(3, review.getReview_num());
			for(int i=0; i<review.getFilesPath().size(); i++) {
				pstmt.setString(4, review.getFilesPath().get(i));
				pstmt.executeUpdate();
			}
		}catch(Exception ex) {
			System.out.println("Review_Insert ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return review; //넣기 성공!
	}
	public String getNikname(String email_id) {
		String sql="select * from member where email_id=?";
		String name=null;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				name=rs.getString("nikname");
			}
	
		}catch(Exception ex) {
			System.out.println("getNikname ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return name;
	}
	public int RemoveReview(int contentid, int contenttypeid, int review_num) {
		String sql="delete from review where content_id=? and content_type_id=? and review_num=?";
		int result=0;
		try {
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			pstmt.setInt(3, review_num);
			result=pstmt.executeUpdate();
			
			sql="delete from review_hashtag where content_id=? and content_type_id=? and review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			pstmt.setInt(3, review_num);
			result=pstmt.executeUpdate();
			
			sql="delete from review_imgfile where content_id=? and content_type_id=? and review_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, contentid);
			pstmt.setInt(2, contenttypeid);
			pstmt.setInt(3, review_num);
			result=pstmt.executeUpdate();
		}catch(Exception ex) {
			System.out.println("getNikname ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return result;
	}
	
}
