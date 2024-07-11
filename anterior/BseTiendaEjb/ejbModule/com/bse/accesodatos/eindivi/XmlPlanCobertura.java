package com.bse.accesodatos.eindivi;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class XmlPlanCobertura {

    private String codigo;
    private String descripcion;
    private List<XmlFormaPago> formasDePago;


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlPlanCobertura() {
        this.codigo       = null;
        this.descripcion  = null;
        this.formasDePago = null;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param codigo
     * @param descripcion
     * @param formasDePago
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public XmlPlanCobertura(String codigo, String descripcion, List<XmlFormaPago> formasDePago) {
        this.codigo       = codigo;
        this.descripcion  = descripcion;
        this.formasDePago = formasDePago;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codigo
     */
    @XmlElement(name="cod-plan-cobertura-cert")
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
    @XmlElement(name="desc-plan-cobertura-cert")
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
     * @return the formasDePago
     */
    @XmlElementWrapper(name = "forma-pago-list")
    @XmlElement(name="forma-pago")
    public List<XmlFormaPago> getFormasDePago() {
        return formasDePago;
    }
    /**
     * @param formasDePago the formasDePago to set
     */
    public void setFormasDePago(List<XmlFormaPago> formasDePago) {
        this.formasDePago = formasDePago;
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
        String auxFormasDePago;

        String logueo = null;

        if (this.getCodigo() == null) { auxCodigo = "null";
        } else { auxCodigo = this.getCodigo(); }

        if (this.getDescripcion() == null) { auxDescripcion = "null";
        } else { auxDescripcion = this.getDescripcion(); }

        if        (this.getFormasDePago() == null)   { auxFormasDePago = "ListaFormasDePago=[ null ]";
        } else if (this.getFormasDePago().isEmpty()) { auxFormasDePago = "ListaFormasDePago=[ VACIO ]";
        } else { auxFormasDePago = "ListaFormasDePago=" + this.getFormasDePago().toString(); }

        logueo =   "PlanCobertura { Codigo=[" + auxCodigo
               + "] Descripcion=["            + auxDescripcion
               + "] " + auxFormasDePago + " }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
