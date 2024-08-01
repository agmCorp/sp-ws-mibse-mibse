package uy.com.bse.polizas.entidades;

import java.io.Serializable;

public class Factura implements Serializable{
	private static final long serialVersionUID = -3257913804793177880L;
	private String numFacturaElectronica;
	private Integer numFactura;
	private String fechaEmisionFactura;
	private String fechaVto;
	private String fechaSegundoVto;
	private String descripEstadoFactura; 
	private String fechaPago;
	private Double totalFactura;
	private Double totalImporte;
	private Double importe;
	private Double recargoFinanciero;
	private Double punitario;
	private Double iva;
	private Double cantOtros;
	private String aplicada;
	private String documentId;
	private String permiteImprimir;
	
	public String getNumFacturaElectronica() {
		return numFacturaElectronica;
	}
	public void setNumFacturaElectronica(String numFacturaElectronica) {
		this.numFacturaElectronica = numFacturaElectronica;
	}
	public Integer getNumFactura() {
		return numFactura;
	}
	public void setNumFactura(Integer numFactura) {
		this.numFactura = numFactura;
	}
	public String getFechaEmisionFactura() {
		return fechaEmisionFactura;
	}
	public void setFechaEmisionFactura(String fechaEmisionFactura) {
		this.fechaEmisionFactura = fechaEmisionFactura;
	}
	public String getFechaVto() {
		return fechaVto;
	}
	public void setFechaVto(String fechaVto) {
		this.fechaVto = fechaVto;
	}
	public String getFechaSegundoVto() {
		return fechaSegundoVto;
	}
	public void setFechaSegundoVto(String fechaSegundoVto) {
		this.fechaSegundoVto = fechaSegundoVto;
	}
	public String getDescripEstadoFactura() {
		return descripEstadoFactura;
	}
	public void setDescripEstadoFactura(String descripEstadoFactura) {
		this.descripEstadoFactura = descripEstadoFactura;
	}
	public String getFechaPago() {
		return fechaPago;
	}
	public void setFechaPago(String fechaPago) {
		this.fechaPago = fechaPago;
	}
	public Double getTotalFactura() {
		return totalFactura;
	}
	public void setTotalFactura(Double totalFactura) {
		this.totalFactura = totalFactura;
	}
	public Double getTotalImporte() {
		return totalImporte;
	}
	public void setTotalImporte(Double totalImporte) {
		this.totalImporte = totalImporte;
	}
	public Double getImporte() {
		return importe;
	}
	public void setImporte(Double importe) {
		this.importe = importe;
	}
	public Double getRecargoFinanciero() {
		return recargoFinanciero;
	}
	public void setRecargoFinanciero(Double recargoFinanciero) {
		this.recargoFinanciero = recargoFinanciero;
	}
	public Double getPunitario() {
		return punitario;
	}
	public void setPunitario(Double punitario) {
		this.punitario = punitario;
	}
	public Double getIva() {
		return iva;
	}
	public void setIva(Double iva) {
		this.iva = iva;
	}
	public Double getCantOtros() {
		return cantOtros;
	}
	public void setCantOtros(Double cantOtros) {
		this.cantOtros = cantOtros;
	}
	public String getAplicada() {
		return aplicada;
	}
	public void setAplicada(String aplicada) {
		this.aplicada = aplicada;
	}
	public String getDocumentId() {
		return documentId;
	}
	public void setDocumentId(String documentId) {
		this.documentId = documentId;
	}
	public String getPermiteImprimir() {
		return permiteImprimir;
	}
	public void setPermiteImprimir(String permiteImprimir) {
		this.permiteImprimir = permiteImprimir;
	}

}
