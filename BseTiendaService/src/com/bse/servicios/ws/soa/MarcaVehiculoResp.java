package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.MarcaVehiculo;

import java.io.Serializable;

public class MarcaVehiculoResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private MarcaVehiculo marca;

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

	public void setMarcaVehiculo(MarcaVehiculo marca){
		this.marca = marca;
	}

	public MarcaVehiculo getMarcaVehiculo(){
		return this.marca;
	}
    
}
