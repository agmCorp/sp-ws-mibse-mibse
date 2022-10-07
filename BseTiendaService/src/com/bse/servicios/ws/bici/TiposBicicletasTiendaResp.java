package com.bse.servicios.ws.bici;

import com.bse.accesodatos.ebici.TipoBiciTienda;

import java.io.Serializable;
import java.util.List;

public class TiposBicicletasTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<TipoBiciTienda> tiposBicicletas;

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

  public List<TipoBiciTienda> getTiposBicicletas() {
    return tiposBicicletas;
  }

  public void setTiposBicicletas(List<TipoBiciTienda> tiposBicicletas) {
    this.tiposBicicletas = tiposBicicletas;
  }

}
