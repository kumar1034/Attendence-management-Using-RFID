package com;
import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.IOException;  
import java.io.PrintWriter;  
  
public class CheckLanguage extends HttpServlet {  
  
    public void doGet(HttpServletRequest request, HttpServletResponse response) {  
        try {  
			HttpSession session = request.getSession();
			if(request.getSession(false).getAttribute("language") == null){
				session.setAttribute("language","english");
			}
			String name  = (String)session.getAttribute("language");
            response.setContentType("text/html");  
            response.setHeader("Cache-Control", "no-cache");  
            PrintWriter out = response.getWriter();  
            out.print(name);  
            System.out.println("I WAS HERE");  
        } catch (Exception io) {  
            io.printStackTrace();  
        }  
    }  
}  