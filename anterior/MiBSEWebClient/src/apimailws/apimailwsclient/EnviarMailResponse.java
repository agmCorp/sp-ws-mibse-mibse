
package apimailws.apimailwsclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enviarMailResponse complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="enviarMailResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarMailResponse", propOrder = {
    "idMail"
})
public class EnviarMailResponse {

    protected String idMail;

    /**
     * Obtiene el valor de la propiedad idMail.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdMail() {
        return idMail;
    }

    /**
     * Define el valor de la propiedad idMail.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdMail(String value) {
        this.idMail = value;
    }

}
