package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.PolizaEDepor;

import java.io.Serializable;

public class EmisionEDeporResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private PolizaEDepor polizaEDepor;

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

	public void setPolizaEDepor(PolizaEDepor polizaEDepor){
		this.polizaEDepor = polizaEDepor;
	}

	public PolizaEDepor getPolizaEDepor(){
		return this.polizaEDepor;
	}
    
}
