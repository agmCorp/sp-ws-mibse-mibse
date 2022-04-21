package com.bse.servicios.ws.bici;

import com.bse.accesodatos.ebici.CotizacionBici;
import com.bse.servicios.ws.soa.CotizacionResp;

public class CotizacionBiciResp extends CotizacionResp{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CotizacionBici cotizacionBici;


	public void setCotizacionBici(CotizacionBici cotizacionBici){
		this.cotizacionBici = cotizacionBici;
	}

	public CotizacionBici getCotizacionBici(){
		return this.cotizacionBici;
	}
    
}
