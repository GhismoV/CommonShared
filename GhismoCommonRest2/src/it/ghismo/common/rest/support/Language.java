package it.ghismo.common.rest.support;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import it.ghismo.common.utils.StringUtil;
import it.ghismo.common.utils.Util;


public final class Language {
	
	private enum DefaultModeType {DefaultValue, Key, ConfigSettings};
	
	public final static String LANGUAGE_ITALIAN = "it";
	public final static String LANGUAGE_ENGLISH = "en";
	public final static String LANGUAGE_GERMAN = "de";
	public final static String LANGUAGE_FRENCH = "fr";
	public final static String LANGUAGE_DEFAULT = LANGUAGE_ENGLISH;
	
	
	public final static String PATH_DEFAULT = "resourcebundle.";
	public final static String BASE_DEFAULT = "Lang";

	private final static String CONSTANT_PREFIX = "__";
	public final static String PREFIX__ESCAPE_HTML = "EHTML_"; 
	public final static String PREFIX__ESCAPE_UNICODE = "EUNICODE_"; 
	
	public final static String PARAMETER_PATTERN_BASE_DEFAULT = "@#";
	
	private boolean isEscaped = false;
	private Locale lingua = null;
    private String prefisso = null;
    private ResourceBundle bundle = null;
    private ParametersReader parameters = null;
    private static Map<String, ResourceBundle> langs = null;
    
    static {
    	langs = new HashMap<String, ResourceBundle>();
    }

    
    public Language(ParametersReader parameters) {
    	this(parameters, null, null, (Locale)null);
    }
    public Language(ParametersReader parameters, Locale lingua) {
    	this(parameters, null, null, lingua);
    }
    public Language(ParametersReader parameters, String lingua) {
    	this(parameters, null, null, Language.getLocale(lingua));
    }
    public Language(ParametersReader parameters, String classPathProp, String base) {
    	this(parameters, classPathProp, base, (Locale)null);
    }
    public Language(ParametersReader parameters, String classPathProp, String base, Locale lingua) {
    	this.parameters = parameters;
    	this.lingua = (lingua != null) ?  lingua : getLocale(LANGUAGE_DEFAULT);
    	

    	String newBase = (base != null) ? base : BASE_DEFAULT;   	
    	String newpath = (classPathProp != null) ? classPathProp : PATH_DEFAULT;
    	
    	if(!"".equals(newpath)) {
    		if(!newpath.endsWith(".")) newpath += ".";
    	}
    	
    	this.prefisso = newpath + newBase;
        
    	String strLingua = lingua.getLanguage();
    	bundle = langs.get(strLingua);
    	if(bundle == null) {
            try {
            	bundle = ResourceBundle.getBundle(this.prefisso, this.lingua);
            	langs.put(strLingua, bundle);
            } catch(Exception ex) {
        		ex.printStackTrace(); 
            }
    	}
    }
    
    
    
    
    public boolean isEscaped() {
		return isEscaped;
	}

	public void setEscaped(boolean isEscaped) {
		this.isEscaped = isEscaped;
	}	
    
    private static Locale getLocale(String lingua) {
    	Locale ris = null;
    	if(lingua != null) ris = new Locale(lingua);
    	return ris;
    }

    public synchronized boolean cambiaLingua(String lingua) {
    	boolean ris = false;
    	
		String oldStrLingua = (this.lingua != null) ? this.lingua.getLanguage() : null;
		boolean effettuaCambio = false;
		if(oldStrLingua == null) effettuaCambio = (lingua != null);
		else effettuaCambio = !(oldStrLingua.equals(lingua));
		
		if(effettuaCambio) {
	    	try {
		    	Locale newLingua = getLocale(lingua);
		        bundle = ResourceBundle.getBundle(this.prefisso, newLingua);
		        this.lingua = newLingua;
		        ris = true;
	        } catch(Exception e) {
	    		e.printStackTrace(); 
	        }
		} else ris = true;
    	
    	return ris;
    }
    
    
	public String get(String key) {
		return get(key, DefaultModeType.ConfigSettings);
	}
	private String get(String key, DefaultModeType defaultMode) {
		String ris = null;
		try {
			ris = getString(key, defaultMode, null);
		} catch (Exception e) { }
		return ris;
	}
	public String get(String key, boolean keyAsDefault) {
		return getString(key, (keyAsDefault ? DefaultModeType.Key : DefaultModeType.ConfigSettings), null);
	}
	public Map<String,String> get(List<String> keys) {
		return get(keys, false);
	}
	public Map<String,String> get(List<String> keys, boolean keyAsDefault) {
		return getStrings(keys, (keyAsDefault ? DefaultModeType.Key : DefaultModeType.ConfigSettings), null);
	}
	public String get(String key, String defaultOutputValue ) {
		return getString(key, DefaultModeType.DefaultValue, defaultOutputValue);
	}
	
