package it.ghismo.common.rest.utils;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import it.ghismo.common.rest.jaxb.msg.BeanType;
import it.ghismo.common.rest.jaxb.msg.ButtonType;
import it.ghismo.common.rest.jaxb.msg.DataFieldsType;
import it.ghismo.common.rest.jaxb.msg.DataTypeEnum;
import it.ghismo.common.rest.jaxb.msg.ErrorType;
import it.ghismo.common.rest.jaxb.msg.FieldOptionType;
import it.ghismo.common.rest.jaxb.msg.FieldType;
import it.ghismo.common.rest.jaxb.msg.ListButtonType;
import it.ghismo.common.rest.jaxb.msg.ListEntityType;
import it.ghismo.common.rest.jaxb.msg.ListRecordActionType;
import it.ghismo.common.rest.jaxb.msg.ListType;
import it.ghismo.common.rest.jaxb.msg.MenuType;
import it.ghismo.common.rest.jaxb.msg.PageObjectBaseType;
import it.ghismo.common.rest.jaxb.msg.ServerBaseTranslateType;
import it.ghismo.common.rest.jaxb.msg.ServerTranslateAttributeType;
import it.ghismo.common.rest.jaxb.msg.ServerTranslateContainerType;
import it.ghismo.common.rest.jaxb.msg.ServerTranslateType;
import it.ghismo.common.rest.servlets.CommonRestServlet;
import it.ghismo.common.rest.support.Errors;
import it.ghismo.common.rest.support.Language;
import it.ghismo.common.rest.support.ParametersReader;
import it.ghismo.common.utils.AmountUtil;
import it.ghismo.common.utils.NumberUtil;
import it.ghismo.common.utils.StringUtil;
import it.ghismo.common.utils.date.DateUtil;
import it.ghismo.common.utils.reflections.CustomMethod;

public class MsgBeanUtil {
	public enum MsgBeanUtilCachingMode{Complete, Progressive, NoCache} 
	public enum MsgBeanUtilOperation{Get, Remove} 

	
	public final static String REFLECT_METHOD_PREFIX_GET = "get";
	public final static String REFLECT_METHOD_PREFIX_SET = "set";
	
	public final static Boolean TRUE = new Boolean(true);
	public final static Boolean FALSE = new Boolean(false);

	
	private static HashMap<Class<? extends ServerBaseTranslateType>, ServerTranslateType> mapDefaultTranslateProcedure = null;

	
	private BeanType bean = null;
	private MsgBeanUtilCachingMode cachingMode = MsgBeanUtilCachingMode.Complete;
	
	private Map<String, FieldType> fieldsMap = null;
	
	
	static {
		mapDefaultTranslateProcedure = new HashMap<Class<? extends ServerBaseTranslateType>, ServerTranslateType>();
		List<String> attributeList = null;
		
		// PageObjectBaseType (FieldType, ButtonType, ListType, ListEntityType, ListRecordActionType, ListButtonType)
		ServerTranslateType settings = new ServerTranslateType();
		attributeList = settings.getAttributeName();
		attributeList.add("label");
		attributeList.add("sublabel");
		mapDefaultTranslateProcedure.put(PageObjectBaseType.class, settings);
		mapDefaultTranslateProcedure.put(FieldType.class, settings);
		mapDefaultTranslateProcedure.put(ButtonType.class, settings);
		mapDefaultTranslateProcedure.put(ListType.class, settings);
		mapDefaultTranslateProcedure.put(ListEntityType.class, settings);
		mapDefaultTranslateProcedure.put(ListRecordActionType.class, settings);
		mapDefaultTranslateProcedure.put(ListButtonType.class, settings);
	
		
		// ErrorType, MenuType
		settings = new ServerTranslateType();
		attributeList = settings.getAttributeName();
		attributeList.add("label");
		mapDefaultTranslateProcedure.put(ErrorType.class, settings);
		mapDefaultTranslateProcedure.put(MenuType.class, settings);

		// FieldOptionType
		settings = new ServerTranslateType();
		attributeList = settings.getAttributeName();
		attributeList.add("label");
		settings.setIgnore(TRUE);
		mapDefaultTranslateProcedure.put(FieldOptionType.class, settings);
		
	}
	
	
	public MsgBeanUtil(List<FieldType> fields, MsgBeanUtilCachingMode cachingMode) {
		this.bean = new BeanType();
		DataFieldsType dft = new DataFieldsType();
		this.bean.setDataFields(dft);
		dft.getFields().addAll(fields);
		construct(bean, cachingMode);
	}
	
