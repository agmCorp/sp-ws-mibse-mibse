package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.TipoBuqueTienda;
import java.io.Serializable;
import java.util.List;

public class TiposBuquesTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<TipoBuqueTienda> tiposBuques;

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

  public void setTiposBuques(List<TipoBuqueTienda> tiposBuques){
    this.tiposBuques = tiposBuques;
  }

  public List<TipoBuqueTienda> getTiposBuques(){
    return this.tiposBuques;
  }

}
