<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
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
	<form action="ViewAttendence2.jsp">
   <h1 align="center"><bean:message key="viewattendenceform" /></h1><br/>
   
	<%
	String res = request.getParameter("response");
	if(res != null){
		out.println("<center>"+res+"</center>");
	}%>
	<br/>
	 <table align="center">
		  <tr><td><b>Student Tagid</td>
		  <td><input type="text" name="t1"></td></tr>

			<tr><td></td><td><input type="submit" value="View"></td>
        
				</td></tr></table>
				</form>
</body>
</html>