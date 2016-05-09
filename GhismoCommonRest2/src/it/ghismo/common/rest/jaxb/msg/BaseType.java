
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BaseType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BaseType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BaseType")
@XmlSeeAlso({
    BeanType.class,
    ServerTranslateAttributeType.class,
    ListRecordType.class,
    FieldValueType.class,
    OutEntityType.class,
    SessionType.class,
    ServerTranslateContainerType.class,
    OutputType.class,
    ServerTranslateType.class,
    ActionType.class,
    ServerBaseTranslateContainerType.class
})
public class BaseType {


}
