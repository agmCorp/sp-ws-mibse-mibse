package uy.com.bse.maestro.personas.domicilio;

import java.io.Serializable;

public class InfoPoliza implements Serializable{
	
	private Integer codRamo;
	private String descripRamo;
	private Integer numPoliza;
	private String codProducto;
	private String descripProducto;
	private String descEstado;
	
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getDescripRamo() {
		return descripRamo;
	}
	public void setDescripRamo(String descripRamo) {
		this.descripRamo = descripRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getDescripProducto() {
		return descripProducto;
	}
	public void setDescripProducto(String descripProducto) {
		this.descripProducto = descripProducto;
	}
	public String getDescEstado() {
		return descEstado;
	}
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
			

}
