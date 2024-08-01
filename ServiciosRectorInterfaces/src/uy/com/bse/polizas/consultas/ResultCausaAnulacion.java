package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.CausaAnulacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCausaAnulacion extends ResultGenerico{
	private ArrayList<CausaAnulacion> causasAnulacion = new ArrayList<CausaAnulacion>();

	public ArrayList<CausaAnulacion> getCausasAnulacion() {
		return causasAnulacion;
	}

	public void setCausasAnulacion(ArrayList<CausaAnulacion> causasAnulacion) {
		this.causasAnulacion = causasAnulacion;
	}
	
	public void setUnaCausaAnulacion (CausaAnulacion item){
		this.causasAnulacion.add(item);
	}
}
