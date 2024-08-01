package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAltaCliente extends ResultGenerico {

	private static final long serialVersionUID = -9020595827613994655L;
	private String codCliente;
	private Integer codPersona;
	private String tieneComunicaciones;
	private String tieneTarjetas;
	private String tieneDireccion;

	public String getTieneTarjetas() {
		return tieneTarjetas;
	}

	public void setTieneTarjetas(String tieneTarjetas) {
		this.tieneTarjetas = tieneTarjetas;
	}

	public String getTieneDireccion() {
		return tieneDireccion;
	}

	public void setTieneDireccion(String tieneDireccion) {
		this.tieneDireccion = tieneDireccion;
	}

	public String getCodCliente() {
		return codCliente;
	}

	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}

	public String getTieneComunicaciones() {
		return tieneComunicaciones;
	}

	public void setTieneComunicaciones(String tieneComunicaciones) {
		this.tieneComunicaciones = tieneComunicaciones;
	}

	public Integer getCodPersona() {
		return codPersona;
	}

	public void setCodPersona(Integer codPersona) {
		this.codPersona = codPersona;
	}

}
