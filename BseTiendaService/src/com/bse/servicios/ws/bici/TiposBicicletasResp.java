package com.bse.servicios.ws.bici;

import com.bse.accesodatos.ebici.TipoBici;
import com.bse.accesodatos.esoa.MarcaVehiculo;

import java.io.Serializable;
import java.util.List;

public class TiposBicicletasResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private List<TipoBici> tiposBicicletas;

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

	public List<TipoBici> getTiposBicicletas() {
		return tiposBicicletas;
	}

	public void setTiposBicicletas(List<TipoBici> tiposBicicletas) {
		this.tiposBicicletas = tiposBicicletas;
	}

}
