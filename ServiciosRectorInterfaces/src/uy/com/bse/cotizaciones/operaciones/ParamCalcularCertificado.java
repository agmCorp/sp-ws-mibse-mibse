package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamCalcularCertificado extends ParamGenerico{
	
	private Integer numCotizacion;
	private Integer numCertificado;
	private String planCobertura;
	private String planPago;
	private Integer diaVto;
	private String requeridoPlanesCobertura;
	private String codNivelComisionProductor;
	private String codNivelComisionBroker;
	
	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getPlanCobertura() {
		return planCobertura;
	}
	public void setPlanCobertura(String planCobertura) {
		this.planCobertura = planCobertura;
	}

	public String getPlanPago() {
		return planPago;
	}
	public void setPlanPago(String planPago) {
		this.planPago = planPago;
	}
	public Integer getDiaVto() {
		return diaVto;
	}
	public void setDiaVto(Integer diaVto) {
		this.diaVto = diaVto;
	}
	public String getRequeridoPlanesCobertura() {
		return requeridoPlanesCobertura;
	}
	public void setRequeridoPlanesCobertura(String requeridoPlanesCobertura) {
		this.requeridoPlanesCobertura = requeridoPlanesCobertura;
	}
	public String getCodNivelComisionProductor() {
		return codNivelComisionProductor;
	}
	public void setCodNivelComisionProductor(String codNivelComisionProductor) {
		this.codNivelComisionProductor = codNivelComisionProductor;
	}
	public String getCodNivelComisionBroker() {
		return codNivelComisionBroker;
	}
	public void setCodNivelComisionBroker(String codNivelComisionBroker) {
		this.codNivelComisionBroker = codNivelComisionBroker;
	}
		

}
