package uy.com.bse.recuotificacion;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.BienCert;
import uy.com.bse.cotizaciones.entidades.DatosBanco;
import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamModificacionSinPremio extends ParamGenerico{
	
	private static final long serialVersionUID = -5316636338301330280L;
	private String numRamo; 
	private String numPoliza;
	private String numCertificado;
	private String numEndoso;
	private String codOrigenEndoso;
	private String fechaDesde;
	private Integer codMedioPago;
	private String codOrigenPago;
	
	public String getCodOrigenPago() {
		return codOrigenPago;
	}
	public void setCodOrigenPago(String codOrigenPago) {
		this.codOrigenPago = codOrigenPago;
	}
	private DatosBanco datosBancarios;
	
	private ArrayList<BienCert> bienes;
	
	
	public String getNumRamo() {
		return numRamo;
	}
	public void setNumRamo(String numRamo) {
		this.numRamo = numRamo;
	}
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(String numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getFechaDesde() {
		return fechaDesde;
	}
	public String getCodOrigenEndoso() {
		return codOrigenEndoso;
	}
	public void setCodOrigenEndoso(String codOrigenEndoso) {
		this.codOrigenEndoso = codOrigenEndoso;
	}
	public void setFechaDesde(String fechaDesde) {
		this.fechaDesde = fechaDesde;
	}
	public DatosBanco getDatosBancarios() {
		return datosBancarios;
	}
	public void setDatosBancarios(DatosBanco datosBancarios) {
		this.datosBancarios = datosBancarios;
	}
	public ArrayList<BienCert> getBienes() {
		return bienes;
	}
	public void setBienes(ArrayList<BienCert> bienes) {
		this.bienes = bienes;
	}
	public Integer getCodMedioPago() {
		return codMedioPago;
	}
	public void setCodMedioPago(Integer codMedioPago) {
		this.codMedioPago = codMedioPago;
	}


}
