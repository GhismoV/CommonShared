
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ErrorSeverityEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="ErrorSeverityEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="info"/>
 *     &lt;enumeration value="warning"/>
 *     &lt;enumeration value="error"/>
 *     &lt;enumeration value="fatal"/>
 *     &lt;enumeration value="secure"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "ErrorSeverityEnum")
@XmlEnum
public enum ErrorSeverityEnum {

    @XmlEnumValue("info")
    INFO("info"),
    @XmlEnumValue("warning")
    WARNING("warning"),
    @XmlEnumValue("error")
    ERROR("error"),
    @XmlEnumValue("fatal")
    FATAL("fatal"),
    @XmlEnumValue("secure")
    SECURE("secure");
    private final String value;

    ErrorSeverityEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ErrorSeverityEnum fromValue(String v) {
        for (ErrorSeverityEnum c: ErrorSeverityEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
