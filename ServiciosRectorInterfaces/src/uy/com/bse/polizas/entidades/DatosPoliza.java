package uy.com.bse.polizas.entidades;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.DatosBien;

public class DatosPoliza extends DatosBasicosPoliza{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codNacionalidad;
	private String codCliente;
	private String cliente;
	private String descripEstado;
	private String descripTipoNegocio;
	private Integer numRefactPermitida;
	private String descripMedioPago;
	private String fechaAmortizacion;
	private Integer numCotizacion;
	private String fechaEmision;
	private String codTipoCalculo;
	private String descTipoCalculo;
	private String habilitoModificarSinPremio;
	private String habilitoNoRenovar;
	private String habilitoClonar;
	private String habilitoEndosar;
	private String habilitoAnular;
	private String habilitoRecuotificar;
	private String codAdhesion;
	private Double deducible;
	private String numPersona;
	private String codPlanCobertura;
	private String descPlanCobertura;
	

	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public String getDescPlanCobertura() {
		return descPlanCobertura;
	}
	public void setDescPlanCobertura(String descPlanCobertura) {
		this.descPlanCobertura = descPlanCobertura;
	}
	public Double getDeducible() {
		return deducible;
	}
	public void setDeducible(Double deducible) {
		this.deducible = deducible;
	}

	private ArrayList<DatosBien> datos = new ArrayList<DatosBien>();
	
	
	
	

	public ArrayList<DatosBien> getDatos() {
		return datos;
	}
	public void setDatos(ArrayList<DatosBien> datos) {
		this.datos = datos;
	}
	public String getCodAdhesion() {
		return codAdhesion;
	}
	public void setCodAdhesion(String codAdhesion) {
		this.codAdhesion = codAdhesion;
	}
	public String getHabilitoRecuotificar() {
		return habilitoRecuotificar;
	}
	public void setHabilitoRecuotificar(String habilitoRecuotificar) {
		this.habilitoRecuotificar = habilitoRecuotificar;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public String getHabilitoModificarSinPremio() {
		return habilitoModificarSinPremio;
	}
	public void setHabilitoModificarSinPremio(String habilitoModificarSinPremio) {
		this.habilitoModificarSinPremio = habilitoModificarSinPremio;
	}
	public String getHabilitoNoRenovar() {
		return habilitoNoRenovar;
	}
	public void setHabilitoNoRenovar(String habilitoNoRenovar) {
		this.habilitoNoRenovar = habilitoNoRenovar;
	}
	public String getHabilitoClonar() {
		return habilitoClonar;
	}
	public void setHabilitoClonar(String habilitoClonar) {
		this.habilitoClonar = habilitoClonar;
	}
	public String getHabilitoEndosar() {
		return habilitoEndosar;
	}
	public void setHabilitoEndosar(String habilitoEndosar) {
		this.habilitoEndosar = habilitoEndosar;
	}
	public String getHabilitoAnular() {
		return habilitoAnular;
	}
	public void setHabilitoAnular(String habilitoAnular) {
		this.habilitoAnular = habilitoAnular;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getDescripEstado() {
		return descripEstado;
	}
	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	public String getDescripTipoNegocio() {
		return descripTipoNegocio;
	}
	public void setDescripTipoNegocio(String descripTipoNegocio) {
		this.descripTipoNegocio = descripTipoNegocio;
	}
	public Integer getNumRefactPermitida() {
		return numRefactPermitida;
	}
	public void setNumRefactPermitida(Integer numRefactPermitida) {
		this.numRefactPermitida = numRefactPermitida;
	}
	public String getDescripMedioPago() {
		return descripMedioPago;
	}
	public void setDescripMedioPago(String descripMedioPago) {
		this.descripMedioPago = descripMedioPago;
	}
	public String getFechaAmortizacion() {
		return fechaAmortizacion;
	}
	public void setFechaAmortizacion(String fechaAmortizacion) {
		this.fechaAmortizacion = fechaAmortizacion;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getFechaEmision() {
		return fechaEmision;
	}
	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}
	public String getCodTipoCalculo() {
		return codTipoCalculo;
	}
	public void setCodTipoCalculo(String codTipoCalculo) {
		this.codTipoCalculo = codTipoCalculo;
	}
	public String getDescTipoCalculo() {
		return descTipoCalculo;
	}
	public void setDescTipoCalculo(String descTipoCalculo) {
		this.descTipoCalculo = descTipoCalculo;
	}	
	
	public String getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(String numPersona) {
		this.numPersona = numPersona;
	}
	public void setUnDato(DatosBien item){
		this.datos.add(item);
	}
	
}
