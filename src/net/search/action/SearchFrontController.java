package net.search.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.member.action.MemberJoinAction;
import net.member.action.MemberLoginAction;
import net.member.action.MemberLogoutAction;

public class SearchFrontController extends HttpServlet implements Servlet{
	static final long serialVersionUID=1L;
	
	
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{ 
        String RequestURI=request.getRequestURI(); 
        String contextPath=request.getContextPath(); 
        String command = RequestURI.substring(contextPath.length()); 
        ActionForward forward = null; 
        Action action = null; 
        
        /*
         * 페이지 이동부분
         */
        if(command.equals("/SearchAction.se")) { //검색 액션
        	action=new SearchAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/Search.se")){ //검색 페이지 이동
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./Search/Search_Form.jsp");
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