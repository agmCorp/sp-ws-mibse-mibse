package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanCoberturaTienda;

import java.io.Serializable;

public class PlanCoberturaTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private PlanCoberturaTienda plan;

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

  public void setPlanCobertura(PlanCoberturaTienda plan){
    this.plan = plan;
  }

  public PlanCoberturaTienda getPlanCobertura(){
    return this.plan;
  }

}
