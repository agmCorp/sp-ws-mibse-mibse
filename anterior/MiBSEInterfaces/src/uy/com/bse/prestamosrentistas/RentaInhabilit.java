package uy.com.bse.prestamosrentistas;

import java.io.Serializable;

public class RentaInhabilit implements Serializable {
	private static final long serialVersionUID = 955375424119747310L;
	
	private Integer nroPersona;
	private Double prestacion;
	private Double cuotaMax;
	private Integer cantCuotas;
	private Double tasa;
	private Double capitalMax;
	private Double deudaPrestamoPrevio;
	private String motivoDeNoRenovacion;

	public Integer getNroPersona() {
		return nroPersona;
	}

	public void setNroPersona(Integer nroPersona) {
		this.nroPersona = nroPersona;
	}

	public Double getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(Double prestacion) {
		this.prestacion = prestacion;
	}

	public Double getCuotaMax() {
		return cuotaMax;
	}

	public void setCuotaMax(Double cuotaMax) {
		this.cuotaMax = cuotaMax;
	}

	public Integer getCantCuotas() {
		return cantCuotas;
	}

	public void setCantCuotas(Integer cantCuotas) {
		this.cantCuotas = cantCuotas;
	}

	public Double getTasa() {
		return tasa;
	}

	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}

	public Double getCapitalMax() {
		return capitalMax;
	}

	public void setCapitalMax(Double capitalMax) {
		this.capitalMax = capitalMax;
	}

	public Double getDeudaPrestamoPrevio() {
		return deudaPrestamoPrevio;
	}

	public void setDeudaPrestamoPrevio(Double deudaPrestamoPrevio) {
		this.deudaPrestamoPrevio = deudaPrestamoPrevio;
	}

	public String getMotivoDeNoRenovacion() {
		return motivoDeNoRenovacion;
	}

	public void setMotivoDeNoRenovacion(String motivoDeNoRenovacion) {
		this.motivoDeNoRenovacion = motivoDeNoRenovacion;
	}
}
