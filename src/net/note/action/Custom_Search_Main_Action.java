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
import net.note.db.Custom_DAO;
import net.note.db.Note_Basic_Info_Bean;
import net.note.db.Note_Detail_DAO;
import net.note.db.Note_Detail_Info_Bean;
import net.note.db.Note_Plans_List_Bean;
import net.note.db.Note_Travel_Area_Bean;

public class Custom_Search_Main_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		int people_cnt=Integer.parseInt(request.getParameter("people_cnt"));
		int travel_day=Integer.parseInt(request.getParameter("travel_day"));
		String tema=request.getParameter("tema");
		int area_cnt=Integer.parseInt(request.getParameter("area_cnt"));
		
		System.out.println(people_cnt);
		System.out.println(travel_day);
		System.out.println(tema);
		System.out.println(area_cnt);
		
		ArrayList<Note_Plans_List_Bean> data=new ArrayList<Note_Plans_List_Bean>();
		Custom_DAO custom=new Custom_DAO();
		
		data=custom.getCustom_Search(people_cnt, travel_day, tema, area_cnt);
		
		System.out.println("검색결과 : "+data.size());
		
		request.setAttribute("data", data);
		request.setAttribute("people_cnt", people_cnt);
		request.setAttribute("travel_day", travel_day);
		request.setAttribute("tema", tema);
		request.setAttribute("area_cnt", area_cnt);
        forward.setRedirect(false); //disconnect
        forward.setPath("./Search/Custom_Search.jsp"); //page forwarding  
		return forward;
	}

}