package uy.com.bse.cotizadores.pymes;

import java.io.Serializable;
import java.util.ArrayList;

public class CapitalPlan implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1794399131784023693L;
	private Double montoCapital;
	private ArrayList<Modulo> modulos = new ArrayList<Modulo>();
	

	public Double getMontoCapital() {
		return montoCapital;
	}



	public void setMontoCapital(Double montoCapital) {
		this.montoCapital = montoCapital;
	}



	public ArrayList<Modulo> getModulos() {
		return modulos;
	}



	public void setModulos(ArrayList<Modulo> modulos) {
		this.modulos = modulos;
	}

	public void setUnoModulos(Modulo modulo) {
		this.modulos.add(modulo);
	}


}
