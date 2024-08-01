package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultValidarPoliza extends ResultGenerico{
	private String tieneFacultativo;
	private String datosRelevantes;
	private Integer numCertificado;
	private ValidacionGenerica confirmacion;

	public String getTieneFacultativo() {
		return tieneFacultativo;
	}

	public void setTieneFacultativo(String tieneFacultativo) {
		this.tieneFacultativo = tieneFacultativo;
	}

	public Integer getNumCertificado() {
		return numCertificado;
	}

	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}

	public ValidacionGenerica getConfirmacion() {
		return confirmacion;
	}

	public void setConfirmacion(ValidacionGenerica confirmacion) {
		this.confirmacion = confirmacion;
	}

	public String getDatosRelevantes() {
		return datosRelevantes;
	}

	public void setDatosRelevantes(String datosRelevantes) {
		this.datosRelevantes = datosRelevantes;
	}

	
	
}
