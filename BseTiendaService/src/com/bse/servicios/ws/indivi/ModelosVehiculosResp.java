package com.bse.servicios.ws.indivi;

import java.util.List;

import com.bse.accesodatos.eindivi.ModeloVehiculo;
import com.bse.accesodatos.eindivi.TipoVehiculo;

public class ModelosVehiculosResp {

    private String codigoError;    
    private String descripcionError;
	private List<ModeloVehiculo> modelosVehiculos;

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

	public List<ModeloVehiculo> getTiposVehiculos() {
		return modelosVehiculos;
	}

	public void setTiposVehiculos(List<ModeloVehiculo> tiposVehiculos) {
		this.modelosVehiculos = tiposVehiculos;
	}



}
