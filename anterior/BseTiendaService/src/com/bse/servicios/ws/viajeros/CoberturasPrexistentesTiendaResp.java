package com.bse.servicios.ws.viajeros;

import java.util.List;

import com.bse.accesodatos.eviajeros.CoberturaPrexistentesTienda;

public class CoberturasPrexistentesTiendaResp {

  private String codigoError;
  private String descripcionError;
  private List<CoberturaPrexistentesTienda> coberturasPrexistentes;

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

  public void setCoberturasPrexistentes(List<CoberturaPrexistentesTienda> coberturasPrexistentes) {
    this.coberturasPrexistentes = coberturasPrexistentes;
  }

  public List<CoberturaPrexistentesTienda> getCoberturasPrexistentes() {
    return coberturasPrexistentes;
  }

}
