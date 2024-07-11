package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PolizaSoaTienda;

import java.io.Serializable;

public class EmisionSoaTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private PolizaSoaTienda polizaSoa;

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

  public void setPolizaSoa(PolizaSoaTienda polizaSoa){
    this.polizaSoa = polizaSoa;
  }

  public PolizaSoaTienda getPolizaSoa(){
    return this.polizaSoa;
  }

}
