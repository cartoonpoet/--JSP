package net.note.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.action.MemberJoinAction;

public class NoteFrontController extends HttpServlet implements Servlet{
	static final long serialVersionUID=1L;
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        HttpSession session = request.getSession();
        String id = (String)session.getAttribute("id");
    	
        /*
         * 페이지 이동부분
         */
        
        if(command.equals("/Railro_Note_Step1.pl")) { //내일로노트 스탭 1 이동
        	if(id == null){
    			response.setContentType("text/html;charset=UTF-8");
    			PrintWriter out = response.getWriter();
    			out.println("<script>");
    			out.println("alert('로그인 후 이용해주세요.');");
    			out.println("history.back();");
    			out.println("</script>");
    			out.close();
    			return;
    		}
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./planner_writer/Railro_Note_Step1.jsp");
        }
        else if(command.equals("/Railro_Note_Step2.pl")) {
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./planner_writer/Railro_Note_Step2.jsp");
        }
        else if(command.equals("/Note1InsertAction.pl")){ //Sign up Action page
        	action=new Note_Step1_InsertAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        
        
        
        if(forward!=null){ 
            if(forward.isRedirect()){ 
                response.sendRedirect(forward.getPath()); 
            }else{ 
                RequestDispatcher dispatcher=request.getRequestDispatcher(forward.getPath()); 
                dispatcher.forward(request, response); 
            } 
        } 
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        doProcess(request, response); 
    } 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        doProcess(request, response); 
    } 
}
