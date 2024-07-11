package com.bse.accesodatos.eindivi;

import javax.xml.bind.annotation.XmlElement;

import com.bse.accesodatos.esoa.MonedaTienda;
import com.bse.accesodatos.esoa.ProductoTienda;
import com.bse.accesodatos.esoa.RamoTienda;

public class DatosVariosIndivi {

    private final String codPlanCoberturaGlobal = "GLOBAL";
    private final String codPlanCoberturaTriple = "TRIPLE";

    private final String codModalidadAnual = "A";
    private final String codModalidad3x2   = "3X2";

    private final String codPagoContado    = "1";
    
    private String sucursal;
    private RamoTienda ramo;
    private ProductoTienda producto;
    private MonedaTienda moneda;

    // Plan de Pago que se visualiza en la primer pantalla luego de la cotización - Actual 10 cuotas
    private String planPagoInicial;

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DatosVariosIndivi() {
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * CONSTRUCTOR
     * @param sucursal
     * @param ramo
     * @param producto
     * @param moneda
     */
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public DatosVariosIndivi( String sucursal, 
                              RamoTienda ramo, ProductoTienda producto, MonedaTienda moneda,
                              String planPagoInicial ) {
        this.sucursal = sucursal;
        this.ramo     = ramo;
        this.producto = producto;
        this.moneda   = moneda;
        this.planPagoInicial = planPagoInicial;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codPlanCoberturaGlobal
     */
    @XmlElement(name="coberturaGlobal")
    public String getCodPlanCoberturaGlobal() {
        return codPlanCoberturaGlobal;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codPlanCoberturaTriple
     */
    @XmlElement(name="coberturaTriple")
    public String getCodPlanCoberturaTriple() {
        return codPlanCoberturaTriple;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codModalidadAnual
     */
    @XmlElement(name="modalidadAnual")
    public String getCodModalidadAnual() {
        return codModalidadAnual;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codModalidad3x2
     */
    @XmlElement(name="modalidad3x2")
    public String getCodModalidad3x2() {
        return codModalidad3x2;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the codPagoContado
     */
    @XmlElement(name="pagoContado")
    public String getCodPagoContado() {
        return codPagoContado;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the sucursal
     */
    @XmlElement(name="sucursal")
    public String getSucursal() {
        return sucursal;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the ramo
     */
    @XmlElement(name="ramo")
    public RamoTienda getRamo() {
        return ramo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the producto
     */
    @XmlElement(name="producto")
    public ProductoTienda getProducto() {
        return producto;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the moneda
     */
    @XmlElement(name="moneda")
    public MonedaTienda getMoneda() {
        return moneda;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     * @return the planPagoInicial
     */
    @XmlElement(name="planPagoInicial")
    public String getPlanPagoInicial() {
        return planPagoInicial;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /**
     *
     */
    @Override
    public String toString() {

        String auxCodPlanCoberturaGlobal;
        String auxCodPlanCoberturaTriple;
        String auxCodModalidadAnual;
        String auxCodModalidad3x2;
        String auxCodPagoContado;

        String auxSucursal;
        String auxRamo;
        String auxProducto;
        String auxMoneda;

        String auxPlanPagoInicial;

        String logueo = null;

        if (this.getCodPlanCoberturaGlobal() == null) { auxCodPlanCoberturaGlobal = "null";
        } else { auxCodPlanCoberturaGlobal = this.getCodPlanCoberturaGlobal(); }

        if (this.getCodPlanCoberturaTriple() == null) { auxCodPlanCoberturaTriple = "null";
        } else { auxCodPlanCoberturaTriple = this.getCodPlanCoberturaTriple(); }

        if (this.getCodModalidadAnual() == null) { auxCodModalidadAnual = "null";
        } else { auxCodModalidadAnual = this.getCodModalidadAnual(); }

        if (this.getCodModalidad3x2() == null) { auxCodModalidad3x2 = "null";
        } else { auxCodModalidad3x2 = this.getCodModalidad3x2(); }

        if (this.getCodPagoContado() == null) { auxCodPagoContado = "null";
        } else { auxCodPagoContado = this.getCodPagoContado(); }

        if (this.getSucursal() == null) { auxSucursal = "null";
        } else { auxSucursal = this.getSucursal(); }

        if (this.getRamo() == null) { auxRamo = "null";
        } else { auxRamo = this.getRamo().toString(); }

        if (this.getProducto() == null) { auxProducto = "null";
        } else { auxProducto = this.getProducto().getProducto() + "-" + this.getProducto().getDescripcion(); }

        if (this.getMoneda() == null) { auxMoneda = "null";
        } else { auxMoneda = this.getMoneda().getCodigo() + " - "
                           + this.getMoneda().getDescripcion() + "(" + this.getMoneda().getSimbolo() + ")" ; }

        if (this.getPlanPagoInicial() == null) { auxPlanPagoInicial = "null";
        } else { auxPlanPagoInicial = this.getPlanPagoInicial(); }

        logueo = "DatosVarios { CoberturaGlobal=[" + auxCodPlanCoberturaGlobal
               +             "] CoberturaTriple=[" + auxCodPlanCoberturaTriple
               +             "] ModalidadAnual=["  + auxCodModalidadAnual
               +             "] Modalidad3x2=["    + auxCodModalidad3x2
               +             "] PagoContado=["     + auxCodPagoContado
               +             "] Sucursal=["        + auxSucursal
               +             "] Ramo=["            + auxRamo
               +             "] Producto=["        + auxProducto
               +             "] Moneda=["          + auxMoneda
               +             "] PlanPagoInicial=[" + auxPlanPagoInicial
               + "] }";

        return logueo;
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
