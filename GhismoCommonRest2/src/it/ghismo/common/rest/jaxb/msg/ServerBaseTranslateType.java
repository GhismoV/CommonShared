
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServerBaseTranslateType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServerBaseTranslateType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}ServerBaseTranslateContainerType">
 *       &lt;choice>
 *         &lt;element name="servTranslateObj" type="{msg.jaxb.rest.common.ghismo.it}ServerTranslateType" minOccurs="0"/>
 *         &lt;element name="servTrans" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerBaseTranslateType", propOrder = {
    "servTranslateObj",
    "servTrans"
})
@XmlSeeAlso({
    ErrorType.class,
    MenuType.class,
    FieldOptionType.class,
    PageObjectBaseType.class
})
public class ServerBaseTranslateType
    extends ServerBaseTranslateContainerType
{

    protected ServerTranslateType servTranslateObj;
    protected Boolean servTrans;

    /**
     * Gets the value of the servTranslateObj property.
     * 
     * @return
     *     possible object is
     *     {@link ServerTranslateType }
     *     
     */
    public ServerTranslateType getServTranslateObj() {
        return servTranslateObj;
    }

    /**
     * Sets the value of the servTranslateObj property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServerTranslateType }
     *     
     */
    public void setServTranslateObj(ServerTranslateType value) {
        this.servTranslateObj = value;
    }

    /**
     * Gets the value of the servTrans property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServTrans() {
        return servTrans;
    }

    /**
     * Sets the value of the servTrans property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServTrans(Boolean value) {
        this.servTrans = value;
    }

}
