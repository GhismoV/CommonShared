package it.ghismo.common.rest.utils;

import java.io.File;

import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.resources.CommonRsc;
import it.ghismo.common.utils.FileUtil;
import it.ghismo.common.webapps.support.ParametersReader;
import it.ghismo.common.webapps.utils.TemplateUtil;

public class JsonTemplateUtil extends TemplateUtil {
	public static final String TEMPLATE_ERRORE_NAME = "errore";

	public JsonTemplateUtil(ParametersReader parameters) {
		super(parameters);
	}

	public BeanType getTemplateJson(CommonRsc resource, String radiceTemplate) {
		JsonUtil<BeanType> jsonUtil = new JsonUtil<BeanType>(BeanType.class);
		return (BeanType)getTemplateJson(jsonUtil, resource, radiceTemplate);
	}

	public <A> A getTemplateJson(JsonUtil<A> jsonUtil, CommonRsc resource, String radiceTemplate) {
		return getTemplateJsonExtended(jsonUtil, resource, radiceTemplate, null, null, new String[0]);
	}

	public <A> A getTemplateJsonExtended(JsonUtil<A> jsonUtil, CommonRsc resource, String radiceTemplate, String prefix, String suffix, String... levels) {
		File f = getTemplateFileExtended(radiceTemplate, TemplateUtil.TemplateType.json, prefix, suffix, levels);
		String strFile = FileUtil.leggiFileText(f);
		return jsonUtil.jsonToObject(strFile);
	}

	public BeanType getTemplateJson(File f) {
		JsonUtil<BeanType> jsonUtil = new JsonUtil<BeanType>(BeanType.class);
		return (BeanType)getTemplateJson(jsonUtil, f);
	}

	public <A> A getTemplateJson(JsonUtil<A> jsonUtil, File f) {
		String strFile = FileUtil.leggiFileText(f);
		return jsonUtil.jsonToObject(strFile);
	}

	public BeanType getTemplateJsonErrore(CommonRsc resource) {
		return getTemplateJson(resource, "errore");
	}
}
