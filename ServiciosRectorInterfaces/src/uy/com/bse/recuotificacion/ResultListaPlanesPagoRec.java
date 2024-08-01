package uy.com.bse.recuotificacion;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaPlanesPagoRec extends ResultGenerico{
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
