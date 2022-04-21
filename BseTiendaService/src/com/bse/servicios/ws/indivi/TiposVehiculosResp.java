package com.bse.servicios.ws.indivi;

import java.util.List;

import com.bse.accesodatos.eindivi.TipoVehiculo;

public class TiposVehiculosResp {

    private String codigoError;    
    private String descripcionError;
	private List<TipoVehiculo> tiposVehiculos;

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

	public List<TipoVehiculo> getTiposVehiculos() {
		return tiposVehiculos;
	}

	public void setTiposVehiculos(List<TipoVehiculo> tiposVehiculos) {
		this.tiposVehiculos = tiposVehiculos;
	}



}
