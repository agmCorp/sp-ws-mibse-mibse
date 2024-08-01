package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DatosBasicosSiniestro;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerSiniestros extends ResultGenerico{
	private ArrayList<DatosBasicosSiniestro> siniestros = new ArrayList<DatosBasicosSiniestro>();

	public ArrayList<DatosBasicosSiniestro> getSiniestros() {
		return siniestros;
	}

	public void setSiniestros(ArrayList<DatosBasicosSiniestro> siniestros) {
		this.siniestros = siniestros;
	}
	
	public void setUnDatoSiniestro(DatosBasicosSiniestro item){
		this.siniestros.add(item);
	}

}
