package uy.com.bse.utilitario.dato;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

public class ParamGenerico implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/* Para el comun de los params la clave es el Token, menos para el ParamLogin.
	 * En el ParamLogin lo usamos para la clave del Portal.
	 */
	private String clave;
	
	// es el usuario final, el que se loguea al portal (LDAP)
	private String usuario;
	
	@XmlElement(nillable=true, required=true)
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
