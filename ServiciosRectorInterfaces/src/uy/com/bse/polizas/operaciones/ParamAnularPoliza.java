package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAnularPoliza extends ParamGenerico{
	private String numPoliza;
	private String codRamo;
	private String numCertificado;
	private String numCotizacion;
	private String codMotivoAbandono;
	private String aclaracion;
	
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
	public String getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(String numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
	public String getCodMotivoAbandono() {
		return codMotivoAbandono;
	}
	public void setCodMotivoAbandono(String codMotivoAbandono) {
		this.codMotivoAbandono = codMotivoAbandono;
	}
	public String getAclaracion() {
		return aclaracion;
	}
	public void setAclaracion(String aclaracion) {
		this.aclaracion = aclaracion;
	}
	

}
