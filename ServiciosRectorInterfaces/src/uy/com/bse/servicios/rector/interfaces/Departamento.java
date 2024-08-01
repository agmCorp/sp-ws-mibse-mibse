package uy.com.bse.servicios.rector.interfaces;

import java.io.Serializable;

public class Departamento implements Serializable{
	private String codDepartamento;
	private String descDepartamento;
		
	public String getCodDepartamento() {
		return codDepartamento;
	}
	public void setCodDepartamento(String codDepartamento) {
		this.codDepartamento = codDepartamento;
	}
	public String getDescDepartamento() {
		return descDepartamento;
	}
	public void setDescDepartamento(String descDepartamento) {
		this.descDepartamento = descDepartamento;
	}
}
