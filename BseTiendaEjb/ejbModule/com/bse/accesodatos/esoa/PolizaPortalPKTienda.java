package com.bse.accesodatos.esoa;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class PolizaPortalPKTienda {

    @Column(name = "CPP_SUCURSAL")
    private int sucursal;

    @Column(name = "CPP_RAMO")
    private int ramo;

    @Column(name = "CPP_PRODUCTO")
    private String producto;

    @Column(name = "CPP_NU_COTIZACION")
    private long nroCotizacion;


    public int getSucursal() {
        return sucursal;
    }
    public void setSucursal(int sucursal) {
        this.sucursal = sucursal;
    }


    public int getRamo() {
        return ramo;
    }
    public void setRamo(int ramo) {
        this.ramo = ramo;
    }


    public String getProducto() {
        return producto;
    }
    public void setProducto(String producto) {
        this.producto = producto;
    }


    public long getNroCotizacion() {
        return nroCotizacion;
    }
    public void setNroCotizacion(long nroCotizacion) {
        this.nroCotizacion = nroCotizacion;
    }

}
