package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.CategoriaVehiculoTienda;
import java.io.Serializable;

public class CategoriaVehiculoTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private CategoriaVehiculoTienda categoria;

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

  public void setCategoriaVehiculo(CategoriaVehiculoTienda categoria){
    this.categoria = categoria;
  }

  public CategoriaVehiculoTienda getCategoriaVehiculo(){
    return this.categoria;
  }

}
