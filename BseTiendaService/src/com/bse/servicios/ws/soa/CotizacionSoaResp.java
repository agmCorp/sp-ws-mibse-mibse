package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.CotizacionSoa;

public class CotizacionSoaResp extends CotizacionResp{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CotizacionSoa cotizacionSoa;


	public void setCotizacionSoa(CotizacionSoa cotizacionSoa){
		this.cotizacionSoa = cotizacionSoa;
	}

	public CotizacionSoa getCotizacionSoa(){
		return this.cotizacionSoa;
	}
    
}
