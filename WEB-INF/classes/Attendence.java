package com;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
public class Attendence extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
	HttpSession session = request.getSession();
	String tag=request.getParameter("t1");
	String professor = (String)session.getAttribute("professor");
	String classname  = (String)session.getAttribute("class"); 
	int i = DBCon.attendence(tag,professor,classname);
	PrintWriter out=response.getWriter();
	response.setContentType("text/html");
	out.println(i);
}
}