    private synchronized String getString(String key, DefaultModeType defaultMode, String defaultOutputValue) {
    	String valore = null;
    	if(key != null) {
        	List<String> list_key = new ArrayList<String>();
    		list_key.add(key);
    		Map<String, String> ris = getStrings(list_key, defaultMode, defaultOutputValue);
    		if(ris != null && ris.size() > 0) {
    			valore = ris.get(key);
    		}
    	}
    	return valore;
    }
    
    private synchronized Map<String, String> getStrings(List<String> keys, DefaultModeType defaultMode, String defaultOutputValue) {
    	Map<String, String> map = null;
    	if(keys != null) {
    		map = new HashMap<String, String>();
    		String value = null;
    		boolean isConfigSettingDebug = parameters != null ? Util.getBoolean(parameters.get(ParametersReader.INTERNAZIONALIZING_DEBUG), false) : false;
    		for (String key : keys) {
    			
    			boolean escapeHtml = false;
    			boolean escapeUnicode = false;
    			String realKeyToFind = key;
    			if(key != null) {
    				if(key.startsWith(PREFIX__ESCAPE_HTML)) {
    					escapeHtml = true;
    					realKeyToFind = key.substring(PREFIX__ESCAPE_HTML.length());
    				} else if(key.startsWith(PREFIX__ESCAPE_UNICODE)) {
    					escapeUnicode = true;
    					realKeyToFind = key.substring(PREFIX__ESCAPE_UNICODE.length());
    				} else ;
    			}
    			
				value = getStringFromResourceBundle(realKeyToFind);
				if(value == null) {
					switch (defaultMode) {
					case Key:
						value = realKeyToFind;
						break;
					case DefaultValue:
						value = defaultOutputValue;
						break;
					case ConfigSettings:
						value = isConfigSettingDebug ? realKeyToFind : "";
						break;
					default:
						break;
					}
				}
				
				if(value != null) {
					if(escapeHtml) value = StringUtil.escapeHtml(value);
					else if(escapeUnicode) value = StringUtil.escapeUnicode(value);
					else ;
				}
				
				map.put(key, value);
			}
    	}
    	return map;
    }
    
    private synchronized String getStringFromResourceBundle(String key) {
    	String valore = null;
    	String tmpVal = null;
    	try {
    		String s = bundle.getString(key);
    		if(s != null) {
    			tmpVal = new String(s.getBytes() /*, "UTF-8"*/ );
    			tmpVal = StringUtil.unescapeUnicode(tmpVal);
    		}
    			
    	} catch (Exception e) {
    		//e.printStackTrace();
    	} 
    	
    	if(tmpVal != null)
    		valore = tmpVal;
    	
    	return valore;
    }
    
    
    /**
     * Restituisce la traduzione dell'etichetta parametrica key, 
     * sostituendo ai pattern effettivi, i corrispondenti valori presenti in paramsValue.
     * Il pattern effettivo e' formato nel seguente modo:
     *		patternEffettivo = PARAMETER_PATTERN_BASE_DEFAULT + k
     * dove k = numero intero.
     * Al patternEffettivo cosi' formato, corrispondera' il valore paramsValue[k].  
     * @param key: Etichetta parametrica
     * @param paramsValue: Lista ordinata dei valori dei parametri
     * @return Testo tradotto corrispondente all'etichetta key, sostituendo i pattern con i valori dei parametri. 
     */
    public String getParameterString(String key, List<String> paramsValue) {
    	return getParameterString(key, paramsValue, PARAMETER_PATTERN_BASE_DEFAULT);
    }
    public String getParameterString(String key, String... paramsValue) {
    	List<String> paramsValueList = Arrays.asList(paramsValue);
    	return getParameterString(key, paramsValueList, PARAMETER_PATTERN_BASE_DEFAULT);
    }
    
