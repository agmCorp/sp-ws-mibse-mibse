package uy.com.bse.consultas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerIntegrantesNomina extends ParamGenerico {

	private static final long serialVersionUID = 3445420378005612839L;
	
	private  Integer numDetalle;

	public Integer getNumDetalle() {
		return numDetalle;
	}

	public void setNumDetalle(Integer numDetalle) {
		this.numDetalle = numDetalle;
	}
	
	
}
