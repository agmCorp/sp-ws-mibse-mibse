package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.MarcaVehiculoTienda;

import java.io.Serializable;

public class MarcaVehiculoTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private MarcaVehiculoTienda marca;

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getDescripcionError() {
        return descripcionError;
    }

    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }

  public void setMarcaVehiculo(MarcaVehiculoTienda marca){
    this.marca = marca;
  }

  public MarcaVehiculoTienda getMarcaVehiculo(){
    return this.marca;
  }

}
