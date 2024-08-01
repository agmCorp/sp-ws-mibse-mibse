package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerDatosSiniestro extends ParamGenerico{
	private String codRamo;
	private String anio;
	private String numSiniestro;
	
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getAnio() {
		return anio;
	}
	public void setAnio(String anio) {
		this.anio = anio;
	}
	public String getNumSiniestro() {
		return numSiniestro;
	}
	public void setNumSiniestro(String numSiniestro) {
		this.numSiniestro = numSiniestro;
	}

}
