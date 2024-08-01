package uy.com.bse.mibse;

import java.io.Serializable;

public class Siniestro implements Serializable {
	private static final long serialVersionUID = 4075229154405980733L;

	private Integer nroPoliza;
	private Integer nroCertificado;
	private String nroSiniestro;
	private String fechaOcurrenciaSiniestro;
	private String fechaOcurrenciaSiniestroFormateada;

	public Integer getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(Integer nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public Integer getNroCertificado() {
		return nroCertificado;
	}

	public void setNroCertificado(Integer nroCertificado) {
		this.nroCertificado = nroCertificado;
	}

	public String getNroSiniestro() {
		return nroSiniestro;
	}

	public void setNroSiniestro(String nroSiniestro) {
		this.nroSiniestro = nroSiniestro;
	}

	public String getFechaOcurrenciaSiniestro() {
		return fechaOcurrenciaSiniestro;
	}

	public void setFechaOcurrenciaSiniestro(String fechaOcurrenciaSiniestro) {
		this.fechaOcurrenciaSiniestro = fechaOcurrenciaSiniestro;
	}

	public String getFechaOcurrenciaSiniestroFormateada() {
		return fechaOcurrenciaSiniestroFormateada;
	}

	public void setFechaOcurrenciaSiniestroFormateada(String fechaOcurrenciaSiniestroFormateada) {
		this.fechaOcurrenciaSiniestroFormateada = fechaOcurrenciaSiniestroFormateada;
	}
}
