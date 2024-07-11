package com.bse.servicios.ws.viajeros;

import com.bse.accesodatos.eviajeros.CotizacionViajerosTienda;
import com.bse.servicios.ws.soa.CotizacionTiendaResp;

public class CotizacionViajerosTiendaResp extends CotizacionTiendaResp{

    /**
   *
   */
  private static final long serialVersionUID = 1L;
  private CotizacionViajerosTienda cotizacionViajeros;

  public void setCotizacionViajeros(CotizacionViajerosTienda cotizacionViajeros){
    this.cotizacionViajeros = cotizacionViajeros;
  }

  public CotizacionViajerosTienda getCotizacionViajeros(){
    return this.cotizacionViajeros;
  }

}
