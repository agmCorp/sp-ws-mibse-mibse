package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.consultas.BonificacionPoliza;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultBonificacionNS extends ResultGenerico{
	
	private ArrayList<BonificacionCotizacion> bonificacionesCotizacion = new ArrayList<BonificacionCotizacion>();
	private ArrayList<BonificacionPoliza> bonificacionesPoliza = new ArrayList<BonificacionPoliza>();
	
	public ArrayList<BonificacionCotizacion> getBonificacionesCotizacion() {
		return bonificacionesCotizacion;
	}
	public void setBonificacionesCotizacion(
			ArrayList<BonificacionCotizacion> bonificacionesCotizacion) {
		this.bonificacionesCotizacion = bonificacionesCotizacion;
	}
	public ArrayList<BonificacionPoliza> getBonificacionesPoliza() {
		return bonificacionesPoliza;
	}
	public void setBonificacionesPoliza(
			ArrayList<BonificacionPoliza> bonificacionesPoliza) {
		this.bonificacionesPoliza = bonificacionesPoliza;
	}
	
	public void setUnaBonCotizacion(BonificacionCotizacion item){
		this.bonificacionesCotizacion.add(item);
	}
	
	public void setUnaBonPoliza(BonificacionPoliza item){
		this.bonificacionesPoliza.add(item);
	}
}
