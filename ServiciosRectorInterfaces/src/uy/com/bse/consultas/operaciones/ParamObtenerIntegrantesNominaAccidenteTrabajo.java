package uy.com.bse.consultas.operaciones;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerIntegrantesNominaAccidenteTrabajo extends ParamGenerico {

	private static final long serialVersionUID = 3445420378005612839L;
	
	private Integer codRamo;
	private Integer numPoliza;
	private String mesCargo;
	private String tipoDocumento;
	private String numDocumento;
	private String nombre;
	private String codProductor;
	
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
	public String getMesCargo() {
		return mesCargo;
	}
	public void setMesCargo(String mesCargo) {
		this.mesCargo = mesCargo;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(String numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCodProductor() {
		return codProductor;
	}
	public void setCodProductor(String codProductor) {
		this.codProductor = codProductor;
	}

}
