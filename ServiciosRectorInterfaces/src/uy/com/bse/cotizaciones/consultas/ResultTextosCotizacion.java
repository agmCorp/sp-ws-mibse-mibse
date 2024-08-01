package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Texto;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTextosCotizacion extends ResultGenerico{
	private ArrayList<Texto> textos = new ArrayList<Texto>();

	public ArrayList<Texto> getTextos() {
		return textos;
	}

	public void setTextos(ArrayList<Texto> textos) {
		this.textos = textos;
	}
	
	public void setUnTexto(Texto item){
		this.textos.add(item);
	}

}
