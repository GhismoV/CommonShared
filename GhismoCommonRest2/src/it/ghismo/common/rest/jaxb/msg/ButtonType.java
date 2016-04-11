
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ButtonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ButtonType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}PageObjectBaseType">
 *       &lt;sequence>
 *         &lt;element name="image_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="auto_send" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="async" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="flg_visibility" type="{msg.jaxb.rest.common.ghismo.it}VisibilityEnum" minOccurs="0"/>
 *         &lt;element name="action" type="{msg.jaxb.rest.common.ghismo.it}ActionType"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ButtonType", propOrder = {
    "imageUrl",
    "autoSend",
    "async",
    "flgVisibility",
    "action"
})
public class ButtonType
    extends PageObjectBaseType
{

    @XmlElement(name = "image_url")
    protected String imageUrl;
    @XmlElement(name = "auto_send")
    protected Boolean autoSend;
    protected Boolean async;
    @XmlElement(name = "flg_visibility")
    protected VisibilityEnum flgVisibility;
    @XmlElement(required = true)
    protected ActionType action;

    /**
     * Gets the value of the imageUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageUrl() {
        return imageUrl;
    }

    /**
     * Sets the value of the imageUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageUrl(String value) {
        this.imageUrl = value;
    }

    /**
     * Gets the value of the autoSend property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoSend() {
        return autoSend;
    }

    /**
     * Sets the value of the autoSend property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoSend(Boolean value) {
        this.autoSend = value;
    }

    /**
     * Gets the value of the async property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAsync() {
        return async;
    }

    /**
     * Sets the value of the async property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAsync(Boolean value) {
        this.async = value;
    }

    /**
     * Gets the value of the flgVisibility property.
     * 
     * @return
     *     possible object is
     *     {@link VisibilityEnum }
     *     
     */
    public VisibilityEnum getFlgVisibility() {
        return flgVisibility;
    }

    /**
     * Sets the value of the flgVisibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link VisibilityEnum }
     *     
     */
    public void setFlgVisibility(VisibilityEnum value) {
        this.flgVisibility = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link ActionType }
     *     
     */
    public ActionType getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link ActionType }
     *     
     */
    public void setAction(ActionType value) {
        this.action = value;
    }

}
