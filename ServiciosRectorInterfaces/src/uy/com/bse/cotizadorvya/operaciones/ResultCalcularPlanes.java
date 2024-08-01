package uy.com.bse.cotizadorvya.operaciones;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import uy.com.bse.cotizadorvya.entidades.Plan;
import uy.com.bse.cotizadorvya.entidades.Referencia;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularPlanes extends ResultGenerico {
	private static final long serialVersionUID = -7434827319466727016L;

	private Integer codMoneda;
	private String desMoneda;
	private String simboloMoneda;
	private String fechaNacimiento;
	private Integer edad;
	private Double capital;
	private Boolean muerteAccidental;
	private Boolean invalidez;
	private Boolean ingresoSeguro;
	private Double ingresoMensual;
	private Double rentaIngresoSeguro;
	private ArrayList<Plan> planes = new ArrayList<>();
	private ArrayList<Referencia> referencias= new ArrayList<>();
	
	public Integer getCodMoneda() {
		return codMoneda;
	}
	
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	
	public String getDesMoneda() {
		return desMoneda;
	}
	
	public void setDesMoneda(String desMoneda) {
		this.desMoneda = desMoneda;
	}
	
	public String getSimboloMoneda() {
		return simboloMoneda;
	}
	
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}
	
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public Integer getEdad() {
		return edad;
	}
	
	public void setEdad(Integer edad) {
		this.edad = edad;
	}
	
	public Double getCapital() {
		return capital;
	}
	
	public void setCapital(Double capital) {
		this.capital = capital;
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
	
	@XmlElementWrapper(name="Planes") 
    @XmlElement(name="Plan")	
	public ArrayList<Plan> getPlanes() {
		return planes;
	}
	
	public void setPlanes(ArrayList<Plan> planes) {
		this.planes = planes;
	}

	@XmlElementWrapper(name="Referencias") 
    @XmlElement(name="Referencia")	
	public ArrayList<Referencia> getReferencias() {
		return referencias;
	}
	
	public void setReferencias(ArrayList<Referencia> referencias) {
		this.referencias = referencias;
	}
}
