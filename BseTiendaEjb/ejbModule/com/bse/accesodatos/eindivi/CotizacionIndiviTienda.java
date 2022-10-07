package com.bse.accesodatos.eindivi;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElementWrapper;


public class CotizacionIndiviTienda {

    private String marcaVehiculo;
    private String anioVehiculo;
    private String tipoVehiculo;
    private String combustible;
    private String versionVehiculo;
    private String areaCirculacion;

    private String nroCotizacion;

    // Planes de Pagos preferenciales a mostrar en pantalla - Lista dada por el PL de Rector
    private ArrayList<String> pagoPreferecial;

    private String fechaCotizacion;
    private ArrayList<XmlPlanCobertura> planesCobertura;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public CotizacionIndiviTienda() {
        this.marcaVehiculo   = null;
        this.versionVehiculo = null;
        this.anioVehiculo    = null;
        this.tipoVehiculo    = null;
        this.combustible     = null;
        this.areaCirculacion = null;

        this.nroCotizacion   = null;
        this.pagoPreferecial = null;
        this.fechaCotizacion = null;
        this.planesCobertura = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the marcaVehiculo
     */
    public String getMarcaVehiculo() {
        return marcaVehiculo;
    }
    /**
     * @param marcaVehiculo the marcaVehiculo to set
     */
    public void setMarcaVehiculo(String marcaVehiculo) {
        this.marcaVehiculo = marcaVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the anioVehiculo
     */
    public String getAnioVehiculo() {
        return anioVehiculo;
    }
    /**
     * @param anioVehiculo the anioVehiculo to set
     */
    public void setAnioVehiculo(String anioVehiculo) {
        this.anioVehiculo = anioVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the tipoVehiculo
     */
    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
    /**
     * @param tipoVehiculo the tipoVehiculo to set
     */
    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the combustible
     */
    public String getCombustible() {
        return combustible;
    }
    /**
     * @param combustible the combustible to set
     */
    public void setCombustible(String combustible) {
        this.combustible = combustible;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the versionVehiculo
     */
    //@XmlElement(name="versionVehiculo")
    public String getVersionVehiculo() {
        return versionVehiculo;
    }
    /**
     * @param versionVehiculo the versionVehiculo to set
     */
    public void setVersionVehiculo(String versionVehiculo) {
        this.versionVehiculo = versionVehiculo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the areaCirculacion
     */
    public String getAreaCirculacion() {
        return areaCirculacion;
    }
    /**
     * @param areaCirculacion the areaCirculacion to set
     */
    public void setAreaCirculacion(String areaCirculacion) {
        this.areaCirculacion = areaCirculacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the nroCotizacion
     */
    public String getNroCotizacion() {
        return nroCotizacion;
    }
    /**
     * @param nroCotizacion the nroCotizacion to set
     */
    public void setNroCotizacion(String nroCotizacion) {
        this.nroCotizacion = nroCotizacion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the pagoPreferecial
     */
    @XmlElementWrapper(name = "pagoPreferecial-list")
    public ArrayList<String> getPagoPreferecial() {
        return pagoPreferecial;
    }
    /**
     * @param pagosPreferecial the pagoPreferecial to set
     */
    public void setPagoPreferecial(ArrayList<String> pagoPreferecial) {
        this.pagoPreferecial = pagoPreferecial;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the fechaCotizacion
     */
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
     * @return the planesCobertura
     */
    @XmlElementWrapper(name = "planesCobertura-list")
    public ArrayList<XmlPlanCobertura> getPlanesCobertura() {
        return planesCobertura;
    }
    /**
     * @param planesCobertura the planesCobertura to set
     */
    public void setPlanesCobertura(ArrayList<XmlPlanCobertura> planesCobertura) {
        this.planesCobertura = planesCobertura;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {

        String auxNroCotizacion;

        String auxMarcaVehiculo;
        String auxAnioVehiculo;
        String auxTipoVehiculo;
        String auxCombustible;
        String auxVersionVehiculo;
        String auxAreaCirculacion;

        String auxPagoPreferencial;
        String auxFechaCotizacion;
        String auxPlanesCobertura;

        String logueo = null;

        if (this.getNroCotizacion() == null) { auxNroCotizacion = "null";
        } else { auxNroCotizacion = this.getNroCotizacion(); }

        if (this.getMarcaVehiculo() == null) { auxMarcaVehiculo = "null";
        } else { auxMarcaVehiculo = this.getMarcaVehiculo(); }

        if (this.getAnioVehiculo() == null) { auxAnioVehiculo = "null";
        } else { auxAnioVehiculo = this.getAnioVehiculo(); }

        if (this.getTipoVehiculo() == null) { auxTipoVehiculo = "null";
        } else { auxTipoVehiculo = this.getTipoVehiculo(); }

        if (this.getCombustible() == null) { auxCombustible = "null";
        } else { auxCombustible = this.getCombustible(); }

        if (this.getVersionVehiculo() == null) { auxVersionVehiculo = "null";
        } else { auxVersionVehiculo = this.getVersionVehiculo(); }

        if (this.getAreaCirculacion() == null) { auxAreaCirculacion = "null";
        } else { auxAreaCirculacion = this.getAreaCirculacion(); }

        if        (this.getPagoPreferecial() == null)   { auxPagoPreferencial = "ListaPagoPreferencial=[ null ]";
        } else if (this.getPagoPreferecial().isEmpty()) { auxPagoPreferencial = "ListaPagoPreferencial=[ VACIO ]";
        } else { auxPagoPreferencial = "ListaPagoPreferencial=" + this.getPagoPreferecial().toString(); }

        if (this.getFechaCotizacion() == null) { auxFechaCotizacion = "null";
        } else { auxFechaCotizacion = this.getFechaCotizacion(); }

        if        (this.getPlanesCobertura() == null)   { auxPlanesCobertura = "ListaPlanesCobertura=[ null ]";
        } else if (this.getPlanesCobertura().isEmpty()) { auxPlanesCobertura = "ListaPlanesCobertura=[ VACIO ]";
        } else { auxPlanesCobertura = "ListaPlanesCobertura=" + this.getPlanesCobertura().toString(); }

        logueo = "CotizacionIndivi { NroCotizacion=["    + auxNroCotizacion
                +                 "] Marca=["            + auxMarcaVehiculo
                +                 "] Anio=["             + auxAnioVehiculo
                +                 "] Tipo=["             + auxTipoVehiculo
                +                 "] Combustible=["      + auxCombustible
                +                 "] Version=["          + auxVersionVehiculo
                +                 "] AreaCirculacion=["  + auxAreaCirculacion
                +                 "] "                   + auxPagoPreferencial
                +                  " FechaCotizacion=["  + auxFechaCotizacion
                +                 "] "                   + auxPlanesCobertura
                + " }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
