package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanPago;

import java.io.Serializable;

public class PlanPagoResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private PlanPago plan;

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

	public void setPlanPago(PlanPago plan){
		this.plan = plan;
	}

	public PlanPago getPlanPago(){
		return this.plan;
	}
    
}
