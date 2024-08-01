package uy.com.bse.guc.interfaces;


import uy.com.bse.utilitario.dato.ParamGenerico;

public class ParamAltaUsuario extends ParamGenerico {

	private static final long serialVersionUID = -303641124330626825L;
	
	private Integer tipoClave =GUCValues.CLAVEUSUARIO;

	private String claveUsuario;


	public Integer getTipoClave() {
		return tipoClave;
	}

	public void setTipoClave(Integer tipoClave) {
		this.tipoClave = tipoClave;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario ) {
		this.claveUsuario = claveUsuario;
	}

}
