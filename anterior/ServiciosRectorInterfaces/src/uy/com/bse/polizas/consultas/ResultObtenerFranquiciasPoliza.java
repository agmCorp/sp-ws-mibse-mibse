package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.consultas.Bien;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerFranquiciasPoliza extends ResultGenerico{
	private ArrayList<Bien> bienes = new ArrayList<Bien>();

	public ArrayList<Bien> getBienes() {
		return bienes;
	}

	public void setBienes(ArrayList<Bien> bienes) {
		this.bienes = bienes;
	}
	
	public void setUnBien(Bien item){
		this.bienes.add(item);
	}
}
