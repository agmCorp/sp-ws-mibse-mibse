package uy.com.bse.refacturar;

import java.io.Serializable;
import java.util.ArrayList;

public class Refacturacion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2724774606446715678L;
	private String habilitoEmitir;
	private String habilitoModificar;
	private String habilitoRefacturar;
	private String habilitoFacturacion;
	private String nroCotizacion;
	private String descMensaje;
	private Double premioFact;
	private ArrayList<Advertencia> advertencias = new ArrayList<Advertencia>();
	private String codProducto;
	private String descRamo;
	private Integer codMoneda;
	private Integer codPlan;
	

	
	

	
	
	public ArrayList<Advertencia> getAdvertencias() {
		return advertencias;
	}
	public void setAdvertencias(ArrayList<Advertencia> advertencias) {
		this.advertencias = advertencias;
	}
	public Integer getCodPlan() {
		return codPlan;
	}
	public void setCodPlan(Integer codPlan) {
		this.codPlan = codPlan;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getDescRamo() {
		return descRamo;
	}
	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}
	public Integer getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(Integer codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getHabilitoEmitir() {
		return habilitoEmitir;
	}
	public void setHabilitoEmitir(String habilitoEmitir) {
		this.habilitoEmitir = habilitoEmitir;
	}
	public String getHabilitoModificar() {
		return habilitoModificar;
	}
	public void setHabilitoModificar(String habilitoModificar) {
		this.habilitoModificar = habilitoModificar;
	}
	public String getHabilitoRefacturar() {
		return habilitoRefacturar;
	}
	public void setHabilitoRefacturar(String habilitoRefacturar) {
		this.habilitoRefacturar = habilitoRefacturar;
	}
	public String getHabilitoFacturacion() {
		return habilitoFacturacion;
	}
	public void setHabilitoFacturacion(String habilitoFacturacion) {
		this.habilitoFacturacion = habilitoFacturacion;
	}
	public String getNroCotizacion() {
		return nroCotizacion;
	}
	public void setNroCotizacion(String nroCotizacion) {
		this.nroCotizacion = nroCotizacion;
	}
	public String getDescMensaje() {
		return descMensaje;
	}
	public void setDescMensaje(String descMensaje) {
		this.descMensaje = descMensaje;
	}
	public Double getPremioFact() {
		return premioFact;
	}
	public void setPremioFact(Double premioFact) {
		this.premioFact = premioFact;
	}
	public ArrayList<Advertencia> getAdvertencia() {
		return advertencias;
	}
	public void setAdvertencia(ArrayList<Advertencia> advertencia) {
		this.advertencias = advertencia;
	}
	
	public void setUnAdvertencia(Advertencia item){
		this.advertencias.add(item);
	}
	

	  
	  

}
