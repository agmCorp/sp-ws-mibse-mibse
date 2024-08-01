package uy.com.bse.dnic;

import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamObtenerPersonaPorDoc extends ParamGenerico {

	private static final long serialVersionUID = -1997718709703540643L;
	private String numeroDeDocumento;
	private String tipoDeDocumento;
	private String usuario;
	private String aplicacion;

	public String getNumeroDeDocumento() {
		return numeroDeDocumento;
	}

	public void setNumeroDeDocumento(String numeroDeDocumento) {
		this.numeroDeDocumento = numeroDeDocumento;
	}

	public String getTipoDeDocumento() {
		return tipoDeDocumento;
	}

	public void setTipoDeDocumento(String tipoDeDocumento) {
		this.tipoDeDocumento = tipoDeDocumento;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}
}
