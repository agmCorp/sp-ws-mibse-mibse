package uy.com.bse.maestro.personas.interfaces;

import java.util.ArrayList;

import uy.com.bse.servicios.rector.interfaces.Postal;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultRadio extends ResultGenerico{
	private ArrayList<Postal> postal = new ArrayList<Postal>();

	public ArrayList<Postal> getPostal() {
		return postal;
	}

	public void setPostal(ArrayList<Postal> postal) {
		this.postal = postal;
	}

	public void setUnaPostal(Postal item){
		this.postal.add(item);
	}
}
