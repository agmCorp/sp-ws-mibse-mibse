package com.bse.accesodatos.eindivi;

import javax.xml.bind.annotation.XmlElement;


public class XmlPlanPago {

    private String codigo;
    private String descripcion;
    private Double montoCuota;
    private Double montoTotal;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlPlanPago() {
        this.codigo      = null;
        this.descripcion = null;
        this.montoCuota  = null;
        this.montoTotal  = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigo
     * @param descripcion
     * @param montoCuota
     * @param montoTotal
     */
    public XmlPlanPago(String codigo, String descripcion, Double montoCuota, Double montoTotal) {
        this.codigo      = codigo;
        this.descripcion = descripcion;
        this.montoCuota  = montoCuota;
        this.montoTotal  = montoTotal;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codigo
     */
    @XmlElement(name="cod-plan-pago")
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
    @XmlElement(name="desc-plan-pago")
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
     * @return the montoCuota
     */
    @XmlElement(name="mt-cuota", nillable=true)
    public Double getMontoCuota() {
        return montoCuota;
    }
    /**
     * @param monto the montoCuota to set
     */
    public void setMontoCuota(Double montoCuota) {
        this.montoCuota = montoCuota;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the montoTotal
     */
    @XmlElement(name="mt-total", nillable=true)
    public Double getMontoTotal() {
        return montoTotal;
    }
    /**
     * @param montoTotal the montoTotal to set
     */
    public void setMontoTotal(Double montoTotal) {
        this.montoTotal = montoTotal;
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
        String auxMontoCouta;
        String auxMontoTotal;

        String logueo = null;

        if (this.getCodigo() == null) { auxCodigo = "null";
        } else { auxCodigo = this.getCodigo(); }

        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else { auxDescripcion = this.getDescripcion(); }

        if (this.getMontoCuota() == null) { auxMontoCouta = "null";
        } else { auxMontoCouta = this.getMontoCuota().toString(); }

        if (this.getMontoTotal() == null) { auxMontoTotal = "null";
        } else { auxMontoTotal = this.getMontoTotal().toString(); }

        logueo = "PlanPago { Codigo=["      + auxCodigo
               +          "] Descripcion=[" + auxDescripcion
               +          "] MontoCuota=["  + auxMontoCouta
               +          "] MontoTotal=["  + auxMontoTotal
               + "] }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
