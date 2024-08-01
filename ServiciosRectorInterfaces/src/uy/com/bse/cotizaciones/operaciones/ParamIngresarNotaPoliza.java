package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamIngresarNotaPoliza extends ParamGenerico{
	private String codTipoNota;
	private String enlace;
	private String observaciones;
	private String valor1;
	private String valor2;
	
	public String getCodTipoNota() {
		return codTipoNota;
	}
	public void setCodTipoNota(String codTipoNota) {
		this.codTipoNota = codTipoNota;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getValor1() {
		return valor1;
	}
	public void setValor1(String valor1) {
		this.valor1 = valor1;
	}
	public String getValor2() {
		return valor2;
	}
	public void setValor2(String valor2) {
		this.valor2 = valor2;
	}
	
}
