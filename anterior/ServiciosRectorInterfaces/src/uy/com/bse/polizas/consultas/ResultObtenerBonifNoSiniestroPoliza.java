package uy.com.bse.polizas.consultas;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerBonifNoSiniestroPoliza extends ResultGenerico {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<BonificacionPoliza> bonificacionesPoliza = new ArrayList<BonificacionPoliza>();

	public ArrayList<BonificacionPoliza> getBonificacionesPoliza() {
		return bonificacionesPoliza;
	}

	public void setBonificacionesPoliza(ArrayList<BonificacionPoliza> bonificacionesPoliza) {
		this.bonificacionesPoliza = bonificacionesPoliza;
	}

	public void setUnaBonPoliza(BonificacionPoliza item) {
		this.bonificacionesPoliza.add(item);
	}

}
