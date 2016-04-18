package it.ghismo.common.rest.servlets;

import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.glassfish.jersey.servlet.ServletContainer;

import freemarker.cache.MruCacheStorage;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateModelException;
import it.ghismo.common.rest.support.Log;
import it.ghismo.common.rest.support.ParametersReader;
import it.ghismo.common.rest.support.freemarker.CustomWebappTemplateLoader;
import it.ghismo.common.utils.ExceptionUtil;
import it.ghismo.common.utils.Util;
import it.ghismo.common.webapps.support.ContextParametersMap;

public class CommonRestServlet
  extends ServletContainer
{
  private static final long serialVersionUID = -1256706285927312003L;
  public static final String SERVLET_NAME = "CommonRestServlet";
  public static final String DEFAULT_LANGUAGE = "it";
  protected static final String DEFAULT_CONF_FILE_NAME = "conf.properties";
  protected static final String DEFAULT_CONF_PATH = "conf";
  protected static boolean isInitialised = false;
  protected static ParametersReader confReader;
  protected static boolean isUrlProvider = false;
  protected static String absConfPath;
  public static ContextParametersMap contextParametersMap = null;
  private static Configuration freemarkerCfg = null;
  
  public synchronized void init()
    throws ServletException
  {
    if (!isInitialised)
    {
      super.init();
      
		// TODO : ghismo - codice per simulare le proprietà di sistema
		/*
		System.setProperty("sys_conf_path", "C:/Develop/runtime/conf");
		System.setProperty("sys_log_path", "C:/Develop/runtime/log");
		*/
      
      contextParametersMap = new ContextParametersMap();
      

      String fs = System.getProperty("file.separator");
      

      String appName = getInitParameter("app_name");
      String appVersion = getInitParameter("app_version");
      if (appVersion == null) {
        appVersion = "1.0";
      }
      contextParametersMap.put(ContextParametersMap.PARAM__APP__NAME, appName);
      contextParametersMap.put(ContextParametersMap.PARAM__APP__VERSION, appVersion);
      
      
	  // **************** gestione context-root ***************
      String contextRoot = getServletContext().getRealPath("");
      if (contextRoot != null) {
        char lastContextRootChar = contextRoot.charAt(contextRoot.length() - 1);
        if ((lastContextRootChar != '\\') &&  (lastContextRootChar != '/')) {
          contextRoot = contextRoot + fs;
        }
      } else {
        System.out.println("Context-root nulla, i percorsi dei file di configurazione devono essere assoluti.");
      }
      contextParametersMap.put(ContextParametersMap.PARAM__APP__CONTEXT_ROOT_PATH, contextRoot);
      

	  // **************** gestione path dei files di configurazione ***************
      absConfPath = getInitParameter("absolute_config_path");
      if (absConfPath != null)
      {
        absConfPath = Util.sysProp2Value(absConfPath);
        if (absConfPath.startsWith("java:comp/env/")) {
          try
          {
            InitialContext ctx = new InitialContext();
            Object urlObj = ctx.lookup(absConfPath);
            URL url = (URL)PortableRemoteObject.narrow(urlObj, URL.class);
            absConfPath = url.toString();
            System.out.println("URL configurazione: " + absConfPath);
            isUrlProvider = true;
          }
          catch (NamingException e)
          {
            System.err.println("Errore recupero URL di configurazione: " + e.getMessage());
            e.printStackTrace(System.err);
            throw new ServletException(e);
          }
        }
      }
      else if (contextRoot != null)
      {
        String relConfPath = getInitParameter("relative_config_path");
        if (relConfPath != null) {
          relConfPath = Util.sysProp2Value(relConfPath);
        } else {
          relConfPath = "conf";
        }
        absConfPath = contextRoot + relConfPath;
      }
      else
      {
        System.out.println("Context-root nulla, i percorsi dei file di configurazione non possono essere relativi.");
        throw new ServletException("Context-root nulla, i percorsi dei file di configurazione non possono essere relativi.");
      }
      char lastAbsConfPathChar = absConfPath.charAt(absConfPath.length() - 1);
      if ((lastAbsConfPathChar != '\\') && (lastAbsConfPathChar != '/')) {
        absConfPath += fs;
      }
      absConfPath = Util.sysProp2Value(absConfPath, contextParametersMap.getContextParametersMap());
      contextParametersMap.put(ContextParametersMap.PARAM__APP__CONFIG_PATH, absConfPath);
      

	  // **************** gestione del file di configurazione principale ***************
      String confFileName = getInitParameter("config_file");
      if (confFileName != null) {
        confFileName = Util.sysProp2Value(confFileName);
      } else {
        confFileName = "conf.properties";
      }
      confFileName = absConfPath + confFileName;
      
      Pattern pattern = null;
      if (fs.equals("/")) {
        pattern = Pattern.compile("\\\\");
      } else if (fs.equals("\\")) {
        pattern = Pattern.compile("/");
      }
      if (pattern != null) {
        fs = Matcher.quoteReplacement(fs);
        Matcher matcher = pattern.matcher(absConfPath);
        absConfPath = matcher.replaceAll(fs);
        matcher = pattern.matcher(confFileName);
        confFileName = matcher.replaceAll(fs);
      }
      confFileName = Util.sysProp2Value(confFileName, contextParametersMap.getContextParametersMap());
      System.out.println("CONFIGURATION FILE......: " + confFileName);
      
      
	  // **************** gestione del ParametersReader ***************
      if (confReader == null) {
        if (isUrlProvider) {
          try {
            confReader = new ParametersReader(new URL(confFileName));
          } catch (MalformedURLException e) {
            System.err.println("Errore durante la lettura della configurazione da ParametersReader da URL: " + e.getMessage());
            destroy();
            throw new ServletException(e);
          }
        } else {
          confReader = new ParametersReader(confFileName);
        }
        synchronized (confReader) {
          try {
            confReader.wait(5000L);
          } catch (InterruptedException localInterruptedException) {}
        }
      }
      
      
      
      /********************************************************/
      /************** Inizializzazione Logger *****************/
      /********************************************************/
      Log.init(getContextParameterMap(), confReader);
      /********************************************************/
      

      /********************************************************/
      /*********** Configurazione Freemarker ******************/
      /********************************************************/
      freemarkerCfg = new Configuration(Configuration.VERSION_2_3_24);
      String freemarkerFilePath = absConfPath + confReader.get("freemarker_config_filename", "freemarker.properties");
      Properties freemarkerConfigProps = new Properties();
      try {
        freemarkerConfigProps.load(new FileReader(freemarkerFilePath));
        freemarkerCfg.setSettings(freemarkerConfigProps);
        freemarkerCfg.setTemplateLoader(new CustomWebappTemplateLoader(getServletContext(), confReader));
        freemarkerCfg.setLogTemplateExceptions(true);

        freemarkerCfg.setSharedVariable("sysprop", System.getProperties());
        freemarkerCfg.setSharedVariable("context", contextParametersMap);
        freemarkerCfg.setSharedVariable("parameters", confReader);

      } catch (IOException e2) {
        Log.warn("Errore durante il recupero del file di configurazione di Freemarker: " + freemarkerFilePath + "\n" + ExceptionUtil.exceptionToText(e2));
      }
      catch (Exception e3) {
        Log.warn("Errore durante la configurazione di Freemarker\n" + ExceptionUtil.exceptionToText(e3));
      }
      /********************************************************/
      
      
      /********************************************************/
      /******** Inizializzazione DP Prepared Statement ********/
      /********************************************************/
      try {
        it.ghismo.common.webapps.db.dataprovider.ps.DataProvider.staticInitialize(confReader, new InitialContext());
      } catch (NamingException e1) {
        e1.printStackTrace();
        destroy();
        throw new ServletException(e1);
      }
      /********************************************************/

      
      /********************************************************/
      /************ Inizializzazione DP Hibernate *************/
      /********************************************************/
      it.ghismo.common.webapps.db.dataprovider.hb.DataProvider.staticInitialize(confReader, absConfPath);
      /********************************************************/



      Log.info("************************************************************************************");
      Log.info("Inizializzazione Servlet CommonRestServlet...");
      Log.info("************************************************************************************");
      
      isInitialised = true;
      
      System.out.println("Servlet Started.....");
    }
    else
    {
      System.out.println("Servlet Not initialised.....");
    }
  }
  
  
  // TODO : ghismo .... come promemoria per eventuali impostazioni di freemarker
  @SuppressWarnings("unused")
  private static void freemarkerConfig(ServletContext context)
  {
    freemarkerCfg.setLocale(Locale.ITALY);
    freemarkerCfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    freemarkerCfg.setDefaultEncoding("UTF-8");
    freemarkerCfg.setTemplateUpdateDelayMilliseconds(5000L);
    freemarkerCfg.setCacheStorage(new MruCacheStorage(20, 250));
    try
    {
      freemarkerCfg.setSharedVariable("ghismo", "strappattero");
    }
    catch (TemplateModelException e)
    {
      e.printStackTrace();
    }
  }
  
  public static String getAbsConfPath()
  {
    return absConfPath;
  }
  
  public static Map<String, String> getContextParameterMap()
  {
    return contextParametersMap.getContextParametersMap();
  }
  
  public static String getConfigParameter(String key, String defaultValue)
  {
    String result = null;
    if (key != null) {
      result = confReader.get(key, defaultValue);
    }
    return result;
  }
  
  public static ParametersReader getParametersReader()
  {
    return confReader;
  }
  
  public static Configuration getFreemarkerConfiguration()
  {
    return freemarkerCfg;
  }
}
