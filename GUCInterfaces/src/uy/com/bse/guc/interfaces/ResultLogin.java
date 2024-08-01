package uy.com.bse.guc.interfaces;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultLogin extends ResultGenerico {

	private static final long serialVersionUID = 7719046131083476560L;
	private String usuarioAutenticado;
	private Boolean autenticado;
	private String mensajeInformacion;

	public String getUsuarioAutenticado() {
		return usuarioAutenticado;
	}

	public void setUsuarioAutenticado(String usuarioAutenticado) {
		this.usuarioAutenticado = usuarioAutenticado;
	}

	public Boolean getAutenticado() {
		return autenticado;
	}

	public void setAutenticado(Boolean autenticado) {
		this.autenticado = autenticado;
	}

	public String getMensajeInformacion() {
		return mensajeInformacion;
	}

	public void setMensajeInformacion(String mensajeInformacion) {
		this.mensajeInformacion = mensajeInformacion;
	}

}
