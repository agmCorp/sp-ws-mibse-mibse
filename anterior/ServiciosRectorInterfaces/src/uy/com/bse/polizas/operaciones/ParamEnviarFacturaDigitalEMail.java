package uy.com.bse.polizas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamEnviarFacturaDigitalEMail extends ParamGenerico {
	private static final long serialVersionUID = 5099073693700177158L;
	private Integer codRamo;
	private Integer numPoliza;
	private Integer consecutivo;
	private String comunicacion;
	private String enviarFacturaDigitalEMail;

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

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(String comunicacion) {
		this.comunicacion = comunicacion;
	}

	public String getEnviarFacturaDigitalEMail() {
		return enviarFacturaDigitalEMail;
	}

	public void setEnviarFacturaDigitalEMail(String enviarFacturaEMail) {
		this.enviarFacturaDigitalEMail = enviarFacturaEMail;
	}	

}
