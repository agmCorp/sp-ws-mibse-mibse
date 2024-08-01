package uy.com.bse.consultas.operaciones;

import java.util.ArrayList;

import uy.com.bse.consultas.entidades.DatoNominaVida;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerNominasVida extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	
	private ArrayList<DatoNominaVida> datos = new ArrayList<DatoNominaVida>();
	
	public void setUno(DatoNominaVida dato){
		datos.add(dato);
	}

	public ArrayList<DatoNominaVida> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<DatoNominaVida> datos) {
		this.datos = datos;
	}
}
