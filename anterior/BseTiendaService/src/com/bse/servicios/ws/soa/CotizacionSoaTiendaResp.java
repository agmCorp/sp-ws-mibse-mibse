package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.CotizacionSoaTienda;

public class CotizacionSoaTiendaResp extends CotizacionTiendaResp{

    /**
   *
   */
  private static final long serialVersionUID = 1L;
  private CotizacionSoaTienda cotizacionSoa;


  public void setCotizacionSoa(CotizacionSoaTienda cotizacionSoa){
    this.cotizacionSoa = cotizacionSoa;
  }

  public CotizacionSoaTienda getCotizacionSoa(){
    return this.cotizacionSoa;
  }

}
