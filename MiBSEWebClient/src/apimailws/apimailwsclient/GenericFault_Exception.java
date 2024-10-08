
package apimailws.apimailwsclient;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebFault(name = "GenericFault", targetNamespace = "http://bse.com.uy/services/apimailWS/apimailWS")
public class GenericFault_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private GenericFault faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public GenericFault_Exception(String message, GenericFault faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param faultInfo
     * @param cause
     * @param message
     */
    public GenericFault_Exception(String message, GenericFault faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: apimailws.apimailwsclient.GenericFault
     */
    public GenericFault getFaultInfo() {
        return faultInfo;
    }

}
