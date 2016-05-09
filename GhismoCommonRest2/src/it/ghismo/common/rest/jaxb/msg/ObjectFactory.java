
package it.ghismo.common.rest.jaxb.msg;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.ghismo.common.rest.jaxb.msg package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Bean_QNAME = new QName("msg.jaxb.rest.common.ghismo.it", "Bean");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.ghismo.common.rest.jaxb.msg
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link BeanType }
     * 
     */
    public BeanType createBeanType() {
        return new BeanType();
    }

    /**
     * Create an instance of {@link ErrorType }
     * 
     */
    public ErrorType createErrorType() {
        return new ErrorType();
    }

    /**
     * Create an instance of {@link BaseType }
     * 
     */
    public BaseType createBaseType() {
        return new BaseType();
    }

    /**
     * Create an instance of {@link DataFieldsType }
     * 
     */
    public DataFieldsType createDataFieldsType() {
        return new DataFieldsType();
    }

    /**
     * Create an instance of {@link ServerTranslateAttributeType }
     * 
     */
    public ServerTranslateAttributeType createServerTranslateAttributeType() {
        return new ServerTranslateAttributeType();
    }

    /**
     * Create an instance of {@link DataButtonsType }
     * 
     */
    public DataButtonsType createDataButtonsType() {
        return new DataButtonsType();
    }

    /**
     * Create an instance of {@link ListRecordType }
     * 
     */
    public ListRecordType createListRecordType() {
        return new ListRecordType();
    }

    /**
     * Create an instance of {@link ListType }
     * 
     */
    public ListType createListType() {
        return new ListType();
    }

    /**
     * Create an instance of {@link ServerBaseTranslateType }
     * 
     */
    public ServerBaseTranslateType createServerBaseTranslateType() {
        return new ServerBaseTranslateType();
    }

    /**
     * Create an instance of {@link ListEntityType }
     * 
     */
    public ListEntityType createListEntityType() {
        return new ListEntityType();
    }

    /**
     * Create an instance of {@link FieldValueType }
     * 
     */
    public FieldValueType createFieldValueType() {
        return new FieldValueType();
    }

    /**
     * Create an instance of {@link MenuType }
     * 
     */
    public MenuType createMenuType() {
        return new MenuType();
    }

    /**
     * Create an instance of {@link ServerBaseTranslateContainerType }
     * 
     */
    public ServerBaseTranslateContainerType createServerBaseTranslateContainerType() {
        return new ServerBaseTranslateContainerType();
    }

    /**
     * Create an instance of {@link FieldType }
     * 
     */
    public FieldType createFieldType() {
        return new FieldType();
    }

    /**
     * Create an instance of {@link OutEntityType }
     * 
     */
    public OutEntityType createOutEntityType() {
        return new OutEntityType();
    }

    /**
     * Create an instance of {@link SessionType }
     * 
     */
    public SessionType createSessionType() {
        return new SessionType();
    }

    /**
     * Create an instance of {@link PageObjectBaseType }
     * 
     */
    public PageObjectBaseType createPageObjectBaseType() {
        return new PageObjectBaseType();
    }

    /**
     * Create an instance of {@link ServerTranslateContainerType }
     * 
     */
    public ServerTranslateContainerType createServerTranslateContainerType() {
        return new ServerTranslateContainerType();
    }

    /**
     * Create an instance of {@link DataListsType }
     * 
     */
    public DataListsType createDataListsType() {
        return new DataListsType();
    }

    /**
     * Create an instance of {@link OutputType }
     * 
     */
    public OutputType createOutputType() {
        return new OutputType();
    }

    /**
     * Create an instance of {@link DataMenuType }
     * 
     */
    public DataMenuType createDataMenuType() {
        return new DataMenuType();
    }

    /**
     * Create an instance of {@link ServerTranslateType }
     * 
     */
    public ServerTranslateType createServerTranslateType() {
        return new ServerTranslateType();
    }

    /**
     * Create an instance of {@link FieldOptionType }
     * 
     */
    public FieldOptionType createFieldOptionType() {
        return new FieldOptionType();
    }

    /**
     * Create an instance of {@link ListButtonType }
     * 
     */
    public ListButtonType createListButtonType() {
        return new ListButtonType();
    }

    /**
     * Create an instance of {@link ActionType }
     * 
     */
    public ActionType createActionType() {
        return new ActionType();
    }

    /**
     * Create an instance of {@link ButtonType }
     * 
     */
    public ButtonType createButtonType() {
        return new ButtonType();
    }

    /**
     * Create an instance of {@link ListRecordActionType }
     * 
     */
    public ListRecordActionType createListRecordActionType() {
        return new ListRecordActionType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BeanType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "msg.jaxb.rest.common.ghismo.it", name = "Bean")
    public JAXBElement<BeanType> createBean(BeanType value) {
        return new JAXBElement<BeanType>(_Bean_QNAME, BeanType.class, null, value);
    }

}
