package uy.com.bse.consultas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamActualizarPolizaFlotante extends ParamGenerico {

	private static final long serialVersionUID = 3445420378005612839L;
	
	private String numDua;
	private String observaciones;
	private Integer identificadorWeb;
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getIdentificadorWeb() {
		return identificadorWeb;
	}
	public void setIdentificadorWeb(Integer identificadorWeb) {
		this.identificadorWeb = identificadorWeb;
	}
	public String getNumDua() {
		return numDua;
	}
	public void setNumDua(String numDua) {
		this.numDua = numDua;
	}
	
	
}
