package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamPorcentajeAnticipo extends ParamGenerico {
	private static final long serialVersionUID = 6596650284961280420L;
	
	private String codProducto;
	private String codPlan;
	private Integer codMoneda;
	private Integer edad;
	
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
	
	public Integer getCodMoneda() {
		return codMoneda;
	}
	
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	
	public Integer getEdad() {
		return edad;
	}
	
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
}
