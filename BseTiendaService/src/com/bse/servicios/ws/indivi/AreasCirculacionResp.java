package com.bse.servicios.ws.indivi;

import java.util.List;

import com.bse.accesodatos.eindivi.AreaCirculacion;
import com.bse.accesodatos.eindivi.TipoVehiculo;

public class AreasCirculacionResp {

    private String codigoError;    
    private String descripcionError;

	private List<AreaCirculacion> areasCirculacion;

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

	public List<AreaCirculacion> getAreasCirculacion() {
		return areasCirculacion;
	}

	public void setAreasCirculacion(List<AreaCirculacion> areasCirculacion) {
		this.areasCirculacion = areasCirculacion;
	}



}
