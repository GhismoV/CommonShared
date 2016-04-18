package it.ghismo.common.rest.resources;

import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.servlets.CommonRestServlet;
import it.ghismo.common.rest.support.ParametersReader;
import it.ghismo.common.rest.utils.JsonTemplateUtil;
import it.ghismo.common.webapps.log.Log;
import it.ghismo.common.webapps.support.ContextParametersMap;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import freemarker.core.ParseException;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateNotFoundException;

public abstract class CommonRsc {
  @Context  protected UriInfo uriInfo;
  @Context  protected Request request;
  @Context  protected ServletContext servletContext;
  @Context  protected HttpServletRequest httpRequest;
  
  protected static boolean isFirstReqInitialized = false;
  
  public abstract String getDescrizione();
  
  public String getUserid() {
    return "";
  }
  
  
  protected void preProcessReq() {
	  if(!isFirstReqInitialized) {
		  initFirstReq();
	  }
  }
  
  protected ParametersReader parameters = CommonRestServlet.getParametersReader();
  protected JsonTemplateUtil jsonTemplateUtil = new JsonTemplateUtil(this.parameters);
  
  protected BeanType getTemplateJson(String radiceTemplate) {
    return this.jsonTemplateUtil.getTemplateJson(this, radiceTemplate);
  }
  
  protected synchronized void initFirstReq() {
	  if(!isFirstReqInitialized) {
		  Log.info("Common Resource: First Request initializing");
		  String contextPath = this.httpRequest.getContextPath();
		  String servletPath = this.httpRequest.getServletPath();
		  String completeReqUrl = this.httpRequest.getRequestURL().toString();
		  
		  int idx = completeReqUrl.indexOf(contextPath);
		  if(idx > -1) {
			  StringBuilder sb_appCompleteUrl = new StringBuilder();
			  StringBuilder sb_servletCompleteUrl = new StringBuilder();
			  
			  String baseUrl = completeReqUrl.substring(0, idx);
			  sb_appCompleteUrl.append(baseUrl).append(contextPath);
			  sb_servletCompleteUrl.append(sb_appCompleteUrl).append(servletPath).append('/');
			  
			  CommonRestServlet.getContextParameterMap().put(ContextParametersMap.PARAM__APP__COMPLETE_CONTEXT_URL, sb_appCompleteUrl.toString());
			  CommonRestServlet.getContextParameterMap().put(ContextParametersMap.PARAM__APP__COMPLETE_SERVLET_URL, sb_servletCompleteUrl.toString());
		  }
		  CommonRestServlet.getContextParameterMap().put(ContextParametersMap.PARAM__APP__CONTEXT_URL, contextPath);
		  CommonRestServlet.getContextParameterMap().put(ContextParametersMap.PARAM__APP__SERVLET_URL, servletPath);
		  isFirstReqInitialized = true;
	  }
  }
  
  protected Template getFreemarkerTemplate(String templateName) {
	  Template ret = null;
		try {
			ret = CommonRestServlet.getFreemarkerConfiguration().getTemplate(templateName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ret;
  }
  
}
