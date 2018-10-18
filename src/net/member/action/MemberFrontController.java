package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.commons.action.Action;
import net.commons.action.ActionForward;
import net.search.action.Detail_Info_Search_Action;


public class MemberFrontController extends HttpServlet implements Servlet{
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
        if(command.equals("/MemberJoin1.me")) { //Agreement agreement page
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/sign_up_Form_1.jsp");
        }
        else if(command.equals("/MemberJoin2.me")){ //Sign UP page
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/sign_up_Form_2.jsp");
        }
        else if(command.equals("/Main.me")) { //MainPage
        	action=new Main_Action();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
//        else if(command.equals("/index.me")){ //데이터 가져오기
//        	action=new Main_Action();
//        	try {
//        		forward=action.execute(request, response);
//        	}catch(Exception e) {
//        		e.printStackTrace();
//        	}
//        }
        else if(command.equals("/MemberLogin.me")) {//LoginPage
        	forward=new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./member/loginForm.jsp");
        }
        /*
         * 아래는 처리 부분
         */
        else if(command.equals("/MemberJoinAction.me")){ //Sign up Action page
        	action=new MemberJoinAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MemberLoginAction.me")) { //Login Action page
        	action=new MemberLoginAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MemberLogoutAction.me")) {//Logout Action Page
        	action=new MemberLogoutAction();
        	try {
        		forward=action.execute(request, response);
        	}catch(Exception e) {
        		e.printStackTrace();
        	}
        }
        else if(command.equals("/MemberFollowAction.me")) {//내일러 팔로우
        	action=new MemberFollowAction();
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
