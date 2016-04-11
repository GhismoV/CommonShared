
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataTypeEnum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="DataTypeEnum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="string"/>
 *     &lt;enumeration value="number"/>
 *     &lt;enumeration value="decimal"/>
 *     &lt;enumeration value="boolean"/>
 *     &lt;enumeration value="date"/>
 *     &lt;enumeration value="time"/>
 *     &lt;enumeration value="dateTime"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "DataTypeEnum")
@XmlEnum
public enum DataTypeEnum {

    @XmlEnumValue("string")
    STRING("string"),
    @XmlEnumValue("number")
    NUMBER("number"),
    @XmlEnumValue("decimal")
    DECIMAL("decimal"),
    @XmlEnumValue("boolean")
    BOOLEAN("boolean"),
    @XmlEnumValue("date")
    DATE("date"),
    @XmlEnumValue("time")
    TIME("time"),
    @XmlEnumValue("dateTime")
    DATE_TIME("dateTime");
    private final String value;

    DataTypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DataTypeEnum fromValue(String v) {
        for (DataTypeEnum c: DataTypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
