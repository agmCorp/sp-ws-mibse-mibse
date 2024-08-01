package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaPlanesPagoRen extends ResultGenerico{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4739683687321488996L;
	private ArrayList<PlanPago> planesPago = new ArrayList<PlanPago>();

	public ArrayList<PlanPago> getPlanesPago() {
		return planesPago;
	}

	public void setPlanesPago(ArrayList<PlanPago> planesPago) {
		this.planesPago = planesPago;
	}
	
	public void setUnPlan(PlanPago item){
		this.planesPago.add(item);
	}

}
