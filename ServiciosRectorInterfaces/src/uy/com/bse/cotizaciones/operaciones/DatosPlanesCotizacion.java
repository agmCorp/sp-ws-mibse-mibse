package uy.com.bse.cotizaciones.operaciones;

import java.io.Serializable;


public class DatosPlanesCotizacion implements Serializable{

	private Integer numCertificado;
	private String codPlanCobertura;
	private String codPlanPago;
	
	public Integer getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public String getCodPlanPago() {
		return codPlanPago;
	}
	public void setCodPlanPago(String codPlanPago) {
		this.codPlanPago = codPlanPago;
	}
		
}
