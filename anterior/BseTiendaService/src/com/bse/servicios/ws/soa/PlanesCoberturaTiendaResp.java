package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanCoberturaTienda;

import java.io.Serializable;
import java.util.List;

public class PlanesCoberturaTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<PlanCoberturaTienda> planes;

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

  public void setPlanesCobertura(List<PlanCoberturaTienda> planes){
    this.planes = planes;
  }

  public List<PlanCoberturaTienda> getPlanesCobertura(){
    return this.planes;
  }

}
