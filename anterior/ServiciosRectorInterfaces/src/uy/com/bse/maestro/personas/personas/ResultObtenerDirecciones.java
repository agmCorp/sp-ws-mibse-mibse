package uy.com.bse.maestro.personas.personas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDirecciones extends ResultGenerico{
	private ArrayList <DatosDirecciones> direccionesPersona;

	public ResultObtenerDirecciones(){
		this.direccionesPersona = new ArrayList <DatosDirecciones>();
	}
	
	public ArrayList<DatosDirecciones> getDireccionesPersona() {
		return direccionesPersona;
	}

	public void setDireccionesPersona(ArrayList<DatosDirecciones> direccionesPersona) {
		this.direccionesPersona = direccionesPersona;
	}
	
	public void setUnaDireccion(DatosDirecciones dir){
		this.direccionesPersona.add(dir);
	}
}
