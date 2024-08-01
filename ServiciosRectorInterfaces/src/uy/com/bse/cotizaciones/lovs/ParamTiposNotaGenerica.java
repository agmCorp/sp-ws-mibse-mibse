package uy.com.bse.cotizaciones.lovs;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamTiposNotaGenerica extends ParamGenerico{
	private String codTipoNota;
	private String codCircuito;
	private String enlace;
	
	public String getCodTipoNota() {
		return codTipoNota;
	}
	public void setCodTipoNota(String codTipoNota) {
		this.codTipoNota = codTipoNota;
	}
	public String getCodCircuito() {
		return codCircuito;
	}
	public void setCodCircuito(String codCircuito) {
		this.codCircuito = codCircuito;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	
	

}
