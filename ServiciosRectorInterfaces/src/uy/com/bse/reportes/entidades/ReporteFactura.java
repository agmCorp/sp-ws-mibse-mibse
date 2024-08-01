package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteFactura implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;
	private Integer numPoliza;
	private Integer codRamo;
	private Integer certificado;
	private String razonSocial;
	private Integer moneda;
	private Double importe;
	private String vencimiento;
	private String productor;
	private String factura;
	private Integer numCuota;
	private String producto;
	private String medioPago;
	
	
	
	
	public ReporteFactura(Integer numPoliza, Integer codRamo, Integer certificado, String razonSocial, Integer moneda, Double importe, String vencimiento, String productor, String factura,
			Integer numCuota, String producto, String medioPago) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.certificado = certificado;
		this.razonSocial = razonSocial;
		this.moneda = moneda;
		this.importe = importe;
		this.vencimiento = vencimiento;
		this.productor = productor;
		this.factura = factura;
		this.numCuota = numCuota;
		this.producto = producto;
		this.medioPago = medioPago;
	}
	public ReporteFactura(Integer numPoliza, Integer codRamo, Integer certificado, String razonSocial, Integer moneda, Double importe, String vencimiento, String productor, String factura) {
		super();
		this.numPoliza = numPoliza;
		this.codRamo = codRamo;
		this.certificado = certificado;
		this.razonSocial = razonSocial;
		this.moneda = moneda;
		this.importe = importe;
		this.vencimiento = vencimiento;
		this.productor = productor;
		this.factura = factura;
	}
	public ReporteFactura() {
		super();
	}
	public Integer getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}
	public Integer getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}
	public Integer getCertificado() {
		return certificado;
	}
	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Integer getMoneda() {
		return moneda;
	}
	public void setMoneda(Integer moneda) {
		this.moneda = moneda;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public String getVencimiento() {
		return vencimiento;
	}
	public void setVencimiento(String vencimiento) {
		this.vencimiento = vencimiento;
	}
	public String getProductor() {
		return productor;
	}
	public void setProductor(String productor) {
		this.productor = productor;
	}
	public String getFactura() {
		return factura;
	}
	public void setFactura(String factura) {
		this.factura = factura;
	}
	public Integer getNumCuota() {
		return numCuota;
	}
	public void setNumCuota(Integer numCuota) {
		this.numCuota = numCuota;
	}
	public String getProducto() {
		return producto;
	}
	public void setProducto(String producto) {
		this.producto = producto;
	}
	public String getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(String medioPago) {
		this.medioPago = medioPago;
	}
}
