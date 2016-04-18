package it.ghismo.common.rest.support;

import it.ghismo.common.rest.servlets.CommonRestServlet;
import java.net.URL;

public class ParametersReader extends it.ghismo.common.webapps.support.ParametersReader {
	public static final String FREEMARKER_CONFIG_FILENAME = "freemarker_config_filename";
	public static final String FREEMARKER_CONFIG_FILENAME_DEFAULT = "freemarker.properties";

	public ParametersReader(String confFileName) {
		super(confFileName);
	}

	public ParametersReader(URL confUrl) {
		super(confUrl);
	}

	public String get(String key) {
		return this.get(key, (String)null);
	}

	public String get(String key, String defaultValue) {
		return super.get(key, CommonRestServlet.getContextParameterMap(), defaultValue);
	}
}
