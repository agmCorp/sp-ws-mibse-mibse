package uy.com.bse.mibse;

import java.io.Serializable;
import java.util.Date;

public class DetalleMontoSiniestro implements Serializable {
	private static final long serialVersionUID = 1195867756768358472L;
	
	private Integer anio;
	private Integer codRamo;
	private Integer stro;
	private Integer numPoliza;
	private Date fechaOcurrencia;
	private String graciable;
	private String moneda;

	public Integer getAnio() {
		return anio;
	}

	public void setAnio(Integer anio) {
		this.anio = anio;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public Integer getStro() {
		return stro;
	}

	public void setStro(Integer stro) {
		this.stro = stro;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Date getFechaOcurrencia() {
		return fechaOcurrencia;
	}

	public void setFechaOcurrencia(Date fechaOcurrencia) {
		this.fechaOcurrencia = fechaOcurrencia;
	}

	public String getGraciable() {
		return graciable;
	}

	public void setGraciable(String graciable) {
		this.graciable = graciable;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
}
