
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
    ErrorType.class,
    DataFieldsType.class,
    DataButtonsType.class,
    ListRecordType.class,
    ListEntityType.class,
    FieldValueType.class,
    MenuType.class,
    OutEntityType.class,
    SessionType.class,
    DataListsType.class,
    OutputType.class,
    FieldOptionType.class,
    ListButtonType.class,
    ActionType.class,
    PageObjectBaseType.class,
    ListRecordActionType.class
})
public class BaseType {


}
