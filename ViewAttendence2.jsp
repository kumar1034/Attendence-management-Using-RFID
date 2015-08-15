<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="java.sql.Connection"%>
<%@page import="com.DBCon"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>STUDENT ATTENDANCE</title>
<link href="http://fonts.googleapis.com/css?family=Arvo" rel="stylesheet" type="text/css" />
<link href="style.css" rel="stylesheet" type="text/css" media="screen" />
<script type="text/javascript" src="jquery/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="jquery/jquery.gallerax-0.2.js"></script>
<style type="text/css">
@import "gallery.css";
</style>



</head>
<body>
<div id="wrapper">
	<div id="header">
		<div id="logo">
		<center>	<h2>AUTOMATIC CONTROL OF STUDENT ATTENDANCE </h2> </center>
			<center>  <h2>IN CLASSROOMS USING RFID</h2></center>
		</div>
	</div>
	<!-- end #header -->
	<div id="menu">
		<ul>
			 <li><a href="AddEmployee.jsp"><bean:message key="addemp" /></a></li>
			  <li><a href="AddStudent.jsp"><bean:message key="addstudent" /></a></li>
			  <li><a href="ViewStudent.jsp"><bean:message key="viewattendence" /></a></li>
			   <li><a href="Logout.jsp"><bean:message key="logout" /></a></li>
			   
		</ul>
	</div>
	
   <h1 align="center"><bean:message key="viewattendenceform" /></h1><br/>
    <table align="center" border=1>
		<tr><th>Professor Name</th><th>Class Name</th><th>Tagid</th><Th>INTime</th><th>OUTTime</th>
		<th>Date</th></tr>
		</tr>
		<%
			try{
				String tag = request.getParameter("t1");
				Connection con = DBCon.getCon();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery("select * from attendence where tagid='"+tag+"'");
				while(rs.next()){
					
					%>
				<tr>
				<td><%=rs.getString(1)%></td>
				<td><%=rs.getString(2)%></td>
				<td><%=rs.getString(3)%></td>
				<td><%=rs.getString(4)%></td>
				<td><%=rs.getString(5)%></td>
				<td><%=rs.getString(6)%></td>
				</tr>
				<%}
				rs.close();stmt.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		%>
		</table>
</body>
</html>