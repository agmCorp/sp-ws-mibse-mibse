package uy.com.bse.maestro.personas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRoles extends ResultGenerico{
	private ArrayList <Rol> rolesPersona;

	public ResultRoles(){
		this.rolesPersona = new ArrayList <Rol>();
	}
	
	public ArrayList<Rol> getRolesPersona() {
		return rolesPersona;
	}

	public void setRolesPersona(ArrayList<Rol> rolesPersona) {
		this.rolesPersona = rolesPersona;
	}
	
	public void setUnRol(Rol rol){
		this.rolesPersona.add(rol);
	}
}
