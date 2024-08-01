package uy.com.bse.utilitario.dato;

import java.util.ArrayList;

public class ResultCodiguera extends ResultGenerico {

	private static final long serialVersionUID = 3653223981826198375L;
	
	private ArrayList<Codiguera> items = new ArrayList<Codiguera>();

	private Codiguera valorDefecto;

	public ArrayList<Codiguera> getItems() {
		return items;
	}

	public void setItems(ArrayList<Codiguera> items) {
		this.items = items;
	}

	public void setUno(Codiguera codiguera) {
		this.items.add(codiguera);
	}

	public Codiguera getValorDefecto() {
		return valorDefecto;
	}

	public void setValorDefecto(Codiguera valorDefecto) {
		this.valorDefecto = valorDefecto;
	}

}
