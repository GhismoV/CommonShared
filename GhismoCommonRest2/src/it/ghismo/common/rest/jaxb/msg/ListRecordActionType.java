
package it.ghismo.common.rest.jaxb.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListRecordActionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListRecordActionType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}PageObjectBaseType">
 *       &lt;sequence>
 *         &lt;element name="image_url" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="flg_visibility" type="{msg.jaxb.rest.common.ghismo.it}VisibilityEnum" minOccurs="0"/>
 *         &lt;element name="pilot_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pilot_enable_values" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pilot_disable_values" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pilot_hidden_values" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="action" type="{msg.jaxb.rest.common.ghismo.it}ActionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListRecordActionType", propOrder = {
    "imageUrl",
    "flgVisibility",
    "pilotId",
    "pilotEnableValues",
    "pilotDisableValues",
    "pilotHiddenValues",
    "action"
})
public class ListRecordActionType
    extends PageObjectBaseType
{

    @XmlElement(name = "image_url")
    protected String imageUrl;
    @XmlElement(name = "flg_visibility")
    protected VisibilityEnum flgVisibility;
    @XmlElement(name = "pilot_id", required = true)
    protected String pilotId;
    @XmlElement(name = "pilot_enable_values")
    protected List<String> pilotEnableValues;
    @XmlElement(name = "pilot_disable_values")
    protected List<String> pilotDisableValues;
    @XmlElement(name = "pilot_hidden_values")
    protected List<String> pilotHiddenValues;
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
     * Gets the value of the pilotId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPilotId() {
        return pilotId;
    }

    /**
     * Sets the value of the pilotId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPilotId(String value) {
        this.pilotId = value;
    }

    /**
     * Gets the value of the pilotEnableValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pilotEnableValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPilotEnableValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPilotEnableValues() {
        if (pilotEnableValues == null) {
            pilotEnableValues = new ArrayList<String>();
        }
        return this.pilotEnableValues;
    }

    /**
     * Gets the value of the pilotDisableValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pilotDisableValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPilotDisableValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPilotDisableValues() {
        if (pilotDisableValues == null) {
            pilotDisableValues = new ArrayList<String>();
        }
        return this.pilotDisableValues;
    }

    /**
     * Gets the value of the pilotHiddenValues property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pilotHiddenValues property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPilotHiddenValues().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPilotHiddenValues() {
        if (pilotHiddenValues == null) {
            pilotHiddenValues = new ArrayList<String>();
        }
        return this.pilotHiddenValues;
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