    /**
     * Restituisce la traduzione dell'etichetta parametrica key, 
     * sostituendo ai pattern effettivi, i corrispondenti valori presenti in paramsValue.
     * Il pattern effettivo e' formato nel seguente modo:
     *		patternEffettivo = paramPatternBase + k
     * dove k = numero intero.
     * Al patternEffettivo cosi' formato, corrispondera' il valore paramsValue[k].  
     * @param key: Etichetta parametrica
     * @param paramsValue: Lista ordinata dei valori dei parametri
     * @param paramPatternBase: Pattern di base del parametro nel testo dell'etichetta.
     * @return Testo tradotto corrispondente all'etichetta key, sostituendo i pattern con i valori dei parametri. 
     */
    public String getParameterString(String key, List<String> paramsValue, String paramPatternBase) {
    	String ris = null;
    	ris = this.getString(key, DefaultModeType.ConfigSettings, null);
    	if(paramPatternBase == null)
    		paramPatternBase = PARAMETER_PATTERN_BASE_DEFAULT;
    	if(ris != null && paramsValue != null && paramPatternBase != null) {
    		int size = paramsValue.size();
    		for(int i = 0 ; i < size ; ++i) {
    			String patternCorr = paramPatternBase + String.valueOf(i + 1);
    			String paramValueCorr = (String)paramsValue.get(i);
    			if(paramValueCorr == null) paramValueCorr = "";
    			ris = ris.replaceAll(patternCorr, paramValueCorr);
    		}
    	}
    	
    	return ris;
    }

    /**
     * Restituisce la concatenazione degli elementi presenti in keys con la seguente modalit':
     * Per ogni elemento, 
     * 	se l'elemento ' una etichetta, appende la traduzione
     * 	altrimenti se l'etichetta ' codificata come una costante, appende la costante decodificata.
     * 
     * @param keys: Lista ordinata delle etichette/costanti
     * @return Concatenazione delle traduzioni delle etichette/costanti presenti in keys.
     */
    public String getConcatString(List<String> keys) {
    	return getConcatString(keys, DefaultModeType.ConfigSettings, null);
    }
    
    /**
     * Restituisce la concatenazione degli elementi presenti in keys con la seguente modalit':
     * Per ogni elemento, 
     * 	se l'elemento ' una etichetta, appende la traduzione (o il valore di default se l'etichetta non esiste)
     * 	altrimenti se l'etichetta ' codificata come una costante, appende la costante decodificata.
     * 
     * @param keys: Lista ordinata delle etichette/costanti
     * @param defaultOutputValue: Valore di default delle etichette
     * @return Concatenazione delle traduzioni delle etichette/costanti presenti in keys.
     */
    public String getConcatString(List<String> keys, String defaultOutputValue) {
    	return getConcatString(keys, DefaultModeType.DefaultValue, defaultOutputValue);
    }
    public String getConcatString(List<String> keys, DefaultModeType defaultMode, String defaultOutputValue) {
    	StringBuffer ris = new StringBuffer();
    	if(keys != null) {
    		int size = keys.size();
    		for(int i = 0 ; i < size ; ++i) {
            	String valore = defaultOutputValue;
    			String etich = (String)keys.get(i);
    			if(etich != null) {
    				valore = (isCostante(etich)) ? decodCostante(etich) : getString(etich, defaultMode, defaultOutputValue);
    			}
    			ris = ris.append(valore);
    		} // end for
    	}
    	return ris.toString();
    }
    
    
    /**
     * Verifica se "s" ' codificata come costante.
     * @param s: Stringa utilizzata nelle concatenazioni, e pu' essere una etichetta o una costante codificata
     * @return true se la stringa risulta codificata come costante.
     */
    private static boolean isCostante(String s) {
    	return (s != null && s.startsWith(CONSTANT_PREFIX)); 
    }
    
    /**
     * Decodifica una costante
     * @param s: Costante codificata
     * @return Il valore decodificato della costante "s"
     */
    private static String decodCostante(String s) {
    	return (isCostante(s) ? s.substring(CONSTANT_PREFIX.length()) : s);
    }
    
    /**
     * Codifica la costante NON codificata "s"
     * @param s: Costante NON codificata
     * @return Il valore codificato della costante "s"
     */
    public static String codifCostante(String s) {
    	return (s != null ? (CONSTANT_PREFIX + s) : null);
    }
    
    /**
     * Restituisce il Codice della lingua attualmente utilizzata.
     * @return Codice della lingua corrente.
     */
    public Locale getLocaleLingua() {
    	return lingua;
    }

    public String getCodiceLingua() {
    	String ris = "";
    	if(lingua != null) ris = lingua.getLanguage();
    	return ris;
    }
    

}
