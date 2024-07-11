package com.bse.accesodatos.eindivi;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class XmlFormaPago {

    private String codigo;
    private String descripcion;
    private Double montoPrima;
    private Double montoPremio;
    private Double montoDeducible;
    private String excluido;
    private String fechaDesde;
    private String fechaHasta;
    private List<XmlPlanPago> planesPago;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlFormaPago() {
        this.codigo   = null;
        this.descripcion  = null;
        this.montoPrima     = null;
        this.montoPremio    = null;
        this.montoDeducible = null;
        this.excluido       = null;
        this.planesPago     = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigo
     * @param descripcion
     * @param montoPrima
     * @param montoPremio
     * @param montoDeducible
     * @param excluido
     * @param planesPago
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlFormaPago( String codigo,     String descripcion,
                         Double montoPrima, Double montoPremio, Double montoDeducible,
                         String excluido,   List<XmlPlanPago> planesPago) {
        this.codigo         = codigo;
        this.descripcion    = descripcion;
        this.montoPrima     = montoPrima;
        this.montoPremio    = montoPremio;
        this.montoDeducible = montoDeducible;
        this.excluido       = excluido;
        this.planesPago     = planesPago;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codigo
     */
    @XmlElement(name="cod-modalidad")
    public String getCodigo() {
        return codigo;
    }
    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the descripcion
     */
    @XmlElement(name="desc-modalidad")
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * @param descripcion the descripcion to set
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the montoPrima
     */
    @XmlElement(name="mt-prima", nillable=true)
    public Double getMontoPrima() {
        return montoPrima;
    }
    /**
     * @param montoPrima the montoPrima to set
     */
    public void setMontoPrima(Double montoPrima) {
        this.montoPrima = montoPrima;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the montoPremio
     */
    @XmlElement(name="mt-premio", nillable=true)
    public Double getMontoPremio() {
        return montoPremio;
    }
    /**
     * @param montoPremio the montoPremio to set
     */
    public void setMontoPremio(Double montoPremio) {
        this.montoPremio = montoPremio;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the montoDeducible
     */
    @XmlElement(name="mt-deducible", nillable=true)
    public Double getMontoDeducible() {
        return montoDeducible;
    }
    /**
     * @param montoDeducible the montoDeducible to set
     */
    public void setMontoDeducible(Double montoDeducible) {
        this.montoDeducible = montoDeducible;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the excluido
     */
    @XmlElement(name="excluido")
    public String getExcluido() {
        return excluido;
    }
    /**
     * @param excluido the excluido to set
     */
    public void setExcluido(String excluido) {
        this.excluido = excluido;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the fechaDesde
     */
    @XmlElement(name="fe-desde")
    public String getFechaDesde() {
        return fechaDesde;
    }
    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the fechaHasta
     */
    @XmlElement(name="fe-hasta")
    public String getFechaHasta() {
        return fechaHasta;
    }
    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(String fechaHasta) {
        this.fechaHasta = fechaHasta;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the planesPago
     */
    @XmlElementWrapper(name = "plan-pago-list")
    @XmlElement(name="plan-pago")
    public List<XmlPlanPago> getPlanesPago() {
        return planesPago;
    }
    /**
     * @param planesPago the planesPago to set
     */
    public void setPlanesPago(List<XmlPlanPago> planesPago) {
        this.planesPago = planesPago;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {

        String auxCodigo;
        String auxDescripcion;
        String auxMontoPrima;
        String auxMontoPremio;
        String auxMontoDeducible;
        String auxExcluido;
        String auxFechaDesde;
        String auxFechaHasta;
        String auxPlanesPago;

        String logueo = null;

        if (this.getCodigo() == null) { auxCodigo = "null";
        } else { auxCodigo = this.getCodigo(); }

        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else { auxDescripcion = this.getDescripcion(); }

        if (this.getMontoPrima() == null) { auxMontoPrima = "null";
        } else { auxMontoPrima = this.getMontoPrima().toString(); }

        if (this.getMontoPremio() == null) { auxMontoPremio = "null";
        } else { auxMontoPremio = this.getMontoPremio().toString(); }

        if (this.getMontoDeducible() == null) { auxMontoDeducible = "null";
        } else { auxMontoDeducible = this.getMontoDeducible().toString(); }

        if (this.getExcluido() == null) { auxExcluido = "null";
        } else { auxExcluido = this.getExcluido(); }

        if (this.getFechaDesde() == null) { auxFechaDesde = "null";
        } else { auxFechaDesde = this.getFechaDesde(); }

        if (this.getFechaHasta() == null) { auxFechaHasta = "null";
        } else { auxFechaHasta = this.getFechaHasta(); }

        if        (this.getPlanesPago() == null)   { auxPlanesPago = "ListaPlanesDePago=[ null ]";
        } else if (this.getPlanesPago().isEmpty()) { auxPlanesPago = "ListaPlanesDePago=[ VACIO ]";
        } else { auxPlanesPago = "ListaPlanesDePago=" + this.getPlanesPago().toString(); }

        logueo =   "FormaPago { Codigo=["         + auxCodigo
               +             "] Descripcion=["    + auxDescripcion
               +             "] MontoPrima=["     + auxMontoPrima
               +             "] MontoPremio=["    + auxMontoPremio
               +             "] MontoDeducible=[" + auxMontoDeducible
               +             "] Excluido=["       + auxExcluido
               +             "] FechaDesde=["     + auxFechaDesde
               +             "] FechaHasta=["     + auxFechaHasta
               +             "] " + auxPlanesPago + " }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
