package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.CotizacionEDepor;

public class CotizacionEDeporResp extends com.bse.servicios.ws.soa.CotizacionResp{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CotizacionEDepor cotizacionEDepor;


	public void setCotizacionEDepor(CotizacionEDepor cotizacionEDepor){
		this.cotizacionEDepor = cotizacionEDepor;
	}

	public CotizacionEDepor getCotizacionEDepor(){
		return this.cotizacionEDepor;
	}
    
}
