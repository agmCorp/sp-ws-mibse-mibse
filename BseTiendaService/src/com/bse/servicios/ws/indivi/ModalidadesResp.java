package com.bse.servicios.ws.indivi;

import java.util.List;

import com.bse.accesodatos.eindivi.Modalidad;
import com.bse.accesodatos.eindivi.TipoVehiculo;

public class ModalidadesResp {

    private String codigoError;    
    private String descripcionError;
	private List<Modalidad> modalidades;

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

	public List<Modalidad> getModalidades() {
		return modalidades;
	}

	public void setModalidades(List<Modalidad> modalidades) {
		this.modalidades = modalidades;
	}



}
