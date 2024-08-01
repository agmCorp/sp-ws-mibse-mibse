package uy.com.bse.maestro.personas.personas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamExistePersona extends ParamGenerico{
	
	private String rut;
	private String tipoDoc;
	private String numDocumento;
	
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
		
}
