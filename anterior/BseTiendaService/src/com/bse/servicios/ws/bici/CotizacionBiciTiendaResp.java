package com.bse.servicios.ws.bici;

import com.bse.accesodatos.ebici.CotizacionBiciTienda;
import com.bse.servicios.ws.soa.CotizacionTiendaResp;

public class CotizacionBiciTiendaResp extends CotizacionTiendaResp{

    /**
   *
   */
  private static final long serialVersionUID = 1L;
  private CotizacionBiciTienda cotizacionBici;


  public void setCotizacionBici(CotizacionBiciTienda cotizacionBici){
    this.cotizacionBici = cotizacionBici;
  }

  public CotizacionBiciTienda getCotizacionBici(){
    return this.cotizacionBici;
  }

}
