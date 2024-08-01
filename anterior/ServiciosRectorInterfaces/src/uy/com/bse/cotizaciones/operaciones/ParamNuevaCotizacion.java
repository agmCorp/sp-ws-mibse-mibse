package uy.com.bse.cotizaciones.operaciones;

import javax.xml.bind.annotation.XmlElement;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamNuevaCotizacion extends ParamGenerico{
	
	private static final long serialVersionUID = 8432584486526494515L;
	private Integer codRamo;
	private String codProducto;
	private String tipoFacturacion;
	private String codMoneda;
	private Integer medioPago;
	private String origenPago;
	private String codPromocion;
	private String renovacion;
	private String tipoCalculo;
	private String vigencia;
	private String fechaDesdeVigencia;
	private String fechaHastaVigencia;
	private String tipoVigenciaTecnica;
	private String anulacionCorrida;
	private String codClienteContratante;
	private Integer codDireccionCobro;
	private Integer codDireccionEnvio;
	private String codProductor;
	private Boolean enviarFacturaEmail;
	private Boolean emitirConRUT;
	
	public Integer getTipoCopia() {
		return tipoCopia;
	}
	public void setTipoCopia(Integer tipoCopia) {
		this.tipoCopia = tipoCopia;
	}
	public Integer getNumACopiar() {
		return numACopiar;
	}
	public void setNumACopiar(Integer numACopiar) {
		this.numACopiar = numACopiar;
	}

	private String codClienteAsegurado;
	private Integer tipoCopia;
	private Integer numACopiar;

	
	@XmlElement(nillable=true, required=true)
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}	
	@XmlElement(nillable=true, required=true)
	public String getCodProducto() {
		return codProducto;
	}
	public void setCodProducto(String codProducto) {
		this.codProducto = codProducto;
	}
	@XmlElement(nillable=true, required=true)
	public String getTipoFacturacion() {
		return tipoFacturacion;
	}
	public void setTipoFacturacion(String tipoFacturacion) {
		this.tipoFacturacion = tipoFacturacion;
	}
	@XmlElement(nillable=true, required=true)
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	@XmlElement(nillable=true, required=true)
	public Integer getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(Integer medioPago) {
		this.medioPago = medioPago;
	}
	@XmlElement(nillable=true, required=true)
	public String getOrigenPago() {
		return origenPago;
	}
	public void setOrigenPago(String origenPago) {
		this.origenPago = origenPago;
	}
	@XmlElement(nillable=true, required=true)
	public String getCodPromocion() {
		return codPromocion;
	}
	public void setCodPromocion(String codPromocion) {
		this.codPromocion = codPromocion;
	}
	@XmlElement(nillable=true, required=true)
	public String getRenovacion() {
		return renovacion;
	}
	public void setRenovacion(String renovacion) {
		this.renovacion = renovacion;
	}
	@XmlElement(nillable=true, required=true)
	public String getTipoCalculo() {
		return tipoCalculo;
	}
	public void setTipoCalculo(String tipoCalculo) {
		this.tipoCalculo = tipoCalculo;
	}
	@XmlElement(nillable=true, required=true)
	public String getVigencia() {
		return vigencia;
	}
	public void setVigencia(String vigencia) {
		this.vigencia = vigencia;
	}
	@XmlElement(nillable=true, required=true)
	public String getFechaDesdeVigencia() {
		return fechaDesdeVigencia;
	}
	public void setFechaDesdeVigencia(String fechaDesdeVigencia) {
		this.fechaDesdeVigencia = fechaDesdeVigencia;
	}
	@XmlElement(nillable=true, required=true)
	public String getFechaHastaVigencia() {
		return fechaHastaVigencia;
	}
	public void setFechaHastaVigencia(String fechaHastaVigencia) {
		this.fechaHastaVigencia = fechaHastaVigencia;
	}
	@XmlElement(nillable=true, required=true)
	public String getTipoVigenciaTecnica() {
		return tipoVigenciaTecnica;
	}
	public void setTipoVigenciaTecnica(String tipoVigenciaTecnica) {
		this.tipoVigenciaTecnica = tipoVigenciaTecnica;
	}
	@XmlElement(nillable=true, required=true)
	public String getAnulacionCorrida() {
		return anulacionCorrida;
	}
	public void setAnulacionCorrida(String anulacionCorrida) {
		this.anulacionCorrida = anulacionCorrida;
	}
	@XmlElement(nillable=true, required=true)
	public String getCodClienteContratante() {
		return codClienteContratante;
	}
	public void setCodClienteContratante(String codClienteContratante) {
		this.codClienteContratante = codClienteContratante;
	}
	@XmlElement(nillable=true, required=true)
	public String getCodClienteAsegurado() {
		return codClienteAsegurado;
	}
	public void setCodClienteAsegurado(String codClienteAsegurado) {
		this.codClienteAsegurado = codClienteAsegurado;
	}
	public Integer getCodDireccionCobro() {
		return codDireccionCobro;
	}
	public void setCodDireccionCobro(Integer codDireccionCobro) {
		this.codDireccionCobro = codDireccionCobro;
	}
	public Integer getCodDireccionEnvio() {
		return codDireccionEnvio;
	}
	public void setCodDireccionEnvio(Integer codDireccionEnvio) {
		this.codDireccionEnvio = codDireccionEnvio;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}
	public Boolean getEnviarFacturaEmail() {
		return enviarFacturaEmail;
	}
	public void setEnviarFacturaEmail(Boolean enviarFacturaEmail) {
		this.enviarFacturaEmail = enviarFacturaEmail;
	}
	public Boolean getEmitirConRUT() {
		return emitirConRUT;
	}
	public void setEmitirConRUT(Boolean emitirConRUT) {
		this.emitirConRUT = emitirConRUT;
	}
	
}
