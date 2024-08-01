package uy.com.bse.cotizaciones.operaciones;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularCotizacion extends ResultGenerico{
	
private ArrayList<PlanCobertura> planesCobertura = new ArrayList<PlanCobertura>();

	
	public ArrayList<PlanCobertura> getPlanesCobertura() {
		return planesCobertura;
	}



	public void setPlanesCobertura(ArrayList<PlanCobertura> planesCobertura) {
		this.planesCobertura = planesCobertura;
	}



	public void setUnPlanCobertura(PlanCobertura planCobertura){
		this.planesCobertura.add(planCobertura);	
	}
		
}
