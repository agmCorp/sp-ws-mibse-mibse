package uy.com.bse.cotizaciones.consultas;

import java.util.ArrayList;

import uy.com.bse.polizas.entidades.DetalleCuota;
import uy.com.bse.utilitario.dato.ResultGenerico;

public class ResultObtenerDetalleCuota extends ResultGenerico{
	
	private ArrayList<DetalleCuota> cuotas = new ArrayList<DetalleCuota>();
	private Double total;

	public ArrayList<DetalleCuota> getCuotas() {
		return cuotas;
	}

	public void setCuotas(ArrayList<DetalleCuota> cuotas) {
		this.cuotas = cuotas;
	}
	
	public void setUnaCuota(DetalleCuota cuota){
		this.cuotas.add(cuota);
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
		

}
