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
			 <li><a href="AddEmployee.jsp"><bean:message key="addemp" /></a></li>
			  <li><a href="AddStudent.jsp"><bean:message key="addstudent" /></a></li>
			  <li><a href="ViewStudent.jsp"><bean:message key="viewattendence" /></a></li>
			   <li><a href="Logout.jsp"><bean:message key="logout" /></a></li>
			   
		</ul>
	</div>
	<html:form action="/AddEmp">
   <h1 align="center"><bean:message key="addempform" /></h1><br/>
   
	<%
	String res = request.getParameter("response");
	if(res != null){
		out.println("<center>"+res+"</center>");
	}%>
	<br/>
	 <table align="center">
	 <tr><td><b><bean:message key="empname" /></b></td><td><html:text name="AddEmpForm" property="t1" styleId="t1"/></td></tr>
     <tr align="center"><td colspan="2" style="color:red"><html:errors property="t1"/></td></tr>

	<tr><td><b><bean:message key="fname" /></b></td><td><html:text name="AddEmpForm" property="t2" styleId="t2"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t2"/></td></tr>

	<tr><td><b><bean:message key="gender" /></b></td><td><html:select name="AddEmpForm" property="t3">
	<option value="Male">Male</option>
	<option value="Female">Female</option>
	</html:select>
	</td></tr>
    
	<tr><td><b><bean:message key="bdate" /></b></td><td><html:select name="AddEmpForm" property="t4">
	<%for(int i=1900;i<=2050;i++){%>
	<option value="<%=i%>"><%=i%></option>
	<%}%>
	</html:select>

	<html:select name="AddEmpForm" property="t5">
	<%for(int i=1;i<=12;i++){%>
	<option value="<%=i%>"><%=i%></option>
	<%}%>
	</html:select>

	<html:select name="AddEmpForm" property="t6">
	<%for(int i=1;i<=31;i++){%>
	<option value="<%=i%>"><%=i%></option>
	<%}%>
	</html:select>
	</td></tr>
    
	<tr><td><b><bean:message key="qualification" /></b></td><td><html:text name="AddEmpForm" property="t7" styleId="t7"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t7"/></td></tr>


	<tr><td><b><bean:message key="designation" /></b></td><td><html:select name="AddEmpForm" property="t8">
	<option value="Professor">Professor</option>
	<option value="Principal">Principal</option>
	<option value="Lecturer">Lecturer</option>
	<option value="Attender">Attender</option>
	</html:select>
	</td></tr>

	<tr><td><b><bean:message key="contact" /></b></td><td><html:text name="AddEmpForm" property="t9" styleId="t9"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t9"/></td></tr>


	<tr><td><b><bean:message key="mail" /></b></td><td><html:text name="AddEmpForm" property="t10" styleId="t10"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t10"/></td></tr>

	<tr><td><b><bean:message key="address" /></b></td><td><html:textarea name="AddEmpForm" property="t11" styleId="t11" cols="15" rows="5"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t7"/></td></tr>

	<tr><td><b><bean:message key="username" /></b></td><td><html:text name="AddEmpForm" property="t12" styleId="t12"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t12"/></td></tr>

	<tr><td><b><bean:message key="password" /></b></td><td><html:password name="AddEmpForm" property="t13" styleId="t13"/></td></tr>
    <tr align="center"><td colspan="2" style="color:red"><html:errors property="t13"/></td></tr>
    	  

			<tr><td></td><td><input type="submit" value="Add Emp"></td>
        
				</td></tr></table>
				</html:form>
</body>
</html>