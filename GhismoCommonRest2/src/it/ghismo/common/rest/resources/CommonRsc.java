package it.ghismo.common.rest.resources;

import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.servlets.CommonRestServlet;
import it.ghismo.common.rest.support.ParametersReader;
import it.ghismo.common.rest.utils.JsonTemplateUtil;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public abstract class CommonRsc
{
  @Context
  UriInfo uriInfo;
  @Context
  Request request;
  @Context
  ServletContext servletContext;
  @Context
  HttpServletRequest httpRequest;
  
  public abstract String getDescrizione();
  
  public String getUserid()
  {
    return "";
  }
  
  protected ParametersReader parameters = CommonRestServlet.getParametersReader();
  protected JsonTemplateUtil jsonTemplateUtil = new JsonTemplateUtil(this.parameters);
  
  protected BeanType getTemplateJson(String radiceTemplate)
  {
    return this.jsonTemplateUtil.getTemplateJson(this, radiceTemplate);
  }
}
