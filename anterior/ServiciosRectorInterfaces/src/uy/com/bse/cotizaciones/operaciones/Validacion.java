package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;

public class Validacion implements Serializable{
	
	private String mensajeValidacion;

	public String getMensajeValidacion() {
		return mensajeValidacion;
	}

	public void setMensajeValidacion(String mensajeValidacion) {
		this.mensajeValidacion = mensajeValidacion;
	}
	
}
