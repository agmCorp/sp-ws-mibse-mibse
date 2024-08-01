package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosBasicosSiniestro;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerSiniestrosConFiltro extends ResultGenerico{
	private ArrayList<DatosBasicosSiniestro> siniestro = new ArrayList<DatosBasicosSiniestro>();

	public ArrayList<DatosBasicosSiniestro> getSiniestro() {
		return siniestro;
	}

	public void setSiniestro(ArrayList<DatosBasicosSiniestro> siniestro) {
		this.siniestro = siniestro;
	} 

	public void setUnDatoBasico(DatosBasicosSiniestro item){
		this.siniestro.add(item);
	}
}
