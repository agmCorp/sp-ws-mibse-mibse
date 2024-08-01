package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamValidarAnulacionPoliza extends ParamGenerico{
	private String numPoliza;
	private String codRamo;
	private String numCertificado;
	private String tipoAnulacion;
	private String fechaAnulacion;
	private String tieneFacultativo;
	
	public String getNumPoliza() {
		return numPoliza;
	}
	public void setNumPoliza(String numPoliza) {
		this.numPoliza = numPoliza;
	}
	public String getCodRamo() {
		return codRamo;
	}
	public void setCodRamo(String codRamo) {
		this.codRamo = codRamo;
	}
	public String getNumCertificado() {
		return numCertificado;
	}
	public void setNumCertificado(String numCertificado) {
		this.numCertificado = numCertificado;
	}
	public String getTipoAnulacion() {
		return tipoAnulacion;
	}
	public void setTipoAnulacion(String tipoAnulacion) {
		this.tipoAnulacion = tipoAnulacion;
	}
	public String getFechaAnulacion() {
		return fechaAnulacion;
	}
	public void setFechaAnulacion(String fechaAnulacion) {
		this.fechaAnulacion = fechaAnulacion;
	}
	public String getTieneFacultativo() {
		return tieneFacultativo;
	}
	public void setTieneFacultativo(String tieneFacultativo) {
		this.tieneFacultativo = tieneFacultativo;
	}

		
}
