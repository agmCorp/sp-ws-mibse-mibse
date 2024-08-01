package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.Cancelacion;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCancelacionesRemesa extends ResultGenerico{
	private ArrayList<Cancelacion> cancelaciones = new ArrayList<Cancelacion>();

	public ArrayList<Cancelacion> getCancelaciones() {
		return cancelaciones;
	}

	public void setCancelaciones(ArrayList<Cancelacion> cancelaciones) {
		this.cancelaciones = cancelaciones;
	}
	
	public void setUnaCancelacion(Cancelacion item){
		this.cancelaciones.add(item);
	}
}
