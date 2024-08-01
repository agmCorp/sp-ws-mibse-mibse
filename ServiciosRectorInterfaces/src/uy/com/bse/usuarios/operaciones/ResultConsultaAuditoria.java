package uy.com.bse.usuarios.operaciones;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import uy.com.bse.usuarios.entidades.UsuarioConsultaAuditoria;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultConsultaAuditoria extends ResultGenerico {
	private ArrayList<UsuarioConsultaAuditoria> usuarios= new ArrayList<>();

	@XmlElementWrapper(name="Usuarios") 
    @XmlElement(name="Usuario")	
	public ArrayList<UsuarioConsultaAuditoria> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ArrayList<UsuarioConsultaAuditoria> usuarios) {
		this.usuarios = usuarios;
	}

}
