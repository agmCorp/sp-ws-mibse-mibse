package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class DetallePolizaFlotante implements Serializable {
	
	private static final long serialVersionUID = 681475327087951438L;
	private Integer identificador;
	private String codTipoFacturacion;
	private String descTipoFacturacion;
	private String fechaInscripcion;
	private Integer numSucursal;
	private Integer codRamo;
	private Integer numPoliza;
	private Integer numEndoso;
	private Integer numCertificado;
	private String nombreAsegurado;
	private String direccionAsegurado;
	private Integer codCorredor;
	private String descCorredor;
	private String descMercaderia;
	private String descEmbalaje;
	private  Integer cantidadTransbordo;
	private String codArancelario;
	private String descOrigen;
	private String ciudadOrigen;
	private String descDestino;
	private String ciudadDestino;
	private String descMedioTransporte;
	private String nombreMedioTransporte;
	private String fechaEmbarque;
	private String descMoneda;
	private Double valorMercaderia;
	private String descPlanCobertura;
	private Double valorFlete;
	private Double tasa;
	private Double gastosRecargo;
	private String tieneRecargo;
	private Double incremento;
	private String incluyePrima;
	private Double sumaAsegurada;
	private String descMonedaPremio;
	private Double premioNeto;
	private Double premioNacional;
	private Double premioTotal;
	private Double premioInternacional;
	private  String usuarioModifica;
	private String fechaModificacion;
	private String codAsignado;
	private String cesionDerechos;
	private String codViaje;
	private String numDua;
	private String descEstado;
	private String codCausaAnulacion;
	private String descCausaAnulacion;
	private String observaciones;
	private String descUsuario;
	
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getDescEstado() {
		return descEstado;
	}
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	public String getCodCausaAnulacion() {
		return codCausaAnulacion;
	}
	public void setCodCausaAnulacion(String codCausaAnulacion) {
		this.codCausaAnulacion = codCausaAnulacion;
	}
	public String getDescCausaAnulacion() {
		return descCausaAnulacion;
	}
	public void setDescCausaAnulacion(String descCausaAnulacion) {
		this.descCausaAnulacion = descCausaAnulacion;
	}
	
	public String getCodViaje() {
		return codViaje;
	}
	public void setCodViaje(String codViaje) {
		this.codViaje = codViaje;
	}
	
	public String getCodAsignado() {
		return codAsignado;
	}
	public String getCesionDerechos() {
		return cesionDerechos;
	}
	public Integer getIdentificador() {
		return identificador;
	}
	public void setIdentificador(Integer identificador) {
		this.identificador = identificador;
	}
	public String getCodTipoFacturacion() {
		return codTipoFacturacion;
	}
	public void setCodTipoFacturacion(String codTipoFacturacion) {
		this.codTipoFacturacion = codTipoFacturacion;
	}
	public String getFechaInscripcion() {
		return fechaInscripcion;
	}
	public void setFechaInscripcion(String fechaInscripcion) {
		this.fechaInscripcion = fechaInscripcion;
	}
	public Integer getNumSucursal() {
		return numSucursal;
	}
	public void setNumSucursal(Integer numSucursal) {
		this.numSucursal = numSucursal;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNombreAsegurado() {
		return nombreAsegurado;
	}
	public void setNombreAsegurado(String nombreAsegurado) {
		this.nombreAsegurado = nombreAsegurado;
	}
	public String getDireccionAsegurado() {
		return direccionAsegurado;
	}
	public void setDireccionAsegurado(String direccionAsegurado) {
		this.direccionAsegurado = direccionAsegurado;
	}
	public Integer getCodCorredor() {
		return codCorredor;
	}
	public void setCodCorredor(Integer codCorredor) {
		this.codCorredor = codCorredor;
	}
	public String getDescCorredor() {
		return descCorredor;
	}
	public void setDescCorredor(String descCorredor) {
		this.descCorredor = descCorredor;
	}
	public String getDescMercaderia() {
		return descMercaderia;
	}
	public void setDescMercaderia(String descMercaderia) {
		this.descMercaderia = descMercaderia;
	}
	public String getDescEmbalaje() {
		return descEmbalaje;
	}
	public void setDescEmbalaje(String descEmbalaje) {
		this.descEmbalaje = descEmbalaje;
	}
	public Integer getCantidadTransbordo() {
		return cantidadTransbordo;
	}
	public void setCantidadTransbordo(Integer cantidadTransbordo) {
		this.cantidadTransbordo = cantidadTransbordo;
	}
	public String getCodArancelario() {
		return codArancelario;
	}
	public void setCodArancelario(String codArancelario) {
		this.codArancelario = codArancelario;
	}
	public String getDescOrigen() {
		return descOrigen;
	}
	public void setDescOrigen(String descOrigen) {
		this.descOrigen = descOrigen;
	}
	public String getCiudadOrigen() {
		return ciudadOrigen;
	}
	public void setCiudadOrigen(String ciudadOrigen) {
		this.ciudadOrigen = ciudadOrigen;
	}
	public String getDescDestino() {
		return descDestino;
	}
	public void setDescDestino(String descDestino) {
		this.descDestino = descDestino;
	}
	public String getCiudadDestino() {
		return ciudadDestino;
	}
	public void setCiudadDestino(String ciudadDestino) {
		this.ciudadDestino = ciudadDestino;
	}
	public String getDescMedioTransporte() {
		return descMedioTransporte;
	}
	public void setDescMedioTransporte(String descMedioTransporte) {
		this.descMedioTransporte = descMedioTransporte;
	}
	public String getNombreMedioTransporte() {
		return nombreMedioTransporte;
	}
	public void setNombreMedioTransporte(String nombreMedioTransporte) {
		this.nombreMedioTransporte = nombreMedioTransporte;
	}
	public String getFechaEmbarque() {
		return fechaEmbarque;
	}
	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}
	public String getDescMoneda() {
		return descMoneda;
	}
	public void setDescMoneda(String descMoneda) {
		this.descMoneda = descMoneda;
	}
	public Double getValorMercaderia() {
		return valorMercaderia;
	}
	public void setValorMercaderia(Double valorMercaderia) {
		this.valorMercaderia = valorMercaderia;
	}
	
	public Double getValorFlete() {
		return valorFlete;
	}
	public void setValorFlete(Double valorFlete) {
		this.valorFlete = valorFlete;
	}
	public Double getTasa() {
		return tasa;
	}
	public void setTasa(Double tasa) {
		this.tasa = tasa;
	}
	public Double getGastosRecargo() {
		return gastosRecargo;
	}
	public void setGastosRecargo(Double gastosRecargo) {
		this.gastosRecargo = gastosRecargo;
	}
	public String getTieneRecargo() {
		return tieneRecargo;
	}
	public void setTieneRecargo(String tieneRecargo) {
		this.tieneRecargo = tieneRecargo;
	}
	public Double getIncremento() {
		return incremento;
	}
	public void setIncremento(Double incremento) {
		this.incremento = incremento;
	}
	public String getIncluyePrima() {
		return incluyePrima;
	}
	public void setIncluyePrima(String incluyePrima) {
		this.incluyePrima = incluyePrima;
	}
	public Double getSumaAsegurada() {
		return sumaAsegurada;
	}
	public void setSumaAsegurada(Double sumaAsegurada) {
		this.sumaAsegurada = sumaAsegurada;
	}
	public String getDescMonedaPremio() {
		return descMonedaPremio;
	}
	public void setDescMonedaPremio(String descMonedaPremio) {
		this.descMonedaPremio = descMonedaPremio;
	}
	public Double getPremioNeto() {
		return premioNeto;
	}
	public void setPremioNeto(Double premioNeto) {
		this.premioNeto = premioNeto;
	}
	public Double getPremioNacional() {
		return premioNacional;
	}
	public void setPremioNacional(Double premioNacional) {
		this.premioNacional = premioNacional;
	}
	public Double getPremioTotal() {
		return premioTotal;
	}
	public void setPremioTotal(Double premioTotal) {
		this.premioTotal = premioTotal;
	}
	public Double getPremioInternacional() {
		return premioInternacional;
	}
	public void setPremioInternacional(Double premioInternacional) {
		this.premioInternacional = premioInternacional;
	}
	public String getUsuarioModifica() {
		return usuarioModifica;
	}
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getDescPlanCobertura() {
		return descPlanCobertura;
	}
	public void setDescPlanCobertura(String descPlanCobertura) {
		this.descPlanCobertura = descPlanCobertura;
	}
	public void setCodAsignado(String codAsignado) {
		this.codAsignado=codAsignado;
		
	}
	public void setCesionDerechos(String cesionDerechos) {
		this.cesionDerechos=cesionDerechos;
		
	}
	public String getNumDua() {
		return numDua;
	}
	public void setNumDua(String numDua) {
		this.numDua = numDua;
	}
	public String getDescUsuario() {
		return descUsuario;
	}
	public void setDescUsuario(String descUsuario) {
		this.descUsuario = descUsuario;
	}
	public String getDescTipoFacturacion() {
		return descTipoFacturacion;
	}
	public void setDescTipoFacturacion(String descTipoFacturacion) {
		this.descTipoFacturacion = descTipoFacturacion;
	}

}
