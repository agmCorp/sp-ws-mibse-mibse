package uy.com.bse.clientes.consultas;

import java.util.ArrayList;

import uy.com.bse.maestro.personas.domicilio.InfoPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPolizasAsociadasClientes extends ResultGenerico{

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
