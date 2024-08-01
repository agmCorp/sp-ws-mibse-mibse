package uy.com.bse.cotizadores.pymes;

import java.util.ArrayList;


import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultCalcularPymes extends ResultGenerico {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4373660128498094969L;
	private ArrayList<CapitalPlan> capitalesPlanes = new ArrayList<CapitalPlan>();
	private ArrayList<CoberturaPymes> coberturas = new ArrayList<CoberturaPymes>();
	
	
	
	
	public ArrayList<CapitalPlan> getCapitalesPlanes() {
		return capitalesPlanes;
	}

	public void setCapitalesPlanes(ArrayList<CapitalPlan> capitalesPlanes) {
		this.capitalesPlanes = capitalesPlanes;
	}

	
	public void setUnoCapitalPlan(CapitalPlan capital) {
		this.capitalesPlanes.add(capital);
	}
	
	public void setUnoCobertura(CoberturaPymes cobertura) {
		this.coberturas.add(cobertura);
	}

	public ArrayList<CoberturaPymes> getCoberturas() {
		return coberturas;
	}

	public void setCoberturas(ArrayList<CoberturaPymes> coberturas) {
		this.coberturas = coberturas;
	}

	


}
