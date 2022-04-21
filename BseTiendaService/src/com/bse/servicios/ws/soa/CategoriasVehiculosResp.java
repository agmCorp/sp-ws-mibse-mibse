package com.bse.servicios.ws.soa;

import com.bse.accesodatos.esoa.CategoriaVehiculo;
import java.io.Serializable;
import java.util.List;

public class CategoriasVehiculosResp implements Serializable {
    
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    private String codigoError;    
    private String descripcionError;
	private List<CategoriaVehiculo> categorias;

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

	public void setCategoriasVehiculos(List<CategoriaVehiculo> categorias){
		this.categorias = categorias;
	}

	public List<CategoriaVehiculo> getCategoriasVehiculos(){
		return this.categorias;
	}
    
}
