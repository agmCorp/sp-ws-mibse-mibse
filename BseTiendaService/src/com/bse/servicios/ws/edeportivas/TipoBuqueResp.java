package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.esoa.CategoriaVehiculo;
import java.io.Serializable;

public class TipoBuqueResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private CategoriaVehiculo categoria;

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

	public void setCategoriaVehiculo(CategoriaVehiculo categoria){
		this.categoria = categoria;
	}

	public CategoriaVehiculo getCategoriaVehiculo(){
		return this.categoria;
	}
    
}
