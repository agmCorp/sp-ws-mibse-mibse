package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Nota;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerNotasPoliza extends ResultGenerico{
	private ArrayList<Nota> listaNotasPoliza =new ArrayList<Nota>();

	
	public ArrayList<Nota> getListaNotasPoliza() {
		return listaNotasPoliza;
	}

	public void setListaNotasPoliza(ArrayList<Nota> listaNotasPoliza) {
		this.listaNotasPoliza = listaNotasPoliza;
	}


	public void SetUnaNotaRenovacion(Nota item){
		this.listaNotasPoliza.add(item);
	}

}
