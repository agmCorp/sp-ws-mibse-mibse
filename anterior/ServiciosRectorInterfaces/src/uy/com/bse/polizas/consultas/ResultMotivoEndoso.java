package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.MotivoEndoso;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultMotivoEndoso extends ResultGenerico{
	
	private ArrayList<MotivoEndoso> motivoEndoso = new ArrayList<MotivoEndoso>();

	public ArrayList<MotivoEndoso> getMotivoEndoso() {
		return motivoEndoso;
	}

	public void setMotivoEndoso(ArrayList<MotivoEndoso> motivoEndoso) {
		this.motivoEndoso = motivoEndoso;
	}	

}
