package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.CoberturaRcTienda;
import java.io.Serializable;
import java.util.List;

public class CoberturasRcTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<CoberturaRcTienda> coberturasRc;

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

  public void setCoberturasRc(List<CoberturaRcTienda> coberturasRc){
    this.coberturasRc = coberturasRc;
  }

  public List<CoberturaRcTienda> getCoberturasRc(){
    return this.coberturasRc;
  }

}
