package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerFacturasPoliza extends ParamGenerico{
	private static final long serialVersionUID = -834298438174464268L;
	private String numPoliza;
	private String codRamo;
	private String aplicadas;
	
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getAplicadas() {
		return aplicadas;
	}
	public void setAplicadas(String aplicadas) {
		this.aplicadas = aplicadas;
	}	

}
