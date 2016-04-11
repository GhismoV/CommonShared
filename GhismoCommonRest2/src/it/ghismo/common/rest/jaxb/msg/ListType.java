
package it.ghismo.common.rest.jaxb.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ListType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}PageObjectBaseType">
 *       &lt;sequence>
 *         &lt;element name="entities" type="{msg.jaxb.rest.common.ghismo.it}ListEntityType" maxOccurs="unbounded"/>
 *         &lt;element name="records" type="{msg.jaxb.rest.common.ghismo.it}ListRecordType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="record_buttons" type="{msg.jaxb.rest.common.ghismo.it}ListRecordActionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="list_buttons" type="{msg.jaxb.rest.common.ghismo.it}ListButtonType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ListType", propOrder = {
    "entities",
    "records",
    "recordButtons",
    "listButtons"
})
public class ListType
    extends PageObjectBaseType
{

    @XmlElement(required = true)
    protected List<ListEntityType> entities;
    @XmlElement(nillable = true)
    protected List<ListRecordType> records;
    @XmlElement(name = "record_buttons")
    protected List<ListRecordActionType> recordButtons;
    @XmlElement(name = "list_buttons")
    protected List<ListButtonType> listButtons;

    /**
     * Gets the value of the entities property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the entities property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEntities().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListEntityType }
     * 
     * 
     */
    public List<ListEntityType> getEntities() {
        if (entities == null) {
            entities = new ArrayList<ListEntityType>();
        }
        return this.entities;
    }

    /**
     * Gets the value of the records property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the records property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecords().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListRecordType }
     * 
     * 
     */
    public List<ListRecordType> getRecords() {
        if (records == null) {
            records = new ArrayList<ListRecordType>();
        }
        return this.records;
    }

    /**
     * Gets the value of the recordButtons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recordButtons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecordButtons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListRecordActionType }
     * 
     * 
     */
    public List<ListRecordActionType> getRecordButtons() {
        if (recordButtons == null) {
            recordButtons = new ArrayList<ListRecordActionType>();
        }
        return this.recordButtons;
    }

    /**
     * Gets the value of the listButtons property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listButtons property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListButtons().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListButtonType }
     * 
     * 
     */
    public List<ListButtonType> getListButtons() {
        if (listButtons == null) {
            listButtons = new ArrayList<ListButtonType>();
        }
        return this.listButtons;
    }

}
