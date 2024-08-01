package uy.com.bse.reportes.entidades;

import java.io.Serializable;

public class ReporteLogsUsuariosProductor implements Serializable {
	private static final long serialVersionUID = -1516526199474765836L;

	
	private String datosAmpliados;
	private Integer numCotizacion;
	private Integer numPoliza;
	private Integer numCertificado;
	private Integer numConsecutivo;
	private Integer codRamo;
	private Integer numEndoso;
	private Integer numPersona;
	private String idLog;
	private String codUsuario;
	private String programa;
	private String tipoAccion;
	private String fechaHora;



	public ReporteLogsUsuariosProductor(String datosAmpliados, Integer numCotizacion, Integer numPoliza, Integer numCertificado, Integer numConsecutivo, Integer codRamo, Integer numEndoso,
			Integer numPersona, String idLog, String codUsuario, String programa, String tipoAccion, String fechaHora) {
		super();
		this.datosAmpliados = datosAmpliados;
		this.numCotizacion = numCotizacion;
		this.numPoliza = numPoliza;
		this.numCertificado = numCertificado;
		this.numConsecutivo = numConsecutivo;
		this.codRamo = codRamo;
		this.numEndoso = numEndoso;
		this.numPersona = numPersona;
		this.idLog = idLog;
		this.codUsuario = codUsuario;
		this.programa = programa;
		this.tipoAccion = tipoAccion;
		this.fechaHora = fechaHora;
	}

	public ReporteLogsUsuariosProductor() {
		super();
	}

	public String getDatosAmpliados() {
		return datosAmpliados;
	}

	public void setDatosAmpliados(String datosAmpliados) {
		this.datosAmpliados = datosAmpliados;
	}

	public Integer getNumCotizacion() {
		return numCotizacion;
	}

	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public void setNumPoliza(Integer numPoliza) {
		this.numPoliza = numPoliza;
	}

	public Integer getNumCertificado() {
		return numCertificado;
	}

	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}

	public Integer getNumConsecutivo() {
		return numConsecutivo;
	}

	public void setNumConsecutivo(Integer numConsecutivo) {
		this.numConsecutivo = numConsecutivo;
	}

	public Integer getCodRamo() {
		return codRamo;
	}

	public void setCodRamo(Integer codRamo) {
		this.codRamo = codRamo;
	}

	public Integer getNumEndoso() {
		return numEndoso;
	}

	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}

	public Integer getNumPersona() {
		return numPersona;
	}

	public void setNumPersona(Integer numPersona) {
		this.numPersona = numPersona;
	}

	public String getIdLog() {
		return idLog;
	}

	public void setIdLog(String idLog) {
		this.idLog = idLog;
	}

	public String getCodUsuario() {
		return codUsuario;
	}

	public void setCodUsuario(String codUsuario) {
		this.codUsuario = codUsuario;
	}

	public String getPrograma() {
		return programa;
	}

	public void setPrograma(String programa) {
		this.programa = programa;
	}

	public String getTipoAccion() {
		return tipoAccion;
	}

	public void setTipoAccion(String tipoAccion) {
		this.tipoAccion = tipoAccion;
	}

	public String getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(String fechaHora) {
		this.fechaHora = fechaHora;
	}

}
