<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ page import="java.net.ServerSocket"%>
    <%@ page import="java.net.Socket"%>
    <%@ page import="java.io.BufferedReader"%>
    <%@ page import="java.io.InputStreamReader"%>
    <%@ page import="java.sql.Connection"%>
    <%@ page import="java.sql.PreparedStatement"%>
     <%@ page import="java.sql.DriverManager"%>
     <%@ page import="java.sql.ResultSet"%>
     <%@ page import="java.sql.Statement"%>
     <%@ page import="java.text.DateFormat"%>
     <%@ page import="java.text.SimpleDateFormat"%>
     <%@ page import="java.util.Calendar"%>
	 <%@ page import="com.DBCon"%>
     ;
    <html>
    <body>
    <h1>Reading tag data</h1><br><br>
    </body></html>
    <%!boolean flag=true; 
    	String uname;
    	public static String getTime(long now) { 
    		DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    	Calendar calendar = Calendar.getInstance();
    	calendar.setTimeInMillis(0);
    	calendar.setTimeInMillis(now); 
    	return formatter.format(calendar.getTime()); 
    	} 
    %>
    <%
ServerSocket socket=new ServerSocket(1111);
    StringBuffer sb=new StringBuffer();
while(flag){
	Socket soc=socket.accept();
	BufferedReader br=new BufferedReader(new InputStreamReader(soc.getInputStream()));
	sb.append(br.readLine());
	if(sb.length() == 10){
	Connection conn = DBCon.getCon();
    java.util.Date dd = new java.util.Date();
    java.sql.Date sql=new java.sql.Date(dd.getTime());
    PreparedStatement stat=conn.prepareStatement("select login_date from attendence where tag_id=? and logout_time=? and login_date=?",ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
    stat.setString(1,sb.toString());
    stat.setTime(2,java.sql.Time.valueOf("00:00:00"));
    stat.setDate(3,sql);
    ResultSet rs=stat.executeQuery();
    if(rs.next()){
    	Statement stmt = conn.createStatement();
    	ResultSet rs1=stmt.executeQuery("select userid from addemp where tag_id='"+sb.toString()+"'");
    	while(rs1.next()){
    		uname=rs1.getString(1);
    	}
    	rs1.close();stmt.close();
     PreparedStatement st=conn.prepareStatement("update attendence set logout_time=? where tag_id=? and logout_time=? and login_date=?");
    st.setTime(1,java.sql.Time.valueOf(getTime(System.currentTimeMillis())));
    st.setString(2,sb.toString());
    st.setTime(3,java.sql.Time.valueOf("00:00:00"));
    st.setDate(4,sql);
    st.executeUpdate();
    st.close();
    }else{
    	Statement stmt = conn.createStatement();
    	ResultSet rs1=stmt.executeQuery("select userid from addemp where tag_id='"+sb.toString()+"'");
    	while(rs1.next()){
    		uname=rs1.getString(1);
    	}
    	rs1.close();stmt.close();
    	PreparedStatement st=conn.prepareStatement("insert into attendence values(?,?,?,?,?)");
    	st.setString(1,sb.toString());
    	st.setString(2,uname);
    	st.setDate(3,sql);
    	st.setTime(4,java.sql.Time.valueOf(getTime(System.currentTimeMillis())));
    	st.setTime(5,java.sql.Time.valueOf("00:00:00"));
    	st.executeUpdate();
    	st.close();
    }
    System.out.println(sb.toString()+" "+uname);
    sb.delete(0,sb.length());
    br.close();
	}
}
    %>