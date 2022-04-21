package com.bse.servicios.ws.indivi;

import com.bse.accesodatos.eindivi.PolizaIndivi;
import java.io.Serializable;

public class EmisionIndiviResp implements Serializable {
    
	private static final long serialVersionUID = 1L;
	private PolizaIndivi polizaIndivi;
    private String codigoError;    
    private String descripcionError;

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

	public void setPolizaIndivi(PolizaIndivi polizaIndivi){
		this.polizaIndivi = polizaIndivi;
	}

	public PolizaIndivi getPolizaIndivi(){
		return this.polizaIndivi;
	}
    
}
