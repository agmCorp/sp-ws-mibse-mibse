package com.bse.accesodatos.eindivi;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class XmlCertificado {

    private Integer numero;
    private String monedaSimbolo;
    private String monedaDescripcion;
    private List<XmlPlanCobertura> planesCobertura;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlCertificado() {
        this.numero            = null;
        this.monedaSimbolo     = null;
        this.monedaDescripcion = null;
        this.planesCobertura   = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param numero
     * @param monedaSimbolo
     * @param monedaDescripcion
     * @param planesCobertura
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlCertificado(Integer numero, String monedaSimbolo, String monedaDescripcion,
                          List<XmlPlanCobertura> planesCobertura) {
        this.numero            = numero;
        this.monedaSimbolo     = monedaSimbolo;
        this.monedaDescripcion = monedaDescripcion;
        this.planesCobertura   = planesCobertura;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the numero
     */
    @XmlElement(name="nu-certificado")
    public Integer getNumero() {
        return numero;
    }
    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the monedaSimbolo
     */
    @XmlElement(name="simbolo-moneda")
    public String getMonedaSimbolo() {
        return monedaSimbolo;
    }
    /**
     * @param monedaSimbolo the monedaSimbolo to set
     */
    public void setMonedaSimbolo(String monedaSimbolo) {
        this.monedaSimbolo = monedaSimbolo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the monedaDescripcion
     */
    @XmlElement(name="desc-moneda")
    public String getMonedaDescripcion() {
        return monedaDescripcion;
    }
    /**
     * @param monedaDescripcion the monedaDescripcion to set
     */
    public void setMonedaDescripcion(String monedaDescripcion) {
        this.monedaDescripcion = monedaDescripcion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the planesCobertura
     */
    @XmlElementWrapper(name = "plan-cobertura-cert-list")
    @XmlElement(name="plan-cobertura-cert")
    public List<XmlPlanCobertura> getPlanesCobertura() {
        return planesCobertura;
    }
    /**
     * @param planesCobertura the planesCobertura to set
     */
    public void setPlanesCobertura(List<XmlPlanCobertura> planesCobertura) {
        this.planesCobertura = planesCobertura;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {

        String auxNumero;
        String auxMonedaSimbolo;
        String auxMonedaDescripcion;
        String auxPlanesCobertura;

        String logueo = null;

        if (this.getNumero() == null) { auxNumero = "null";
        } else { auxNumero = this.getNumero().toString(); }

        if (this.getMonedaSimbolo() == null) { auxMonedaSimbolo = "null";
        } else { auxMonedaSimbolo = this.getMonedaSimbolo(); }

        if (this.getMonedaDescripcion() == null) { auxMonedaDescripcion = "null";
        } else { auxMonedaDescripcion = this.getMonedaDescripcion(); }

        if        (this.getPlanesCobertura() == null)   { auxPlanesCobertura = "Planes Cobertura { null }";
        } else if (this.getPlanesCobertura().isEmpty()) { auxPlanesCobertura = "Planes Cobertura { VACIO }";
        } else { auxPlanesCobertura = "Planes Cobertura {" + this.getPlanesCobertura().toString() + "}"; }

        logueo =   "Certificado { Numero=["  + auxNumero
               + "] MonedaSimbolo=["         + auxMonedaSimbolo
               + "] MonedaDescripcion=["     + auxMonedaDescripcion
               + "] " + auxPlanesCobertura + " }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
