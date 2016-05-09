package it.ghismo.common.rest.resources;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import freemarker.template.Template;
import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.jaxb.msg.FieldType;
import it.ghismo.common.rest.jaxb.msg.SessionType;
import it.ghismo.common.rest.servlets.CommonRestServlet;
import it.ghismo.common.rest.support.Language;
import it.ghismo.common.rest.support.ParametersReader;
import it.ghismo.common.rest.support.session.DataTemp;
import it.ghismo.common.rest.support.session.UserSession;
import it.ghismo.common.rest.utils.JsonTemplateUtil;
import it.ghismo.common.rest.utils.JsonUtil;
import it.ghismo.common.rest.utils.MsgBeanUtil;
import it.ghismo.common.rest.utils.MsgBeanUtil.MsgBeanUtilCachingMode;
import it.ghismo.common.webapps.log.Log;
import it.ghismo.common.webapps.support.ContextParametersMap;

public abstract class CommonRsc {

	public final static String WEBFORM_FIELDNAME__SESSION_ID = "sessid";
	public final static String WEBFORM_FIELDNAME__LANGUAGE = "lang";
	

	@Context  protected UriInfo uriInfo;
	@Context  protected Request request;
	@Context  protected ServletContext servletContext;
	@Context  protected HttpServletRequest httpRequest;

	protected UserSession use = null;
	protected Language lang = null;
	protected JsonUtil<BeanType> jsonUtil = null;


	protected static boolean isFirstReqInitialized = false;

	public abstract String getDescrizione();

	public String getUserid() {
		return "";
	}

	protected String getSingleFormParamValue(MultivaluedMap<String, String> formParams, String param) {
		String ret = null;
		if(formParams != null && param != null) {
			List<String> listTmp = formParams.get(param);
			if(listTmp != null && !listTmp.isEmpty()) {
				ret = listTmp.get(0);
			}
		}
		return ret;
	}

	private void preProcessReq() {
		if(!isFirstReqInitialized) {
			initFirstReq();
		}
		jsonUtil = new JsonUtil<>(BeanType.class);
	}
	protected void preProcessReq(MultivaluedMap<String, String> formParams) {
		String sessionId = null;
		String langStr = null;
		if(formParams != null) {
			sessionId = getSingleFormParamValue(formParams, WEBFORM_FIELDNAME__SESSION_ID);
			langStr = getSingleFormParamValue(formParams, WEBFORM_FIELDNAME__LANGUAGE);
		}
		preProcessReq(sessionId, langStr);
	}  
	protected void preProcessReq(String sessionId, String langStr) {
		preProcessReq();

		this.use = CommonRestServlet.getSessionsManagement().getSession(sessionId);
		if(this.use != null) {
			this.lang = use.getLang();
		}
		if(this.lang == null) {
			this.lang = new Language(CommonRestServlet.getParametersReader(), langStr);
		}
	}
	protected void preProcessReq(BeanType bean) {
		preProcessReq();

		if(bean != null) {
			SessionType msgSession = bean.getSession();
			if(msgSession != null) {
				this.use = CommonRestServlet.getSessionsManagement().getSession(msgSession.getSessId());

				if(this.use != null) {
					this.lang =  use.getLang();
				}
				if(this.lang == null) {
					this.lang = new Language(CommonRestServlet.getParametersReader(), msgSession.getLang());
				}
			}
		}

	}

	protected void completeProcessReq() {
	}
	protected void completeProcessReq(BeanType bean) {
		completeProcessReq();
		
		if(bean != null) {
			Log.info("OUTPUT JSON :" + jsonUtil.objectToJson(bean));
		}
		
		if(bean != null) {
	    	MsgBeanUtil mbu = new MsgBeanUtil(bean, MsgBeanUtilCachingMode.Complete);
	    	mbu.translate(lang);
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

	protected Map<String, Object> getSpecificRqFreemarkerDataModel() { return null; }

	protected String getFreemarkerTemplateAndProcess(String templateName, BeanType bean) {
		String ris = null;
		try {
			Template t = getFreemarkerTemplate(templateName);
			StringWriter sw = new StringWriter();

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("bean", bean);
			map.put("lang", this.lang);

			Map<String, Object> specificDataModel = getSpecificRqFreemarkerDataModel();
			if(specificDataModel != null) map.putAll(specificDataModel);

			t.process(map, sw);
			ris = sw.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ris;

	}

	protected BeanType getLastReturnedJson() {
		BeanType ret = null;
		if(this.use != null) {
			ret = (BeanType)this.use.getDataTemp().get(DataTemp.SESSION__GENERIC__LAST_RETURNED_JSON);
		}
		return ret;
	}


	protected BeanType getBeanInputFromFormParams(MultivaluedMap<String, String> formParams) {
		BeanType beanInCreated = getLastReturnedJson();
		webFormDataToBeanInput(formParams, beanInCreated);
		return beanInCreated;
	}


	protected void webFormDataToBeanInput(MultivaluedMap<String, String> formParams, BeanType beanToFill) {
		webFormDataToBeanInput(formParams, beanToFill, MsgBeanUtilCachingMode.Complete);
	}
	protected void webFormDataToBeanInput(MultivaluedMap<String, String> formParams, BeanType beanToFill, MsgBeanUtilCachingMode mbuCachingMode) {
		if(formParams != null && beanToFill != null) {
			webFormDataToBeanInput(formParams, new MsgBeanUtil(beanToFill, mbuCachingMode));
		}    	
	}
	protected void webFormDataToBeanInput(MultivaluedMap<String, String> formParams, MsgBeanUtil mbuToFill) {
		if(formParams != null && mbuToFill != null) {
			Set<Entry<String, List<String>>> entries = formParams.entrySet();
			Iterator<Entry<String, List<String>>> entriesIter = entries.iterator();
			Entry<String, List<String>> currEntry = null;
			FieldType _currField = null;
			while(entriesIter.hasNext()) {
				currEntry = entriesIter.next();
				_currField = mbuToFill.getField(currEntry.getKey());
				if(_currField != null) {
					if(currEntry.getValue() != null && !currEntry.getValue().isEmpty()) {
						if(currEntry.getValue().size() == 1) {
							_currField.setValue(currEntry.getValue().get(0));
						} else {
							for(String postValue : currEntry.getValue()) {
								_currField.getValues().add(postValue);
							}
						}
					}
				}
			}
		}
	}


}
