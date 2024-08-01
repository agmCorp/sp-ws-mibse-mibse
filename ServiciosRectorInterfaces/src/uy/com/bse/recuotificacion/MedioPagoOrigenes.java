package uy.com.bse.recuotificacion;

import java.io.Serializable;
import java.util.ArrayList;

import uy.com.bse.cotizaciones.lovs.MedioPago;

public class MedioPagoOrigenes implements Serializable{
	private static final long serialVersionUID = -5420354159304864169L;
	private MedioPago medioPago= new MedioPago();
	private ArrayList<OrigenPagoEndoso> origenesPago = new ArrayList<OrigenPagoEndoso>();
	
	public MedioPago getMedioPago() {
		return medioPago;
	}
	public void setMedioPago(MedioPago medioPago) {
		this.medioPago = medioPago;
	}
	public ArrayList<OrigenPagoEndoso> getOrigenesPago() {
		return origenesPago;
	}
	public void setOrigenesPago(ArrayList<OrigenPagoEndoso> origenesPago) {
		this.origenesPago = origenesPago;
	}

}
