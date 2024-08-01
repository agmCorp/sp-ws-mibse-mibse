package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class ImpresionSOA implements Serializable {

	private static final long serialVersionUID = -936363774373554639L;
	private Integer codigoImpresion;
	private Integer numConsecutivoBien;

	private String reporte;
	private String descripcionFormulario;

	public Integer getNumConsecutivoBien() {
		return numConsecutivoBien;
	}

	public void setNumConsecutivoBien(Integer numConsecutivoBien) {
		this.numConsecutivoBien = numConsecutivoBien;
	}

	public  Integer getCodigoImpresion() {
		return codigoImpresion;
	}

	public  void setCodigoImpresion(Integer codigoImpresion) {
		this.codigoImpresion = codigoImpresion;
	}

	public String getReporte() {
		return reporte;
	}

	public  void setReporte(String reporte) {
		this.reporte = reporte;
	}

	public  String getDescripcionFormulario() {
		return descripcionFormulario;
	}

	public  void setDescripcionFormulario(String descripcionFormulario) {
		this.descripcionFormulario = descripcionFormulario;
	}

}
