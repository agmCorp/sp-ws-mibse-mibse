package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValoresTipoNota extends ParamGenerico{
	
	private String enlace;
	private String circuito;
	private String tipoNota;
	private String codValor;
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public String getCircuito() {
		return circuito;
	}
	public void setCircuito(String circuito) {
		this.circuito = circuito;
	}
	public String getTipoNota() {
		return tipoNota;
	}
	public void setTipoNota(String tipoNota) {
		this.tipoNota = tipoNota;
	}
	public String getCodValor() {
		return codValor;
	}
	public void setCodValor(String codValor) {
		this.codValor = codValor;
	}
		

}
