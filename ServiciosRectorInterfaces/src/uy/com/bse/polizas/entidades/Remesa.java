package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Remesa implements Serializable{
	private static final long serialVersionUID = 8048497151873249456L;
	private Integer numEndoso;
	private Integer numRemesa;
	private String fechaIngreso;
	private Double montoOriginal;
	private Double montoSaldo;
	private String codEstado;
	private String descripEstado;
	private String tipoRemesa;
	private Integer codMedioPago;
	private String descripMedioPago;
	private String codOrigen;
	private String descripOrigen;
	private String codMoneda;
	private String descripMoneda;
	private Double montoTotal;
	private Integer codOrigenTipo;                 
	private String descripTipoRemesa; 
	private Integer codProductor; 
	private String productor; 
	private String codNacionalidad; 
	private String codCliente; 
	private String cliente; 
	private Integer codCobrador; 
	private String descripCobrador; 
	private Double montoConversionTotal; 
	private Double montoConversionSaldo; 
	private Integer codSucursal; 
	private String descripSucursal; 
	private String codUsuario; 
	private String usuario; 
	private String fechaActualizacion; 
	private Integer numSecuencia;
	private Integer numIngreso;
	private String documentId;
	private Integer codCompania;
	private Integer codBroker;
	private String descBroker;
	
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getNumRemesa() {
		return numRemesa;
	}
	public void setNumRemesa(Integer numRemesa) {
		this.numRemesa = numRemesa;
	}
	public String getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Double getMontoOriginal() {
		return montoOriginal;
	}
	public void setMontoOriginal(Double montoOriginal) {
		this.montoOriginal = montoOriginal;
	}
	public Double getMontoSaldo() {
		return montoSaldo;
	}
	public void setMontoSaldo(Double montoSaldo) {
		this.montoSaldo = montoSaldo;
	}
	public String getCodEstado() {
		return codEstado;
	}
	public void setCodEstado(String codEstado) {
		this.codEstado = codEstado;
	}
	public String getDescripEstado() {
		return descripEstado;
	}
	public void setDescripEstado(String descripEstado) {
		this.descripEstado = descripEstado;
	}
	public String getTipoRemesa() {
		return tipoRemesa;
	}
	public void setTipoRemesa(String tipoRemesa) {
		this.tipoRemesa = tipoRemesa;
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
	public String getCodOrigen() {
		return codOrigen;
	}
	public void setCodOrigen(String codOrigen) {
		this.codOrigen = codOrigen;
	}
	public String getDescripOrigen() {
		return descripOrigen;
	}
	public void setDescripOrigen(String descripOrigen) {
		this.descripOrigen = descripOrigen;
	}
	public String getCodMoneda() {
		return codMoneda;
	}
	public void setCodMoneda(String codMoneda) {
		this.codMoneda = codMoneda;
	}
	public String getDescripMoneda() {
		return descripMoneda;
	}
	public void setDescripMoneda(String descripMoneda) {
		this.descripMoneda = descripMoneda;
	}
	public Double getMontoTotal() {
		return montoTotal;
	}
	public void setMontoTotal(Double montoTotal) {
		this.montoTotal = montoTotal;
	}
	public Integer getCodOrigenTipo() {
		return codOrigenTipo;
	}
	public void setCodOrigenTipo(Integer codOrigenTipo) {
		this.codOrigenTipo = codOrigenTipo;
	}
	public String getDescripTipoRemesa() {
		return descripTipoRemesa;
	}
	public void setDescripTipoRemesa(String descripTipoRemesa) {
		this.descripTipoRemesa = descripTipoRemesa;
	}
	public Integer getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(Integer codProductor) {
		this.codProductor = codProductor;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getCodNacionalidad() {
		return codNacionalidad;
	}
	public void setCodNacionalidad(String codNacionalidad) {
		this.codNacionalidad = codNacionalidad;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public Integer getCodCobrador() {
		return codCobrador;
	}
	public void setCodCobrador(Integer codCobrador) {
		this.codCobrador = codCobrador;
	}
	public String getDescripCobrador() {
		return descripCobrador;
	}
	public void setDescripCobrador(String descripCobrador) {
		this.descripCobrador = descripCobrador;
	}
	public Double getMontoConversionTotal() {
		return montoConversionTotal;
	}
	public void setMontoConversionTotal(Double montoConversionTotal) {
		this.montoConversionTotal = montoConversionTotal;
	}
	public Double getMontoConversionSaldo() {
		return montoConversionSaldo;
	}
	public void setMontoConversionSaldo(Double montoConversionSaldo) {
		this.montoConversionSaldo = montoConversionSaldo;
	}
	public Integer getCodSucursal() {
		return codSucursal;
	}
	public void setCodSucursal(Integer codSucursal) {
		this.codSucursal = codSucursal;
	}
	public String getDescripSucursal() {
		return descripSucursal;
	}
	public void setDescripSucursal(String descripSucursal) {
		this.descripSucursal = descripSucursal;
	}
	public String getCodUsuario() {
		return codUsuario;
	}
	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public Integer getNumSecuencia() {
		return numSecuencia;
	}
	public void setNumSecuencia(Integer numSecuencia) {
		this.numSecuencia = numSecuencia;
	}
	public Integer getNumIngreso() {
		return numIngreso;
	}
	public void setNumIngreso(Integer numIngreso) {
		this.numIngreso = numIngreso;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	
	public Integer getCodCompania() {
		return codCompania;
	}
	public void setCodCompania(Integer codCompania) {
		this.codCompania = codCompania;
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
