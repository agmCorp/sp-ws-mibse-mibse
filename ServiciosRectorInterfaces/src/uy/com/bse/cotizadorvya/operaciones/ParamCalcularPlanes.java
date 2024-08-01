package uy.com.bse.cotizadorvya.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularPlanes extends ParamGenerico {
	private static final long serialVersionUID = -761145905320616234L;
	private Integer codMoneda;
	private String fechaNacimiento;
	private Double capital;
	private String codCategoria;
	private String codPlan;
	private Boolean muerteAccidental;
	private Boolean invalidez;
	private Boolean ingresoSeguro;
	private Double ingresoMensual;
	private Double rentaIngresoSeguro;
	
	public Integer getCodMoneda() {
		return codMoneda;
	}
	
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Double getCapital() {
		return capital;
	}
	
	public void setCapital(Double capital) {
		this.capital = capital;
	}
	
	public String getCodCategoria() {
		return codCategoria;
	}
	
	public void setCodCategoria(String codCategoria) {
		this.codCategoria = codCategoria;
	}
	
	public String getCodPlan() {
		return codPlan;
	}
	
	public void setCodPlan(String codPlan) {
		this.codPlan = codPlan;
	}
	
	public Boolean getMuerteAccidental() {
		return muerteAccidental;
	}
	
	public void setMuerteAccidental(Boolean muerteAccidental) {
		this.muerteAccidental = muerteAccidental;
	}
	
	public Boolean getInvalidez() {
		return invalidez;
	}
	
	public void setInvalidez(Boolean invalidez) {
		this.invalidez = invalidez;
	}
	
	public Boolean getIngresoSeguro() {
		return ingresoSeguro;
	}
	
	public void setIngresoSeguro(Boolean ingresoSeguro) {
		this.ingresoSeguro = ingresoSeguro;
	}
	
	public Double getIngresoMensual() {
		return ingresoMensual;
	}
	
	public void setIngresoMensual(Double ingresoMensual) {
		this.ingresoMensual = ingresoMensual;
	}
	
	public Double getRentaIngresoSeguro() {
		return rentaIngresoSeguro;
	}
	
	public void setRentaIngresoSeguro(Double rentaIngresoSeguro) {
		this.rentaIngresoSeguro = rentaIngresoSeguro;
	}
}
