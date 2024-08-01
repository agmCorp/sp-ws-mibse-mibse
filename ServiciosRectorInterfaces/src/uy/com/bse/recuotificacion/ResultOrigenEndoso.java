package uy.com.bse.recuotificacion;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultOrigenEndoso extends ResultGenerico{
	private ArrayList<OrigenEndoso> origenesEndoso = new ArrayList<OrigenEndoso>();

	public ArrayList<OrigenEndoso> getOrigenesEndoso() {
		return origenesEndoso;
	}

	public void setOrigenesEndoso(ArrayList<OrigenEndoso> origenesEndoso) {
		this.origenesEndoso = origenesEndoso;
	}

	public void setUnOrigen(OrigenEndoso item){
		this.origenesEndoso.add(item);
	}
}
