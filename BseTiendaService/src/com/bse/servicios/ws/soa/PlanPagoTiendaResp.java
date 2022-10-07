package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanPagoTienda;

import java.io.Serializable;

public class PlanPagoTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private PlanPagoTienda plan;

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

  public void setPlanPago(PlanPagoTienda plan){
    this.plan = plan;
  }

  public PlanPagoTienda getPlanPago(){
    return this.plan;
  }

}
