package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanPagoTienda;

import java.io.Serializable;
import java.util.List;

public class PlanesPagoTiendaResp implements Serializable {

    /**
   *
   */
  private static final long serialVersionUID = 1L;
    private String codigoError;
    private String descripcionError;
  private List<PlanPagoTienda> planes;

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

  public void setPlanesPago(List<PlanPagoTienda> planes){
    this.planes = planes;
  }

  public List<PlanPagoTienda> getPlanesPago(){
    return this.planes;
  }

}
