package uy.com.bse.polizas.operaciones;

import java.io.Serializable;

public class CotizacionAnulacion implements Serializable{
	private String fechaDesde;
	private String fechaHasta;
	private String enlace;
	private Integer codCausaAnulacion;
	private String descripCausaAnulacion;
	private String codModoCalculo;
	private String descripModoCalculo;
	private String cliente;
	private Integer codProductor;
	private String descripProductor;
	private String anioSiniestro;
	private Integer codRamoSiniestro;
	private Integer numSiniestro;
	private Double montoPremioPoliza;
	private Double montoPremioAnulacion; 
	private String fechaImpago;
	private String botonNotasVisible;
	private String botonConsultaVisible;
	private String botonImprimirVisible;
	private String botonAnularVisible;
	private String botonSiniestroVisible;
	private String habilitoCausa;
	private String habilitoModoCalculo;
	private String habilitoAnular;
	private String habilitoSiniestro;
	private Integer numCotizacion;
	private String mensajeAviso;
	private String fechaAnulacion;
    private Integer codBroker;
    private String descBroker;
	
	public String getFechaDesde() {
		return fechaDesde;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public String getFechaHasta() {
		return fechaHasta;
	}
	public void setFechaHasta(String fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
	public String getEnlace() {
		return enlace;
	}
	public void setEnlace(String enlace) {
		this.enlace = enlace;
	}
	public Integer getCodCausaAnulacion() {
		return codCausaAnulacion;
	}
	public void setCodCausaAnulacion(Integer codCausaAnulacion) {
		this.codCausaAnulacion = codCausaAnulacion;
	}
	public String getDescripCausaAnulacion() {
		return descripCausaAnulacion;
	}
	public void setDescripCausaAnulacion(String descripCausaAnulacion) {
		this.descripCausaAnulacion = descripCausaAnulacion;
	}
	public String getCodModoCalculo() {
		return codModoCalculo;
	}
	public void setCodModoCalculo(String codModoCalculo) {
		this.codModoCalculo = codModoCalculo;
	}
	public String getDescripModoCalculo() {
		return descripModoCalculo;
	}
	public void setDescripModoCalculo(String descripModoCalculo) {
		this.descripModoCalculo = descripModoCalculo;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Integer getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(Integer codProductor) {
		this.codProductor = codProductor;
	}
	public String getDescripProductor() {
		return descripProductor;
	}
	public void setDescripProductor(String descripProductor) {
		this.descripProductor = descripProductor;
	}
	public String getAnioSiniestro() {
		return anioSiniestro;
	}
	public void setAnioSiniestro(String anioSiniestro) {
		this.anioSiniestro = anioSiniestro;
	}
	public Integer getCodRamoSiniestro() {
		return codRamoSiniestro;
	}
	public void setCodRamoSiniestro(Integer codRamoSiniestro) {
		this.codRamoSiniestro = codRamoSiniestro;
	}
	public Integer getNumSiniestro() {
		return numSiniestro;
	}
	public void setNumSiniestro(Integer numSiniestro) {
		this.numSiniestro = numSiniestro;
	}
	public Double getMontoPremioPoliza() {
		return montoPremioPoliza;
	}
	public void setMontoPremioPoliza(Double montoPremioPoliza) {
		this.montoPremioPoliza = montoPremioPoliza;
	}
	public Double getMontoPremioAnulacion() {
		return montoPremioAnulacion;
	}
	public void setMontoPremioAnulacion(Double montoPremioAnulacion) {
		this.montoPremioAnulacion = montoPremioAnulacion;
	}
	public String getFechaImpago() {
		return fechaImpago;
	}
	public void setFechaImpago(String fechaImpago) {
		this.fechaImpago = fechaImpago;
	}
	public String getBotonNotasVisible() {
		return botonNotasVisible;
	}
	public void setBotonNotasVisible(String botonNotasVisible) {
		this.botonNotasVisible = botonNotasVisible;
	}
	public String getBotonConsultaVisible() {
		return botonConsultaVisible;
	}
	public void setBotonConsultaVisible(String botonConsultaVisible) {
		this.botonConsultaVisible = botonConsultaVisible;
	}
	public String getBotonImprimirVisible() {
		return botonImprimirVisible;
	}
	public void setBotonImprimirVisible(String botonImprimirVisible) {
		this.botonImprimirVisible = botonImprimirVisible;
	}
	public String getBotonAnularVisible() {
		return botonAnularVisible;
	}
	public void setBotonAnularVisible(String botonAnularVisible) {
		this.botonAnularVisible = botonAnularVisible;
	}
	public String getBotonSiniestroVisible() {
		return botonSiniestroVisible;
	}
	public void setBotonSiniestroVisible(String botonSiniestroVisible) {
		this.botonSiniestroVisible = botonSiniestroVisible;
	}
	public String getHabilitoCausa() {
		return habilitoCausa;
	}
	public void setHabilitoCausa(String habilitoCausa) {
		this.habilitoCausa = habilitoCausa;
	}
	public String getHabilitoModoCalculo() {
		return habilitoModoCalculo;
	}
	public void setHabilitoModoCalculo(String habilitoModoCalculo) {
		this.habilitoModoCalculo = habilitoModoCalculo;
	}
	public String getHabilitoAnular() {
		return habilitoAnular;
	}
	public void setHabilitoAnular(String habilitoAnular) {
		this.habilitoAnular = habilitoAnular;
	}
	public String getHabilitoSiniestro() {
		return habilitoSiniestro;
	}
	public void setHabilitoSiniestro(String habilitoSiniestro) {
		this.habilitoSiniestro = habilitoSiniestro;
	}
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getMensajeAviso() {
		return mensajeAviso;
	}
	public void setMensajeAviso(String mensajeAviso) {
		this.mensajeAviso = mensajeAviso;
	}
	public String getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
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
	
}
