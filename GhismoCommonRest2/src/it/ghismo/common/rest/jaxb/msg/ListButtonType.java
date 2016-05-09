
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListButtonType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListButtonType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}PageObjectBaseType">
 *       &lt;sequence>
 *         &lt;element name="image_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
@XmlType(name = "ListButtonType", propOrder = {
    "imageUrl",
    "flgVisibility",
    "action"
})
public class ListButtonType
    extends PageObjectBaseType
{

    @XmlElement(name = "image_url")
    protected String imageUrl;
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
