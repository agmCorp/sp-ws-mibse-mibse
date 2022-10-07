package com.bse.servicios.ws.viajeros;

import com.bse.accesodatos.eviajeros.PolizaViajerosTienda;

import java.io.Serializable;

public class EmisionViajerosTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private PolizaViajerosTienda polizaViajeros;

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

  public void setPolizaViajeros(PolizaViajerosTienda polizaViajeros){
    this.polizaViajeros = polizaViajeros;
  }

  public PolizaViajerosTienda getPolizaViajeros(){
    return this.polizaViajeros;
  }

}
