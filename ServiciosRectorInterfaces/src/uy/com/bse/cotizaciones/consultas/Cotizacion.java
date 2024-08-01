package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class Cotizacion implements Serializable{
	private static final long serialVersionUID = -4042569425448880768L;
	private Integer numCotizacion;
	private Integer numPoliza;
	private String nombreCliente;
	private String apellidoRazonSocialCliente;
	private String fechaDesde;
	private String fechaHasta;
	private String moneda;
	private Integer planPago;
	private String descripPlanPago;
	private Double premioCotizacion;
	private Double premioPoliza;
	private String estado;
	private String codMoneda;
	private Integer codRamo;
	private String descripRamo;
	private String codProducto;
	private String descripProducto;
	private String modificada;
	private String codTipo;
	private String descripTipo;
	private String enPauta;
	private Integer codProductor;
	private String descripProductor;    
	private Integer codMedioPago;
	private String descripMedioPago;
	private String codOrigenPago;
	private String descripOrigenPago;
	private Integer cantCertificados;
	private Integer cantBienes;
	private String simboloMoneda;
	private	String codPlanCobertura;
	private	Integer diaVencimiento;
	private	String descripNivel;
	private	String codTipoDoc;
	private	String numDocumento;
	private	Integer cantCuotas;
	private	Double montoCuota;
	private Double montoTotal;
	private	String descripPlanCobertura ;
	private String rut;
	private String habilitoVer;
	private String habilitoEmitir;
	private String habilitoModificar;
	private String habilitoImprimir;
	private String habilitoCopia;
	private String habilitoAnular;
	private String habilitoFueraPauta;
	private String habilitoNoSiniestro;
	private String sucursal;
	private Integer codBroker;
	private String descBroker;
	private Double montoDeducible;
	
	public String getSimboloMoneda() {
		return simboloMoneda;
	}
	public void setSimboloMoneda(String simboloMoneda) {
		this.simboloMoneda = simboloMoneda;
	}
	private Bien bien;
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
	public String getNombreCliente() {
		return nombreCliente;
	}
	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
	}
	public String getApellidoRazonSocialCliente() {
		return apellidoRazonSocialCliente;
	}
	public void setApellidoRazonSocialCliente(String apellidoRazonSocialCliente) {
		this.apellidoRazonSocialCliente = apellidoRazonSocialCliente;
	}
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
	public String getMoneda() {
		return moneda;
	}
	public void setMoneda(String moneda) {
		this.moneda = moneda;
	}
	public Integer getPlanPago() {
		return planPago;
	}
	public void setPlanPago(Integer planPago) {
		this.planPago = planPago;
	}	
	public String getDescripPlanPago() {
		return descripPlanPago;
	}
	public void setDescripPlanPago(String descripPlanPago) {
		this.descripPlanPago = descripPlanPago;
	}
	public Double getPremioCotizacion() {
		return premioCotizacion;
	}
	public void setPremioCotizacion(Double premioCotizacion) {
		this.premioCotizacion = premioCotizacion;
	}
	public Double getPremioPoliza() {
		return premioPoliza;
	}
	public void setPremioPoliza(Double premioPoliza) {
		this.premioPoliza = premioPoliza;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public String getDescripRamo() {
		return descripRamo;
	}
	public void setDescripRamo(String descripRamo) {
		this.descripRamo = descripRamo;
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
	public String getModificada() {
		return modificada;
	}
	public void setModificada(String modificada) {
		this.modificada = modificada;
	}
	public String getCodTipo() {
		return codTipo;
	}
	public void setCodTipo(String codTipo) {
		this.codTipo = codTipo;
	}
	public String getDescripTipo() {
		return descripTipo;
	}
	public void setDescripTipo(String descripTipo) {
		this.descripTipo = descripTipo;
	}
	public String getEnPauta() {
		return enPauta;
	}
	public void setEnPauta(String enPauta) {
		this.enPauta = enPauta;
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
	public Integer getCodMedioPago() {
		return codMedioPago;
	}
	public void setCodMedioPago(Integer codMedioPago) {
		this.codMedioPago = codMedioPago;
	}
	public String getDescripMedioPago() {
		return descripMedioPago;
	}
	public void setDescripMedioPago(String descripMedioPago) {
		this.descripMedioPago = descripMedioPago;
	}
	public String getCodOrigenPago() {
		return codOrigenPago;
	}
	public void setCodOrigenPago(String codOrigenPago) {
		this.codOrigenPago = codOrigenPago;
	}
	public String getDescripOrigenPago() {
		return descripOrigenPago;
	}
	public void setDescripOrigenPago(String descripOrigenPago) {
		this.descripOrigenPago = descripOrigenPago;
	}
	public Integer getCantCertificados() {
		return cantCertificados;
	}
	public void setCantCertificados(Integer cantCertificados) {
		this.cantCertificados = cantCertificados;
	}
	public Integer getCantBienes() {
		return cantBienes;
	}
	public void setCantBienes(Integer cantBienes) {
		this.cantBienes = cantBienes;
	}
	public Bien getBien() {
		return bien;
	}
	public void setBien(Bien bien) {
		this.bien = bien;
	}	
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public String getDescripNivel() {
		return descripNivel;
	}
	public void setDescripNivel(String descripNivel) {
		this.descripNivel = descripNivel;
	}
	public String getCodTipoDoc() {
		return codTipoDoc;
	}
	public void setCodTipoDoc(String codTipoDoc) {
		this.codTipoDoc = codTipoDoc;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public Integer getCantCuotas() {
		return cantCuotas;
	}
	public void setCantCuotas(Integer cantCuotas) {
		this.cantCuotas = cantCuotas;
	}
	public Double getMontoCuota() {
		return montoCuota;
	}
	public void setMontoCuota(Double montoCuota) {
		this.montoCuota = montoCuota;
	}
	public String getDescripPlanCobertura() {
		return descripPlanCobertura;
	}
	public void setDescripPlanCobertura(String descripPlanCobertura) {
		this.descripPlanCobertura = descripPlanCobertura;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	
	public String getHabilitoVer() {
		return habilitoVer;
	}
	public void setHabilitoVer(String habilitoVer) {
		this.habilitoVer = habilitoVer;
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
	public String getHabilitoImprimir() {
		return habilitoImprimir;
	}
	public void setHabilitoImprimir(String habilitoImprimir) {
		this.habilitoImprimir = habilitoImprimir;
	}
	public String getHabilitoCopia() {
		return habilitoCopia;
	}
	public void setHabilitoCopia(String habilitoCopia) {
		this.habilitoCopia = habilitoCopia;
	}
	public String getHabilitoAnular() {
		return habilitoAnular;
	}
	public void setHabilitoAnular(String habilitoAnular) {
		this.habilitoAnular = habilitoAnular;
	}
	public String getHabilitoFueraPauta() {
		return habilitoFueraPauta;
	}
	public void setHabilitoFueraPauta(String habilitoFueraPauta) {
		this.habilitoFueraPauta = habilitoFueraPauta;
	}
	public String getHabilitoNoSiniestro() {
		return habilitoNoSiniestro;
	}
	public void setHabilitoNoSiniestro(String habilitoNoSiniestro) {
		this.habilitoNoSiniestro = habilitoNoSiniestro;
	}
	public String getSucursal() {
		return sucursal;
	}
	public void setSucursal(String sucursal) {
		this.sucursal = sucursal;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	
	@Override
    public int hashCode() {
                   final int prime = 31;
                   int result = 1;
                   result = prime * result
                                                   + ((numCotizacion == null) ? 0 : numCotizacion.hashCode());
                   return result;
    }
    @Override
    public boolean equals(Object obj) {
                   if (this == obj)
                                   return true;
                   if (obj == null)
                                   return false;
                   if (getClass() != obj.getClass())
                                   return false;
                   Cotizacion other = (Cotizacion) obj;
                   if (numCotizacion == null) {
                                   if (other.numCotizacion != null)
                                                   return false;
                   } else if (!numCotizacion.equals(other.numCotizacion))
                                   return false;
                   return true;
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
	public Double getMontoDeducible() {
		return montoDeducible;
	}
	public void setMontoDeducible(Double montoDeducible) {
		this.montoDeducible = montoDeducible;
	}
	
	

}
