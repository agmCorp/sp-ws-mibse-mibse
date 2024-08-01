package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.TipoAnulacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultTipoAnulacion extends ResultGenerico{
	private ArrayList<TipoAnulacion> tiposAnulacion = new ArrayList<TipoAnulacion>();

	public ArrayList<TipoAnulacion> getTiposAnulacion() {
		return tiposAnulacion;
	}

	public void setTiposAnulacion(ArrayList<TipoAnulacion> tiposAnulacion) {
		this.tiposAnulacion = tiposAnulacion;
	}
		
	public void setUnTipoAnulacion (TipoAnulacion item){
		this.tiposAnulacion.add(item);
	}
	
}
