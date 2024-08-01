package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class EncabezadoPoliza implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer codSucursal;
	private String descripSucursal;
	private Integer codRamo;
	private String descripRamo;
	private Integer numPoliza;
	private String clausula;
	
	public Integer getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getDescripSucursal() {
		return descripSucursal;
	}
	public void setDescripSucursal(String descripSucursal) {
		this.descripSucursal = descripSucursal;
	}
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
	public String getClausula() {
		return clausula;
	}
	public void setClausula(String clausula) {
		this.clausula = clausula;
	}
	
}
