package com.bse.servicios.ws.soa;

import com.bse.accesodatos.eindivi.TipoCombustible;
import com.bse.accesodatos.esoa.MarcaVehiculo;

import java.io.Serializable;
import java.util.List;

public class MarcasVehiculosResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private List<MarcaVehiculo> marcas;

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

	public void setMarcasVehiculos(List<MarcaVehiculo> marcas){
		this.marcas = marcas;
	}

	public List<MarcaVehiculo> getMarcasVehiculos(){
		return this.marcas;
	}


    
}
