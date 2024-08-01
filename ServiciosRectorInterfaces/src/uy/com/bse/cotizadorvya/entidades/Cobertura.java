package uy.com.bse.cotizadorvya.entidades;

import java.io.Serializable;

public class Cobertura implements Serializable {
	private static final long serialVersionUID = -2970146262090825984L;
	
	private String codCategoria;
	private String codProducto;
	private String codPlan;
	private String nombrePlan;
	
	public String getCodCategoria() {
		return codCategoria;
	}
	
	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public String getCodProducto() {
		return codProducto;
	}
	
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	
	public String getCodPlan() {
		return codPlan;
	}
	
	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}
	
	public String getNombrePlan() {
		return nombrePlan;
	}
	
	public void setNombrePlan(String nombrePlan) {
		this.nombrePlan = nombrePlan;
	}
}
