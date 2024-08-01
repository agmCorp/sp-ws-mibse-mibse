package uy.com.bse.servicios.rector.emision;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Cuota;

public class Emision implements Serializable{
	private Integer codSucursal;
	private Integer codRamo;
	private Integer numPoliza;
	private Integer numCertificado;
	private Integer numEndoso;
	private Integer diaVencimiento;
	private Double totalPrimaPura;
	private Double totalPremio;
	private Double totalPremioFacturacion;
	private String facturaElectronica;
	
	
	
	
	public String getFacturaElectronica() {
		return facturaElectronica;
	}
	public void setFacturaElectronica(String facturaElectronica) {
		this.facturaElectronica = facturaElectronica;
	}

	private ArrayList <Cuota> cuotas = new ArrayList<Cuota>();
	
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
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public Integer getDiaVencimiento() {
		return diaVencimiento;
	}
	public void setDiaVencimiento(Integer diaVencimiento) {
		this.diaVencimiento = diaVencimiento;
	}
	public Double getTotalPrimaPura() {
		return totalPrimaPura;
	}
	public void setTotalPrimaPura(Double totalPrimaPura) {
		this.totalPrimaPura = totalPrimaPura;
	}
	public Double getTotalPremio() {
		return totalPremio;
	}
	public void setTotalPremio(Double totalPremio) {
		this.totalPremio = totalPremio;
	}
	public Double getTotalPremioFacturacion() {
		return totalPremioFacturacion;
	}
	public void setTotalPremioFacturacion(Double totalPremioFacturacion) {
		this.totalPremioFacturacion = totalPremioFacturacion;
	}
	public ArrayList<Cuota> getCuotas() {
		return cuotas;
	}
	public void setCuotas(ArrayList<Cuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	public void setUnaCuota(Cuota item){
		this.cuotas.add(item);
	}

}
