package uy.com.bse.consultas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPolizasFlotantes extends ParamGenerico {

	private static final long serialVersionUID = 3445420378005612839L;
	private String nombreAsegurado;
	private Integer numPoliza;
	private Integer identificadorWeb;
	private String fechaEmbarque;
	private String identificadorEmbarque;
	private Integer numPolizaFactura;
	private String certNoFac;

	public String getCertNoFac() {
		return certNoFac;
	}

	public void setCertNoFac(String certNoFac) {
		this.certNoFac = certNoFac;
	}

	public void setNombreAsegurado(String nombre) {
		this.nombreAsegurado = nombre;
	}

	public void setNumPoliza(Integer nroContrato) {
		this.numPoliza = nroContrato;
	}

	public void setIdentificadorWeb(Integer nroWeb) {
		this.identificadorWeb = nroWeb;
	}


	public void setIdentificadorEmbarque(String idEmbarque) {
		this.identificadorEmbarque = idEmbarque;
	}

	public void setNumPolizaFactura(Integer polizaFactura) {
		this.numPolizaFactura = polizaFactura;
	}

	public String getFechaEmbarque() {
		return fechaEmbarque;
	}

	public void setFechaEmbarque(String fechaEmbarque) {
		this.fechaEmbarque = fechaEmbarque;
	}

	public String getNombreAsegurado() {
		return nombreAsegurado;
	}

	public Integer getNumPoliza() {
		return numPoliza;
	}

	public Integer getIdentificadorWeb() {
		return identificadorWeb;
	}

	public String getIdentificadorEmbarque() {
		return identificadorEmbarque;
	}

	public Integer getNumPolizaFactura() {
		return numPolizaFactura;
	}

}
