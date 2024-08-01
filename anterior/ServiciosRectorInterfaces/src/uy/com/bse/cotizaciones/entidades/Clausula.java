package uy.com.bse.cotizaciones.entidades;

import java.io.Serializable;

public class Clausula implements Serializable{
	private String codClausula;
	private String codClausulaPoliza;
	private String descripcionClausula;
	private String modifica;
	private String impresion;
	private Integer codRamo;
	
	public String getCodClausula() {
		return codClausula;
	}
	public void setCodClausula(String codClausula) {
		this.codClausula = codClausula;
	}
	public String getDescripcionClausula() {
		return descripcionClausula;
	}
	public void setDescripcionClausula(String descripcionClausula) {
		this.descripcionClausula = descripcionClausula;
	}
	public String getModifica() {
		return modifica;
	}
	public void setModifica(String modifica) {
		this.modifica = modifica;
	}
	public String getImpresion() {
		return impresion;
	}
	public void setImpresion(String impresion) {
		this.impresion = impresion;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getCodClausulaPoliza() {
		return codClausulaPoliza;
	}
	public void setCodClausulaPoliza(String codClausulaPoliza) {
		this.codClausulaPoliza = codClausulaPoliza;
	}
	
	
}
