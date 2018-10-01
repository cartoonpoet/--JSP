package net.Ajax.Note.Action;

import java.io.File;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.note.db.Note_Plans_List_DAO;

public class NoteImg_Change_Action implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		ActionForward forward=new ActionForward();
		HttpSession session=request.getSession();
		
		System.out.println("들어옴");
		String path="C:/Users/손준호/eclipse-workspace/Railro_Tour_WEB1234/WebContent/file";
		int max=1024*1024*10;
		DefaultFileRenamePolicy dp=new DefaultFileRenamePolicy();
		MultipartRequest multi=new MultipartRequest(request, path, max, "UTF-8", dp);
		String sysName=multi.getFilesystemName("save");
		String orgName=multi.getOriginalFileName("save");
		String type=multi.getContentType("save");
		
		System.out.println(sysName);
		return null;
	}
}