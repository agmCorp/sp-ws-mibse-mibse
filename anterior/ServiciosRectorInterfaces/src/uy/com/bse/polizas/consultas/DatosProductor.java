package uy.com.bse.polizas.consultas;

import java.io.Serializable;

public class DatosProductor implements Serializable{
	private Integer numEndoso;
	private Integer codProductor;
	private String descripProductor;
	
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(Integer codProductor) {
		this.codProductor = codProductor;
	}
	public String getDescripProductor() {
		return descripProductor;
	}
	public void setDescripProductor(String descripProductor) {
		this.descripProductor = descripProductor;
	}

}
