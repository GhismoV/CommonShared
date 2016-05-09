
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServerTranslateContainerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServerTranslateContainerType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}BaseType">
 *       &lt;sequence>
 *         &lt;element name="property_name" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;choice>
 *           &lt;element name="servTranslateObj" type="{msg.jaxb.rest.common.ghismo.it}ServerTranslateType" minOccurs="0"/>
 *           &lt;element name="servTrans" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerTranslateContainerType", propOrder = {
    "propertyName",
    "servTranslateObj",
    "servTrans"
})
public class ServerTranslateContainerType
    extends BaseType
{

    @XmlElement(name = "property_name", required = true)
    protected String propertyName;
    protected ServerTranslateType servTranslateObj;
    protected Boolean servTrans;

    /**
     * Gets the value of the propertyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropertyName() {
        return propertyName;
    }

    /**
     * Sets the value of the propertyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropertyName(String value) {
        this.propertyName = value;
    }

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
