package uy.com.bse.maestro.personas.consultas;

import java.util.ArrayList;

import uy.com.bse.maestro.personas.domicilio.InfoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPolizasAsociadas extends ResultGenerico{

	/**
	 * 
	 */
	private static final long serialVersionUID = 326941586730421827L;
	private ArrayList<InfoPoliza> infoPoliza = new ArrayList<InfoPoliza>();

	public ArrayList<InfoPoliza> getInfoPoliza() {
		return infoPoliza;
	}

	public void setInfoPoliza(ArrayList<InfoPoliza> infoPoliza) {
		this.infoPoliza = infoPoliza;
	}
	
	public void setUnaPolizaAsociada(InfoPoliza item){
		this.infoPoliza.add(item);
	}
}
