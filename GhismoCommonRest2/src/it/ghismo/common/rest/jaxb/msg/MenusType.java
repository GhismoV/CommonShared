
package it.ghismo.common.rest.jaxb.msg;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MenusType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MenusType">
 *   &lt;complexContent>
 *     &lt;extension base="{msg.jaxb.rest.common.ghismo.it}ServerBaseTranslateContainerType">
 *       &lt;sequence>
 *         &lt;element name="menus" type="{msg.jaxb.rest.common.ghismo.it}MenuType" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MenusType", propOrder = {
    "menus"
})
public class MenusType
    extends ServerBaseTranslateContainerType
{

    @XmlElement(required = true)
    protected List<MenuType> menus;

    /**
     * Gets the value of the menus property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the menus property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMenus().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MenuType }
     * 
     * 
     */
    public List<MenuType> getMenus() {
        if (menus == null) {
            menus = new ArrayList<MenuType>();
        }
        return this.menus;
    }

}
