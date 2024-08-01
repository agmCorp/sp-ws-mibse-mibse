package uy.com.bse.cotizadorvya.operaciones;

import java.util.ArrayList;

import uy.com.bse.cotizadorvya.entidades.Rescate;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultPorcentajeAnticipo extends ResultGenerico {
	private static final long serialVersionUID = 267116843984062728L;
	
	private ArrayList<Rescate> rescates= new ArrayList<>();

	public ArrayList<Rescate> getRescates() {
		return rescates;
	}

	public void setRescates(ArrayList<Rescate> rescates) {
		this.rescates = rescates;
	}
}
