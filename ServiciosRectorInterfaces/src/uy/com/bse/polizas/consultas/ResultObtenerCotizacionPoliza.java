package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.CotizacionPoliza;
import uy.com.bse.polizas.entidades.PlanCoberturaPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerCotizacionPoliza extends ResultGenerico{
	private CotizacionPoliza datosCotizacionPoliza;
	private ArrayList <PlanCoberturaPoliza> planesCoberturaPoliza= new ArrayList <PlanCoberturaPoliza>();
	
	public CotizacionPoliza getDatosCotizacionPoliza() {
		return datosCotizacionPoliza;
	}
	public void setDatosCotizacionPoliza(CotizacionPoliza datosCotizacionPoliza) {
		this.datosCotizacionPoliza = datosCotizacionPoliza;
	}
	public ArrayList<PlanCoberturaPoliza> getPlanesCoberturaPoliza() {
		return planesCoberturaPoliza;
	}
	public void setPlanesCoberturaPoliza(
			ArrayList<PlanCoberturaPoliza> planesCoberturaPoliza) {
		this.planesCoberturaPoliza = planesCoberturaPoliza;
	}
	
	public void setUnPlan(PlanCoberturaPoliza item){
		this.planesCoberturaPoliza.add(item);
	}
	
}
