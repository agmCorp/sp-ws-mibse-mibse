package uy.com.bse.polizas.entidades;

public class DetalleRefacturacionPoliza extends CertificadoPolizaBasico{
	
	/*
	<cod-sucursal>200</cod-sucursal>
    <cod-ramo>2</cod-ramo>
    <nu-poliza>176341</nu-poliza>
    <cod-nacionalidad>M</cod-nacionalidad>
    <nu-cedula-rif>313475</nu-cedula-rif>
    <desc-cliente>BACOT SILVEIRA OSCAR TULIO</desc-cliente>
    <desc-estado>VENCIDA</desc-estado>
    <desc-moneda>PESO URUGUAYO</desc-moneda>
    <desc-tipo-negocio>DIRECTO </desc-tipo-negocio>
    <desc-tipo-facturacion>FACT. POR POLIZA - 1 ASEG.</desc-tipo-facturacion>
    <fe-amortizacion></fe-amortizacion>
    <nu-refact-efect>2</nu-refact-efect>
    <nu-refact-permit>999</nu-refact-permit>
    <nu-poliza-anterior></nu-poliza-anterior>
    <desc-medio-pago>CAJA</desc-medio-pago>
    <cod-vigencia>M</cod-vigencia>
    <desc-vigencia>MENSUAL</desc-vigencia>
    <fe-hasta-vigencia>30/09/2012</fe-hasta-vigencia>
    <fe-desde-vigencia>01/08/2012</fe-desde-vigencia>
    <cod-vigencia-tecnica>U</cod-vigencia-tecnica>
    <desc-vigencia-tecnica>ABIERTA</desc-vigencia-tecnica>
    <fe-hasta-vigencia-tecnica></fe-hasta-vigencia-tecnica>
    <fe-desde-vigencia-tecnica>01/08/2012</fe-desde-vigencia-tecnica>
    <cod-tp-calculo>N</cod-tp-calculo>
    <desc-tp-calculo>NORMAL</desc-tp-calculo>
	 */

	private static final long serialVersionUID = -6131500264540525034L;
	
	private Integer codSucursal;
	private Integer codRamo;
	private Integer numPoliza;
	private String codNacionalidad;
	private String codDocumento;
	private String descCliente;
	private String descEstado;
	private String descripMoneda;
	private String descTipoNegocio;
	private String descripTipoFacturacion;
	private String feAmortizacion;
	private Integer nuRefactEfect;
	private Integer nuRefactPerm;
	private Integer numPolizaAnterior;
	private String codVigencia;
	private String descripVigencia;
	private String fechaDesde;
	private String fechaHasta;
	private String codVigenciaTecnica;
	private String descripVigenciaTecnica;
	private String fechaDesdeVigenciaTecnica;
	private String fechaHastaVigenciaTecnica;
	private Integer codMedioPago;
	private Integer diaVencimiento;
	private String fechaHastaVigencia;
	private String codTpCalculo;
	private String descTpCalculo;

	
	
	
	
	public String getCodTpCalculo() {
		return codTpCalculo;
	}
	public void setCodTpCalculo(String codTpCalculo) {
		this.codTpCalculo = codTpCalculo;
	}
	public String getDescTpCalculo() {
		return descTpCalculo;
	}
	public void setDescTpCalculo(String descTpCalculo) {
		this.descTpCalculo = descTpCalculo;
	}
	public Integer getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
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
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getCodDocumento() {
		return codDocumento;
	}
	public void setCodDocumento(String codDocumento) {
		this.codDocumento = codDocumento;
	}
	public String getDescCliente() {
		return descCliente;
	}
	public void setDescCliente(String descCliente) {
		this.descCliente = descCliente;
	}
	public String getDescEstado() {
		return descEstado;
	}
	public void setDescEstado(String descEstado) {
		this.descEstado = descEstado;
	}
	
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public String getDescTipoNegocio() {
		return descTipoNegocio;
	}
	public void setDescTipoNegocio(String descTipoNegocio) {
		this.descTipoNegocio = descTipoNegocio;
	}
	public String getDescripTipoFacturacion() {
		return descripTipoFacturacion;
	}
	public void setDescripTipoFacturacion(String descripTipoFacturacion) {
		this.descripTipoFacturacion = descripTipoFacturacion;
	}
	public String getFeAmortizacion() {
		return feAmortizacion;
	}
	public void setFeAmortizacion(String feAmortizacion) {
		this.feAmortizacion = feAmortizacion;
	}
	public Integer getNuRefactEfect() {
		return nuRefactEfect;
	}
	public void setNuRefactEfect(Integer nuRefactEfect) {
		this.nuRefactEfect = nuRefactEfect;
	}
	public Integer getNuRefactPerm() {
		return nuRefactPerm;
	}
	public void setNuRefactPerm(Integer nuRefactPerm) {
		this.nuRefactPerm = nuRefactPerm;
	}

	
	public Integer getNumPolizaAnterior() {
		return numPolizaAnterior;
	}
	public void setNumPolizaAnterior(Integer numPolizaAnterior) {
		this.numPolizaAnterior = numPolizaAnterior;
	}
	
	public String getCodVigencia() {
		return codVigencia;
	}
	public void setCodVigencia(String codVigencia) {
		this.codVigencia = codVigencia;
	}
	public String getDescripVigencia() {
		return descripVigencia;
	}
	public void setDescripVigencia(String descripVigencia) {
		this.descripVigencia = descripVigencia;
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
	public String getCodVigenciaTecnica() {
		return codVigenciaTecnica;
	}
	public void setCodVigenciaTecnica(String codVigenciaTecnica) {
		this.codVigenciaTecnica = codVigenciaTecnica;
	}
	public String getDescripVigenciaTecnica() {
		return descripVigenciaTecnica;
	}
	public void setDescripVigenciaTecnica(String descripVigenciaTecnica) {
		this.descripVigenciaTecnica = descripVigenciaTecnica;
	}
	public String getFechaDesdeVigenciaTecnica() {
		return fechaDesdeVigenciaTecnica;
	}
	public void setFechaDesdeVigenciaTecnica(String fechaDesdeVigenciaTecnica) {
		this.fechaDesdeVigenciaTecnica = fechaDesdeVigenciaTecnica;
	}
	public String getFechaHastaVigenciaTecnica() {
		return fechaHastaVigenciaTecnica;
	}
	public void setFechaHastaVigenciaTecnica(String fechaHastaVigenciaTecnica) {
		this.fechaHastaVigenciaTecnica = fechaHastaVigenciaTecnica;
	}
	
	
	public Integer getCodMedioPago() {
		return codMedioPago;
	}
	public void setCodMedioPago(Integer codMedioPago) {
		this.codMedioPago = codMedioPago;
	}
	

	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public String getFechaHastaVigencia() {
		return fechaHastaVigencia;
	}
	public void setFechaHastaVigencia(String fechaHastaVigencia) {
		this.fechaHastaVigencia = fechaHastaVigencia;
	}




	
	
}
