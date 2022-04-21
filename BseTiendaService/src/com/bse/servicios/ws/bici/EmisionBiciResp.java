package com.bse.servicios.ws.bici;

import com.bse.accesodatos.ebici.PolizaBici;

import java.io.Serializable;

public class EmisionBiciResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private PolizaBici polizaBici;

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

	public void setPolizaBici(PolizaBici polizaBici){
		this.polizaBici = polizaBici;
	}

	public PolizaBici getPolizaBici(){
		return this.polizaBici;
	}
    
}
