package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PolizaSoa;

import java.io.Serializable;

public class EmisionSoaResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private PolizaSoa polizaSoa;

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

	public void setPolizaSoa(PolizaSoa polizaSoa){
		this.polizaSoa = polizaSoa;
	}

	public PolizaSoa getPolizaSoa(){
		return this.polizaSoa;
	}
    
}
