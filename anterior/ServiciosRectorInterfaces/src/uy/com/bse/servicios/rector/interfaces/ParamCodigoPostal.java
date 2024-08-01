package uy.com.bse.servicios.rector.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCodigoPostal extends ParamGenerico{
	private String codPais;
	private String codDepartamento;
	private String localidad;
	private String numPostal;	
	
	public String getCodPais() {
		return codPais;
	}
	public void setCodPais(String codPais) {
		this.codPais = codPais;
	}
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getNumPostal() {
		return numPostal;
	}
	public void setNumPostal(String numPostal) {
		this.numPostal = numPostal;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
}
