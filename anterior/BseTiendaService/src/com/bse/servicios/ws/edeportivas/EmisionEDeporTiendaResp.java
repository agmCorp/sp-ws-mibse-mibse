package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.PolizaEDeporTienda;

import java.io.Serializable;

public class EmisionEDeporTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private PolizaEDeporTienda polizaEDepor;

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

  public void setPolizaEDepor(PolizaEDeporTienda polizaEDepor){
    this.polizaEDepor = polizaEDepor;
  }

  public PolizaEDeporTienda getPolizaEDepor(){
    return this.polizaEDepor;
  }

}
