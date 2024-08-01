package uy.com.bse.servicios.rector.interfaces;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamLocalidades extends ParamGenerico{
	private String pais;
	private String Departamento;
	
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public String getDepartamento() {
		return Departamento;
	}
	public void setDepartamento(String departamento) {
		Departamento = departamento;
	}
}
