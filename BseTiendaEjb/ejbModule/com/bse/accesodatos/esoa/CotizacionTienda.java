package com.bse.accesodatos.esoa;

import java.util.ArrayList;
import java.util.Date;

public class CotizacionTienda {

    private RamoTienda ramo;
    private ProductoTienda producto;
    private int sucursal;
    private int nroCotizacion;
    private String tipoDocumento;
    private String nroDocumento;
    private int productor;
    private MonedaTienda moneda;
    private double premio;
    private double premioFacturar;
    private Date fechaDesde;
    private Date fechaHasta;
    private PlanPagoTienda planPago;
    private PlanCoberturaTienda planCobertura;
    private ArrayList<CuotaPagoTienda> cuotas;


    public CotizacionTienda() {
    }


    public RamoTienda getRamo() {
        return ramo;
    }
    public void setRamo(RamoTienda ramo) {
        this.ramo = ramo;
    }


    public ProductoTienda getProducto() {
        return producto;
    }
    public void setProducto(ProductoTienda producto) {
        this.producto = producto;
    }


    public int getSucursal() {
        return sucursal;
    }
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }


    public int getNroCotizacion() {
        return nroCotizacion;
    }
    public void setNroCotizacion(int nroCotizacion) {
        this.nroCotizacion = nroCotizacion;
    }


    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }


    public String getNroDocumento() {
        return nroDocumento;
    }
    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }


    public int getProductor() {
        return productor;
    }
    public void setProductor(int productor) {
        this.productor = productor;
    }


    public double getPremio() {
        return premio;
    }
    public void setPremio(double premio) {
        this.premio = premio;
    }


    public Date getFechaDesde() {
        return fechaDesde;
    }
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }


    public Date getFechaHasta() {
        return fechaHasta;
    }
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }


    public PlanPagoTienda getPlanPago() {
        return planPago;
    }
    public void setPlanPago(PlanPagoTienda planPago) {
        this.planPago = planPago;
    }


    public void setPremioFacturar(double premioFacturar) {
        this.premioFacturar=premioFacturar;
    }
    public double getPremioFacturar(){
        return premioFacturar;
    }


    public ArrayList<CuotaPagoTienda> getCuotas() {
        return cuotas;
    }
    public void setCuotas(ArrayList<CuotaPagoTienda> cuotas) {
        this.cuotas = cuotas;
    }


    public MonedaTienda getMoneda() {
        return moneda;
    }
    public void setMoneda(MonedaTienda moneda) {
        this.moneda = moneda;
    }


    public PlanCoberturaTienda getPlanCobertura() {
        return planCobertura;
    }
    public void setPlanCobertura(PlanCoberturaTienda planCobertura) {
        this.planCobertura = planCobertura;
    }


    public String getCotizacionTiendaString() {

        String auxRamo;
        String auxProducto;
        String auxSucursal;
        String auxNroCotizacion;
        String auxTipoDocumento;
        String auxNroDocumento;
        String auxProductor;
        String auxMoneda;
        String auxPremio;
        String auxPremioFacturar;
        String auxFechaDesde;
        String auxFechaHasta;
        String auxPlanPago;
        String auxPlanCobertura;
        String auxCuotas;

        String logueo = null;

        if (this.getRamo() == null) { auxRamo = "null";
        } else { auxRamo = this.getRamo().toString(); }

        if (this.getProducto() == null) { auxProducto = "null";
        } else { auxProducto = this.getProducto().toString(); }

        auxSucursal = Integer.toString(this.getSucursal());

        auxNroCotizacion = Long.toString(this.getNroCotizacion());

        if (this.getTipoDocumento() == null) { auxTipoDocumento = "null";
        } else { auxTipoDocumento = this.getTipoDocumento(); }

        if (this.getNroDocumento() == null) { auxNroDocumento = "null";
        } else { auxNroDocumento = this.getNroDocumento(); }

        auxProductor = Integer.toString(this.getProductor());

        if (this.getMoneda() == null) { auxMoneda = "null";
        } else { auxMoneda = this.getMoneda().toString(); }

        auxPremio         = Double.toString(this.getPremio());
        auxPremioFacturar = Double.toString(this.getPremioFacturar());

        if (this.getFechaDesde() == null) { auxFechaDesde = "null";
        } else { auxFechaDesde = this.getFechaDesde().toString(); }

        if (this.getFechaHasta() == null) { auxFechaHasta = "null";
        } else { auxFechaHasta = this.getFechaHasta().toString(); }

        if (this.getPlanPago() == null) { auxPlanPago = "null";
        } else { auxPlanPago = this.getPlanPago().toString(); }

        if (this.getPlanCobertura() == null) { auxPlanCobertura = "null";
        } else { auxPlanCobertura = this.getPlanCobertura().toString(); }

        if        (this.getCuotas() == null)   { auxCuotas = "ListaCuotas=[ null ]";
        } else if (this.getCuotas().isEmpty()) { auxCuotas = "ListaCuotas=[ VACIO ]";
        } else { auxCuotas = "ListaCuotas=" + this.getCuotas().toString(); }

        logueo =      "Ramo=["        + auxRamo
               + "] NroCotizacion=["  + auxNroCotizacion
               + "] Producto=["       + auxProducto
               + "] Sucursal=["       + auxSucursal
               + "] TipoDocumento=["  + auxTipoDocumento
               + "] NroDocumento=["   + auxNroDocumento
               + "] Productor=["      + auxProductor
               + "] Moneda=["         + auxMoneda
               + "] Premio=["         + auxPremio
               + "] PremioFacturar=[" + auxPremioFacturar
               + "] FechaDesde=["     + auxFechaDesde
               + "] FechaHasta=["     + auxFechaHasta
               + "] PlanPago=["       + auxPlanPago
               + "] PlanCobertura=["  + auxPlanCobertura
               + "] "                 + auxCuotas;

        return logueo;
    }

    @Override
    public String toString() {
        return getCotizacionTiendaString();
    }

}
