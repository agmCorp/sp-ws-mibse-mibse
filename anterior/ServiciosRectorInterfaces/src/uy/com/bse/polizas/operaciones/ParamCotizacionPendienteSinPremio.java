package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCotizacionPendienteSinPremio extends ParamGenerico{
	private static final long serialVersionUID = 1L;
	private String numPoliza;
	private String codRamo;
	private String tipoTransaccion;
	
	
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
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
		
	
}
