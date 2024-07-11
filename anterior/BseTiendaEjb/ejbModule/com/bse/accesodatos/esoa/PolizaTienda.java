package com.bse.accesodatos.esoa;

import java.util.ArrayList;
import java.util.Date;

public class PolizaTienda {

    private RamoTienda ramo;
    private ProductoTienda producto;
    private int sucursal;
    private long nroCotizacion;
    private String tipoDocumento;
    private String nroDocumento;
    private int productor;
    private MonedaTienda moneda;
    private double premio;
    private double premioFacturar;
    private Date fechaDesde;
    private Date fechaHasta;
    private int nroPoliza;
    private PlanPagoTienda planPago;
    private PlanCoberturaTienda planCobertura;
    private ArrayList<CuotaPagoTienda> cuotas;

    /**
     * CONSTRUCTOR
     */
    public PolizaTienda() {
    }


    /**
     * CONSTRUCTOR
     * @param ramo
     * @param producto
     * @param sucursal
     * @param nroCotizacion
     * @param tipoDocumento
     * @param nroDocumento
     * @param productor
     * @param moneda
     * @param premio
     * @param premioFacturar
     * @param fechaDesde
     * @param fechaHasta
     * @param nroPoliza
     * @param planPago
     * @param planCobertura
     * @param cuotas
     */
    public PolizaTienda(RamoTienda ramo, ProductoTienda producto, int sucursal, long nroCotizacion,
                        String tipoDocumento, String nroDocumento, int productor,
                        MonedaTienda moneda, double premio, double premioFacturar, Date fechaDesde,
                        Date fechaHasta, int nroPoliza, PlanPagoTienda planPago,
                        PlanCoberturaTienda planCobertura, ArrayList<CuotaPagoTienda> cuotas) {
        super();
        this.ramo           = ramo;
        this.producto       = producto;
        this.sucursal       = sucursal;
        this.nroCotizacion  = nroCotizacion;
        this.tipoDocumento  = tipoDocumento;
        this.nroDocumento   = nroDocumento;
        this.productor      = productor;
        this.moneda         = moneda;
        this.premio         = premio;
        this.premioFacturar = premioFacturar;
        this.fechaDesde     = fechaDesde;
        this.fechaHasta     = fechaHasta;
        this.nroPoliza      = nroPoliza;
        this.planPago       = planPago;
        this.planCobertura  = planCobertura;
        this.cuotas         = cuotas;
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


    public long getNroCotizacion() {
        return nroCotizacion;
    }
    public void setNroCotizacion(long nroCotizacion) {
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


    public MonedaTienda getMoneda() {
        return moneda;
    }
    public void setMoneda(MonedaTienda moneda) {
        this.moneda = moneda;
    }


    public double getPremio() {
        return premio;
    }
    public void setPremio(double premio) {
        this.premio = premio;
    }


    public double getPremioFacturar() {
        return premioFacturar;
    }
    public void setPremioFacturar(double premioFacturar) {
        this.premioFacturar = premioFacturar;
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


    public int getNroPoliza() {
        return nroPoliza;
    }
    public void setNroPoliza(int nroPoliza) {
        this.nroPoliza = nroPoliza;
    }


    public PlanPagoTienda getPlanPago() {
        return planPago;
    }
    public void setPlanPago(PlanPagoTienda planPago) {
        this.planPago = planPago;
    }


    public PlanCoberturaTienda getPlanCobertura() {
        return planCobertura;
    }
    public void setPlanCobertura(PlanCoberturaTienda planCobertura) {
        this.planCobertura = planCobertura;
    }


    public ArrayList<CuotaPagoTienda> getCuotas() {
        return cuotas;
    }
    public void setCuotas(ArrayList<CuotaPagoTienda> cuotas) {
        this.cuotas = cuotas;
    }


    /**
     *
     * @return
     */
    public String getPolizaTiendaString() {

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
        String auxNroPoliza;
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

        auxNroPoliza = Integer.toString(this.getNroPoliza());

        if (this.getPlanPago() == null) { auxPlanPago = "null";
        } else { auxPlanPago = this.getPlanPago().toString(); }

        if (this.getPlanCobertura() == null) { auxPlanCobertura = "null";
        } else { auxPlanCobertura = this.getPlanCobertura().toString(); }

        if        (this.getCuotas() == null)   { auxCuotas = "ListaCuotas=[ null ]";
        } else if (this.getCuotas().isEmpty()) { auxCuotas = "ListaCuotas=[ VACIO ]";
        } else { auxCuotas = "ListaCuotas=" + this.getCuotas().toString(); }

        logueo =      "Ramo=["        + auxRamo
               + "] NroPoliza=["      + auxNroPoliza
               + "] NroCotizacion=["  + auxNroCotizacion
               + "] TipoDocumento=["  + auxTipoDocumento
               + "] NroDocumento=["   + auxNroDocumento
               + "] Producto=["       + auxProducto
               + "] Sucursal=["       + auxSucursal
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
        return getPolizaTiendaString();
    }

}
