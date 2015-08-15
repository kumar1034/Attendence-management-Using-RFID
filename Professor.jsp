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
<body onload="UpdateData()">
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
			 <li><a href="index.jsp"><bean:message key="home" /></a></li>
			  <li><a href="Admin.jsp"><bean:message key="admin" /></a></li>
			  <li><a href="Professor.jsp"><bean:message key="professor" /></a></li>
			   <li><a href="Student.jsp"><bean:message key="student" /></a></li>
			    <li><a href="Parent.jsp"><bean:message key="parent" /></a></li>
				
		</ul>
	</div>
	<html:form action="/ProfessorLogin">
   <h1 align="center"><bean:message key="professorloginform" /></h1><br/>
   
	<%
	String res = request.getParameter("response");
	if(res != null){
		out.println("<center>"+res+"</center>");
	}%>
	<br/>
	 <table align="center">
		  <tr><td><b><bean:message key="username" /></b></td><td><html:text name="ProfessorLoginForm" property="t1" styleId="t1"/></td></tr>
         <tr align="center"><td colspan="2" style="color:red"><html:errors property="t1"/></td></tr>

		  <tr><td><b><bean:message key="password" /></b></td><td><html:password name="ProfessorLoginForm" property="t2" styleId="t2"/></td></tr>
         <tr align="center"><td colspan="2" style="color:red"><html:errors property="t2"/></td></tr>

			<tr><td></td><td><input type="submit" value="Login"></td>
        
				</td></tr></table>
				</html:form>
</body>
</html>