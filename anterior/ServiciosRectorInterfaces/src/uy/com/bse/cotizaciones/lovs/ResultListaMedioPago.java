package uy.com.bse.cotizaciones.lovs;

import java.util.ArrayList;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultListaMedioPago extends ResultGenerico{
	private ArrayList<MedioPago> MedioPago;
	
	public ResultListaMedioPago() {		
		this.MedioPago = new ArrayList<MedioPago>();
	}

	public ArrayList<MedioPago> getMedioPago() {
		return MedioPago;
	}

	public void setMedioPago(ArrayList<MedioPago> medioPago) {
		MedioPago = medioPago;
	}

	public void setUnElemento(MedioPago item){
		this.MedioPago.add(item);
	}
}
