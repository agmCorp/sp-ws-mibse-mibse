package com.bse.servicios.ws.indivi;

import com.bse.accesodatos.eindivi.CotizacionIndivi;
import com.bse.servicios.ws.soa.CotizacionResp;

public class CotizacionIndiviResp extends CotizacionResp{
    
	private static final long serialVersionUID = 1L;
	private CotizacionIndivi cotizacionIndivi;


	public void setCotizacionIndivi(CotizacionIndivi cotizacionIndivi){
		this.cotizacionIndivi = cotizacionIndivi;
	}

	public CotizacionIndivi getCotizacionIndivi(){
		return this.cotizacionIndivi;
	}
    
}
