package uy.com.bse.auditoria;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAuditarConsulta extends ParamGenerico {

	private static final long serialVersionUID = 4765749693113761088L;

	private String accion;
	private String detalle;

	public ParamAuditarConsulta() {
		super();
	}

	public ParamAuditarConsulta(String usuario, String clave, String accion, String detalle) {
		super();

		this.accion = accion;
		this.detalle = detalle;
		this.setUsuario(usuario);
		this.setClave(clave);
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
