package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaTodasSecciones extends ResultGenerico{
	private ArrayList<Ramo> seccion;
	
	public ResultListaTodasSecciones() {		
		this.seccion = new ArrayList<Ramo>();
	}
	
	public ArrayList<Ramo> getSeccion() {
		return seccion;
	}

	public void setSeccion(ArrayList<Ramo> seccion) {
		this.seccion = seccion;
	}

	public void setUnRamo(Ramo item){
		this.seccion.add(item);
	}
}