	public MsgBeanUtil(BeanType bean, MsgBeanUtilCachingMode cachingMode) {
		construct(bean, cachingMode);
	}
	
	private void construct(BeanType bean, MsgBeanUtilCachingMode cachingMode) {
		this.bean = bean;
		this.cachingMode = cachingMode != null ? cachingMode : MsgBeanUtilCachingMode.Complete;
		if(this.cachingMode != MsgBeanUtilCachingMode.NoCache) {
			if(this.cachingMode == MsgBeanUtilCachingMode.Complete) {
				cacheAll(true);
			}
		}
	}
	
	private void cacheAll(boolean clear) {
		// inizilizzazione mappa
		if(this.fieldsMap == null) {
			this.fieldsMap = new HashMap<String, FieldType>();
		} else if(clear) {
			this.fieldsMap.clear();
		} else ;
		
		// caching
		if(this.bean != null && this.bean.getDataFields() != null) {
			List<FieldType> fields = this.bean.getDataFields().getFields();
			if(fields != null) {
				for (FieldType field : fields) {
					this.fieldsMap.put(field.getId(), field);
				}
			}
		}
	}
	
	
	public FieldType getField(String fieldId) {
		return getField(fieldId, false);
	}
	public FieldType getField(String fieldId, boolean iterateIfNotFound) {
		return opField(MsgBeanUtilOperation.Get, fieldId, iterateIfNotFound);
	}
	public FieldType removeField(String fieldId) {
		return opField(MsgBeanUtilOperation.Remove, fieldId, true);
	}
	private FieldType opField(MsgBeanUtilOperation op, String fieldId, boolean iterateIfNotFound) {
		FieldType ris = null;
		if(this.bean != null && !StringUtil.isStringEmpty(fieldId)) {
			if(this.cachingMode != MsgBeanUtilCachingMode.NoCache && fieldsMap != null) {
				ris = (op == MsgBeanUtilOperation.Remove) ?  fieldsMap.remove(fieldId) : fieldsMap.get(fieldId);
			}
			if(ris == null || op == MsgBeanUtilOperation.Remove) {
				if(
					op == MsgBeanUtilOperation.Remove
					||
					this.cachingMode == MsgBeanUtilCachingMode.NoCache
					||
					(
						this.cachingMode == MsgBeanUtilCachingMode.Progressive 
						|| 
						(this.cachingMode == MsgBeanUtilCachingMode.Complete && iterateIfNotFound) 
					)
				) {
					if(this.bean.getDataFields() != null) {
						List<FieldType> fields = this.bean.getDataFields().getFields();
						if(fields != null) {
							int size = fields.size();
							int i = 0;
							FieldType field = null;
							while(i < size) {
								field = fields.get(i);
								if(op == MsgBeanUtilOperation.Get && this.cachingMode == MsgBeanUtilCachingMode.Progressive && fieldsMap != null && fieldsMap.get(field.getId()) == null) {
									fieldsMap.put(field.getId(), field);
								}
								if(fieldId.equals(field.getId())) {
									ris = field;
									if(op == MsgBeanUtilOperation.Remove) fields.remove(i);
									break;
								} else i++;
							}
						}
					}
				}
			}
		}
		return ris;
	}
	
	
	public boolean stdCheckFields() {
		boolean isOk = true;
		if(bean != null && bean.getDataFields() != null) {
			isOk = stdCheckFields(bean.getDataFields().getFields());
		}
		if(!isOk) {
			bean.getErrors().add(Errors.getMsgError(Errors.ERR_CD_STANDARD_FIELDS_VALIDATION_KO));
		}
		return isOk;
	}
	public boolean stdCheckFields(List<FieldType> _fields) {
		boolean isOk = true;
		if(_fields != null) {
			for (FieldType _field : _fields) {
				isOk &= stdCheckField(_field);
			}
		}
		return isOk;
	}
	public boolean stdCheckFields(FieldType... _fields) {
		boolean isOk = true;
		if(_fields != null) {
			isOk = stdCheckFields(Arrays.asList(_fields));
		}
		return isOk;
	}
	
