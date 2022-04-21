package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.PlanPago;

import java.io.Serializable;
import java.util.List;

public class PlanesPagoResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private List<PlanPago> planes;

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

	public void setPlanesPago(List<PlanPago> planes){
		this.planes = planes;
	}

	public List<PlanPago> getPlanesPago(){
		return this.planes;
	}
    
}
