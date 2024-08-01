package uy.com.bse.cotizaciones.entidades;

public class DatosCotizacion extends DatosBasicosCotizacion {

	private static final long serialVersionUID = 8207161510610943565L;
	private Integer numCotizacion;	
	private Integer numPoliza;
	private Integer numCertificado;
	private Integer codMotivo;
	private Integer sucursal;

	private Integer nivelCod;
	private Integer nivelCodBroker;
	private String nivelDescBroker;
	
	private Integer numDireEnvio;
	private Integer numDireCobro;

	private String nivelDesc;
	private String planPagoCod;
	private String planPagoDesc;
	private Integer medioPagoCod;
	private String medioPagoDesc;
	private String origenEndoso;
	private String origenEndosoDesc;
	private String estado;
	private String facturacionCod;
	private String facturacionDesc;

	
	private String origenCod;
	private String origenDesc;
	private String vigenciaCod;
	private String vigenciaDesc;
	private String vigenciaDesde;
	private String vigenciaHasta;
	private String frecTecnicaCod;
	private String frecTecnicaDesc;
	private String vigenciaTecnicaDesde;
	private String vigenciaTecnicaHasta;
	private Double premioInformado;
	private String usuarioCod;
	private String usuarioDesc;
	
	private Integer codRamo;
	private String codProducto;
	private String codTipoCalculo;
	private String descTipoCalculo;
	private Integer codBanco;
	private String descBanco;
	private Integer nuDomBancario;
	private String numTarjeta;
	private String descProducto;
	private String descRamo;
	private String codTipo;
	private String descTipo;
	private String habilitoVigenciaTecnica;
	private String facturaConRut;
	private String enviarFacturaEmail;
	private String esUnipersonal;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}	
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getCodMotivo() {
		return codMotivo;
	}
	public void setCodMotivo(Integer codMotivo) {
		this.codMotivo = codMotivo;
	}
	
	public Integer getNivelCod() {
		return nivelCod;
	}
	public void setNivelCod(Integer nivelCod) {
		this.nivelCod = nivelCod;
	}
	public String getNivelDesc() {
		return nivelDesc;
	}
	public void setNivelDesc(String nivelDesc) {
		this.nivelDesc = nivelDesc;
	}
	public String getPlanPagoCod() {
		return planPagoCod;
	}
	public void setPlanPagoCod(String planPagoCod) {
		this.planPagoCod = planPagoCod;
	}
	public String getPlanPagoDesc() {
		return planPagoDesc;
	}
	public void setPlanPagoDesc(String planPagoDesc) {
		this.planPagoDesc = planPagoDesc;
	}
	public Integer getMedioPagoCod() {
		return medioPagoCod;
	}
	public void setMedioPagoCod(Integer medioPagoCod) {
		this.medioPagoCod = medioPagoCod;
	}
	public String getMedioPagoDesc() {
		return medioPagoDesc;
	}
	public void setMedioPagoDesc(String medioPagoDesc) {
		this.medioPagoDesc = medioPagoDesc;
	}
	public String getOrigenEndoso() {
		return origenEndoso;
	}
	public void setOrigenEndoso(String origenEndoso) {
		this.origenEndoso = origenEndoso;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getFacturacionCod() {
		return facturacionCod;
	}
	public void setFacturacionCod(String facturacionCod) {
		this.facturacionCod = facturacionCod;
	}
	public String getFacturacionDesc() {
		return facturacionDesc;
	}
	public void setFacturacionDesc(String facturacionDesc) {
		this.facturacionDesc = facturacionDesc;
	}
	
	
	public String getOrigenCod() {
		return origenCod;
	}
	public void setOrigenCod(String origenCod) {
		this.origenCod = origenCod;
	}
	public String getOrigenDesc() {
		return origenDesc;
	}
	public void setOrigenDesc(String origenDesc) {
		this.origenDesc = origenDesc;
	}
	
	public String getVigenciaCod() {
		return vigenciaCod;
	}
	public void setVigenciaCod(String vigenciaCod) {
		this.vigenciaCod = vigenciaCod;
	}
	public String getVigenciaDesc() {
		return vigenciaDesc;
	}
	public void setVigenciaDesc(String vigenciaDesc) {
		this.vigenciaDesc = vigenciaDesc;
	}
	public String getVigenciaDesde() {
		return vigenciaDesde;
	}
	public void setVigenciaDesde(String vigenciaDesde) {
		this.vigenciaDesde = vigenciaDesde;
	}
	public String getVigenciaHasta() {
		return vigenciaHasta;
	}
	public void setVigenciaHasta(String vigenciaHasta) {
		this.vigenciaHasta = vigenciaHasta;
	}
	public String getFrecTecnicaCod() {
		return frecTecnicaCod;
	}
	public void setFrecTecnicaCod(String frecTecnicaCod) {
		this.frecTecnicaCod = frecTecnicaCod;
	}
	public String getFrecTecnicaDesc() {
		return frecTecnicaDesc;
	}
	public void setFrecTecnicaDesc(String frecTecnicaDesc) {
		this.frecTecnicaDesc = frecTecnicaDesc;
	}
	public String getVigenciaTecnicaDesde() {
		return vigenciaTecnicaDesde;
	}
	public void setVigenciaTecnicaDesde(String vigenciaTecnicaDesde) {
		this.vigenciaTecnicaDesde = vigenciaTecnicaDesde;
	}
	public String getVigenciaTecnicaHasta() {
		return vigenciaTecnicaHasta;
	}
	public void setVigenciaTecnicaHasta(String vigenciaTecnicaHasta) {
		this.vigenciaTecnicaHasta = vigenciaTecnicaHasta;
	}
	public Double getPremioInformado() {
		return premioInformado;
	}
	public void setPremioInformado(Double premioInformado) {
		this.premioInformado = premioInformado;
	}
	public String getUsuarioCod() {
		return usuarioCod;
	}
	public void setUsuarioCod(String usuarioCod) {
		this.usuarioCod = usuarioCod;
	}
	public String getUsuarioDesc() {
		return usuarioDesc;
	}
	public void setUsuarioDesc(String usuarioDesc) {
		this.usuarioDesc = usuarioDesc;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
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
	public Integer getCodBanco() {
		return codBanco;
	}
	public void setCodBanco(Integer codBanco) {
		this.codBanco = codBanco;
	}
	public String getDescBanco() {
		return descBanco;
	}
	public void setDescBanco(String descBanco) {
		this.descBanco = descBanco;
	}
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	public String getDescProducto() {
		return descProducto;
	}
	public void setDescProducto(String descProducto) {
		this.descProducto = descProducto;
	}
	public String getDescRamo() {
		return descRamo;
	}
	public void setDescRamo(String descRamo) {
		this.descRamo = descRamo;
	}
	public String getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}
	public String getDescTipo() {
		return descTipo;
	}
	public void setDescTipo(String descTipo) {
		this.descTipo = descTipo;
	}
	public String getHabilitoVigenciaTecnica() {
		return habilitoVigenciaTecnica;
	}
	public void setHabilitoVigenciaTecnica(String habilitoVigenciaTecnica) {
		this.habilitoVigenciaTecnica = habilitoVigenciaTecnica;
	}
	public Integer getSucursal() {
		return sucursal;
	}
	public void setSucursal(Integer sucursal) {
		this.sucursal = sucursal;
	}
	public String getOrigenEndosoDesc() {
		return origenEndosoDesc;
	}
	public void setOrigenEndosoDesc(String origenEndosoDesc) {
		this.origenEndosoDesc = origenEndosoDesc;
	}
	public Integer getNuDomBancario() {
		return nuDomBancario;
	}
	public void setNuDomBancario(Integer nuDomBancario) {
		this.nuDomBancario = nuDomBancario;
	}
	public String getNumTarjeta() {
		return numTarjeta;
	}
	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}
	public String getFacturaConRut() {
		return facturaConRut;
	}
	public void setFacturaConRut(String facturaConRut) {
		this.facturaConRut = facturaConRut;
	}
	public String getEnviarFacturaEmail() {
		return enviarFacturaEmail;
	}
	public void setEnviarFacturaEmail(String enviarFacturaEmail) {
		this.enviarFacturaEmail = enviarFacturaEmail;
	}
	public String getEsUnipersonal() {
		return esUnipersonal;
	}
	public void setEsUnipersonal(String esUnipersonal) {
		this.esUnipersonal = esUnipersonal;
	}
	
	public Integer getNivelCodBroker() {
		return nivelCodBroker;
	}
	public void setNivelCodBroker(Integer nivelCodBroker) {
		this.nivelCodBroker = nivelCodBroker;
	}
	public String getNivelDescBroker() {
		return nivelDescBroker;
	}
	public void setNivelDescBroker(String nivelDescBroker) {
		this.nivelDescBroker = nivelDescBroker;
	}
	public Integer getNumDireEnvio() {
		return numDireEnvio;
	}
	public void setNumDireEnvio(Integer numDireEnvio) {
		this.numDireEnvio = numDireEnvio;
	}
	public Integer getNumDireCobro() {
		return numDireCobro;
	}
	public void setNumDireCobro(Integer numDireCobro) {
		this.numDireCobro = numDireCobro;
	}
	
}
