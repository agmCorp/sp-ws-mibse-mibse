package uy.com.bse.usuarios.entidades;

import java.io.Serializable;

public class UsuarioConsultaAuditoriaDetalle implements Serializable{
	private String programa;
	private String usuarioEmpleado;
	private String fechaLog;
	private String tipoDeAccion;
	private String ramo;
	private String poliza;
	private String certificado;
	private String endoso;
	private String persona;
	private String datosAmpliados;
	private String cotizacion;
	private String consecutivo;
	private String usuarioProductor;
	private String alcance;

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getUsuarioEmpleado() {
		return usuarioEmpleado;
	}

	public void setUsuarioEmpleado(String usuarioEmpleado) {
		this.usuarioEmpleado = usuarioEmpleado;
	}

	public String getFechaLog() {
		return fechaLog;
	}

	public void setFechaLog(String fechaLog) {
		this.fechaLog = fechaLog;
	}

	public String getTipoDeAccion() {
		return tipoDeAccion;
	}

	public void setTipoDeAccion(String tipoDeAccion) {
		this.tipoDeAccion = tipoDeAccion;
	}

	public String getRamo() {
		return ramo;
	}

	public void setRamo(String ramo) {
		this.ramo = ramo;
	}

	public String getPoliza() {
		return poliza;
	}

	public void setPoliza(String poliza) {
		this.poliza = poliza;
	}

	public String getCertificado() {
		return certificado;
	}

	public void setCertificado(String certificado) {
		this.certificado = certificado;
	}

	public String getEndoso() {
		return endoso;
	}

	public void setEndoso(String endoso) {
		this.endoso = endoso;
	}

	public String getPersona() {
		return persona;
	}

	public void setPersona(String persona) {
		this.persona = persona;
	}

	public String getDatosAmpliados() {
		return datosAmpliados;
	}

	public void setDatosAmpliados(String datosAmpliados) {
		this.datosAmpliados = datosAmpliados;
	}

	public String getCotizacion() {
		return cotizacion;
	}

	public void setCotizacion(String cotizacion) {
		this.cotizacion = cotizacion;
	}

	public String getConsecutivo() {
		return consecutivo;
	}

	public void setConsecutivo(String consecutivo) {
		this.consecutivo = consecutivo;
	}

	public String getUsuarioProductor() {
		return usuarioProductor;
	}

	public void setUsuarioProductor(String usuarioProductor) {
		this.usuarioProductor = usuarioProductor;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}
}
