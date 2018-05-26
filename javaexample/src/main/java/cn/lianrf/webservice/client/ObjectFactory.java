
package cn.lianrf.webservice.client;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cn.lianrf.webservice.server package. 
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

    private final static QName _Say1_QNAME = new QName("http://server.webservice.lianrf.cn/", "say1");
    private final static QName _Say2_QNAME = new QName("http://server.webservice.lianrf.cn/", "say2");
    private final static QName _Say2Response_QNAME = new QName("http://server.webservice.lianrf.cn/", "say2Response");
    private final static QName _Say1Response_QNAME = new QName("http://server.webservice.lianrf.cn/", "say1Response");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cn.lianrf.webservice.server
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Say1Response }
     * 
     */
    public Say1Response createSay1Response() {
        return new Say1Response();
    }

    /**
     * Create an instance of {@link Say2 }
     * 
     */
    public Say2 createSay2() {
        return new Say2();
    }

    /**
     * Create an instance of {@link Say2Response }
     * 
     */
    public Say2Response createSay2Response() {
        return new Say2Response();
    }

    /**
     * Create an instance of {@link Say1 }
     * 
     */
    public Say1 createSay1() {
        return new Say1();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say1 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.lianrf.cn/", name = "say1")
    public JAXBElement<Say1> createSay1(Say1 value) {
        return new JAXBElement<Say1>(_Say1_QNAME, Say1 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say2 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.lianrf.cn/", name = "say2")
    public JAXBElement<Say2> createSay2(Say2 value) {
        return new JAXBElement<Say2>(_Say2_QNAME, Say2 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say2Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.lianrf.cn/", name = "say2Response")
    public JAXBElement<Say2Response> createSay2Response(Say2Response value) {
        return new JAXBElement<Say2Response>(_Say2Response_QNAME, Say2Response.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Say1Response }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://server.webservice.lianrf.cn/", name = "say1Response")
    public JAXBElement<Say1Response> createSay1Response(Say1Response value) {
        return new JAXBElement<Say1Response>(_Say1Response_QNAME, Say1Response.class, null, value);
    }

}
