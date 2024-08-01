package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.entidades.Nota;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerNotasSiniestro extends ResultGenerico{
	private ArrayList<Nota> notasSiniestro = new ArrayList<Nota>();

	public ArrayList<Nota> getNotasSiniestro() {
		return notasSiniestro;
	}

	public void setNotasSiniestro(ArrayList<Nota> notasSiniestro) {
		this.notasSiniestro = notasSiniestro;
	}
	
	public void setUnaNota(Nota item){
		this.notasSiniestro.add(item);
	}
}
