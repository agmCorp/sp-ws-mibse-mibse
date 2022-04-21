package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.TipoBuque;
import com.bse.accesodatos.esoa.CategoriaVehiculo;
import java.io.Serializable;
import java.util.List;

public class TiposBuquesResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private List<TipoBuque> tiposBuques;

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

	public void setTiposBuques(List<TipoBuque> tiposBuques){
		this.tiposBuques = tiposBuques;
	}

	public List<TipoBuque> getTiposBuques(){
		return this.tiposBuques;
	}
    
}
