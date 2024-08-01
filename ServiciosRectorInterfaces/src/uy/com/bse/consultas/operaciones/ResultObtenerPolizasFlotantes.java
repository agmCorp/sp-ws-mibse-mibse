package uy.com.bse.consultas.operaciones;

import java.util.ArrayList;

import uy.com.bse.consultas.entidades.DatoPolizaFlotante;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPolizasFlotantes extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	
	private ArrayList<DatoPolizaFlotante> datos = new ArrayList<DatoPolizaFlotante>();
	
	public void setUno(DatoPolizaFlotante dato){
		datos.add(dato);
	}

	public ArrayList<DatoPolizaFlotante> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<DatoPolizaFlotante> datos) {
		this.datos = datos;
	}
}
