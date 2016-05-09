
package it.ghismo.common.rest.jaxb.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for BeanType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BeanType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}BaseType">
 *       &lt;sequence>
 *         &lt;element name="errors" type="{msg.jaxb.rest.common.ghismo.it}ErrorType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="function_name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="function_descr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mac" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="session" type="{msg.jaxb.rest.common.ghismo.it}SessionType"/>
 *         &lt;element name="data_out" type="{msg.jaxb.rest.common.ghismo.it}OutputType" minOccurs="0"/>
 *         &lt;element name="data_fields" type="{msg.jaxb.rest.common.ghismo.it}DataFieldsType" minOccurs="0"/>
 *         &lt;element name="data_lists" type="{msg.jaxb.rest.common.ghismo.it}DataListsType" minOccurs="0"/>
 *         &lt;element name="data_buttons" type="{msg.jaxb.rest.common.ghismo.it}DataButtonsType" minOccurs="0"/>
 *         &lt;element name="data_menu" type="{msg.jaxb.rest.common.ghismo.it}DataMenuType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BeanType", propOrder = {
    "errors",
    "functionName",
    "functionDescr",
    "mac",
    "session",
    "dataOut",
    "dataFields",
    "dataLists",
    "dataButtons",
    "dataMenu"
})
public class BeanType
    extends BaseType
{

    @XmlElement(nillable = true)
    protected List<ErrorType> errors;
    @XmlElement(name = "function_name")
    protected String functionName;
    @XmlElement(name = "function_descr")
    protected String functionDescr;
    protected String mac;
    @XmlElement(required = true)
    protected SessionType session;
    @XmlElement(name = "data_out")
    protected OutputType dataOut;
    @XmlElement(name = "data_fields")
    protected DataFieldsType dataFields;
    @XmlElement(name = "data_lists")
    protected DataListsType dataLists;
    @XmlElement(name = "data_buttons")
    protected DataButtonsType dataButtons;
    @XmlElement(name = "data_menu")
    protected DataMenuType dataMenu;

    /**
     * Gets the value of the errors property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the errors property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErrors().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErrorType }
     * 
     * 
     */
    public List<ErrorType> getErrors() {
        if (errors == null) {
            errors = new ArrayList<ErrorType>();
        }
        return this.errors;
    }

    /**
     * Gets the value of the functionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionName() {
        return functionName;
    }

    /**
     * Sets the value of the functionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionName(String value) {
        this.functionName = value;
    }

    /**
     * Gets the value of the functionDescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionDescr() {
        return functionDescr;
    }

    /**
     * Sets the value of the functionDescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionDescr(String value) {
        this.functionDescr = value;
    }

    /**
     * Gets the value of the mac property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMac() {
        return mac;
    }

    /**
     * Sets the value of the mac property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMac(String value) {
        this.mac = value;
    }

    /**
     * Gets the value of the session property.
     * 
     * @return
     *     possible object is
     *     {@link SessionType }
     *     
     */
    public SessionType getSession() {
        return session;
    }

    /**
     * Sets the value of the session property.
     * 
     * @param value
     *     allowed object is
     *     {@link SessionType }
     *     
     */
    public void setSession(SessionType value) {
        this.session = value;
    }

    /**
     * Gets the value of the dataOut property.
     * 
     * @return
     *     possible object is
     *     {@link OutputType }
     *     
     */
    public OutputType getDataOut() {
        return dataOut;
    }

    /**
     * Sets the value of the dataOut property.
     * 
     * @param value
     *     allowed object is
     *     {@link OutputType }
     *     
     */
    public void setDataOut(OutputType value) {
        this.dataOut = value;
    }

    /**
     * Gets the value of the dataFields property.
     * 
     * @return
     *     possible object is
     *     {@link DataFieldsType }
     *     
     */
    public DataFieldsType getDataFields() {
        return dataFields;
    }

    /**
     * Sets the value of the dataFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataFieldsType }
     *     
     */
    public void setDataFields(DataFieldsType value) {
        this.dataFields = value;
    }

    /**
     * Gets the value of the dataLists property.
     * 
     * @return
     *     possible object is
     *     {@link DataListsType }
     *     
     */
    public DataListsType getDataLists() {
        return dataLists;
    }

    /**
     * Sets the value of the dataLists property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataListsType }
     *     
     */
    public void setDataLists(DataListsType value) {
        this.dataLists = value;
    }

    /**
     * Gets the value of the dataButtons property.
     * 
     * @return
     *     possible object is
     *     {@link DataButtonsType }
     *     
     */
    public DataButtonsType getDataButtons() {
        return dataButtons;
    }

    /**
     * Sets the value of the dataButtons property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataButtonsType }
     *     
     */
    public void setDataButtons(DataButtonsType value) {
        this.dataButtons = value;
    }

    /**
     * Gets the value of the dataMenu property.
     * 
     * @return
     *     possible object is
     *     {@link DataMenuType }
     *     
     */
    public DataMenuType getDataMenu() {
        return dataMenu;
    }

    /**
     * Sets the value of the dataMenu property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataMenuType }
     *     
     */
    public void setDataMenu(DataMenuType value) {
        this.dataMenu = value;
    }

}
