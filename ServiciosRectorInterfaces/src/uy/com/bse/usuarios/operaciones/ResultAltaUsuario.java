package uy.com.bse.usuarios.operaciones;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultAltaUsuario extends ResultGenerico{
	private String usuarioEjecutor;
	private String nroCorredor;
	public String getUsuarioEjecutor() {
		return usuarioEjecutor;
	}
	public void setUsuarioEjecutor(String usuarioEjecutor) {
		this.usuarioEjecutor = usuarioEjecutor;
	}
	public String getNroCorredor() {
		return nroCorredor;
	}
	public void setNroCorredor(String nroCorredor) {
		this.nroCorredor = nroCorredor;
	}
	
}