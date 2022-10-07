package com.bse.servicios.ws.bici;

public class ClienteDeudaTiendaResp {

  private String codigoError;
    private String descripcionError;
    private String clienteConDeuda;

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
  public String getClienteConDeuda() {
    return clienteConDeuda;
  }
  public void setClienteConDeuda(String clienteConDeuda) {
    this.clienteConDeuda = clienteConDeuda;
  }

}
