package com.bse.servicios.ws.bici;

import com.bse.accesodatos.ebici.PolizaBiciTienda;

import java.io.Serializable;

public class EmisionBiciTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private PolizaBiciTienda polizaBici;

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

  public void setPolizaBici(PolizaBiciTienda polizaBici){
    this.polizaBici = polizaBici;
  }

  public PolizaBiciTienda getPolizaBici(){
    return this.polizaBici;
  }

}
