package net.note.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Train_Select_Bean;
import net.note.db.Train_Select_DAO;

public class Note_Train_Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		String start=request.getParameter("start");
		String end=request.getParameter("end");
		
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Calendar c1 = Calendar.getInstance();
        String strToday = sdf.format(c1.getTime());

		Train_Select_DAO train=new Train_Select_DAO();
		
		ArrayList<Train_Select_Bean> data=new ArrayList<Train_Select_Bean>();
		data=train.getTrainTime(strToday, start, end);
		
		request.setAttribute("data", data);
        forward.setRedirect(false); //disconnect
        forward.setPath("./planner_writer/Train_PopUp.jsp"); //page forwarding  
		return forward;
	}

}