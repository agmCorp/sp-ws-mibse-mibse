package uy.com.bse.cotizaciones.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerFechaEmision extends ResultGenerico{
	
	private String fechaEmision;

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	
	

}
