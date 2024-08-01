package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultDatoParametrico extends ResultGenerico{
	private ArrayList<DatoParametrico> bien;
	
	public ResultDatoParametrico(){
		this.bien = new ArrayList<DatoParametrico>();
	}

	public ArrayList<DatoParametrico> getBien() {
		return bien;
	}

	public void setBien(ArrayList<DatoParametrico> bien) {
		this.bien = bien;
	}
	
	public void setUnElemento(DatoParametrico item){
		this.bien.add(item);
	}
	
}
