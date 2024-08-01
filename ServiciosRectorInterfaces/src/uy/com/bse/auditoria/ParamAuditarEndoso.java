package uy.com.bse.auditoria;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAuditarEndoso extends ParamGenerico {

	private static final long serialVersionUID = 4765749693113761088L;

	private Integer numCotizacion;
	private Integer codRamo;
	
	private Integer numPoliza;
	private Integer numEndoso;
	private String accion;
	private String detalle;
	
	public ParamAuditarEndoso() {
		super();
	}
	
	
	public ParamAuditarEndoso(String usuario, String clave, Integer numCotizacion, Integer codRamo, Integer numPoliza, Integer numEndoso, String accion, String detalle) {
		super();
		this.numCotizacion = numCotizacion;
		this.codRamo = codRamo;
		this.numPoliza = numPoliza;
		this.numEndoso = numEndoso;
		this.accion = accion;
		this.detalle = detalle;
		this.setUsuario(usuario);
		this.setClave(clave);
	}



	public Integer getNumCotizacion() {
		return numCotizacion;
	}
	public void setNumCotizacion(Integer numCotizacion) {
		this.numCotizacion = numCotizacion;
	}
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
	public Integer getNumEndoso() {
		return numEndoso;
	}
	public void setNumEndoso(Integer numEndoso) {
		this.numEndoso = numEndoso;
	}
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	public String getDetalle() {
		return detalle;
	}
	public void setDetalle(String detalle) {
		this.detalle = detalle;
	}

}
