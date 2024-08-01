
package apimailws.apimailwsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para validationsFaultInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validationsFaultInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ListaErrores" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Error" type="{http://bse.com.uy/services/apimailWS/apimailWS}serviceError" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validationsFaultInfo", propOrder = {
    "listaErrores"
})
public class ValidationsFaultInfo {

    @XmlElement(name = "ListaErrores")
    protected ValidationsFaultInfo.ListaErrores listaErrores;

    /**
     * Obtiene el valor de la propiedad listaErrores.
     * 
     * @return
     *     possible object is
     *     {@link ValidationsFaultInfo.ListaErrores }
     *     
     */
    public ValidationsFaultInfo.ListaErrores getListaErrores() {
        return listaErrores;
    }

    /**
     * Define el valor de la propiedad listaErrores.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationsFaultInfo.ListaErrores }
     *     
     */
    public void setListaErrores(ValidationsFaultInfo.ListaErrores value) {
        this.listaErrores = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="Error" type="{http://bse.com.uy/services/apimailWS/apimailWS}serviceError" maxOccurs="unbounded" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "error"
    })
    public static class ListaErrores {

        @XmlElement(name = "Error")
        protected List<ServiceError> error;

        /**
         * Gets the value of the error property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the error property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getError().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ServiceError }
         * 
         * 
         */
        public List<ServiceError> getError() {
            if (error == null) {
                error = new ArrayList<ServiceError>();
            }
            return this.error;
        }

    }

}
