package com.bse.servicios.ws.indivi;

import java.util.List;

import com.bse.accesodatos.eindivi.TipoContratante;

public class TiposContratanteResp {

    private String codigoError;    
    private String descripcionError;
	private List<TipoContratante> tipos;

    public List<TipoContratante> getTipos() {
		return tipos;
	}

	public void setTipos(List<TipoContratante> tipos) {
		this.tipos = tipos;
	}

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


}
