package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.ModoCalculo;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultModoCalculo extends ResultGenerico{
	private ArrayList<ModoCalculo> modosCalculo = new ArrayList<ModoCalculo>();

	public ArrayList<ModoCalculo> getModosCalculo() {
		return modosCalculo;
	}

	public void setModosCalculo(ArrayList<ModoCalculo> modosCalculo) {
		this.modosCalculo = modosCalculo;
	}
	
	public void setUnModoCalculo(ModoCalculo item){
		this.modosCalculo.add(item);
	}
	
}
