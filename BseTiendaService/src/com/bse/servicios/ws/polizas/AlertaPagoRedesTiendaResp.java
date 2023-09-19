package com.bse.servicios.ws.polizas;

import java.io.Serializable;

public class AlertaPagoRedesTiendaResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

    
}
