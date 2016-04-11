
package it.ghismo.common.rest.jaxb.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ActionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ActionType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}BaseType">
 *       &lt;sequence>
 *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="url_params" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="key_params" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="url_method" type="{msg.jaxb.rest.common.ghismo.it}RequestMethodEnum" minOccurs="0"/>
 *         &lt;element name="url_body" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ActionType", propOrder = {
    "url",
    "urlParams",
    "keyParams",
    "urlMethod",
    "urlBody"
})
public class ActionType
    extends BaseType
{

    @XmlElement(required = true)
    protected String url;
    @XmlElement(name = "url_params")
    protected List<String> urlParams;
    @XmlElement(name = "key_params")
    protected Boolean keyParams;
    @XmlElement(name = "url_method")
    protected RequestMethodEnum urlMethod;
    @XmlElement(name = "url_body")
    protected String urlBody;

    /**
     * Gets the value of the url property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrl(String value) {
        this.url = value;
    }

    /**
     * Gets the value of the urlParams property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the urlParams property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUrlParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUrlParams() {
        if (urlParams == null) {
            urlParams = new ArrayList<String>();
        }
        return this.urlParams;
    }

    /**
     * Gets the value of the keyParams property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isKeyParams() {
        return keyParams;
    }

    /**
     * Sets the value of the keyParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setKeyParams(Boolean value) {
        this.keyParams = value;
    }

    /**
     * Gets the value of the urlMethod property.
     * 
     * @return
     *     possible object is
     *     {@link RequestMethodEnum }
     *     
     */
    public RequestMethodEnum getUrlMethod() {
        return urlMethod;
    }

    /**
     * Sets the value of the urlMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestMethodEnum }
     *     
     */
    public void setUrlMethod(RequestMethodEnum value) {
        this.urlMethod = value;
    }

    /**
     * Gets the value of the urlBody property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUrlBody() {
        return urlBody;
    }

    /**
     * Sets the value of the urlBody property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUrlBody(String value) {
        this.urlBody = value;
    }

}
