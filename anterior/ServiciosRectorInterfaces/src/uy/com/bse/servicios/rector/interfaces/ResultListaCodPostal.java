package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaCodPostal extends ResultGenerico{
	ArrayList<Postal> postal = new ArrayList<Postal>();
		
	public ArrayList<Postal> getPostal() {
		return postal;
	}

	public void setPostal(ArrayList<Postal> postal) {
		this.postal = postal;
	}
	
	public void setUnPostal(Postal p){
		this.postal.add(p);
	}
}
