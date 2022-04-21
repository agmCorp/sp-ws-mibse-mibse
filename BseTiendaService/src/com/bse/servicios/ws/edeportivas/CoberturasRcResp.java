package com.bse.servicios.ws.edeportivas;

import com.bse.accesodatos.edepor.CoberturaRc;
import com.bse.accesodatos.edepor.TipoBuque;
import com.bse.accesodatos.esoa.CategoriaVehiculo;
import java.io.Serializable;
import java.util.List;

public class CoberturasRcResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private List<CoberturaRc> coberturasRc;

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

	public void setCoberturasRc(List<CoberturaRc> coberturasRc){
		this.coberturasRc = coberturasRc;
	}

	public List<CoberturaRc> getCoberturasRc(){
		return this.coberturasRc;
	}
    
}
