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
	<div id="banner"><img src="images/canada.jpg" width="1000" height="361" alt="" /></div>
	
	
	<div id="two-columns">
		<div id="col1">
			
			<p> Educational institutions administrators areconcerned about student irregular attendance. Checking students attendance is one of the important issues for universities, because many universities evaluate students attendance while giving the final grade, professors consider their total number of appearances on classes during the whole semester. This brings to the idea of having some tool to control students attendance. However, Current manual way of taking student attendance is not an efficient way since there will be spent much of time for calling students names and putting marks like "presence" or "absence" if the class is a lecture class, and in this class at least 5 groups are presented. Moreover, some students may call his/her friend as "presence" even though this student is currently absent. After thinking all these issues, author of the following research paper decided to create a system that makes easier to check students attendance automatically. The system is based on RFID technology, and in this paper, details of this system are presented. The system can be easily accessed by the lecturers via the web and most importantly, the reports can be generated in real-time processing, thus, providing valuable information about the students.</p>
		</div>
		<div id="col2"><img src="images/RFID.jpg" alt="" width="320" height="260" class="image-style" />
		</div>
	</div>
		<!-- end #content -->
		
	</div>
	<!-- end #page --> 
</div>

</body>
</html>