	public boolean stdCheckField(FieldType _field) {
		boolean isOk = false;
		
		if(_field != null) {
			isOk = true;
			
			String strValue = _field.getValue();
			List<String> strValues = _field.getValues();
			DataTypeEnum tipo = _field.getType();
			if(tipo == null) tipo = DataTypeEnum.STRING;
			Object objValue = null;
			List<Object> objValues = null;

			
			// Checks single and multi values...
			boolean isMultiValue = _field.isMultivalue() != null && _field.isMultivalue();
			if(!isMultiValue && strValues.size() > 0) {
				_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__MULTIVALUE_NA));
			}
			if(!StringUtil.isStringEmpty(strValue) && strValues.size() > 0) {
				_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__MONO_AND_MULTI_VALUE));
			}

			// Check mandatory
			boolean isMandatory = _field.isMandatory() != null && _field.isMandatory();
			if(isMandatory && StringUtil.isStringEmpty(strValue) && strValues.size() == 0 ) {
				_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_MANDATORY));
			}

			// Checks min and max length
			if(_field.getMinlength() != null || _field.getMaxlength() != null) {
				int minLength = _field.getMinlength() != null ? _field.getMinlength() : Integer.MIN_VALUE;
				int maxLength = _field.getMaxlength() != null ? _field.getMaxlength() : Integer.MAX_VALUE;
				if(!StringUtil.isStringEmpty(strValue)) {
					if(strValue.length() < minLength || strValue.length() > maxLength) {
						_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_LENGTH_NV));
					}
				}
				if(strValues.size() > 0) {
					for (String currVal : strValues) {
						if(currVal.length() < minLength || currVal.length() > maxLength) {
							_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_MULTIVALUE_LENGTH_NV));
							break;
						}
					}
				}
			}
			

			// Checks about value type
			Object minObj = parseValue(tipo, _field.getMinvalue());
			Object maxObj = parseValue(tipo, _field.getMaxvalue());
			if(!StringUtil.isStringEmpty(strValue)) {
				objValue = parseValue(_field);
				if(objValue == null) { // checks the value type
					_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_VALUE_NV));
				} else {
					// checks min and max values
					if(!checkMinMaxVal(tipo, objValue, minObj, maxObj)) {
						_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_VALUE_NIR));
					}
					
				}
			}
			if(!strValues.isEmpty()) {
				objValues = parseValues(_field);
				if(objValues == null || objValues.size() != strValues.size()) { // checks the value type
					_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_MULTIVALUE_VALUE_NV));
				}
				if(objValues != null && !objValues.isEmpty()) {
					// checks min and max values
					boolean isMinMaxMultiOk = true;
					for (Object objCorr : objValues) {
						isMinMaxMultiOk &= checkMinMaxVal(tipo, objCorr, minObj, maxObj);
						if(!isMinMaxMultiOk) break;
					}
					if(!isMinMaxMultiOk)
						_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_MULTIVALUE_VALUE_NIR));
				}
			}

			
			// Checks min and max values
			if(!StringUtil.isStringEmpty(strValue)) {
				objValue = parseValue(_field);
				if(objValue == null) {
					_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_VALUE_NV));
				}
			}
			if(!strValues.isEmpty()) {
				objValues = parseValues(_field);
				if(objValues == null || objValues.size() != strValues.size()) {
					_field.getErrors().add(Errors.getMsgError(Errors.ERR_CD__FIELD_MULTIVALUE_VALUE_NV));
				}
			}
			
			
			if(!_field.getErrors().isEmpty()) {
				isOk = false;
			}
		}
		
		return isOk;
	}
	
	
	public <T> boolean checkMinMaxVal(Comparable<T> chkValue, T minVal, T maxVal) {
		boolean ret = true;
		if(chkValue != null && (minVal != null || maxVal != null)) {
			if(minVal != null) {
				ret &= chkValue.compareTo(minVal) >= 0 ;
			}
			if(maxVal != null) {
				ret &= chkValue.compareTo(maxVal) <= 0 ;
			}
		}
		return ret;
	}
	public boolean checkMinMaxVal(DataTypeEnum tipo, Object chkValue, Object minVal, Object maxVal) {
		boolean ret = true;
		if(chkValue != null && (minVal != null || maxVal != null)) {
			if(tipo == null) tipo = DataTypeEnum.STRING;
			switch (tipo) {
			case DATE:
			case TIME:
			case DATE_TIME:
				ret = checkMinMaxVal((Date)chkValue, (Date)minVal, (Date)maxVal);
				break;
			case DECIMAL: // produce Double
				ret = checkMinMaxVal((Double)chkValue, (Double)minVal, (Double)maxVal);
				break;
			case NUMBER:  // produce Long
				ret = checkMinMaxVal((Long)chkValue, (Long)minVal, (Long)maxVal);
				break;
			case STRING:
				ret = checkMinMaxVal((String)chkValue, (String)minVal, (String)maxVal);
				break;
			default:
				break;
			}		
		}
		return ret;
	}
	public Object parseValue(DataTypeEnum tipo, String strValue) {
		Object objValue = null;
		if(tipo == null) tipo = DataTypeEnum.STRING;
		if(!StringUtil.isStringEmpty(strValue)) {
			switch (tipo) {
			case DATE: // produce Date
				objValue = DateUtil.dateGuiFormatter.parse(strValue);
				break;
			case TIME: // produce Date
				objValue = DateUtil.timeGuiFormatter.parse(strValue);
				break;
			case DATE_TIME: // produce Date
				objValue = DateUtil.dateTimeGuiFormatter.parse(strValue);
				break;
			case DECIMAL: // produce Double
				try { objValue = AmountUtil.amountFormat.parse(strValue); } catch (ParseException e) { }
				break;
			case NUMBER:  // produce Long
				Number nTmp = null;
				try { nTmp = NumberUtil.integerFormat.parse(strValue); } catch (ParseException e) { }
				if(nTmp != null) {
					objValue = nTmp.longValue();
				}
				break;
			case STRING:
				// usa direttamente gli oggetti String (o List<String>) degli oggetti del Bean
				objValue = strValue;
				break;
			default:
				break;
			}		
		}
		return objValue;
	}
	public Object parseValue(FieldType _field) {
		Object objValue = null;
		if(_field != null) {
			DataTypeEnum tipo = _field.getType();
			String strValue = _field.getValue();
			objValue = parseValue(tipo, strValue);
		}
		return objValue;
	}
	public List<Object> parseValues(FieldType _field) {
		List<Object> objValues = null;
		DataTypeEnum tipo = _field.getType();
		List<String> strValues = _field.getValues();
		if(!strValues.isEmpty()) {
			objValues = new LinkedList<Object>();
			Object objCorrParsed = null;
			for (String valCorr : strValues) {
				objCorrParsed = parseValue(tipo, valCorr);
				if(objCorrParsed != null) {
					objValues.add(objCorrParsed);
				}
			}
		}
		return objValues;
	}
	
	
	/*********************************************************/
	/*************** Translate Management ********************/
	/*********************************************************/
	private static String translateGetMethodNameFromAttributeName(String attributeName, String prefix) {
		String ris = null;
		if(attributeName != null) {
			StringBuffer sb = new StringBuffer();
			if(prefix != null)
				sb.append(prefix);
			
			String[] tokens = attributeName.split("_");
			for (String token : tokens) {
				if(token != null) {
					token = token.trim();
					if(token.length() > 0) {
						sb.append(token.substring(0, 1).toUpperCase());
						sb.append(token.substring(1));
					}
				}
			}
			ris = sb.toString();
		}
		return ris;
	}
	private static void translateAttribute(ServerBaseTranslateType objToTranslate, String attributeName, String paramPrefix, List<String> labelParams, Language lang) {
		String getMethodName = translateGetMethodNameFromAttributeName(attributeName, REFLECT_METHOD_PREFIX_GET);
		String setMethodName = translateGetMethodNameFromAttributeName(attributeName, REFLECT_METHOD_PREFIX_SET);
		try {
			CustomMethod<ServerBaseTranslateType, ?, String> customMethodGet = new CustomMethod<ServerBaseTranslateType, Object, String>(objToTranslate, getMethodName);
			String label = customMethodGet.exec();
			String labelTranslated = null;
			
			if(label != null && label.trim().length() > 0) {
				// verifica se siamo in presenza di etichette parametriche
				if(labelParams != null && !labelParams.isEmpty()) { // e' parametrica
					if(paramPrefix != null)
						labelTranslated = lang.getParameterString(label, labelParams, paramPrefix);
					else
						labelTranslated = lang.getParameterString(label, labelParams);
				} else { // non e' parametrica
					labelTranslated = lang.get(label, true);
				}
				
				if(labelTranslated == null)
					labelTranslated = "";

				CustomMethod<ServerBaseTranslateType, String, ?> customMethodSet = new CustomMethod<ServerBaseTranslateType, String, Object>(objToTranslate, setMethodName);
				customMethodSet.setOggettiParametri(labelTranslated);
				customMethodSet.exec();
			}
		} catch (Exception e) {
			//e.printStackTrace();
		}
		
	}
	public static void translateField(ServerBaseTranslateType objToTranslate, ServerTranslateType containerSpecificSettings, Language lang) {
		Boolean traduci = objToTranslate.isServTrans();
		ServerTranslateType servTranslate = objToTranslate.getServTranslateObj();
		
		if(servTranslate == null && (traduci == null || traduci)) {
			if(containerSpecificSettings != null) {
				servTranslate = containerSpecificSettings;

				// verifica se contiene solo l'attributo ignore... in tal caso copiare il resto del comportamento di default
				if(servTranslate.getAttribute().isEmpty() && servTranslate.getAttributeName().isEmpty() && servTranslate.isIgnore() != null) {
					ServerTranslateType defaultServTranslate = mapDefaultTranslateProcedure.get(objToTranslate.getClass());
					if(!defaultServTranslate.getAttribute().isEmpty()) {
						servTranslate.getAttribute().addAll(defaultServTranslate.getAttribute());
					}
					if(!defaultServTranslate.getAttributeName().isEmpty()) {
						servTranslate.getAttributeName().addAll(defaultServTranslate.getAttributeName());
					}
				}
			} else {
				// applicare comportamento di default a seconda della classe
				servTranslate = mapDefaultTranslateProcedure.get(objToTranslate.getClass());
			}
		}
		if(servTranslate != null) {
			Boolean ignora = servTranslate.isIgnore();
			boolean applicaTraduzione = ( traduci == null && (ignora == null || !ignora) ) || ( traduci != null && traduci );
			if(applicaTraduzione) {
				List<String> strAttributes = servTranslate.getAttributeName();
				if(strAttributes != null && !strAttributes.isEmpty()) {
					for (String strAttributeCorr : strAttributes) {
						translateAttribute(objToTranslate, strAttributeCorr, null, null, lang);
					}
				} else {
					List<ServerTranslateAttributeType> attributesToTranslate = servTranslate.getAttribute();
					for (ServerTranslateAttributeType attributeCorr : attributesToTranslate) {
						translateAttribute(objToTranslate, attributeCorr.getName(), attributeCorr.getParamPrefix(), attributeCorr.getParams(), lang);
					}
				}
			}
		}
	}
	public static void translateFieldList(List<? extends ServerBaseTranslateType> listObjToTranslate, ServerTranslateType containerSpecificSettings, Language lang) {
		if(listObjToTranslate != null) {
			for (ServerBaseTranslateType objToTranslateCorr : listObjToTranslate) {
				translateField(objToTranslateCorr, containerSpecificSettings, lang);

				// recupero eventuali settings delle liste di oggetti da tradurre
				List<ServerTranslateContainerType> translateSettingsMap = objToTranslateCorr.getServContainerTranslate();
				
				if(objToTranslateCorr instanceof PageObjectBaseType) { // superclasse di: FieldType, ButtonType, ListType, ListEntityType, ListRecordActionType, ListButtonType
					// puo' contenere oggetti di tipo ErrorType
					translateFieldList(((PageObjectBaseType) objToTranslateCorr).getErrors(), getTranslateSpecificSettings(translateSettingsMap, "errors", ErrorType.class), lang);
				}
					
				if(objToTranslateCorr instanceof FieldType) {
					// puo' contenere oggetti di tipo FieldOptionType
					translateFieldList(((FieldType) objToTranslateCorr).getOptions(), getTranslateSpecificSettings(translateSettingsMap, "options", FieldOptionType.class), lang);
				} else if(objToTranslateCorr instanceof ListType) {
					// puo' contenere oggetti di tipo ListEntityType
					ListType corrListType = (ListType) objToTranslateCorr;
					translateFieldList(corrListType.getEntities(), getTranslateSpecificSettings(translateSettingsMap, "entities", ListEntityType.class), lang);
					translateFieldList(corrListType.getRecordButtons(), getTranslateSpecificSettings(translateSettingsMap, "record_buttons", ListRecordActionType.class), lang);
					translateFieldList(corrListType.getListButtons(), getTranslateSpecificSettings(translateSettingsMap, "list_buttons", ListButtonType.class), lang);
				} else if(objToTranslateCorr instanceof MenuType) {
					// puo' contenere oggetti di tipo MenuType
					translateFieldList(((MenuType) objToTranslateCorr).getMenuChildren(), getTranslateSpecificSettings(translateSettingsMap, "menu_children", MenuType.class), lang);
				} else ;
			}
		}
	}
	
	private static ServerTranslateType translateClonaServerTranslateType(ServerTranslateType src) {
		ServerTranslateType ris = new ServerTranslateType();
		
		ris.setIgnore(src.isIgnore());
		
		List<ServerTranslateAttributeType> listAttributes = src.getAttribute();
		List<String> attributeNames = src.getAttributeName();
		
		if(listAttributes != null && !listAttributes.isEmpty())
			ris.getAttribute().addAll(listAttributes);
		
		if(attributeNames != null && !attributeNames.isEmpty())
			ris.getAttributeName().addAll(attributeNames);
		
		return ris;
	}
	
	private static ServerTranslateType getTranslateSpecificSettings(List<ServerTranslateContainerType> translateSettingsMap, String propertyName, Class<?> propertyClass) {
		ServerTranslateType servTranslate = null;
		if(translateSettingsMap != null && propertyName != null) {
			for (ServerTranslateContainerType settingCorr : translateSettingsMap) {
				if(propertyName.equals(settingCorr.getPropertyName())) {
					servTranslate = settingCorr.getServTranslateObj();
					Boolean traduci = settingCorr.isServTrans();
					if(traduci != null) {
						ServerTranslateType servTranslateDefault = mapDefaultTranslateProcedure.get(propertyClass);
						servTranslate = translateClonaServerTranslateType(servTranslateDefault);
						if(servTranslate != null) { 
							if(traduci) {
								servTranslate.setIgnore(null);
							} else {
								servTranslate.setIgnore(TRUE);
							}
						}
					}
				}
			}
		}
		return servTranslate;
	}
	public void translate(Language lang) {
		translate(this.bean, lang);
	}
	public static void translate(BeanType bean, Language lang) {
		boolean translate = Util.getBoolean(CommonRestServlet.getParametersReader().get(ParametersReader.TRANSLATE_LABEL, "0"), false);
		if(translate) {
			try {
				if(lang == null) {
					String strLang = null;
					
					if(		bean.getSession() != null 
							&& bean.getSession().getLang() != null 
							&& bean.getSession().getLang().trim().length() > 0) {
						strLang = bean.getSession().getLang().trim();
					} else {
						strLang = Language.LANGUAGE_DEFAULT;
					}
					lang = new Language(CommonRestServlet.getParametersReader(), strLang);
				}
				
				
				List<ErrorType> errors = bean.getErrors();
				translateFieldList(errors, null, lang);
				
				if( bean.getDataFields() != null) {
					List<FieldType> fields = bean.getDataFields().getFields();
					if(fields.size() > 0)
						translateFieldList(fields, getTranslateSpecificSettings(bean.getDataFields().getServContainerTranslate(), "fields", FieldType.class), lang);
				}

				if(bean.getDataLists() != null) {
					List<ListType> lists = bean.getDataLists().getLists();
					if(lists.size() > 0)
						translateFieldList(lists, getTranslateSpecificSettings(bean.getDataLists().getServContainerTranslate(), "lists", ListType.class), lang);
				}

				if(bean.getDataButtons() != null) {
					List<ButtonType> buttons = bean.getDataButtons().getButtons();
					if(buttons.size() > 0)
						translateFieldList(buttons, getTranslateSpecificSettings(bean.getDataButtons().getServContainerTranslate(), "buttons", ButtonType.class), lang);
				}
				
				if(bean.getDataMenu() != null) {
					List<MenuType> menu = bean.getDataMenu().getMenu();
					if(menu.size() > 0)
						translateFieldList(menu, getTranslateSpecificSettings(bean.getDataMenu().getServContainerTranslate(), "menu", MenuType.class), lang);
				}
				
			} catch(Exception e) {
				//Log.error("Errore generico translatelabel->Bean Util : " + Thread.currentThread().getStackTrace()[1], e);
			}
		}
	}
	
	public static void translateAddParameterToLabel(ServerBaseTranslateType obj, String attributeName, String paramValue) {
		if(obj != null && attributeName != null && paramValue != null) {
			
			if(obj.getServTranslateObj() == null) {
				ServerTranslateType stt = new ServerTranslateType();
				obj.setServTranslateObj(stt);
				ServerTranslateAttributeType attribute = new ServerTranslateAttributeType();
				stt.getAttribute().add(attribute);
				attribute.setName(attributeName);
				attribute.getParams().add(paramValue);
			} else {
				List<ServerTranslateAttributeType> attributi = obj.getServTranslateObj().getAttribute();
				if(attributi != null) {
					for (ServerTranslateAttributeType attribute : attributi) {
						if(attributeName.equals(attribute.getName())) {
							attribute.getParams().add(paramValue);
							break;
						}
					}
				}
			}
		}
	}
	
	
	
}
