package com.bse.servicios.ws.viajeros;

import com.bse.accesodatos.eviajeros.CotizacionViajeros;
import com.bse.servicios.ws.soa.CotizacionResp;

import java.io.Serializable;
import java.util.List;

public class CotizacionViajerosResp extends CotizacionResp{
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CotizacionViajeros cotizacionViajeros;

	public void setCotizacionViajeros(CotizacionViajeros cotizacionViajeros){
		this.cotizacionViajeros = cotizacionViajeros;
	}

	public CotizacionViajeros getCotizacionViajeros(){
		return this.cotizacionViajeros;
	}
    
}
