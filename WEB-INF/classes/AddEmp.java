package com;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;
import javax.sql.DataSource;
import java.sql.Connection;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class AddEmp extends Action
{
  String output;

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    DynaActionForm regisForm = (DynaActionForm)form;
    String name = regisForm.get("t1").toString();
    String fname = regisForm.get("t2").toString();
    String gender = regisForm.get("t3").toString();
	String year= regisForm.get("t4").toString();
	String month = regisForm.get("t5").toString();
	String day = regisForm.get("t6").toString();
    String qualification = regisForm.get("t7").toString();
	String designation = regisForm.get("t8").toString();
	String contact = regisForm.get("t9").toString();
    String mail = regisForm.get("t10").toString();
	String address = regisForm.get("t11").toString();
	String user = regisForm.get("t12").toString();
	String pass = regisForm.get("t13").toString();
	HttpSession session = request.getSession();

    ActionErrors errors = new ActionErrors();
    if (isEmpty(name))
      errors.add("t1", new ActionMessage("error.field.empty", "Employee name"));
    if (isEmpty(fname))
      errors.add("t2", new ActionMessage("error.field.empty", "Father name"));
    if (isEmpty(qualification))
      errors.add("t7", new ActionMessage("error.field.empty", "Qualification"));
    if (isEmpty(contact))
      errors.add("t9", new ActionMessage("error.field.empty", "Contact no"));
    if (isEmpty(mail))
      errors.add("t10", new ActionMessage("error.field.empty", "Mailid"));
	if (isEmpty(address))
      errors.add("t11", new ActionMessage("error.field.empty", "Address"));
	if (isEmpty(user))
      errors.add("t12", new ActionMessage("error.field.empty", "Username"));
	if (isEmpty(pass))
      errors.add("t13", new ActionMessage("error.field.empty", "Password"));
	if(request.getSession(false).getAttribute("language") == null){
		session.setAttribute("language","english");
	}
	if(session.getAttribute("language").toString().equals("english")){
	if(validateMail(mail))
		errors.add("t10", new ActionMessage("error.field.empty", "Invalid mail id"));
	}
	 if (validateContact(contact))
      errors.add("t9", new ActionMessage("error.field.empty", "Invalid contact no"));
	if (errors.isEmpty()) {
		
		Connection conn = DBCon.getCon();
		String yy="";String dd="";String mm="";
		if(Integer.parseInt(month) <= 9){
			mm = "0"+month;
			month = mm;
		}
		if(Integer.parseInt(day) <= 9){
			dd = "0"+day;
			day = dd;
		}

        int i = DBCon.saveUser(conn,name,fname,gender,year+"-"+month+"-"+day,qualification,designation,contact,mail,address,user,pass);
		if (i == 1){
			return mapping.findForward("success");
		}
		if (i == 2) {
			errors.add("t12", new ActionMessage("error.field.empty", "User Name already exists"));
			saveErrors(request, errors);
			return mapping.getInputForward();
		}
		
		if(i == 0){
			return mapping.findForward("failure");
		}
    }
	else{
		saveErrors(request, errors);
		return mapping.getInputForward();
    }
    return null;
  }

  private boolean isEmpty(String value)
  {
    return ((value == null) || (value.trim().equals("")));
  }
  private boolean validatePassword(String s1,String s2){
	  boolean flag = true;
	  if(s1.equals(s2))
		  flag = false;
	  return false;
  }
  private boolean validateMail(String mail){
	  boolean flag=true;
	  String regEx = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b";
	  Pattern p = Pattern.compile(regEx);
	  Matcher m = p.matcher(mail);
	  if(m.find())
		  flag=false;
	 return flag;
  }
  private boolean validateContact(String value){
	  boolean flag=false;
	  for(int i=0;i<value.length();i++){
		  if(!Character.isDigit(value.charAt(i))){
			  flag=true;
			  break;
		  }
	  }
	  return flag;
  }
}