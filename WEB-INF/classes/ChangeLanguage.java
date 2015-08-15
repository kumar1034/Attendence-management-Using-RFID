package com;

import java.io.PrintStream;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class ChangeLanguage extends DispatchAction
{
  private static final String SUCCESS = "success";

  public ActionForward english(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession();
    session.setAttribute("language","english");
    session.setAttribute("org.apache.struts.action.LOCALE", Locale.ENGLISH);
    return mapping.findForward("success");
  }

  public ActionForward hindi(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession();
    Locale locale = new Locale("hi", "IN");
    session.setAttribute("org.apache.struts.action.LOCALE", locale);
    return mapping.findForward("success");
  }

  public ActionForward telugu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    throws Exception
  {
    HttpSession session = request.getSession();
	session.setAttribute("language","telugu");
	Locale locale = new Locale("te", "IN");
    session.setAttribute("org.apache.struts.action.LOCALE", locale);
    return mapping.findForward("success");
  }
}