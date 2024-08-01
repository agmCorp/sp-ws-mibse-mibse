package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerCuotasPoliza extends ParamGenerico{
	private static final long serialVersionUID = 3402167016121440966L;
	private String numPoliza;
	private String codRamo;
	private String anuladas;
	
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
	public String getAnuladas() {
		return anuladas;
	}
	public void setAnuladas(String anuladas) {
		this.anuladas = anuladas;
	}
	
}
