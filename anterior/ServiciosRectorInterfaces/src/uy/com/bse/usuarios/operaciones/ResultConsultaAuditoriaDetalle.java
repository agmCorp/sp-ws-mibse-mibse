package uy.com.bse.usuarios.operaciones;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import uy.com.bse.usuarios.entidades.UsuarioConsultaAuditoriaDetalle;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultConsultaAuditoriaDetalle extends ResultGenerico {
	private ArrayList<UsuarioConsultaAuditoriaDetalle> usuarios;

	@XmlElementWrapper(name="Usuarios") 
    @XmlElement(name="Usuario")	
	public ArrayList<UsuarioConsultaAuditoriaDetalle> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<UsuarioConsultaAuditoriaDetalle> usuarios) {
		this.usuarios = usuarios;
	}
}
