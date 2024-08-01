package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaModoCalculo extends ResultGenerico{
	private ArrayList<ModoCalculo> modoCalculo;
	
	public ResultListaModoCalculo() {		
		this.modoCalculo = new ArrayList<ModoCalculo>();
	}

	public ArrayList<ModoCalculo> getModoCalculo() {
		return modoCalculo;
	}

	public void setModoCalculo(ArrayList<ModoCalculo> renovacion) {
		this.modoCalculo = renovacion;
	}

	public void setUnElemento(ModoCalculo item){
		this.modoCalculo.add(item);
	}
}
