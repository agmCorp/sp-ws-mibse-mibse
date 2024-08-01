package uy.com.bse.consultas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerNominasVida extends ParamGenerico {

	private static final long serialVersionUID = 3445420378005612839L;
     private Integer numNomina;
     private Integer numCertificado;
     private String tipoIntegrante;
     private String vigente;
     private Integer columnaOrden;
     private String primerNombre;
     private String primerApellido;
     private String numDocumento;
     private String numDocumentoRelacionado;
	

	public void setNumNomina(Integer numNomina) {
		this.numNomina = numNomina;
	}

	public void setNumCertificado(Integer numCertificado) {
		this.numCertificado = numCertificado;
	}

	public void setTipoIntegrante(String tipoIntegrante) {
		this.tipoIntegrante = tipoIntegrante;
	}

	public void setVigente(String vigente) {
		this.vigente = vigente;
	}

	public void setColumnaOrden(Integer columnaOrden) {
		this.columnaOrden = columnaOrden;
	}

	public Integer getNumNomina() {
		return numNomina;
	}

	public Integer getNumCertificado() {
		return numCertificado;
	}

	public String getTipoIntegrante() {
		return tipoIntegrante;
	}

	public String getVigente() {
		return vigente;
	}

	public Integer getColumnaOrden() {
		return columnaOrden;
	}

	public String getPrimerNombre() {
		return primerNombre;
	}

	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getNumDocumento() {
		return numDocumento;
	}

	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}

	public String getNumDocumentoRelacionado() {
		return numDocumentoRelacionado;
	}

	public void setNumDocumentoRelacionado(String numDocumentoRelacionado) {
		this.numDocumentoRelacionado = numDocumentoRelacionado;
	}
	
	
}
