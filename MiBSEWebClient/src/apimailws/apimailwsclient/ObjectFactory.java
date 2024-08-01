
package apimailws.apimailwsclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the apimailws.apimailwsclient package. 
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

    private final static QName _ValidationsFault_QNAME = new QName("http://bse.com.uy/services/apimailWS/apimailWS", "ValidationsFault");
    private final static QName _EnviarMailResponse_QNAME = new QName("http://bse.com.uy/services/apimailWS/apimailWS", "EnviarMailResponse");
    private final static QName _GenericFault_QNAME = new QName("http://bse.com.uy/services/apimailWS/apimailWS", "GenericFault");
    private final static QName _EnviarMailRequest_QNAME = new QName("http://bse.com.uy/services/apimailWS/apimailWS", "EnviarMailRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: apimailws.apimailwsclient
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviarMailRequest }
     * 
     */
    public EnviarMailRequest createEnviarMailRequest() {
        return new EnviarMailRequest();
    }

    /**
     * Create an instance of {@link ValidationsFaultInfo }
     * 
     */
    public ValidationsFaultInfo createValidationsFaultInfo() {
        return new ValidationsFaultInfo();
    }

    /**
     * Create an instance of {@link GenericFault }
     * 
     */
    public GenericFault createGenericFault() {
        return new GenericFault();
    }

    /**
     * Create an instance of {@link EnviarMailResponse }
     * 
     */
    public EnviarMailResponse createEnviarMailResponse() {
        return new EnviarMailResponse();
    }

    /**
     * Create an instance of {@link Metadato }
     * 
     */
    public Metadato createMetadato() {
        return new Metadato();
    }

    /**
     * Create an instance of {@link ServiceError }
     * 
     */
    public ServiceError createServiceError() {
        return new ServiceError();
    }

    /**
     * Create an instance of {@link Adjunto }
     * 
     */
    public Adjunto createAdjunto() {
        return new Adjunto();
    }

    /**
     * Create an instance of {@link EnviarMailRequest.Adjuntos }
     * 
     */
    public EnviarMailRequest.Adjuntos createEnviarMailRequestAdjuntos() {
        return new EnviarMailRequest.Adjuntos();
    }

    /**
     * Create an instance of {@link EnviarMailRequest.Metadatos }
     * 
     */
    public EnviarMailRequest.Metadatos createEnviarMailRequestMetadatos() {
        return new EnviarMailRequest.Metadatos();
    }

    /**
     * Create an instance of {@link ValidationsFaultInfo.ListaErrores }
     * 
     */
    public ValidationsFaultInfo.ListaErrores createValidationsFaultInfoListaErrores() {
        return new ValidationsFaultInfo.ListaErrores();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ValidationsFaultInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bse.com.uy/services/apimailWS/apimailWS", name = "ValidationsFault")
    public JAXBElement<ValidationsFaultInfo> createValidationsFault(ValidationsFaultInfo value) {
        return new JAXBElement<ValidationsFaultInfo>(_ValidationsFault_QNAME, ValidationsFaultInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bse.com.uy/services/apimailWS/apimailWS", name = "EnviarMailResponse")
    public JAXBElement<EnviarMailResponse> createEnviarMailResponse(EnviarMailResponse value) {
        return new JAXBElement<EnviarMailResponse>(_EnviarMailResponse_QNAME, EnviarMailResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenericFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bse.com.uy/services/apimailWS/apimailWS", name = "GenericFault")
    public JAXBElement<GenericFault> createGenericFault(GenericFault value) {
        return new JAXBElement<GenericFault>(_GenericFault_QNAME, GenericFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarMailRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://bse.com.uy/services/apimailWS/apimailWS", name = "EnviarMailRequest")
    public JAXBElement<EnviarMailRequest> createEnviarMailRequest(EnviarMailRequest value) {
        return new JAXBElement<EnviarMailRequest>(_EnviarMailRequest_QNAME, EnviarMailRequest.class, null, value);
    }

}
