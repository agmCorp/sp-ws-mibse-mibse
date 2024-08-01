package uy.com.bse.cotizaciones.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamGuardarCoberturas extends ParamGenerico {
	
	String numCotizacion;
	String numCertificado;
	String codPlanCobertura;
	String listarCoberturas;
	
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getCodPlanCobertura() {
		return codPlanCobertura;
	}
	public void setCodPlanCobertura(String codPlanCobertura) {
		this.codPlanCobertura = codPlanCobertura;
	}
	public String getListarCoberturas() {
		return listarCoberturas;
	}
	public void setListarCoberturas(String listarCoberturas) {
		this.listarCoberturas = listarCoberturas;
	}

	
}
