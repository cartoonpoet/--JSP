package net.Ajax.Note.Action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.Ajax.Note.db.Note_Step2_Ajax_DAO;
import net.commons.action.Action;
import net.commons.action.ActionForward;

public class Note_Plans_Update_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		int StartIndex=Integer.parseInt(request.getParameter("StartIndex"));
		int EndIndex=Integer.parseInt(request.getParameter("EndIndex"));
		
		Note_Step2_Ajax_DAO update=new Note_Step2_Ajax_DAO();
		update.Plans_Update_Action(StartIndex, EndIndex);
		
		return null;
	}
}
