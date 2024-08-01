package uy.com.bse.vehiculos;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamInformarDatosVehiculo extends ParamGenerico {
	private static final long serialVersionUID = 6710563983995644009L;
	
	private String celular;
	private Integer nroPoliza;
	private Integer certificado;
	private String matricula;
	private String padron;

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public Integer getNroPoliza() {
		return nroPoliza;
	}

	public void setNroPoliza(Integer nroPoliza) {
		this.nroPoliza = nroPoliza;
	}

	public Integer getCertificado() {
		return certificado;
	}

	public void setCertificado(Integer certificado) {
		this.certificado = certificado;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getPadron() {
		return padron;
	}

	public void setPadron(String padron) {
		this.padron = padron;
	}
}
