package com.bse.servicios.ws.viajeros;

import java.util.List;

import com.bse.accesodatos.eviajeros.CoberturaMuerteTienda;

public class CoberturasMuerteTiendaResp {

  private String codigoError;
  private String descripcionError;
  private List<CoberturaMuerteTienda> coberturasMuerte;

  public void setCodigoError(String codigoError) {
    this.codigoError = codigoError;
  }

  public void setDescripcionError(String descripcionError) {
    this.descripcionError = descripcionError;
  }

  public String getCodigoError() {
    return codigoError;
  }

  public String getDescripcionError() {
    return descripcionError;
  }

  public void setCoberturasMuerte(List<CoberturaMuerteTienda> coberturasMuerte) {
    this.coberturasMuerte = coberturasMuerte;
  }

  public List<CoberturaMuerteTienda> getCoberturasMuerte() {
    return coberturasMuerte;
  }

}
