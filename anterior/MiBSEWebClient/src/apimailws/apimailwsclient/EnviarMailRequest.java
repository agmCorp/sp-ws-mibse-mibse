
package apimailws.apimailwsclient;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enviarMailRequest complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="enviarMailRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="adjuntos" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="adjunto" type="{http://bse.com.uy/services/apimailWS/apimailWS}adjunto" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="asunto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cuerpo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="destinatario" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lista" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="metadatos" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="metadato" type="{http://bse.com.uy/services/apimailWS/apimailWS}metadato" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="nombreRemitente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="remitente" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "enviarMailRequest", propOrder = {
    "adjuntos",
    "asunto",
    "cuerpo",
    "destinatario",
    "lista",
    "metadatos",
    "nombreRemitente",
    "remitente"
})
public class EnviarMailRequest {

    protected EnviarMailRequest.Adjuntos adjuntos;
    protected String asunto;
    protected String cuerpo;
    protected String destinatario;
    protected String lista;
    protected EnviarMailRequest.Metadatos metadatos;
    protected String nombreRemitente;
    protected String remitente;

    /**
     * Obtiene el valor de la propiedad adjuntos.
     * 
     * @return
     *     possible object is
     *     {@link EnviarMailRequest.Adjuntos }
     *     
     */
    public EnviarMailRequest.Adjuntos getAdjuntos() {
        return adjuntos;
    }

    /**
     * Define el valor de la propiedad adjuntos.
     * 
     * @param value
     *     allowed object is
     *     {@link EnviarMailRequest.Adjuntos }
     *     
     */
    public void setAdjuntos(EnviarMailRequest.Adjuntos value) {
        this.adjuntos = value;
    }

    /**
     * Obtiene el valor de la propiedad asunto.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Define el valor de la propiedad asunto.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAsunto(String value) {
        this.asunto = value;
    }

    /**
     * Obtiene el valor de la propiedad cuerpo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCuerpo() {
        return cuerpo;
    }

    /**
     * Define el valor de la propiedad cuerpo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCuerpo(String value) {
        this.cuerpo = value;
    }

    /**
     * Obtiene el valor de la propiedad destinatario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestinatario() {
        return destinatario;
    }

    /**
     * Define el valor de la propiedad destinatario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestinatario(String value) {
        this.destinatario = value;
    }

    /**
     * Obtiene el valor de la propiedad lista.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLista() {
        return lista;
    }

    /**
     * Define el valor de la propiedad lista.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLista(String value) {
        this.lista = value;
    }

    /**
     * Obtiene el valor de la propiedad metadatos.
     * 
     * @return
     *     possible object is
     *     {@link EnviarMailRequest.Metadatos }
     *     
     */
    public EnviarMailRequest.Metadatos getMetadatos() {
        return metadatos;
    }

    /**
     * Define el valor de la propiedad metadatos.
     * 
     * @param value
     *     allowed object is
     *     {@link EnviarMailRequest.Metadatos }
     *     
     */
    public void setMetadatos(EnviarMailRequest.Metadatos value) {
        this.metadatos = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreRemitente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreRemitente() {
        return nombreRemitente;
    }

    /**
     * Define el valor de la propiedad nombreRemitente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreRemitente(String value) {
        this.nombreRemitente = value;
    }

    /**
     * Obtiene el valor de la propiedad remitente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemitente() {
        return remitente;
    }

    /**
     * Define el valor de la propiedad remitente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemitente(String value) {
        this.remitente = value;
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
     *         &lt;element name="adjunto" type="{http://bse.com.uy/services/apimailWS/apimailWS}adjunto" maxOccurs="unbounded" minOccurs="0"/>
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
        "adjunto"
    })
    public static class Adjuntos {

        protected List<Adjunto> adjunto;

        /**
         * Gets the value of the adjunto property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the adjunto property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAdjunto().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Adjunto }
         * 
         * 
         */
        public List<Adjunto> getAdjunto() {
            if (adjunto == null) {
                adjunto = new ArrayList<Adjunto>();
            }
            return this.adjunto;
        }

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
     *         &lt;element name="metadato" type="{http://bse.com.uy/services/apimailWS/apimailWS}metadato" maxOccurs="unbounded" minOccurs="0"/>
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
        "metadato"
    })
    public static class Metadatos {

        protected List<Metadato> metadato;

        /**
         * Gets the value of the metadato property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the metadato property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMetadato().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Metadato }
         * 
         * 
         */
        public List<Metadato> getMetadato() {
            if (metadato == null) {
                metadato = new ArrayList<Metadato>();
            }
            return this.metadato;
        }

    }

}
