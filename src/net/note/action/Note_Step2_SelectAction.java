package net.note.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Step2_Day_List_Bean;
import net.note.db.Note_Step2_Select_Bean;
import net.note.db.Note_Step2_Select_DAO;

public class Note_Step2_SelectAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		Note_Step2_Select_Bean note_Step2_Bean=new Note_Step2_Select_Bean();
		Note_Step2_Select_DAO note_Step2_Select_DAO=new Note_Step2_Select_DAO();
		
		/*
		 * 노트 기본 정보 갖고 오는 구간
		 */
		note_Step2_Bean.setTravel_ID(Integer.parseInt(request.getParameter("num")));
		note_Step2_Bean.setEmail_ID((String)session.getAttribute("ID"));
		note_Step2_Bean.setNote_Name(note_Step2_Select_DAO.Search_Note_Name(note_Step2_Bean.getTravel_ID(), note_Step2_Bean.getEmail_ID()));
		note_Step2_Bean.setTravel_Start_Day(note_Step2_Select_DAO.Search_Travel_Start_Day(note_Step2_Bean.getTravel_ID(), note_Step2_Bean.getEmail_ID()));
		note_Step2_Bean.setTravel_Day(note_Step2_Select_DAO.Search_Travel_Day(note_Step2_Bean.getTravel_ID(), note_Step2_Bean.getEmail_ID()));
		note_Step2_Bean.setTravel_Tema(note_Step2_Select_DAO.Search_Travel_Tema(note_Step2_Bean.getTravel_ID(), note_Step2_Bean.getEmail_ID()));
		note_Step2_Bean.setTravel_People(note_Step2_Select_DAO.Search_Travel_People(note_Step2_Bean.getTravel_ID(), note_Step2_Bean.getEmail_ID()));
		
		System.out.println("Travel_ID : "+note_Step2_Bean.getTravel_ID());
		System.out.println("Email_ID : "+note_Step2_Bean.getEmail_ID());
		System.out.println("Note_Name : "+note_Step2_Bean.getNote_Name());
		System.out.println("Start_DAY : "+note_Step2_Bean.getTravel_Start_Day());
		System.out.println("여행 일수 : "+note_Step2_Bean.getTravel_Day());
		System.out.println("여행 테마 : "+note_Step2_Bean.getTravel_Tema());
		System.out.println("여행 인원 : "+note_Step2_Bean.getTravel_People());
		
		/*
		 * 노트 일정 정보 갖고 오는 구간
		 */
		
		ArrayList<Note_Step2_Day_List_Bean> day_list=note_Step2_Select_DAO.Search_Day_List(note_Step2_Bean.getTravel_ID());;
		
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");

		String to = transFormat.format(note_Step2_Bean.getTravel_Start_Day()); //시작일 저장

		for(int i=0; i<day_list.size(); i++) {
			Calendar cal=Calendar.getInstance(); //캘릭터 설정
			cal.setTime(note_Step2_Bean.getTravel_Start_Day()); //시작일 지정
			cal.add(Calendar.DATE, day_list.get(i).getTravel_Area_Day()-1); //시작일에서 해당 날짜의 일차만큼 더해준다.
			day_list.get(i).setDate(transFormat.format(cal.getTime())); //저장
			
			switch(cal.get(Calendar.DAY_OF_WEEK)) {
				case 1: day_list.get(i).setDay("일요일"); break;
				case 2: day_list.get(i).setDay("월요일"); break;
				case 3: day_list.get(i).setDay("화요일"); break;
				case 4: day_list.get(i).setDay("수요일"); break;
				case 5: day_list.get(i).setDay("목요일"); break;
				case 6: day_list.get(i).setDay("금요일"); break;
				case 7: day_list.get(i).setDay("토요일"); break;
			}
		}
		
		for(int i=0; i<day_list.size(); i++) {
			System.out.println(day_list.get(i).getTravel_Area_Day()+"일차 : "+day_list.get(i).getTravel_Area_Name());
		}
		
		request.setAttribute("note_Step2_Bean", note_Step2_Bean);
		request.setAttribute("note_Step2_day_list", day_list);
        forward.setRedirect(false); //접속 끊었다가 다시 연결하면서 새로운 정보를 보여준다.
        forward.setPath("./planner_writer/Railro_Note_Step2.jsp"); //메인으로   
		return forward;
	}
	
}
