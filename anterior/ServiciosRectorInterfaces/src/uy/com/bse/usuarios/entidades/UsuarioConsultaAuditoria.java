package uy.com.bse.usuarios.entidades;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class UsuarioConsultaAuditoria implements Serializable{
	private String telefono;
	private String nombreCompleto;
	private String cedula;
	private String usuarioProductor;
	private String mail;
	private String alcance;
	private ArrayList<TipoDeAccion> tipoDeAccion = new ArrayList<TipoDeAccion>();

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getUsuarioProductor() {
		return usuarioProductor;
	}

	public void setUsuarioProductor(String usuarioProductor) {
		this.usuarioProductor = usuarioProductor;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getAlcance() {
		return alcance;
	}

	public void setAlcance(String alcance) {
		this.alcance = alcance;
	}

	@XmlElementWrapper(name="TiposDeAccion") 
    @XmlElement(name="TipoDeAccion")	
	public ArrayList<TipoDeAccion> getTipoDeAccion() {
		return tipoDeAccion;
	}

	public void setTipoDeAccion(ArrayList<TipoDeAccion> tipoDeAccion) {
		this.tipoDeAccion = tipoDeAccion;
	}
}
