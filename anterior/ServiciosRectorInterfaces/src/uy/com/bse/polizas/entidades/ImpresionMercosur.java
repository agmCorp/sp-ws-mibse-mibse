package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class ImpresionMercosur implements Serializable {

	private static final long serialVersionUID = -936363774373554639L;
	private Integer codigoImpresionMercosur;
	private Integer numConsecutivoBien;
	private String reporte;
	private String descripcionFormulario;
	public Integer getCodigoImpresionMercosur() {
		return codigoImpresionMercosur;
	}
	public void setCodigoImpresionMercosur(Integer codigoImpresionMercosur) {
		this.codigoImpresionMercosur = codigoImpresionMercosur;
	}
	public Integer getNumConsecutivoBien() {
		return numConsecutivoBien;
	}
	public void setNumConsecutivoBien(Integer numConsecutivoBien) {
		this.numConsecutivoBien = numConsecutivoBien;
	}
	public synchronized String getReporte() {
		return reporte;
	}
	public synchronized void setReporte(String reporte) {
		this.reporte = reporte;
	}
	public synchronized String getDescripcionFormulario() {
		return descripcionFormulario;
	}
	public synchronized void setDescripcionFormulario(String descripcionFormulario) {
		this.descripcionFormulario = descripcionFormulario;
	}

}
