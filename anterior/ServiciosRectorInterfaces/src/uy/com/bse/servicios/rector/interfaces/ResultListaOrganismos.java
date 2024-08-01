package uy.com.bse.servicios.rector.interfaces;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaOrganismos extends ResultGenerico{

	ArrayList<Organismo> organismo = new ArrayList<Organismo>();
		
	public ArrayList<Organismo> getOrganismo() {
		return organismo;
	}
	
	public void setOrganismo(ArrayList<Organismo> organismo) {
		this.organismo = organismo;
	}
	
	public void setUnOrganismo(Organismo org){
		this.organismo.add(org);
	}
}
