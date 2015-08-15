package com;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
public class DBCon
{
public static Connection getCon()throws Exception {
	Class.forName("com.mysql.jdbc.Driver");
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost/student_att","root","root");
    return con;
}
public static String getTime(long now) { 
	DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
    Calendar calendar = Calendar.getInstance();
    calendar.setTimeInMillis(0);
    calendar.setTimeInMillis(now); 
    return formatter.format(calendar.getTime()); 
} 
public static int attendence(String tag,String professor,String cname){
	int index = 0;
	try{
		Connection con = getCon();
		Statement statement = con.createStatement();
		ResultSet set = statement.executeQuery("select tagid from addstd where tagid='"+tag+"'");
		if(set.next()){
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from tempclass");
		if(rs.next()){
			professor = rs.getString(1);
			cname = rs.getString(2);
		}
		java.util.Date dd1 = new java.util.Date();
		java.sql.Date dd2 = new java.sql.Date(dd1.getTime());
		PreparedStatement stat = con.prepareStatement("select tagid from attendence where professorname=? and classname=? and tagid=? and outtime=? and lecturedate=?");
		stat.setString(1,professor);
		stat.setString(2,cname);
		stat.setString(3,tag);
		stat.setTime(4,java.sql.Time.valueOf("00:00:00"));
		stat.setDate(5,dd2);
		rs = stat.executeQuery();
		if(rs.next()){
			PreparedStatement st = con.prepareStatement("update attendence set outtime=? where professorname=? and classname=? and tagid=? and outtime=? and lecturedate=?");
			st.setTime(1,java.sql.Time.valueOf(getTime(System.currentTimeMillis())));
			st.setString(2,professor);
			st.setString(3,cname);
			st.setString(4,tag);
			st.setTime(5,java.sql.Time.valueOf("00:00:00"));
			st.setDate(6,dd2);
			index = st.executeUpdate();
			st.close();
		}else{
			PreparedStatement st = con.prepareStatement("insert into attendence values(?,?,?,?,?,?)");
			st.setString(1,professor);
			st.setString(2,cname);
			st.setString(3,tag);
			java.sql.Time t1 = java.sql.Time.valueOf(getTime(System.currentTimeMillis())); 
			st.setTime(4,t1);
			java.sql.Time t2 = java.sql.Time.valueOf("00:00:00");
			st.setTime(5,t2);
			st.setDate(6,dd2);
			System.out.println(professor+" "+cname+" "+tag+" "+t1.toString()+" "+t2.toString()+" "+dd2.toString());
			index = st.executeUpdate();
			st.close();
		}
		rs.close();
		stat.close();
		}else{
			index = 3;
		}
		
	}catch(Exception e){
	e.printStackTrace();
	}
	return index;
}
public static int saveUser(Connection con,String name,String fname,String gender,String date,String qualification,String designation,String contact,String mail,String address,String user,String pass) {
    int index = 0;
	boolean f1=true;
	try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select userid from addemp where userid='"+user+"'");
      if (rs.next()){
		  f1 = false;
		  index = 2;
	  }
	  if(f1){
		  PreparedStatement stat = con.prepareStatement("insert into addemp values(?,?,?,?,?,?,?,?,?,?,?)");
		  stat.setString(1,name);
          stat.setString(2,fname);
          stat.setString(3,gender);
          stat.setDate(4,java.sql.Date.valueOf(date));
		  stat.setString(5,qualification);
		  stat.setString(6,designation);
          stat.setString(7,contact);
          stat.setString(8,mail);
          stat.setString(9,address);
		  stat.setString(10,user);
		  stat.setString(11,pass);
          index = stat.executeUpdate();
		  stat.close();
	  }
	  rs.close();stmt.close();	
	}
    catch (Exception e) {
      e.printStackTrace();
    }
    return index;
  }

public static int saveStudent(Connection con,String name,String fname,String gender,String date,String qualification,String designation,String contact,String mail,String address,String tag) {
    int index = 0;
	boolean f1=true;
	try {
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("select tagid from addstd where tagid='"+tag+"'");
      if (rs.next()){
		  f1 = false;
		  index = 2;
	  }
	  if(f1){
		  PreparedStatement stat = con.prepareStatement("insert into addstd values(?,?,?,?,?,?,?,?,?,?)");
		  stat.setString(1,name);
          stat.setString(2,fname);
          stat.setString(3,gender);
          stat.setDate(4,java.sql.Date.valueOf(date));
		  stat.setString(5,qualification);
		  stat.setString(6,designation);
          stat.setString(7,contact);
          stat.setString(8,mail);
          stat.setString(9,address);
		  stat.setString(10,tag);
		  index = stat.executeUpdate();
		  stat.close();
	  }
	  rs.close();stmt.close();	
	}
    catch (Exception e) {
      e.printStackTrace();
    }
    return index;
  }
 
public static int addClass(Connection con,String pname,String cname) {
    int index = 0;
	try {
		java.util.Date dd = new java.util.Date();
		java.sql.Time time = new java.sql.Time(dd.getTime());
		java.sql.Date date = new java.sql.Date(dd.getTime());
		PreparedStatement stat = con.prepareStatement("insert into addclass values(?,?,?,?)");
		stat.setString(1,pname);
        stat.setString(2,cname);
		stat.setTime(3,time);
		stat.setDate(4,date);
        index = stat.executeUpdate();
		stat.close();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("select * from tempclass");
		if(rs.next()){
			stat = con.prepareStatement("delete from tempclass");
			stat.executeUpdate();
		}
		stat = con.prepareStatement("insert into tempclass values(?,?)");
		stat.setString(1,pname);
		stat.setString(2,cname);
		stat.executeUpdate();
		
	}
	catch (Exception e) {
      e.printStackTrace();
    }
    return index;
  }


public static String validateEmployee(Connection con, String user, String pass){
	String result = "failure";
	String usertype="";
	
    try{
		PreparedStatement stat = con.prepareStatement("select userid from addemp where userid=? and pass=?");
		stat.setString(1, user);
		stat.setString(2, pass);
		ResultSet rs = stat.executeQuery();
		if(rs.next()){
			result = "success";
		}
      rs.close();
	  stat.close();
	}catch (Exception e) {
      e.printStackTrace();
    }
    return result;
}


}
