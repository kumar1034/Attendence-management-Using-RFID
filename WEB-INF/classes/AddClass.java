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

public class AddClass extends Action
{
  String output;

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    DynaActionForm regisForm = (DynaActionForm)form;
    String pname = regisForm.get("t1").toString();
    String cname = regisForm.get("t2").toString();
    
	HttpSession session = request.getSession();

    ActionErrors errors = new ActionErrors();
    if (isEmpty(pname))
      errors.add("t1", new ActionMessage("error.field.empty", "Professor name"));
    if (isEmpty(cname))
      errors.add("t2", new ActionMessage("error.field.empty", "Class name"));
    if (errors.isEmpty()) {
		Connection conn = DBCon.getCon();
		int i = DBCon.addClass(conn,pname,cname);
		if (i == 1){
			session.setAttribute("professor",pname);
			session.setAttribute("class",cname);
			return mapping.findForward("success");
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