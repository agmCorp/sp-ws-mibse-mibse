package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.CotizacionEDeporTienda;

public class CotizacionEDeporTiendaResp extends com.bse.servicios.ws.soa.CotizacionTiendaResp{

    /**
   *
   */
  private static final long serialVersionUID = 1L;
  private CotizacionEDeporTienda cotizacionEDepor;


  public void setCotizacionEDepor(CotizacionEDeporTienda cotizacionEDepor){
    this.cotizacionEDepor = cotizacionEDepor;
  }

  public CotizacionEDeporTienda getCotizacionEDepor(){
    return this.cotizacionEDepor;
  }

}
