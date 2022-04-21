package com.bse.servicios.ws.viajeros;

import com.bse.accesodatos.eviajeros.PolizaViajeros;

import java.io.Serializable;

public class EmisionViajerosResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private PolizaViajeros polizaViajeros;

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

	public void setPolizaViajeros(PolizaViajeros polizaViajeros){
		this.polizaViajeros = polizaViajeros;
	}

	public PolizaViajeros getPolizaViajeros(){
		return this.polizaViajeros;
	}
    
}
