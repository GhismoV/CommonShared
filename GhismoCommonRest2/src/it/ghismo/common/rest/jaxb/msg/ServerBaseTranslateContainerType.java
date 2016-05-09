
package it.ghismo.common.rest.jaxb.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ServerBaseTranslateContainerType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServerBaseTranslateContainerType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}BaseType">
 *       &lt;sequence>
 *         &lt;element name="servContainerTranslate" type="{msg.jaxb.rest.common.ghismo.it}ServerTranslateContainerType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServerBaseTranslateContainerType", propOrder = {
    "servContainerTranslate"
})
@XmlSeeAlso({
    DataFieldsType.class,
    DataButtonsType.class,
    DataListsType.class,
    DataMenuType.class,
    ServerBaseTranslateType.class
})
public class ServerBaseTranslateContainerType
    extends BaseType
{

    protected List<ServerTranslateContainerType> servContainerTranslate;

    /**
     * Gets the value of the servContainerTranslate property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the servContainerTranslate property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServContainerTranslate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServerTranslateContainerType }
     * 
     * 
     */
    public List<ServerTranslateContainerType> getServContainerTranslate() {
        if (servContainerTranslate == null) {
            servContainerTranslate = new ArrayList<ServerTranslateContainerType>();
        }
        return this.servContainerTranslate;
    }

}
