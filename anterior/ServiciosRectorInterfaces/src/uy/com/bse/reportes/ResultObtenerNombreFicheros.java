package uy.com.bse.reportes;

import java.util.ArrayList;

import uy.com.bse.reportes.entidades.DatoFichero;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerNombreFicheros extends ResultGenerico {

	private static final long serialVersionUID = 8816751106677383700L;
	
	private ArrayList<DatoFichero> ficheros;

	public ResultObtenerNombreFicheros() {
		this.ficheros = new ArrayList<DatoFichero>();
	}


	public ArrayList<DatoFichero> getFicheros() {
		return ficheros;
	}


	public void setFicheros(ArrayList<DatoFichero> ficheros) {
		this.ficheros = ficheros;
	}


	public void setUno(DatoFichero dato) {
		this.ficheros.add(dato);
	}

}
