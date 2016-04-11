package it.ghismo.common.rest.support;

import it.ghismo.common.rest.resources.CommonRsc;
import org.apache.log4j.Logger;

public class Log
  extends it.ghismo.common.webapps.log.Log
{
  private static final Logger log4j = Logger.getLogger(Log.class);
  public static final String LOG_VAR_PREFIX = "%log_";
  
  public static void info(CommonRsc resource, String message)
  {
    log4j.info(getMessage(resource, message));
  }
  
  private static String getMessage(CommonRsc resource, String message)
  {
    String userId = null;
    String resourceDescr = null;
    if (resource != null)
    {
      userId = resource.getUserid();
      resourceDescr = resource.getDescrizione();
    }
    StringBuilder sb = new StringBuilder();
    sb.append(userId != null ? userId : "no_userid");
    sb.append(" - ");
    sb.append(resourceDescr != null ? resourceDescr : "no_resourceDescr");
    sb.append(" - ");
    sb.append(message != null ? message : "no_message");
    
    return message;
  }
}
