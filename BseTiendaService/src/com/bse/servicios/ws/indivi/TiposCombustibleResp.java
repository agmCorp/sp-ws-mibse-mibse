package com.bse.servicios.ws.indivi;

import java.util.List;

import com.bse.accesodatos.eindivi.TipoCombustible;

public class TiposCombustibleResp {

    private String codigoError;    
    private String descripcionError;
	private List<TipoCombustible> tiposCombustible;

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

    public List<TipoCombustible> getTiposCombustible() {
		return tiposCombustible;
	}

	public void setTiposCombustible(List<TipoCombustible> tiposCombustible) {
		this.tiposCombustible = tiposCombustible;
	}

}
