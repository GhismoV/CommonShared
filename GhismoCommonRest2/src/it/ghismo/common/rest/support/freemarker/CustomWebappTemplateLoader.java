package it.ghismo.common.rest.support.freemarker;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;

import freemarker.cache.WebappTemplateLoader;
import it.ghismo.common.utils.StringUtil;
import it.ghismo.common.webapps.support.ParametersReader;
import it.ghismo.common.webapps.utils.TemplateUtil;

public class CustomWebappTemplateLoader extends WebappTemplateLoader {
	private static final String COMPLEX_TEMPLATE_NAME_SEPARATOR = ";";
	private TemplateUtil templateUtil = null;

	public CustomWebappTemplateLoader(ServletContext servletContext, ParametersReader parameters) throws IOException {
		super(servletContext);
		this.templateUtil = new TemplateUtil(parameters);
	}

	public CustomWebappTemplateLoader(ServletContext servletContext, String subdirPath, ParametersReader parameters) throws IOException {
		super(servletContext, subdirPath);
		this.templateUtil = new TemplateUtil(parameters);
	}

	public static String getComplexTemplateSourceName(String simpleTemplateName, String prefix, String suffix, String... livelli) {
		StringBuilder sb = new StringBuilder();
		sb.append(simpleTemplateName).append(COMPLEX_TEMPLATE_NAME_SEPARATOR);
		sb.append(prefix != null ? prefix : "").append(COMPLEX_TEMPLATE_NAME_SEPARATOR);
		sb.append(suffix != null ? suffix : "");
		if ((livelli != null) && (livelli.length > 0)) {
			sb.append(COMPLEX_TEMPLATE_NAME_SEPARATOR);
			StringUtil.createSeparatedStringFromArray(COMPLEX_TEMPLATE_NAME_SEPARATOR, livelli);
		}
		return sb.toString();
	}

	public Object findTemplateSource(String arg0) throws IOException {
		Object ret = null;
		System.out.println("ghismo - findTemplateSource - start: arg0 = " + arg0);

		String[] tmpArray = arg0 != null ? arg0.split(COMPLEX_TEMPLATE_NAME_SEPARATOR) : null;
		String simpleTemplateName = "";
		String prefix = null;
		String suffix = null;
		String[] livelli = null;
		if ((tmpArray != null) && (tmpArray.length > 0)) {
			simpleTemplateName = tmpArray[0];
			if (tmpArray.length > 2) {
				prefix = tmpArray[1];
				suffix = tmpArray[2];
				if (tmpArray.length > 3) {
					int livelliSize = tmpArray.length - 3;
					livelli = new String[livelliSize];
					System.arraycopy(tmpArray, 3, livelli, 0, livelliSize);
				}
			}
		}
		if (StringUtil.isStringEmpty(prefix)) {
			prefix = null;
		}
		if (StringUtil.isStringEmpty(suffix)) {
			suffix = null;
		}
		List<String> templatesPaths = this.templateUtil.getTemplatesPathsList(simpleTemplateName, TemplateUtil.TemplateType.freemarker, prefix, suffix, livelli);
		String currentPath = null;
		int i = 0;
		do {
			currentPath = (String)templatesPaths.get(i++);
			System.out.println("ghismo - findTemplateSource - process path: " + currentPath);
			ret = super.findTemplateSource(currentPath);
		} while ((ret == null) && (i < templatesPaths.size()));
		return ret;
	}
}
