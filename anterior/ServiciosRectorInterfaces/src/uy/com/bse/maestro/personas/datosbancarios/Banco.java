package uy.com.bse.maestro.personas.datosbancarios;

import java.io.Serializable;

public class Banco implements Serializable{
	private String codBanco;
	private String descBanco;
	public String getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(String codBanco) {
		this.codBanco = codBanco;
	}
	public String getDescBanco() {
		return descBanco;
	}
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}

}
