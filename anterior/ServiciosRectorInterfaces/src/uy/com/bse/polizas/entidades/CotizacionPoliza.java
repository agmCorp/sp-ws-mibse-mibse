package uy.com.bse.polizas.entidades;

import uy.com.bse.cotizaciones.entidades.DatosCotizacion;

public class CotizacionPoliza extends DatosCotizacion{
	private Integer codSucursal;
	private Integer numEndoso;
	private String codProducto;
	private String descripProducto;
	private String nombreAsegurado;
	private String fechaCotizacion;
	private String descripMotivo;
	private String codConvenio;
	private String descripConvenio;
	private Integer porcentajePremioInformado;
	private String tarjeta;
	private Integer codBroker;
	private String descBroker;
	
	public Integer getCodBroker() {
		return codBroker;
	}
	public void setCodBroker(Integer codBroker) {
		this.codBroker = codBroker;
	}
	public String getDescBroker() {
		return descBroker;
	}
	public void setDescBroker(String descBroker) {
		this.descBroker = descBroker;
	}
	public String getTarjeta() {
		return tarjeta;
	}
	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}
	public Integer getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
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
	public String getNombreAsegurado() {
		return nombreAsegurado;
	}
	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}
	public String getFechaCotizacion() {
		return fechaCotizacion;
	}
	public void setFechaCotizacion(String fechaCotizacion) {
		this.fechaCotizacion = fechaCotizacion;
	}
	public String getDescripMotivo() {
		return descripMotivo;
	}
	public void setDescripMotivo(String descripMotivo) {
		this.descripMotivo = descripMotivo;
	}
	public String getCodConvenio() {
		return codConvenio;
	}
	public void setCodConvenio(String codConvenio) {
		this.codConvenio = codConvenio;
	}
	public String getDescripConvenio() {
		return descripConvenio;
	}
	public void setDescripConvenio(String descripConvenio) {
		this.descripConvenio = descripConvenio;
	}
	public Integer getPorcentajePremioInformado() {
		return porcentajePremioInformado;
	}
	public void setPorcentajePremioInformado(Integer porcentajePremioInformado) {
		this.porcentajePremioInformado = porcentajePremioInformado;
	}

}
