package com.bse.accesodatos.eindivi;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="cotizacion")
public class XmlCotizacion {

    private String numero;
    private String listaCuotas;  // INTERNA
    private List<String> cuotas; // VISIBLE
    private List<XmlCertificado> certificados;
    private String fechaCotizacion;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlCotizacion() {
        this.numero          = null;
        this.listaCuotas     = null;
        this.cuotas          = null;
        this.certificados    = null;
        this.fechaCotizacion = null;

    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the numero
     */
    @XmlElement(name="nu-cotizacion")
    public String getNumero() {
        return numero;
    }
    /**
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the listaCuotas
     */
    @XmlElement(name="lista-cuotas")
    private String getListaCuotas() {
        return listaCuotas;
    }
    /**
     * @param listaCuotas the listaCuotas to set
     */
    public void setListaCuotas(String listaCuotas) {
        this.listaCuotas = listaCuotas;
        if ( listaCuotas != null && !listaCuotas.trim().equals("") ) {
            this.cuotas = new ArrayList<String>();
            String[] cuotas = listaCuotas.split(",");
            for (int i = 0; i < cuotas.length; i++) {
                this.cuotas.add(cuotas[i]);
            }
        } else {
            this.cuotas = null;
        }
    }
    /**
     * @return the cuotas
     */
    public List<String> getCuotas() {
        return cuotas;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the certificados
     */
    @XmlElementWrapper(name = "certificado-list")
    @XmlElement(name="certificado")
    public List<XmlCertificado> getCertificados() {
        return certificados;
    }
    /**
     * @param certificados the certificados to set
     */
    public void setCertificados(List<XmlCertificado> certificados) {
        this.certificados = certificados;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the fechaCotizacion
     */
    @XmlElement(name="fe-cotizacion")
    public String getFechaCotizacion() {
        return fechaCotizacion;
    }
    /**
     * @param fechaCotizacion the fechaCotizacion to set
     */
    public void setFechaCotizacion(String fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {

        String auxNumero;
        String auxListaCuotas;
        //String auxCuotas;
        String auxFechaCotizacion;
        String auxCertificados;

        String logueo = null;

        if (this.getNumero() == null) { auxNumero = "null";
        } else { auxNumero = this.getNumero(); }

        if (this.getListaCuotas() == null) { auxListaCuotas = "null";
        } else { auxListaCuotas = this.getListaCuotas(); }

        /*if (this.getCuotas() == null) { auxCuotas = "null";
        } else { auxCuotas = this.getCuotas().toString(); }*/

        if (this.getFechaCotizacion() == null) { auxFechaCotizacion = "null";
        } else { auxFechaCotizacion = this.getFechaCotizacion(); }

        if        (this.getCertificados() == null)   { auxCertificados = "Certificados { null }";
        } else if (this.getCertificados().isEmpty()) { auxCertificados = "Certificados { VACIO }";
        } else { auxCertificados = "Certificados {" + this.getCertificados().toString() + "}"; }

        logueo =   "Cotizacion { Numero=["      + auxNumero
               + "] ListaCuotas=["              + auxListaCuotas
               // + "] Cuotas=["    + auxCuotas
               + "] FechaCotizacion=["          + auxFechaCotizacion
               + "] " + auxCertificados
               + " }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
