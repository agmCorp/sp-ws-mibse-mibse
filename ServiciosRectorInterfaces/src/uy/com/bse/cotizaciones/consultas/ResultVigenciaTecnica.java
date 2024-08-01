package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultVigenciaTecnica extends ResultGenerico{
	private ArrayList<VigenciaTecnica> vigenciaTecnica;
	
	public ResultVigenciaTecnica(){
		this.vigenciaTecnica = new ArrayList<VigenciaTecnica>();
	}

	public ArrayList<VigenciaTecnica> getVigenciaTecnica() {
		return vigenciaTecnica;
	}

	public void setVigenciaTecnica(ArrayList<VigenciaTecnica> vigenciaTecnica) {
		this.vigenciaTecnica = vigenciaTecnica;
	}
	
	public void setUnItem(VigenciaTecnica item){
		this.vigenciaTecnica.add(item);
	}
	
}
