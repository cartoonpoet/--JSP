package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.media.jfxmedia.MediaManager;


public class MemberDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	

	public MemberDAO() {
		try {
			Context init=new InitialContext();
	        DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/CUBRIDDS");
            con = ds.getConnection();   
		}catch(Exception ex) {
			System.out.println("DB 접속에러:"+ex);
			return;
		}
	}
	//회원인증 (ID, PW CHECKING)
	public int isMember(MemberBean member) {
		String sql="select password from member where email_id=?";
		int result=-1;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			rs=pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getString("password").equals(member.getMEMBER_PW())) {
					result=1; //일치
				}
				else {
					result=0;
				}
			}
			else {
				result=-1;
				//아이디가 존재하지 않음.
			}
		}catch(Exception ex) {
			System.out.println("isMember 에러 : "+ex);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return result;
	}
	public boolean joinIDCheck(MemberBean member) { // 아이디 체크
		String sql="SELECT * FROM MEMBER WHERE EMAIL_ID=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_ID());
			rs=pstmt.executeQuery();

			while(rs.next()) {
				if(rs.getString("EMAIL_ID").equals(member.getMEMBER_ID())==true) {
					return true;
				}
			}
		}catch(Exception e) {
			System.out.println("joinIDCheck Error : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return false;
	}
	public boolean joinNickNameCheck(MemberBean member) { //닉네임 체크
		String sql="SELECT * FROM MEMBER WHERE NIKNAME=?";
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, member.getMEMBER_NIKNAME());
			rs=pstmt.executeQuery();

			while(rs.next()) {
				if(rs.getString("NIKNAME").equals(member.getMEMBER_NIKNAME())==true) {
					return true;
				}
			}
		}catch(Exception e) {
			System.out.println("joinNIKNAMECheck Error : "+e);
		}finally {
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
	        if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		return false;
	}
	public boolean joinMember(MemberBean member) { //Sign UP
		String sql="INSERT INTO MEMBER VALUES(?,?,?,?,?,?,?,?,?)";
		int result=0;
		
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,  member.getMEMBER_ID());
			pstmt.setString(2, member.getMEMBER_PW());
			pstmt.setString(3, member.getMEMBER_NAME());
			pstmt.setString(4, member.getMEMBER_NIKNAME());
			pstmt.setString(5, member.getPHONE_NUMBER());
			pstmt.setString(6, member.getIMG_NAME());
			pstmt.setString(7, member.getMEMBER_GENDER());
			pstmt.setInt(8, member.getSTAMP_CNT());
			pstmt.setString(9, " ");
			
			result=pstmt.executeUpdate();
			if(result!=0) {
				return true; 
			}
		}catch(Exception ex) {
			System.out.println("joinMember ERROR:"+ex);
		}finally {
				if(rs!=null) try{rs.close();}catch(SQLException ex){}
	           if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){} 
		}
		
		return false;
	}
}
