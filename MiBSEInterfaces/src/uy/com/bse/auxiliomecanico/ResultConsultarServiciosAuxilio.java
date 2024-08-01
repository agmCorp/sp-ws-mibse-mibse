package uy.com.bse.auxiliomecanico;

import java.util.ArrayList;
import java.util.List;

import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultConsultarServiciosAuxilio extends ResultGenerico {
	

	private static final long serialVersionUID = 5012603500984220778L;

	public List<DatoPolizaAuxilio> getPolizaAuxilio() {
		return polizaAuxilio;
	}

	public void setPolizaAuxilio(List<DatoPolizaAuxilio> polizaAuxilio) {
		this.polizaAuxilio = polizaAuxilio;
	}

	private List<DatoPolizaAuxilio> polizaAuxilio = new ArrayList<DatoPolizaAuxilio>();
	
}
