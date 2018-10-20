package net.MyPage.db;

import java.sql.SQLException;

import etc.function.DB_Connection;

public class MyPage_DAO extends DB_Connection{
	
	public void setProFileImg(String url, String email_id) {
		String sql="update member set imgfile=? where email_id=?";
		int result=0;
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, url);
			pstmt.setString(2, email_id);
			result=pstmt.executeUpdate();
			
		}catch(Exception ex) {
			System.out.println("setProFileImg ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
	}
	
	public MemberINFO_Bean getMemberInfo(String email_id) {
		MemberINFO_Bean member=new MemberINFO_Bean();
		String sql="select * from member where email_id=?";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email_id);
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				member.setEmail_id(rs.getString("email_id"));
				member.setName(rs.getString("name"));
				member.setNikname(rs.getString("nikname"));
				member.setPhone(rs.getString("phone"));
				member.setProfile(rs.getString("imgfile"));
				member.setSex(rs.getString("sex"));
			}

		}catch(Exception ex) {
			System.out.println("getMemberInfo ERROR : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return member;
	}
}
