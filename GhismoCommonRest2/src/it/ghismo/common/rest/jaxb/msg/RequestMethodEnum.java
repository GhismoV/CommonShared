
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RequestMethodEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RequestMethodEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="get"/>
 *     &lt;enumeration value="post"/>
 *     &lt;enumeration value="put"/>
 *     &lt;enumeration value="delete"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "RequestMethodEnum")
@XmlEnum
public enum RequestMethodEnum {

    @XmlEnumValue("get")
    GET("get"),
    @XmlEnumValue("post")
    POST("post"),
    @XmlEnumValue("put")
    PUT("put"),
    @XmlEnumValue("delete")
    DELETE("delete");
    private final String value;

    RequestMethodEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RequestMethodEnum fromValue(String v) {
        for (RequestMethodEnum c: RequestMethodEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
