package uy.com.bse.utilitario.dato;

import java.util.ArrayList;

public class ResultCodigueraEntero extends ResultGenerico {

	private static final long serialVersionUID = 3653223981826198375L;
	
	private ArrayList<CodigueraEntero> items = new ArrayList<CodigueraEntero>();

	public ArrayList<CodigueraEntero> getItems() {
		return items;
	}

	public void setItems(ArrayList<CodigueraEntero> items) {
		this.items = items;
	}

	public void setUno(CodigueraEntero codiguera){
		this.items.add(codiguera);
	}
	

}
