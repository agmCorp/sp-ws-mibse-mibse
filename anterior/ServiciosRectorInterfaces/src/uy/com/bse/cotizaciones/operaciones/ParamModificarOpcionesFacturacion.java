package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamModificarOpcionesFacturacion extends ParamGenerico {
	private Integer numCotizacion;
	private Boolean enviarFacturaEmail;
	private Boolean emitirConRUT;

	public Integer getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

	public Boolean getEnviarFacturaEmail() {
		return enviarFacturaEmail;
	}

	public void setEnviarFacturaEmail(Boolean enviarFacturaEmail) {
		this.enviarFacturaEmail = enviarFacturaEmail;
	}

	public Boolean getEmitirConRUT() {
		return emitirConRUT;
	}

	public void setEmitirConRUT(Boolean emitirConRUT) {
		this.emitirConRUT = emitirConRUT;
	}
}
