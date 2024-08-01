package uy.com.bse.consultas.operaciones;

import java.util.ArrayList;

import uy.com.bse.consultas.entidades.IntegranteNominaAccidenteTrabajo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerIntegrantesNominaAccidenteTrabajo extends ResultGenerico{

	private static final long serialVersionUID = 1053006948145293687L;
	
	private ArrayList<IntegranteNominaAccidenteTrabajo> datos = new ArrayList<IntegranteNominaAccidenteTrabajo>();
	
	public void setUno(IntegranteNominaAccidenteTrabajo dato){
		datos.add(dato);
	}

	public ArrayList<IntegranteNominaAccidenteTrabajo> getDatos() {
		return datos;
	}

	public void setDatos(ArrayList<IntegranteNominaAccidenteTrabajo> datos) {
		this.datos = datos;
	}
}
