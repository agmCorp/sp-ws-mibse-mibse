package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanCobertura;

import java.io.Serializable;

public class PlanCoberturaResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private PlanCobertura plan;

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

	public void setPlanCobertura(PlanCobertura plan){
		this.plan = plan;
	}

	public PlanCobertura getPlanCobertura(){
		return this.plan;
	}
    
}
