
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListEntityType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListEntityType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}PageObjectBaseType">
 *       &lt;sequence>
 *         &lt;element name="data_ord" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="key_ord" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="type" type="{msg.jaxb.rest.common.ghismo.it}DataTypeEnum" minOccurs="0"/>
 *         &lt;element name="hidden" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="optional" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListEntityType", propOrder = {
    "dataOrd",
    "keyOrd",
    "type",
    "hidden",
    "optional"
})
public class ListEntityType
    extends PageObjectBaseType
{

    @XmlElement(name = "data_ord")
    protected int dataOrd;
    @XmlElement(name = "key_ord")
    protected Integer keyOrd;
    protected DataTypeEnum type;
    protected Boolean hidden;
    protected Boolean optional;

    /**
     * Gets the value of the dataOrd property.
     * 
     */
    public int getDataOrd() {
        return dataOrd;
    }

    /**
     * Sets the value of the dataOrd property.
     * 
     */
    public void setDataOrd(int value) {
        this.dataOrd = value;
    }

    /**
     * Gets the value of the keyOrd property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKeyOrd() {
        return keyOrd;
    }

    /**
     * Sets the value of the keyOrd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKeyOrd(Integer value) {
        this.keyOrd = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link DataTypeEnum }
     *     
     */
    public DataTypeEnum getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataTypeEnum }
     *     
     */
    public void setType(DataTypeEnum value) {
        this.type = value;
    }

    /**
     * Gets the value of the hidden property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHidden() {
        return hidden;
    }

    /**
     * Sets the value of the hidden property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHidden(Boolean value) {
        this.hidden = value;
    }

    /**
     * Gets the value of the optional property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOptional() {
        return optional;
    }

    /**
     * Sets the value of the optional property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOptional(Boolean value) {
        this.optional = value;
    }

}
