
package apimailws.apimailwsclient;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "ValidationsFault", targetNamespace = "http://bse.com.uy/services/apimailWS/apimailWS")
public class ValidationsFault
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private ValidationsFaultInfo faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public ValidationsFault(String message, ValidationsFaultInfo faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public ValidationsFault(String message, ValidationsFaultInfo faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: apimailws.apimailwsclient.ValidationsFaultInfo
     */
    public ValidationsFaultInfo getFaultInfo() {
        return faultInfo;
    }

}
