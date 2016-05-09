package it.ghismo.common.rest.support;

import it.ghismo.common.rest.jaxb.msg.ErrorSeverityEnum;
import it.ghismo.common.rest.jaxb.msg.ErrorType;

public class Errors {

	public static final String BASE_LABEL_ERR_CD = "LABEL_";
	
	public static final String ERR_CD__OK = "000";
	public static final String ERR_CD__MULTIVALUE_NA = "001";
	public static final String ERR_CD__MONO_AND_MULTI_VALUE = "002";
	public static final String ERR_CD_STANDARD_FIELDS_VALIDATION_KO = "003";
	public static final String ERR_CD__FIELD_MANDATORY = "004";
	public static final String ERR_CD__FIELD_LENGTH_NV = "005";
	public static final String ERR_CD__FIELD_MULTIVALUE_LENGTH_NV = "006";
	public static final String ERR_CD__FIELD_VALUE_NV = "007";
	public static final String ERR_CD__FIELD_MULTIVALUE_VALUE_NV = "008";
	public static final String ERR_CD__FIELD_VALUE_NIR = "009";
	public static final String ERR_CD__FIELD_MULTIVALUE_VALUE_NIR = "010";

	

	public Errors() {
	}

	
	public static ErrorType getMsgInfoError() {
		return getMsgInfoError(null);
	}
	public static ErrorType getMsgInfoError(String msg) {
		return getMsgError(ERR_CD__OK, ErrorSeverityEnum.INFO, msg);
	}
	public static ErrorType getMsgError(String code) {
		return getMsgError(code, ErrorSeverityEnum.ERROR, null);
	}
	public static ErrorType getMsgError(String code, ErrorSeverityEnum severity) {
		return getMsgError(code, severity, null);
	}
	public static ErrorType getMsgError(String code, ErrorSeverityEnum severity, String msg) {
		ErrorType ris = new ErrorType();
		ris.setCode(code);
		ris.setSev(severity);
		ris.setMsg(msg != null ? msg : getErrorLabel(code));
		return ris;
	}
	
	private static String getErrorLabel(String errorCode) {
		return BASE_LABEL_ERR_CD + errorCode;
	}
}
