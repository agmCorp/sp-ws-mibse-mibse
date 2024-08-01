package uy.com.bse.consultas.entidades;

import java.io.Serializable;

public class IntegranteNomina implements Serializable {
	
	private static final long serialVersionUID = -3604506093131465246L;
	
	private String tipoDocumento;
	private String numDocumento;
	private String nombre;
	private Integer numPersona;
	private String codIntegrante;
	private Integer numNomina;
	private String descIntegrante;
	
	private String tipoDocumentoRelacionado;
	private String numDocumentoRelacionado;
	private String nombreRelacionado;
	private Integer numPersonaRelacionado;
	
	private Integer numCertificado;
	private Integer cantAniosRecargo;
	
	private String codCategoriaFuncional;
	private String fechaInicioVigencia;
	private String fechaAdhesion;
	
	private String tieneDeclaracion;
	private Double capitalMuerte;
	private String opcionSumaAsegurado;
	
	private Double sumaAsegurado;
	private String fechaVigenciaCapital;
	private String codProductor;
	private String descProductor;
	private String tieneFacturaImpresa;
	private String fechaModificacion;
	private String usuarioModifica;
	
	private Integer numCobro;
	private String revalorizado;
	private String fechaNacimientoIntegrante;
	private String fechaBaja;
	private String codBaja;
	private String descBaja;
	private String observaciones;
	private String mensaje;
	private Integer tope;
	
	
 
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getNumPersona() {
		return numPersona;
	}
	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}
	public String getCodIntegrante() {
		return codIntegrante;
	}
	public void setCodIntegrante(String codIntegrante) {
		this.codIntegrante = codIntegrante;
	}
	public Integer getNumNomina() {
		return numNomina;
	}
	public void setNumNomina(Integer numNomina) {
		this.numNomina = numNomina;
	}
	public String getTipoDocumentoRelacionado() {
		return tipoDocumentoRelacionado;
	}
	public void setTipoDocumentoRelacionado(String tipoDocumentoRelacionado) {
		this.tipoDocumentoRelacionado = tipoDocumentoRelacionado;
	}
	public String getNumDocumentoRelacionado() {
		return numDocumentoRelacionado;
	}
	public void setNumDocumentoRelacionado(String numDocumentoRelacionado) {
		this.numDocumentoRelacionado = numDocumentoRelacionado;
	}
	public String getNombreRelacionado() {
		return nombreRelacionado;
	}
	public void setNombreRelacionado(String nombreRelacionado) {
		this.nombreRelacionado = nombreRelacionado;
	}
	public Integer getNumPersonaRelacionado() {
		return numPersonaRelacionado;
	}
	public void setNumPersonaRelacionado(Integer numPersonaRelacionado) {
		this.numPersonaRelacionado = numPersonaRelacionado;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getCantAniosRecargo() {
		return cantAniosRecargo;
	}
	public void setCantAniosRecargo(Integer cantAniosRecargo) {
		this.cantAniosRecargo = cantAniosRecargo;
	}
	public String getCodCategoriaFuncional() {
		return codCategoriaFuncional;
	}
	public void setCodCategoriaFuncional(String codCategoriaFuncional) {
		this.codCategoriaFuncional = codCategoriaFuncional;
	}
	public String getFechaInicioVigencia() {
		return fechaInicioVigencia;
	}
	public void setFechaInicioVigencia(String fechaInicioVigencia) {
		this.fechaInicioVigencia = fechaInicioVigencia;
	}
	public String getFechaAdhesion() {
		return fechaAdhesion;
	}
	public void setFechaAdhesion(String fechaAdhesion) {
		this.fechaAdhesion = fechaAdhesion;
	}
	public String getTieneDeclaracion() {
		return tieneDeclaracion;
	}
	public void setTieneDeclaracion(String tieneDeclaracion) {
		this.tieneDeclaracion = tieneDeclaracion;
	}
	public Double getCapitalMuerte() {
		return capitalMuerte;
	}
	public void setCapitalMuerte(Double capitalMuerte) {
		this.capitalMuerte = capitalMuerte;
	}
	public String getOpcionSumaAsegurado() {
		return opcionSumaAsegurado;
	}
	public void setOpcionSumaAsegurado(String opcionSumaAsegurado) {
		this.opcionSumaAsegurado = opcionSumaAsegurado;
	}
	public Double getSumaAsegurado() {
		return sumaAsegurado;
	}
	public void setSumaAsegurado(Double sumaAsegurado) {
		this.sumaAsegurado = sumaAsegurado;
	}
	public String getFechaVigenciaCapital() {
		return fechaVigenciaCapital;
	}
	public void setFechaVigenciaCapital(String fechaVigenciaCapital) {
		this.fechaVigenciaCapital = fechaVigenciaCapital;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	public String getDescProductor() {
		return descProductor;
	}
	public void setDescProductor(String descProductor) {
		this.descProductor = descProductor;
	}
	public String getTieneFacturaImpresa() {
		return tieneFacturaImpresa;
	}
	public void setTieneFacturaImpresa(String tieneFacturaImpresa) {
		this.tieneFacturaImpresa = tieneFacturaImpresa;
	}
	public String getFechaModificacion() {
		return fechaModificacion;
	}
	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	public String getUsuarioModifica() {
		return usuarioModifica;
	}
	public void setUsuarioModifica(String usuarioModifica) {
		this.usuarioModifica = usuarioModifica;
	}
	public String getDescIntegrante() {
		return descIntegrante;
	}
	public void setDescIntegrante(String descIntegrante) {
		this.descIntegrante = descIntegrante;
	}
	public Integer getNumCobro() {
		return numCobro;
	}
	public void setNumCobro(Integer numCobro) {
		this.numCobro = numCobro;
	}

	public String getRevalorizado() {
		return revalorizado;
	}
	public void setRevalorizado(String revalorizado) {
		this.revalorizado = revalorizado;
	}
	public String getFechaNacimientoIntegrante() {
		return fechaNacimientoIntegrante;
	}
	public void setFechaNacimientoIntegrante(String fechaNacimientoIntegrante) {
		this.fechaNacimientoIntegrante = fechaNacimientoIntegrante;
	}
	public String getFechaBaja() {
		return fechaBaja;
	}
	public void setFechaBaja(String fechaBaja) {
		this.fechaBaja = fechaBaja;
	}
	public String getCodBaja() {
		return codBaja;
	}
	public void setCodBaja(String codBaja) {
		this.codBaja = codBaja;
	}
	public String getDescBaja() {
		return descBaja;
	}
	public void setDescBaja(String descBaja) {
		this.descBaja = descBaja;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Integer getTope() {
		return tope;
	}
	public void setTope(Integer tope) {
		this.tope = tope;
	}


}
