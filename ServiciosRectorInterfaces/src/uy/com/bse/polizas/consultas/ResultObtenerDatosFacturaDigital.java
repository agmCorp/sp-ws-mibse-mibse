package uy.com.bse.polizas.consultas;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDatosFacturaDigital extends ResultGenerico {
	private static final long serialVersionUID = 1L;
	private Integer consecutivo;
	private Integer codComunicacion;
	private String comunicacion;
	private String envioFacturaEMail;
	private String comValidada;

	public Integer getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}

	public Integer getCodComunicacion() {
		return codComunicacion;
	}

	public void setCodComunicacion(Integer codComunicacion) {
		this.codComunicacion = codComunicacion;
	}

	public String getComunicacion() {
		return comunicacion;
	}

	public void setComunicacion(String comunicacion) {
		this.comunicacion = comunicacion;
	}

	public String getEnvioFacturaEMail() {
		return envioFacturaEMail;
	}

	public void setEnvioFacturaEMail(String envioFacturaEMail) {
		this.envioFacturaEMail = envioFacturaEMail;
	}

	public String getComValidada() {
		return comValidada;
	}

	public void setComValidada(String comValidada) {
		this.comValidada = comValidada;
	}
}
