package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteNuevaTarifaIncendioProductor implements Serializable {

	private static final long serialVersionUID = -3464630236323378581L;

	private Long numCotizacion;
	private Integer codRamo;
	private Long nroCliente;
	private String codProducto;
	private String nombre ;
	private Integer certificado;
	private Long polizaAnterior;
	private String moneda;
	private Double premioNuevo;
	private String actividadNueva;
	private Double premioAnterior;
	private String actividadAnterior;
	private Double diferenciaPremios;
	private String feDesde;
	private String feHasta;

	
	

	public ReporteNuevaTarifaIncendioProductor(Long numCotizacion, Integer codRamo, Long nroCliente,String codProducto,
	String nombre,Integer certificado,Long polizaAnterior,String moneda,Double premioNuevo,String actividadNueva,Double premioAnterior,
	String actividadAnterior,Double diferenciaPremios,String feDesde,String feHasta){
		super();
		this.numCotizacion = numCotizacion;
		this.codRamo = codRamo;
		this.nroCliente = nroCliente;
		this.codRamo = codRamo;
		this.nroCliente = nroCliente;
		this.codProducto = codProducto;
		this.nombre = nombre;
		this.certificado = certificado;
		this.polizaAnterior = polizaAnterior;
		this.moneda = moneda;
		this.premioNuevo = premioNuevo;
		this.actividadNueva = actividadNueva;
		this.premioAnterior = premioAnterior;
		this.actividadAnterior = actividadAnterior;

		this.diferenciaPremios = diferenciaPremios;
		this.feDesde = feDesde;
		this.feHasta = feHasta;
	}

	public Long getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(Long numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public Long getNroCliente() {
		return nroCliente;
	}

	public void setNroCliente(Long nroCliente) {
		this.nroCliente = nroCliente;
	}

	public String getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public Long getPolizaAnterior() {
		return polizaAnterior;
	}

	public void setPolizaAnterior(Long polizaAnterior) {
		this.polizaAnterior = polizaAnterior;
	}

	public String getMoneda() {
		return moneda;
	}

	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}

	public Double getPremioNuevo() {
		return premioNuevo;
	}

	public void setPremioNuevo(Double premioNuevo) {
		this.premioNuevo = premioNuevo;
	}

	public String getActividadNueva() {
		return actividadNueva;
	}

	public void setActividadNueva(String actividadNueva) {
		this.actividadNueva = actividadNueva;
	}

	public Double getPremioAnterior() {
		return premioAnterior;
	}

	public void setPremioAnterior(Double premioAnterior) {
		this.premioAnterior = premioAnterior;
	}

	public String getActividadAnterior() {
		return actividadAnterior;
	}

	public void setActividadAnterior(String actividadAnterior) {
		this.actividadAnterior = actividadAnterior;
	}



	public Double getDiferenciaPremios() {
		return diferenciaPremios;
	}

	public void setDiferenciaPremios(Double diferenciaPremios) {
		this.diferenciaPremios = diferenciaPremios;
	}

	public String getFeDesde() {
		return feDesde;
	}

	public void setFeDesde(String feDesde) {
		this.feDesde = feDesde;
	}

	public String getFeHasta() {
		return feHasta;
	}

	public void setFeHasta(String feHasta) {
		this.feHasta = feHasta;
	}

	public ReporteNuevaTarifaIncendioProductor() {
		super();
		
	}

}
