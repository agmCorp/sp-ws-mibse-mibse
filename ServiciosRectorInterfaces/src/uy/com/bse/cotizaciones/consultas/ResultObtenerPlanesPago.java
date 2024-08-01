package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.cotizaciones.operaciones.PlanPago;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerPlanesPago extends ResultGenerico{
	private ArrayList<PlanPago> listaPlanPago = new ArrayList<PlanPago>();

	public ArrayList<PlanPago> getListaPlanPago() {
		return listaPlanPago;
	}

	public void setListaPlanPago(ArrayList<PlanPago> listaPlanPago) {
		this.listaPlanPago = listaPlanPago;
	}
	
	public void setUnPlanPago(PlanPago item){
		this.listaPlanPago.add(item);
	}
}
