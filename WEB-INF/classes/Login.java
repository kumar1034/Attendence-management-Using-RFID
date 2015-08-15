package com;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Connection;
import javax.sql.DataSource;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.DynaActionForm;

public class Login extends Action
{
  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    DynaActionForm regisForm = (DynaActionForm)form;
    String output = "none";
    String result = "";
    String user = regisForm.get("t1").toString();
    String pass = regisForm.get("t2").toString();
    HttpSession session = request.getSession();
    ActionErrors errors = new ActionErrors();
    if (isEmpty(user))
      errors.add("t1", new ActionMessage("error.field.empty", "User Name"));
    if (isEmpty(pass)) {
      errors.add("t2", new ActionMessage("error.field.empty", "Password"));
    }
    if (errors.isEmpty()){
		Connection conn = DBCon.getCon();
        result = DBCon.validateEmployee(conn, user, pass);
		if (result.equals("failure")){
			output = "failure";
		}else{
			output = "success";
			session.setAttribute("user", user);
		}
		return mapping.findForward(output);
    }

    saveErrors(request, errors);
    return mapping.getInputForward();
  }

  private boolean isEmpty(String value)
  {
    return ((value == null) || (value.trim().equals("")));
  }
}