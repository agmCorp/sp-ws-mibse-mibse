package uy.com.bse.cotizaciones.consultas;

import java.io.Serializable;

public class DatoParametrico implements Serializable{
	private Integer consecutivo;
	private String bienDesc;
	private Integer certificado;
	private String datoValor;
	private String asegurado;
	private String estado;
	private String datoDesc;
	
	public Integer getConsecutivo() {
		return consecutivo;
	}
	public void setConsecutivo(Integer consecutivo) {
		this.consecutivo = consecutivo;
	}
	public String getBienDesc() {
		return bienDesc;
	}
	public void setBienDesc(String bienDesc) {
		this.bienDesc = bienDesc;
	}
	public Integer getCertificado() {
		return certificado;
	}
	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}
	public String getDatoValor() {
		return datoValor;
	}
	public void setDatoValor(String datoValor) {
		this.datoValor = datoValor;
	}
	public String getAsegurado() {
		return asegurado;
	}
	public void setAsegurado(String asegurado) {
		this.asegurado = asegurado;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getDatoDesc() {
		return datoDesc;
	}
	public void setDatoDesc(String datoDesc) {
		this.datoDesc = datoDesc;
	}
}
